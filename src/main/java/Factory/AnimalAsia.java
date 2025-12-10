package Factory;

public class AnimalAsia implements IAnimalFactory {

    @Override
    public Animal createAnimal(String nombre, String especie, int id, String dieta, String descripcion, String imagenURL) {

        String zona = "Asia";

        String finalDieta = (dieta == null || dieta.trim().isEmpty())
                ? DietaPorEspecie(nombre)
                : dieta;

        String finalDescripcion = (descripcion == null || descripcion.trim().isEmpty())
                ? "Fauna asiática: desde bosques templados hasta monzones; adaptaciones variadas."
                : descripcion;

        String finalImagen = (imagenURL == null || imagenURL.trim().isEmpty())
                ? "images/animales/" + nombre.toLowerCase().replaceAll("\\s+", "_") + ".jpg"
                : imagenURL;

        return new Animal(nombre, especie, zona, finalDieta, finalDescripcion, finalImagen, id);

    }

    private String DietaPorEspecie(String especie) {
        if (especie == null) {
            return "Omnívoro";
        }
        String e = especie.toLowerCase();
        if (e.contains("leopardo de las nieves") || e.contains("tigre")) {
            return "Carnívoro";
        }
        if (e.contains("panda") || e.contains("elefante asiático")) {
            return "Herbívoro";
        }
        return "Omnívoro";
    }

}
