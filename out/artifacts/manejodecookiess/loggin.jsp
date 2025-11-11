<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 10/11/2025
  Time: 8:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%---Creación del formulario---%>
<html>
<head>
    <title>Iniciar Sesión</title>
    <link rel="stylesheet" href="Login-Estilos.css">
</head>
<body>
<div class="login-container">
    <h1 class="login-title">Iniciar Sesión</h1>
    <form class="login-form" action="Login" method="post">
        <div class="form-group">
            <label for="user">Usuario</label>
            <input type="text" id="user" name="user" placeholder="Ingrese su usuario" required>
        </div>
        <div class="form-group">
            <label for="password">Contraseña</label>
            <input type="password" id="password" name="password" placeholder="Ingrese su contraseña" required>
        </div>
        <div class="form-group">
            <input type="submit" class="submit-btn" value="Entrar">
        </div>
    </form>
</div>
</body>
</html>