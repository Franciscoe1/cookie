package servlet;
/*
    * Servlet para el manejo del login
 * */
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import services.LoginService;
import services.LoginServiceSessionImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {
    // Iniciamos las variables estaticas para el login
    final static String USERNAME = "admin";
    final static String PASSWORD = "1234";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        if (usernameOptional.isPresent()) {
            HttpSession session = req.getSession();

            // 2. RECUPERAR EL CONTADOR
            Integer counter = (Integer) session.getAttribute("loginCounter");

            // 3. DECLARAR Y ASIGNAR counterText
            // Se declara la variable local que el error te pedía.
            String counterText = (counter != null) ? counter.toString() : "0";
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("  <meta charset=\"UTF-8\">");
                out.println("  <title>Hola " + usernameOptional.get() + "</title>");
                out.println("<link rel=\"stylesheet\" href=\"" + req.getContextPath() + "/estilos.css\">");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Hola " + usernameOptional.get() + " has iniciado sesión con éxito!</h1>");
                out.println("<p>Veces logueado en esta sesión: <strong>" + counterText + "</strong></p>");
                out.println("  <p><a href='" + req.getContextPath() + "/Index.html'>volver</a></p>");
                out.println("  <p><a href='" + req.getContextPath() + "/logout'>cerrar sesión</a></p>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/Login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {

            HttpSession session = req.getSession();
            session.setAttribute("username", username);

            resp.sendRedirect(req.getContextPath() + "/login.html");

            Integer counter = (Integer) session.getAttribute("loginCounter");

            if (counter == null) {
                // Si es la primera vez en esta sesión
                counter = 1;
            } else {
                // Si ya existe, lo incrementamos
                counter++;
            }
            // Guardar el valor actualizado en la sesión
            session.setAttribute("loginCounter", counter);

        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para ingresar a esta página!");
        }
    }
}