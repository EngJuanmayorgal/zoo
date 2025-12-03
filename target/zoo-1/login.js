
window.addEventListener("DOMContentLoaded", () => {
    const params = new URLSearchParams(window.location.search);
    if (params.get("error") === "1") {
        document.getElementById("nombre").value = "";
        document.getElementById("password").value = "";
        document.getElementById("rol").selectedIndex = 0;
    }
});

function validarFormulario() {
    const nombre = document.getElementById("nombre").value.trim();
    const password = document.getElementById("password").value.trim();
    const rol = document.getElementById("rol").value;

    if (!nombre || !password || !rol) {
        alert("Por favor completa todos los campos.");
        return false;
    }

    return true;
}
