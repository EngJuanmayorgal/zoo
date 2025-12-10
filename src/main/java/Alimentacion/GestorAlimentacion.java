package Alimentacion;

import Factory.Animal;

public class GestorAlimentacion {

    private EstrategiaAlimentacion estrategia;

    public GestorAlimentacion(EstrategiaAlimentacion estrategia) {
        this.estrategia = estrategia;
    }

    // Cambiar estrategia en runtime
    public void setEstrategia(EstrategiaAlimentacion estrategia) {
        this.estrategia = estrategia;
    }

    // Ejecutar alimentación con la estrategia actual
    public String ejecutarAlimentacion(Animal animal) {
        if (estrategia == null) {
            return "Error: No hay estrategia de alimentación definida";
        }
        return estrategia.alimentar(animal);
    }
    // Gestor segun dieta del animal
    public static GestorAlimentacion crearPara(Animal animal) {
        System.out.println("llll");
        EstrategiaAlimentacion estrategia;
        String dieta = animal.getDieta().toLowerCase();
        switch (dieta) {
            case "carnivoro":
            case "carnívoro":
                estrategia = new AlimentacionCarnivora();
                break;
            case "herbivoro":
            case "herbívoro":
                estrategia = new AlimentacionHerbivora();
                break;
            case "omnivoro":
            case "omnívoro":
                estrategia = new AlimentacionOmnivora();
                break;
            default:
                throw new IllegalArgumentException("Dieta no reconocida: " + dieta);
        }
        return new GestorAlimentacion(estrategia);
    }

    // Obtener información de la estrategia actual
    public String obtenerInfoEstrategia(Animal animal) {
        if (estrategia == null) {
            return "Sin estrategia definida";
        }
        StringBuilder info = new StringBuilder();
        info.append("").append(estrategia.getClass().getSimpleName()).append("\n");
        info.append("Tipo de comida: ").append(estrategia.obtenerTipoComida()).append("\n");
        info.append("Cantidad: ").append(estrategia.calcularCantidad(animal)).append(" kg\n");
        info.append("Frecuencia: ").append(estrategia.obtenerFrecuencia()).append(" veces/día");

        return info.toString();
    }
}
