
mostrar("animales");
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
            .then(animales => renderProductos(animales));

    }
}

function cargarPerfil() {
    fetch('DatosUsuario')
        .then(res => res.text())
        .then(html => {
            document.getElementById('perfil').innerHTML = html;
        });
}

let animales = [];

cargarTodosLosAnimales();

async function cargarTodosLosAnimales() {
    try {
        const res = await fetch("AnimalesServlet?zona=todas");
        animales = await res.json();
    } catch (error) {
        console.error("Error al cargar los animales:", error);
    }
}

function mostrarModalAnimal(animal) {
    document.getElementById("modal-img-animal").src = animal.imagenUrl;
    document.getElementById("modal-nombre-animal").textContent = animal.nombre;
    document.getElementById("modal-especie").textContent = animal.especie;
    document.getElementById("modal-dieta").textContent = animal.dieta;
    document.getElementById("modal-descripcion-animal").textContent = animal.descripcion;
    document.getElementById("modalAnimal").classList.add("mostrar");
    document.getElementById("modalAnimal").dataset.animalId = animal.id;
}

function renderProductos(animales) {
    const contenedor = document.getElementById('contenedor-productos');
    contenedor.innerHTML = '';
    animales.forEach(p => {
        const card = document.createElement("div");
        card.classList.add("card-producto");
        card.onclick = () => mostrarModalAnimal(p);
        card.innerHTML = `
            <img src="${p.imagenUrl}" alt="${p.nombre}">
            <h4>${p.nombre}</h4>
        `;
        contenedor.appendChild(card);
    });
}

function cerrarModalAnimal() {
    document.getElementById("modalAnimal").classList.remove("mostrar");
}

function cerrarModalEditar() {
    document.getElementById("modalProductoEditar").classList.remove("mostrar");
}

function agregarEditarDesdeModal() {
    const id = parseInt(document.getElementById("modalAnimal").dataset.animalId);
    const animal = animales.find(a => a.id === id);
    editarProducto(animal);
    cerrarModalAnimal();
}

function editarProducto(p) {
    document.getElementById("prod-id").value = p.id;
    document.getElementById("prod-nombre").value = p.nombre;
    document.getElementById("prod-descripcion").value = p.descripcion;
    document.getElementById("preview-img").src = p.imagenUrl;
    document.getElementById("prod-imagen-file").value = "";
    document.getElementById("modalProductoEditar").classList.add("mostrar");
    document.getElementById("modalProductoEditar").dataset.animalId = p.id;
}

function alimentacionAnimal() {
    const id = parseInt(document.getElementById("modalAnimal").dataset.animalId);
    const animal = animales.find(a => a.id === id);
    document.getElementById("nombre-animal").textContent = animal.nombre;
    document.getElementById("img-animal").src = animal.imagenUrl; 
    document.getElementById("modal-estrategia").innerHTML = animal.estrategia;

    document.getElementById("modalAlimentarAnimal").classList.add("mostrar");
    document.getElementById("modalAlimentarAnimal").dataset.animalId = animal.id;
    cerrarModalAnimal();
    console.log("Estrategia de alimentacion:", animal.estrategia);
}
function cerrarModalAlimentar() {
    document.getElementById("modalAlimentarAnimal").classList.remove("mostrar");
}

function alimentarAnimal() {
    const id = parseInt(document.getElementById("modalAlimentarAnimal").dataset.animalId);
    fetch(`AnimalesServlet?accion=alimentar&id=${id}`, { method: "POST" })
        .then(() => {
            alert("El animal ha sido alimentado.");
            cerrarModalAlimentar();
        })
        .catch(error => {
            console.error("Error al alimentar al animal:", error);
            alert("Error al alimentar al animal. Verifica la consola.");
        });
}


