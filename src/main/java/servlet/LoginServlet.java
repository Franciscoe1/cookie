package servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

@WebServlet({"/loggin","/loggin.html"})
public class LoginServlet extends HttpServlet {
    //inicializamos las variables estaticas para el login
    final static String USERNAME= "admin";
    final static String PASSWORD= "12345";

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp)
            throws jakarta.servlet.ServletException, java.io.IOException {
        //creamos la cookie
        Cookie[] cookies = req.getCookies()!=null ? req.getCookies() : new Cookie[0];
        //buscamos dentro de las cookies si existe la cookie loggin
        Optional<String> cookieOptional = Arrays.stream(cookies)
                .filter(c ->"username".equals(c.getName()))
                //si existe la cookie obtenemos su valor
                .map(Cookie::getValue)
                .findAny();
        if (cookieOptional.isPresent()) {
            resp.setContentType("text/html");
            //si la cookie existe mostramos mensaje de bienvenida
            try (PrintWriter out = resp.getWriter()) {
                resp.setContentType("text/html;charset=UTF-8");
                //creamos la plantilla html
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Login"+ cookieOptional.get() +"</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Login</h1>");
                out.println("Bienvenido a mi sistema holawsas"+cookieOptional.get()+"has iniciado sesion correctamente");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/loggin.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp)
            throws jakarta.servlet.ServletException, java.io.IOException {
        String username = req.getParameter("user");
        String password = req.getParameter("password");
        if(username.equals(USERNAME) && password.equals(PASSWORD)){
         resp.setContentType("text/html");
            //creamos la cookie para el usuario
            Cookie cookie = new Cookie("username", username);
            //agregamos la cookie a la respuesta
            resp.addCookie(cookie);
            //redireccionamos al usuario al servlet login
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Login correcto</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Login Exitoso</h1>");
                out.println("<p>Bienvenido a mi aplicaciòn " +" "+ username + " , has iniciado sesión correctamente.</p>");
                out.println("<a href="+req.getContextPath()+"/index.html>Ir al inicio</a>");
                out.println("</body>");
                out.println("</html>");
            }
            resp.sendRedirect(req.getContextPath()+"/index.html");
        }else {
            resp.sendError(401,"Lo siento no estas autorizado para ingresar");
        }
    }
}
