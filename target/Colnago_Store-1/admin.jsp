<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, logica.Usuario"%>
<%@page session="true" %>
<%
    Usuario admin = (Usuario) session.getAttribute("usuario");
    if (admin == null || !"admin".equals(admin.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<%

    if ("true".equals(request.getParameter("logout"))) {
        session.invalidate();
        response.sendRedirect("index.jsp"); 
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Panel Administ|rador</title>
        <link rel="stylesheet" href="css/usuario.css">
    </head>
    <body data-admin-id="<%= admin.getIdUser()%>">
        <div class="contenedor-principal">
            <header>
                <h1>Panel de Administración</h1>
                <nav>
                    <button onclick="mostrar('perfil')">Perfil</button>
                    <button onclick="mostrar('productos')">Productos</button>
                    <button onclick="mostrar('eliminados')">Eliminados</button>
                    <button onclick="location.href = 'admin.jsp?logout=true'">Cerrar sesión</button>
                </nav>
            </header>

            <main style="display: flex">
                <section id="perfil" class="contenido oculto"></section>
                <section id="productos" class="contenido oculto">
                    <nav class="submenu">
                        <button onclick="mostrarFormularioNuevo()">Agregar producto</button>
                    </nav>
                    <div id="contenedor-productos" class="productos-grid"></div>

                    <!-- Modal para vista ampliada -->
                    <div id="modalProducto" class="modal">
                        <div class="modal-contenido">
                            <span class="cerrar" onclick="cerrarModal()">&times;</span>
                            <img id="modal-img" src="" alt="Imagen del producto">
                            <h3 id="modal-nombre"></h3>
                            <p id="modal-descripcion"></p>
                            <p id="modal-precio"></p>
                            <button onclick="agregarEditarDesdeModal()">Editar</button>
                            <button onclick="eliminarProducto()">Eliminar</button>
                        </div>
                    </div>

                </section>

                <section id="eliminados" class="contenido oculto">
                    <h2>Productos Eliminados</h2>
                    <div id="contenedor-eliminados" class="productos-grid"></div>
                </section>
            </main>

            <!-- Modal de edición -->
            <div id="modalProductoEditar" class="modal">
                <div class="modal-contenido">
                    <span class="cerrar" onclick="cerrarModalEditar()">&times;</span>
                    <h3 id="modal-titulo">Editar Producto</h3>
                    <form id="form-producto" action="ProductosServlet" method="post" enctype="multipart/form-data">
                        <input type="hidden" id="prod-id" name="prod-id">

                        <label>Nombre:</label>
                        <input type="text" id="prod-nombre" name="prod-nombre" placeholder="Nombre" required>

                        <label>Descripción:</label>
                        <textarea id="prod-descripcion" name="prod-descripcion" placeholder="Descripción" required></textarea>

                        <label>Precio:</label>
                        <input type="text" id="prod-precio" name="prod-precio" placeholder="Precio" required>

                        <!-- Imagen -->
                        <label for="prod-imagen-file" style="cursor: pointer;">
                            <img id="preview-img" src="" alt="Vista previa" style="width: 200px; display: block; margin: 10px auto;">
                        </label>
                        <input id="prod-imagen-file" name="prod-imagen-file" accept="image/*" style="display: none;">

                        <button type="submit">Guardar</button>
                    </form>
                </div>
            </div>
            <!-- Modal de creación -->
            <div id="modalProductoAgregar" class="modal">
                <div class="modal-contenido">
                    <span class="cerrar" onclick="cerrarModalAgregar()">&times;</span>
                    <h3 id="modal-titulo">Agregar Producto</h3>
                    <form id="producto" action="AgregarServlet" method="post" enctype="multipart/form-data">
                        <label>Nombre:</label>
                        <input type="text" id="Agregar-nombre" name="Agregar-nombre" placeholder="Nombre" required>
                        <label for="tipo">Tipo:</label>
                        <select name="tipo" id="tipo" required>
                            <option value="">Selecciona un tipo</option>
                            <option value="bicicletas">Bicicleta</option>
                            <option value="accesorios">Accesorio</option>
                            <option value="vestimenta">Vestimenta</option>
                        </select>
                        <label>Descripción:</label>
                        <textarea id="Agregar-descripcion" name="Agregar-descripcion" placehAgregar-descripcionolder="Descripción" required></textarea>

                        <label>Precio:</label>
                        <input type="text" id="Agregar-precio" name="Agregar-precio" placeholder="Precio" required>

                        <!-- Imagen -->
                        <label for="imagen-file" style="cursor: pointer;">
                            <img id="preview" src="images/default.png" alt="Vista previa" style="width: 200px; display: block; margin: 10px auto;">
                        </label>
                        <input type="file" id="imagen-file" name="imagen-file" accept="image/*" style="display: none;">

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
