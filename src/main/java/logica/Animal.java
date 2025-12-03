package logica;

public class Animal {

    private String nombre, especie, zona, dieta, descripcion, imagenURL;
    private int idAnimal;

    public Animal() {
    }

    public Animal(String nombre, String especie, String zona, String dieta, String descripcion, String imagenURL, int idAnimal) {
        this.nombre = nombre;
        this.especie = especie;
        this.zona = zona;
        this.dieta = dieta;
        this.descripcion = descripcion;
        this.imagenURL = imagenURL;
        this.idAnimal = idAnimal;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getDieta() {
        return dieta;
    }

    public void setDieta(String dieta) {
        this.dieta = dieta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }
}
