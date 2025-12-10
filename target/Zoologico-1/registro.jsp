<%-- 
    Document   : registro
    Created on : 3 jul 2025, 08:55:13
    Author     : jeide
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro</title>
    <link rel="stylesheet" href="css/style.css">
    <script>
        function validarCorreo() {
            const correo = document.getElementById("email").value;
            const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!regex.test(correo)) {
                alert("Por favor ingresa un correo válido.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body style="background-image: url(images/fondo1.jpg);">
    <div class="login-container">
        <form class="formulario-login" action="RegistroServlet" method="post" onsubmit="return validarCorreo();">
            <h2>Registro de Usuario</h2>

            <label>Nombre:</label>
            <input type="text" name="name" required>

            <label>Correo electrónico:</label>
            <input type="email" id="email" name="email" required>

            <label>Contraseña:</label>
            <input type="password" name="pass" required>

            <label>Identificación:</label>
            <input type="text" name="id" required>


            <label>Rol:</label>
            <select name="role" required>
                <option value="" disabled selected>Selecciona un rol</option>
                <option value="user">Usuario</option>
                <option value="admin">Administrador</option>
            </select>

            <button type="submit">Registrarse</button>

            <p>¿Ya tienes cuenta? <a href="index.jsp">Inicia sesión</a></p>
        </form>
    </div>
</body>
</html>


