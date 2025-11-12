<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 10/11/2025
  Time: 8:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login de Usuario</title>
    <link rel="stylesheet" href="estilos.css">
</head>
<body>
<h1>Iniciar Sesión</h1>
<%-- El formulario envía la petición POST al LoginServlet --%>
<form action="<%= request.getContextPath() %>/login" method="post">
    <div>
        <label for="username">Usuario:</label>
        <input type="text" id="username" name="username" required>
    </div>
    <br>
    <div>
        <label for="password">Contraseña:</label>
        <input type="password" id="password" name="password" required>
    </div>
    <br>
    <div>
        <button type="submit">Ingresar</button>
    </div>
</form>
<br>
<p><a href="<%= request.getContextPath() %>/Index.html">Volver al inicio</a></p>
</body>
</html>