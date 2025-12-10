mostrar("productos");
document.getElementById("prod-imagen-file").addEventListener("change", function () {
    const file = this.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            document.getElementById("preview-img").src = e.target.result;
        };
        reader.readAsDataURL(file);
    }
});

function mostrar(seccionId) {
    // Ocultar secciones
    document.querySelectorAll('.contenido').forEach(s => {
        s.classList.remove('activo');
        s.classList.add('oculto');
    });

    // Mostrar sección seleccionada
    const seccion = document.getElementById(seccionId);
    seccion.classList.add('activo');
    seccion.classList.remove('oculto');

    // Marcar botón activo
    document.querySelectorAll('nav button').forEach(btn => btn.classList.remove('activo'));
    const boton = Array.from(document.querySelectorAll('nav button')).find(b => b.textContent.toLowerCase().includes(seccionId));
    if (boton)
        boton.classList.add('activo');

    // Cargar contenido dinámico
    if (seccionId === 'perfil') {
        cargarPerfil();
    } else if (seccionId === 'eliminados') {
        cargarEliminados();
    } else if (seccionId === 'animales') {
        fetch(`AnimalesServlet?zona=${"todas"}`)
                .then(res => res.json())
                .then(productos => renderProductos(productos));
    }
}


function cargarPerfil() {
    fetch('DatosUsuario')
            .then(res => res.text())
            .then(html => {
                document.getElementById('perfil').innerHTML = html;
            });
}

let productos = [];

cargarTodosLosProductos();

async function cargarTodosLosProductos() {
    try {
        const res = await fetch("ProductosServlet?tipo=todas");
        productos = await res.json();
        console.log("Productos cargados:", productos);
    } catch (error) {
        console.error("Error al cargar los productos:", error);
    }
}

function mostrarModalProducto(producto) {
    document.getElementById("modal-img").src = producto.imagen;
    document.getElementById("modal-nombre").textContent = producto.nombre;
    document.getElementById("modal-descripcion").textContent = producto.descripcion;
    document.getElementById("modal-precio").textContent = producto.precio;
    document.getElementById("modalProducto").classList.add("mostrar");
    document.getElementById("modalProducto").dataset.productoId = producto.id;
}

function renderProductos(productos) {
    const contenedor = document.getElementById('contenedor-productos');
    contenedor.innerHTML = '';
    productos.forEach(p => {
        const card = document.createElement("div");
        card.classList.add("card-producto");
        card.onclick = () => mostrarModalProducto(p);
        card.innerHTML = `
            <img src="${p.imagen}" alt="${p.nombre}">
            <h4>${p.nombre}</h4>
        `;
        contenedor.appendChild(card);
    });
}

function cerrarModal() {
    document.getElementById("modalProducto").classList.remove("mostrar");
}

function cerrarModalEditar() {
    document.getElementById("modalProductoEditar").classList.remove("mostrar");
}

function agregarEditarDesdeModal() {
    const id = parseInt(document.getElementById("modalProducto").dataset.productoId);
    const producto = productos.find(p => p.id === id);
    editarProducto(producto);
    cerrarModal();
}

function editarProducto(p) {
    document.getElementById("prod-id").value = p.id;
    document.getElementById("prod-nombre").value = p.nombre;
    document.getElementById("prod-descripcion").value = p.descripcion;
    document.getElementById("prod-precio").value = p.precio;
    document.getElementById("preview-img").src = p.imagen;
    document.getElementById("prod-imagen-file").value = "";
    document.getElementById("modalProductoEditar").classList.add("mostrar");
    document.getElementById("modalProductoEditar").dataset.productoId = p.id;
}

function mostrarFormularioNuevo() {
    document.getElementById("modal-titulo").textContent = "Nuevo Producto";
    document.getElementById("producto").reset();
    document.getElementById("imagen-file").value = "";
    document.getElementById("modalProductoAgregar").classList.add("mostrar");
}
function cerrarModalAgregar() {
    document.getElementById("modalProductoAgregar").classList.remove("mostrar");
}

function cargarEliminados() {
    fetch("AgregarServlet")
            .then(res => res.json())
            .then(productos => {
                const contenedor = document.getElementById("contenedor-eliminados");
                contenedor.innerHTML = "";
                productos.forEach(p => {
                    const card = document.createElement("div");
                    card.classList.add("card-producto");
                    card.innerHTML = `
                    <img src="${p.imagen}" alt="${p.nombre}">
                    <h4>${p.nombre}</h4>
                    <button onclick='restaurarProducto(${p.id})'>Restaurar</button>
                `;
                    contenedor.appendChild(card);
                });
            });
}

function eliminarProducto() {
    mostrar("productos");
        const id = parseInt(document.getElementById("modalProducto").dataset.productoId);
    fetch(`AgregarServlet?accion=eliminar&id=${id}`, {method: "POST"})
            .then(() => cargarProductos());
    mostrar("productos");
    cerrarModal();
}

function restaurarProducto(id) {
    fetch(`AgregarServlet?accion=restaurar&id=${id}`, {method: "POST"})
            .then(() => cargarEliminados());
}
