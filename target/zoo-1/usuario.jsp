<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, logica.Producto, logica.Usuario" %>
<%@page session="true" %>
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
        <title>Panel del Usuario</title>
        <link rel="stylesheet" href="css/usuario.css">
    </head>
    <body style="background-image: url(images/fondo2.jpg);
          background-size: cover;">
        <div class="contenedor-principal">
            <header>
                <h1>Bienvenido, <%= session.getAttribute("nombreUsuario")%></h1>
                <body data-usuario-id="<%= session.getAttribute("usuarioId") %>">
                <nav>
                    <button onclick="mostrar('perfil')">Mi perfil</button>
                    <button onclick="mostrar('compras')">Mis compras</button>
                    <button onclick="mostrar('productos')">Comprar</button>
                    <button onclick="mostrarCarrito()">Carrito 🛒 (<span id="contador">0</span>)</button>
                    <button onclick="location.href = 'usuario.jsp?logout=true'">Cerrar sesion</button>
                </nav>
            </header>

            <main style="display: flex">
                <section id="perfil" class="contenido oculto"></section>
                <section id="compras" class="contenido oculto">
                    <div id="contenedor-compras" class="productos-grid"></div>

                    <!-- Modal para vista ampliada -->
                    <div id="modalCompra" class="modal">
                        <div class="modal-contenido">
                            <span class="cerrar" onclick="cerrarModalCompra()">&times;</span>
                            <img id="modal-img1" src="" alt="Imagen del producto">
                            <h3 id="modal-nombre1"></h3>
                            <p id="modal-descripcion1"></p>
                            <p id="modal-precio1"></p>
                        </div>
                    </div>
                </section>
                <section id="productos" class="contenido oculto">
                    <nav class="submenu">
                        <button onclick="filtrar('todas')">Todas</button>
                        <button onclick="filtrar('bicicletas')">Bicicletas</button>
                        <button onclick="filtrar('accesorios')">Accesorios</button>
                        <button onclick="filtrar('vestimenta')">Vestimenta</button>
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
                            <button onclick="agregarAlCarritoDesdeModal()">Agregar al carrito</button>
                        </div>
                    </div>

                </section>
                <section id="carrito" class="carrito-overlay oculto">
                    <div class="carrito-contenido">
                        <span class="cerrar-carrito" onclick="ocultarCarrito()">×</span>
                        <h2>Carrito de Compras</h2>
                        <ul id="lista-carrito"></ul>
                        <div class="carrito-footer">
                            <button onclick="finalizarCompra()">Finalizar Compra</button>
                        </div>
                    </div>
                </section>


            </main>
        </div>
        <script src="usuario.js" ></script>
    </body>
</html>
