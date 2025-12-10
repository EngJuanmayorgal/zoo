package Factory;

public class AnimalAfrica implements IAnimalFactory {

    @Override
    public Animal createAnimal(String nombre, String especie, int id, String dieta, String descripcion, String imagenURL) {

        String zona = "África";

        String finalDieta = (dieta == null || dieta.trim().isEmpty())
                ? DietaPorEspecie(nombre)
                : dieta;

        String finalDescripcion = (descripcion == null || descripcion.trim().isEmpty())
                ? "Animal típico de sabanas africanas. Adaptado a altas temperaturas y grandes llanuras."
                : descripcion;

        String finalImagen = (imagenURL == null || imagenURL.trim().isEmpty())
                ? "images/animales/" + nombre.toLowerCase() + ".jpg"
                : imagenURL;

        return new Animal(nombre, especie, zona, finalDieta, finalDescripcion, finalImagen, id);

    }

    @Override
    public String DietaPorEspecie(String especie) {
        if (especie == null) {
            return "Omnívoro";
        }
        String e = especie.toLowerCase();
        if (e.contains("león") || e.contains("leon") || e.contains("leopardo")) {
            return "Carnívoro";
        }
        if (e.contains("elefante") || e.contains("jirafa") || e.contains("cebra") || e.contains("rinoceronte")) {
            return "Herbívoro";
        }
        return "Omnívoro";
    }

}
