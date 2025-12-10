<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@page import="java.util.*, logica.Animal, logica.Usuario" %>
        <%@page session="true" %>
            <% if ("true".equals(request.getParameter("logout"))) { session.invalidate();
                response.sendRedirect("index.jsp"); return; } %>

                <!DOCTYPE html>
                <html>

                <head>
                    <meta charset="UTF-8">
                    <title>Panel del Usuario</title>
                    <link rel="stylesheet" href="css/usuario.css">
                </head>

                <body style="background-image: url(images/fondozoo1.jpg);
          background-size: cover;">
                    <div class="contenedor-principal">
                        <header style="background-image: url(images/Planta.jpg);
          background-size: cover;">
                            <h1 class="welcome">Bienvenido, <%= session.getAttribute("nombreUsuario")%>
                            </h1>

                                <body data-usuario-id="<%= session.getAttribute(" usuarioId") %>">
                            <nav class="nav-buttons">
                                <button onclick="mostrar('perfil')">Mi perfil</button>
                                <button onclick="mostrar('animales')">Ver Animales</button>
                                <button onclick="location.href = 'usuario.jsp?logout=true'">Cerrar
                                    sesión</button>
                        </header>

                        <main style="display: flex">
                            <section id="perfil" class="contenido oculto"></section>

                            <section id="animales" class="contenido oculto">
                                <nav class="submenu">
                                    <button onclick="filtrar('todas')">Todas</button>
                                    <button onclick="filtrar('africa')">Africa</button>
                                    <button onclick="filtrar('amazonas')">Amazonas</button>
                                    <button onclick="filtrar('asia')">Asia</button>
                                    <button onclick="filtrar('artico')">Artico/Antartico</button>
                                </nav>
                                <div id="contenedor-productos" class="productos-grid"></div>

                                <!-- Modal para vista ampliada -->
                                <div id="modalAnimal" class="modal">
                                    <div class="modal-contenido">
                                        <span class="cerrar" onclick="cerrarModalAnimal()">&times;</span>
                                        <img id="modal-img-animal" src="" alt="Imagen del animal">
                                        <h3 id="modal-nombre-animal"></h3>
                                        <p><strong>Especie:</strong> <span id="modal-especie"></span></p>
                                        <p><strong>Zona:</strong> <span id="modal-zona"></span></p>
                                        <p><strong>Dieta:</strong> <span id="modal-dieta"></span></p>
                                        <p id="modal-descripcion-animal"></p>
                                    </div>
                                </div>
                            </section>
                        </main>
                    </div>
                    <script src="usuario.js"></script>
                </body>

                </html>