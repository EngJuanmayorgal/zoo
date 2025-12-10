package Alimentacion;

import logica.Animal;

public class AlimentacionCarnivora implements EstrategiaAlimentacion {

    @Override
    public String alimentar(Animal animal) {
        StringBuilder reporte = new StringBuilder();

        reporte.append("ALIMENTACIÓN CARNÍVORA \n");
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
        double pesoEstimado = estimarPeso(animal);
        return pesoEstimado * 0.04;
    }

    @Override
    public String obtenerTipoComida() {
        return "Carne fresca (pollo, res, pescado)";
    }

    @Override
    public int obtenerFrecuencia() {
        return 2;
    }

    private double estimarPeso(Animal animal) {
        String especie = animal.getEspecie().toLowerCase();
        if (especie.contains("leo") || especie.contains("tigr")) {
            return 200.0;
        } else if (especie.contains("jaguar")) {
            return 100.0;
        } else if (especie.contains("leopardo")) {
            return 70.0;
        }
        return 50.0;
    }
}
