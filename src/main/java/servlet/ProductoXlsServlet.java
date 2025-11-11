package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Producto;
import services.ProductosServicesImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebServlet("/producto.xls")
public class ProductoXlsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductosServicesImplement service = new ProductosServicesImplement();
        List<Producto> productos = service.listar();

        // Verificar si el usuario está logueado mediante cookies
        boolean isLoggedIn;
        String username = "";

        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
        Optional<String> cookieOptional = Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();

        if (cookieOptional.isPresent()) {
            isLoggedIn = true;
            username = cookieOptional.get();
        } else {
            isLoggedIn = false;
        }

        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Listado Productos</title>");
            out.println("<style>");
            out.println("table { border-collapse: collapse; width: 100%; }");
            out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            if (isLoggedIn) {
                out.println("<h1>Listado de Productos - Bienvenido " + username + "</h1>");
            } else {
                out.println("<h1>Listado de Productos</h1>");
                out.println("<p>Inicie sesión para ver precios completos</p>");
            }

            out.println("<table>");
            out.println("<tr>");
            out.println("<th>ID PRODUCTO</th>");
            out.println("<th>NOMBRE</th>");
            out.println("<th>TIPO</th>");
            if (isLoggedIn) {
                out.println("<th>PRECIO</th>");
            }
            out.println("</tr>");

            productos.forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.getIdProducto() + "</td>");
                out.println("<td>" + p.getNombre() + "</td>");
                out.println("<td>" + p.getTipo() + "</td>");
                if (isLoggedIn) {
                    out.println("<td>$" + String.format("%.2f", p.getPrecio()) + "</td>");
                }
                out.println("</tr>");
            });

            out.println("</table>");
            out.println("<br>");
            out.println("<a href='" + req.getContextPath() + "/Index.html'>Volver al Inicio</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}