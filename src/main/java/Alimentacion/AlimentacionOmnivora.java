package Alimentacion;

import Factory.Animal;

public class AlimentacionOmnivora implements EstrategiaAlimentacion {

    @Override
    public String alimentar(Animal animal) {
        StringBuilder reporte = new StringBuilder();

        reporte.append("ALIMENTACIÓN OMNÍVORA \n");
        reporte.append("Animal: ").append(animal.getNombre()).append("\n");
        reporte.append("Especie: ").append(animal.getEspecie()).append("\n\n");

        double cantidad = calcularCantidad(animal);
        double carnePorcion = cantidad * 0.5;
        double vegetalPorcion = cantidad * 0.5;

        reporte.append("Detalles de alimentación:\n");
        reporte.append(" Tipo: ").append(obtenerTipoComida()).append("\n");
        reporte.append(" Cantidad total: ").append(cantidad).append(" kg\n");
        reporte.append("  - Carne: ").append(carnePorcion).append(" kg\n");
        reporte.append("  - Vegetales: ").append(vegetalPorcion).append(" kg\n");
        reporte.append(" Frecuencia: ").append(obtenerFrecuencia()).append(" veces/día\n\n");

        return reporte.toString();
    }

    @Override
    public double calcularCantidad(Animal animal) {
        // Omnívoros: balance entre carnívoro y herbívoro
        double pesoEstimado = estimarPeso(animal);
        return pesoEstimado * 0.035;
    }

    @Override
    public String obtenerTipoComida() {
        return "Dieta mixta (50% carne, 50% vegetales)";
    }

    @Override
    public int obtenerFrecuencia() {
        return 3;
    }

    private double estimarPeso(Animal animal) {
        String especie = animal.getEspecie().toLowerCase();
        if (especie.contains("ursus") || especie.contains("oso")) {
            return 300.0;
        } else if (especie.contains("pongo") || especie.contains("orangután")) {
            return 75.0;
        } else if (especie.contains("ateles") || especie.contains("mono")) {
            return 10.0;
        }
        return 50.0;
    }
}
