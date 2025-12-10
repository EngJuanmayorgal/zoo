package Alimentacion;

import Factory.Animal;

public interface EstrategiaAlimentacion {

    // Alimentar al animal
    String alimentar(Animal animal);

    // Calcular cantidad de comida
    double calcularCantidad(Animal animal);

    // Tipo de comida
    String obtenerTipoComida();

    // Frecuencia con la que come
    int obtenerFrecuencia();
}
