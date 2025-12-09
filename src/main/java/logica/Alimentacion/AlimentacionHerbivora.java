package logica.Alimentacion;

import logica.Animal;

public class AlimentacionHerbivora implements EstrategiaAlimentacion {

    @Override
    public String alimentar(Animal animal) {
        StringBuilder reporte = new StringBuilder();

        reporte.append("ALIMENTACIÓN HERBÍVORA \n");
        reporte.append("Animal: ").append(animal.getNombre()).append("\n");
        reporte.append("Especie: ").append(animal.getEspecie()).append("\n\n");

        double cantidad = calcularCantidad(animal);

        reporte.append("Detalles de alimentación:\n");
        reporte.append(" Tipo: ").append(obtenerTipoComida()).append("\n");
        reporte.append(" Cantidad: ").append(cantidad).append(" kg\n");
        reporte.append(" Frecuencia: ").append(obtenerFrecuencia()).append(" veces/día\n\n");

        return reporte.toString();
    }

    @Override
    public double calcularCantidad(Animal animal) {
        // Herbívoros: 2-5% de su peso
        double pesoEstimado = estimarPeso(animal);
        return pesoEstimado * 0.03;
    }

    @Override
    public String obtenerTipoComida() {
        return "Heno, vegetales frescos, frutas";
    }

    @Override
    public int obtenerFrecuencia() {
        return 3;
    }

    private double estimarPeso(Animal animal) {
        String especie = animal.getEspecie().toLowerCase();
        if (especie.contains("elefante") || especie.contains("elephas")) {
            return 5000.0; //Jodidamente grande
        } else if (especie.contains("jirafa")) {
            return 1200.0;
        } else if (especie.contains("cebra") || especie.contains("equus")) {
            return 400.0;
        } else if (especie.contains("rinoceronte")) {
            return 2000.0;
        }
        return 100.0;
    }
}
