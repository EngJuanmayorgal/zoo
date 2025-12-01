mostrar('productos');
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
    } else if (seccionId === 'compras') {
        cargarCompras();
    } else if (seccionId === 'productos') {
        filtrar("todas");
    }
}

function cargarPerfil() {
    fetch('DatosUsuario')
            .then(res => res.text())
            .then(html => {
                document.getElementById('perfil').innerHTML = html;
            });
}

function cargarCompras() {
    fetch(`CompraServlet?usuarioId=${parseInt(document.body.dataset.usuarioId)}`)
            .then(res => res.json())
            .then(data => renderCompras(data));
}

function renderCompras(compras) {
    const contenedor = document.getElementById('contenedor-compras');
    contenedor.innerHTML = '';
    compras.forEach(p => {
        const card = document.createElement("div");
        card.classList.add("card-producto");
        card.onclick = () => mostrarModalCompra(p);

        card.innerHTML = `
            <img src="${p.imagen}" alt="${p.nombre}">
            <h4>${p.nombre}</h4>
        `;
        contenedor.appendChild(card);
    });
}

function mostrarModalCompra(producto) {
    document.getElementById("modal-img1").src = producto.imagen;
    document.getElementById("modal-nombre1").textContent = producto.nombre;
    document.getElementById("modal-descripcion1").textContent = producto.descripcion;
    document.getElementById("modal-precio1").textContent = producto.precio;
    document.getElementById("modalCompra").classList.add("mostrar");
    document.getElementById("modalCompra").dataset.productoId = producto.id;
}

function cerrarModalCompra() {
    document.getElementById("modalCompra").classList.remove("mostrar");
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

function agregarAlCarritoDesdeModal() {
    const id = parseInt(document.getElementById("modalProducto").dataset.productoId);
    const producto = productos.find(p => p.id === id);
    cerrarModal();
    agregarAlCarrito(producto);
}

function filtrar(tipo) {
    fetch(`ProductosServlet?tipo=${tipo}`)
            .then(res => res.json())
            .then(productos => renderProductos(productos));
}

// Carrito
let carrito = [];

function agregarAlCarrito(producto) {
    carrito.push(producto);
    actualizarCarrito();
}

function actualizarCarrito() {
    document.getElementById('contador').textContent = carrito.length;
    const lista = document.getElementById('lista-carrito');
    lista.innerHTML = '';
    carrito.forEach((prod, i) => {
        const item = document.createElement('li');
        item.textContent = `${prod.nombre} - ${prod.precio}`;
        lista.appendChild(item);
    });
}

function mostrarCarrito() {
    document.querySelectorAll('.contenido').forEach(s => s.classList.remove('activo'));
    document.getElementById('carrito').style.display = 'flex';
}

function ocultarCarrito() {
    mostrar('productos');
    document.getElementById('carrito').style.display = 'none';
}

function finalizarCompra() {
    const usuarioId = parseInt(document.body.dataset.usuarioId);
    carrito.forEach(producto => {
        fetch('CompraServlet', {
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            body: `usuarioId=${usuarioId}&productoId=${producto.id}`
        })
                .then(res => res.json())
                .then(data => {
                    if (!data.exito) {
                        console.error("Error registrando compra de producto ID:", producto.id);
                    }
                });
    });

    carrito = [];
    actualizarCarrito();
    alert("¡Compra finalizada con éxito!");
    ocultarCarrito();
}

