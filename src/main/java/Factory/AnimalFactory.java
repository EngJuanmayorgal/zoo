package Factory;

public class AnimalFactory {

    public static IAnimalFactory getFactory(String zona) {
        if (zona == null) {
            return null;
        }
        switch (zona.toLowerCase()) {
            case "áfrica":
            case "africa":
                return new AnimalAfrica();
            case "amazonas":
                return new AnimalAmazonas();
            case "asia":
                return new AnimalAsia();
            case "ártico":
            case "artico":
                return new AnimalArtico();
            default:
                throw new IllegalArgumentException("Zona no soportada: " + zona);
        }
    }

}
