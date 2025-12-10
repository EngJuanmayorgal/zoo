package Factory;

public class AnimalAmazonas implements IAnimalFactory {

    @Override
    public Animal createAnimal(String nombre, String especie, int id, String dieta, String descripcion, String imagenURL) {
        String zona = "Amazonas";

        String finalDieta = (dieta == null || dieta.trim().isEmpty())
                ? DietaPorEspecie(nombre)
                : dieta;

        String finalDescripcion = (descripcion == null || descripcion.trim().isEmpty())
                ? "Animal de selva tropical: adaptado a humedad, denso follaje y diversidad ecológica."
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
        if (e.contains("jaguar") || e.contains("anaconda")) {
            return "Carnívoro";
        }
        if (e.contains("perezoso") || e.contains("capibara") || e.contains("mono araña")) {
            return "Herbívoro";
        }
        return "Omnívoro";
    }
}
