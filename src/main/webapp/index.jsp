<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login </title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body
        style="background-image: url(images/fondozoo2.png);"
        >
        <div class="login-container">
            <h1>Iniciar Sesión</h1><% String error = request.getParameter("error"); %>
            <% if (error != null && error.equals("1")) { %>
            <div id="mensaje-error" style="color: red; text-align:center;">
                Usuario o contraseña incorrectos.
            </div>
            <% }%>

            <form action="Ingresar" method="post" onsubmit="return validarFormulario();">
                <label for="nombre">Nombre de usuario:</label>
                <input type="text" id="nombre" name="nombre" required>

                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required>

                <label for="rol">Rol:</label>
                <select name="rol" id="rol" required>
                    <option value="">Selecciona un rol</option>
                    <option value="admin">Administrador</option>
                    <option value="user">Usuario</option>
                </select>

                <button type="submit">Ingresar</button>
            </form>

            <p class="registro-link">
                ¿No tienes cuenta? <a href="registro.jsp">Regístrate aquí</a>
            </p>
        </div>
        <script src="login.js"></script>
    </body>
</html>
