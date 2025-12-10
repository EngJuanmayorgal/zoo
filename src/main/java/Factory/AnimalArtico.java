package Factory;

public class AnimalArtico implements IAnimalFactory {

    @Override
    public Animal createAnimal(String nombre, String especie, int id, String dieta, String descripcion, String imagenURL) {
        String zona = "Ártico";

        String finalDieta = (dieta == null || dieta.trim().isEmpty())
                ? DietaPorEspecie(nombre)
                : dieta;

        String finalDescripcion = (descripcion == null || descripcion.trim().isEmpty())
                ? "Adaptado a bajas temperaturas, pelaje grueso o aislamiento y comportamiento migratorio."
                : descripcion;

        String finalImagen = (imagenURL == null || imagenURL.trim().isEmpty())
                ? "images/artico/" + nombre.toLowerCase().replaceAll("\\s+", "_") + ".jpg"
                : imagenURL;

        return new Animal(nombre, especie, zona, finalDieta, finalDescripcion, finalImagen, id);
    }

    private String DietaPorEspecie(String especie) {
        if (especie == null) {
            return "Carnívoro";
        }
        String e = especie.toLowerCase();
                if (e.contains("reno")) {
            return "Herbívoro";
        }
        return "Carnívoro";
    }
}
