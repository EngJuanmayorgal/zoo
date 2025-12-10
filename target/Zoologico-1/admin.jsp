<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@page import="java.util.*, logica.Usuario" %>
        <%@page session="true" %>
            <% Usuario admin=(Usuario) session.getAttribute("usuario"); if (admin==null ||
                !"admin".equals(admin.getRole())) { response.sendRedirect("login.jsp"); return; } %>
                <% if ("true".equals(request.getParameter("logout"))) { session.invalidate();
                    response.sendRedirect("index.jsp"); return; } %>
                    <!DOCTYPE html>
                    <html>

                    <head>
                        <meta charset="UTF-8">
                        <title>Panel Administ|rador</title>
                        <link rel="stylesheet" href="css/usuario.css">
                    </head>

                    <body data-admin-id="<%= admin.getIdUser()%>" style="background-image: url(images/fondozoo1.jpg);
          background-size: cover;">
                        <div class="contenedor-principal">
                            <header style="background-image: url(images/Planta.jpg);
          background-size: cover;">
                                <h1 class="welcome">Panel de Administración</h1>
                                <nav>
                                    <button onclick="mostrar('perfil')">Perfil</button>
                                    <button onclick="mostrar('animales')">Animales</button>
                                    <button onclick="location.href = 'admin.jsp?logout=true'">Cerrar sesión</button>
                                </nav>
                            </header>

                            <main style="display: flex">
                                <section id="perfil" class="contenido oculto"></section>
                                <section id="animales" class="contenido oculto">
                                    <nav class="submenu">
                                        <button onclick="mostrarFormularioNuevo()">Agregar producto</button>
                                    </nav>
                                    <div id="contenedor-productos" class="productos-grid"></div>



                                </section>

                            </main>
                            <!-- Modal para vista ampliada -->
                            <div id="modalAnimal" class="modal">
                                <div class="modal-contenido">
                                    <span class="cerrar" onclick="cerrarModalAnimal()">&times;</span>
                                    <img id="modal-img-animal" src="" alt="Imagen del animal">
                                    <h3 id="modal-nombre-animal"></h3>
                                    <p><strong>Especie:</strong> <span id="modal-especie"></span></p>
                                    <p><strong>Dieta:</strong> <span id="modal-dieta"></span></p>
                                    <p id="modal-descripcion-animal"></p>
                                    <button onclick="agregarEditarDesdeModal()">Editar</button>
                                    <button onclick="alimentacionAnimal()">Alimentacion</button>
                                </div>
                            </div>

                            <!-- Modal de alimentacion -->
                            <div id="modalAlimentarAnimal" class="modal">
                                <div class="modal-contenido">
                                    <span class="cerrar" onclick="cerrarModalAlimentar()">&times;</span>
                                    <img id="img-animal" src="" alt="Imagen del animal">
                                    <h3 id="nombre-animal"></h3>
                                    <p><strong>Estrategia:</strong></p>
                                    <p id="modal-estrategia"></p>
                                    <button onclick="alimentarAnimal()">Alimentar</button>
                                </div>
                            </div>
                            <!-- Modal de edición -->
                            <div id="modalProductoEditar" class="modal">
                                <div class="modal-contenido">
                                    <span class="cerrar" onclick="cerrarModalEditar()">&times;</span>
                                    <h3 id="modal-titulo">Editar Animal</h3>
                                    <form id="form-producto" action="ProductosServlet" method="post"
                                        enctype="multipart/form-data">
                                        <input type="hidden" id="prod-id" name="prod-id">

                                        <label>Nombre:</label>
                                        <input type="text" id="prod-nombre" name="prod-nombre" placeholder="Nombre"
                                            required>

                                        <label>Descripción:</label>
                                        <textarea id="prod-descripcion" name="prod-descripcion"
                                            placeholder="Descripción" required></textarea>
                                        <!-- Imagen -->
                                        <label for="prod-imagen-file" style="cursor: pointer;">
                                            <img id="preview-img" src="" alt="Vista previa"
                                                style="width: 200px; display: block; margin: 10px auto;">
                                        </label>
                                        <input id="prod-imagen-file" name="prod-imagen-file" accept="image/*"
                                            style="display: none;">

                                        <button type="submit">Guardar</button>
                                    </form>
                                </div>
                            </div>
                            <!-- Modal de creación -->
                            <div id="modalProductoAgregar" class="modal">
                                <div class="modal-contenido">
                                    <span class="cerrar" onclick="cerrarModalAgregar()">&times;</span>
                                    <h3 id="modal-titulo">Agregar Producto</h3>
                                    <form id="producto" action="AgregarServlet" method="post"
                                        enctype="multipart/form-data">
                                        <label>Nombre:</label>
                                        <input type="text" id="Agregar-nombre" name="Agregar-nombre"
                                            placeholder="Nombre" required>
                                        <label for="tipo">Tipo:</label>
                                        <select name="tipo" id="tipo" required>
                                            <option value="">Selecciona un tipo</option>
                                            <option value="bicicletas">Bicicleta</option>
                                            <option value="accesorios">Accesorio</option>
                                            <option value="vestimenta">Vestimenta</option>
                                        </select>
                                        <label>Descripción:</label>
                                        <textarea id="Agregar-descripcion" name="Agregar-descripcion"
                                            placehAgregar-descripcionolder="Descripción" required></textarea>

                                        <label>Precio:</label>
                                        <input type="text" id="Agregar-precio" name="Agregar-precio"
                                            placeholder="Precio" required>

                                        <!-- Imagen -->
                                        <label for="imagen-file" style="cursor: pointer;">
                                            <img id="preview" src="images/default.png" alt="Vista previa"
                                                style="width: 200px; display: block; margin: 10px auto;">
                                        </label>
                                        <input type="file" id="imagen-file" name="imagen-file" accept="image/*"
                                            style="display: none;">

                                        <button type="submit">Guardar</button>
                                    </form>
                                </div>
                            </div>

                        </div>
                        <script>
                            document.addEventListener("DOMContentLoaded", function () {
                                const inputFile = document.getElementById("imagen-file");
                                const preview = document.getElementById("preview");

                                inputFile.addEventListener("change", function () {
                                    const file = this.files[0];
                                    if (file) {
                                        const reader = new FileReader();
                                        reader.onload = function (e) {
                                            preview.src = e.target.result;
                                        };
                                        reader.readAsDataURL(file);
                                    } else {
                                        preview.src = "images/default.png";
                                    }
                                });
                            });

                        </script>

                        <script src="admin.js"></script>
                    </body>

                    </html>