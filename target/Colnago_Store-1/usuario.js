// Cargar la sección de productos al inicio
console.log("Iniciando la aplicación...");
mostrar('productos');

function mostrar(seccionId) {
    // Ocultar todas las secciones
    document.querySelectorAll('.contenido').forEach(s => {
        s.classList.remove('activo');
        s.classList.add('oculto');
    });

    // Mostrar sección seleccionada
    const seccion = document.getElementById(seccionId);
    seccion.classList.add('activo');
    seccion.classList.remove('oculto');

    // Cargar contenido según la sección
    if (seccionId === 'perfil') {
        cargarPerfil();
    } else if (seccionId === 'productos') {
        filtrar('todas');
    }
}

function cargarPerfil() {
    fetch('DatosUsuario')
        .then(res => res.text())
        .then(html => {
            document.getElementById('perfil').innerHTML = html;
        })
        .catch(error => {
            console.error("Error al cargar perfil:", error);
        });
}

// Función para filtrar animales por zona
function filtrar(zona) {
    console.log("Filtrando zona:", zona);
    fetch(`AnimalesServlet?zona=${zona}`)
        .then(res => res.json())
        .then(animales => {
            console.log("Animales recibidos:", animales);
            renderAnimales(animales);
        })
        .catch(error => {
            console.error("Error al cargar animales:", error);
            document.getElementById('contenedor-productos').innerHTML = 
                '<p style="color: red;">Error al cargar los animales. Verifica la consola.</p>';
        });
}

// Función para mostrar los animales en el grid
function renderAnimales(animales) {
    const contenedor = document.getElementById('contenedor-productos');
    contenedor.innerHTML = '';
    
    if (!animales || animales.length === 0) {
        contenedor.innerHTML = '<p>No hay animales en esta zona</p>';
        return;
    }
    
    animales.forEach(animal => {
        const card = document.createElement("div");
        card.classList.add("card-producto");
        card.onclick = () => mostrarModalAnimal(animal);

        card.innerHTML = `
            <img src="${animal.imagenUrl}" alt="${animal.nombre}" onerror="this.src='images/no-image.jpg'">
            <h4>${animal.nombre}</h4>
        `;
        contenedor.appendChild(card);
    });
}

// Función para mostrar el modal con los detalles del animal
function mostrarModalAnimal(animal) {
    document.getElementById("modal-img-animal").src = animal.imagenUrl;
    document.getElementById("modal-nombre-animal").textContent = animal.nombre;
    document.getElementById("modal-especie").textContent = animal.especie;
    document.getElementById("modal-zona").textContent = animal.zona;
    document.getElementById("modal-dieta").textContent = animal.dieta;
    document.getElementById("modal-descripcion-animal").textContent = animal.descripcion;
    document.getElementById("modalAnimal").classList.add("mostrar");
}

// Función para cerrar el modal
function cerrarModalAnimal() {
    document.getElementById("modalAnimal").classList.remove("mostrar");
}