package servlet;
/*
    * Servlet para el manejo del login
 * */
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

@WebServlet({"/Login" , "/Login.html"})
public class LoginServlet extends HttpServlet {
    // Iniciamos las variables estaticas para el login
    final static String USERNAME = "admin";
    final static String PASSWORD = "1234";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Creamos la cookie
        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
        // Busco dentro de la cookie si existe información
        Optional<String> cookieOptional = Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName()))
                // Convertimos la cookie a tipo string
                .map(Cookie::getValue)
                .findAny();
        if (cookieOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try(PrintWriter out = resp.getWriter()) {
                // Creamos la plantilla html
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Login " + cookieOptional.get() + "</title>");
                out.println("<meta charset=utf-8>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet Login</h1>");
                out.println("<p>Bienvenido a mi sistema " + cookieOptional.get() + ", ya has iniciado sesión</p>");
                out.println("<a href='" + req.getContextPath() + "/Index.html'>Volver al Inicio</a>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/Login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("user");
        String password = req.getParameter("password");

        // Validar contra las constantes USERNAME y PASSWORD
        if(USERNAME.equals(username) && PASSWORD.equals(password)) {
            // Crear cookie de sesión
            Cookie cookie = new Cookie("username", username);
            // Configurar la cookie para que dure 1 hora
            cookie.setMaxAge(3600);
            cookie.setPath("/");
            resp.addCookie(cookie);

            // Redirigir al index después del login exitoso
            resp.sendRedirect(req.getContextPath() + "/Index.html");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no tiene acceso o no ingreso los datos correctamente");
        }
    }
}
