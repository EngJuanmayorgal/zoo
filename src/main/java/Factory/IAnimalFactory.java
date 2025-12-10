package Factory;

public interface IAnimalFactory {

    Animal createAnimal(String nombre,
            String especie,
            int id,
            String dieta,
            String descripcion,
            String imagenURL);

}
