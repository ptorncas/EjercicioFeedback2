/*************************************
 Programación Concurrente
 Ejercicio Feedback 2
 Pablo Tornero Casas
 *************************************/
/*
Clase LineaDeEnsamblaje:

    - Es la consumidora de componentes
    - Lo componentes son sacados del buffer para ensamblarlos
    - es la parte intermedia entre la EstacionDeTrabajo y la visualización
    - Usa el buffer para consumo de componentes.

 */

package com.uax.campanaGauss;

public class LineaDeEnsamblaje implements Runnable {
    private Buffer buffer;

    public LineaDeEnsamblaje(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Componente componente = buffer.take();
                // Procesar componente...
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // el ensamblaje es una pausa en el proceso que podría ser cualquier otro tipo de operación
    private void assembleMachine(Componente componente) throws InterruptedException {
        System.out.println("Ensamblando " + componente);
        Thread.sleep(250);
    }
}
