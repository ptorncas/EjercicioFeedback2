/*************************************
 Programación Concurrente
 Ejercicio Feedback 2
 Pablo Tornero Casas
 *************************************/
/*
Ámbito General:

    La aplicación se compone de las clases:
        - Main
        - Fabrica
        - Tablero
        - LineaDeEnsamblaje
        - EstacionDeTrabajo
        - Componente
        - Buffer

    simula un sistema de fabricación en Java, utilizando el concepto de concurrencia,
    implementando hilos independientes con programación en paralelo y distribuída, sincronizándolos.
    La producción y consumo de los componentes mediante el ensamblaje y bufer compartido.
    Visualización en tiempo real cercana a la distribución normal esperada.

 */
/*
Clase Main:
    - Es el punto de inicio de la aplicación, inicia el proceso de fabricación.
    - Se ejecuta al principio creando una instancia de Fábrica para comenzar con la simulación.
 */
package com.uax.campanaGauss;

public class Main {
    public static void main(String[] args) {
        Fabrica fabrica= new Fabrica();
        fabrica.main(args);
    }
}
