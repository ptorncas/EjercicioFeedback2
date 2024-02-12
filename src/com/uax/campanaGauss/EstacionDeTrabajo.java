/*************************************
 Programación Concurrente
 Ejercicio Feedback 2
 Pablo Tornero Casas
 *************************************/
/*
Clase EstacionDeTrabajo:

    - Produce componentes
    - La usa la clase Fábrica en el ExecutorService que es la concurrencia de producción de componentes.
    - Genera la aletoriedad de la producción dentro de un rango.
    - Realiza la simulación de la variabilidad de la producción de componentes.
    - Actualiza el Tablero.

 */

package com.uax.campanaGauss;

import java.util.Random;
class EstacionDeTrabajo implements Runnable {
    private final Tablero tablero;
    private final int numTandas;
    private final Random rand;

    public EstacionDeTrabajo(Tablero tablero, int numTandas) {
        this.tablero = tablero;
        this.numTandas = numTandas;
        this.rand = new Random();
    }

    // genera los números aleatorios, define la lógica de trabajo.
    // parte fundamental del proceso.
    @Override
    public void run() {
        int position = numTandas / 2;
        for (int i = 0; i < numTandas; i++) {
            // genera un número aleatorio (0 ó 1) que se multiplica por 2 y se resta 1
            // le da la posición dentro de la gráfica o a la derecha o a la izquierda (-1 ó +1)
            position += rand.nextInt(2) * 2 - 1;
            // evitamos que se salga de nuestro rango entre 0 y numero de remesas (tandas) -1
            position = Math.max(0, Math.min(position, numTandas - 1));
            try {
                // el delay de tiempo es para que la visualización sea más aparente.
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // actualiza el tablero con un valor más
        tablero.incrementSlot(position);
    }
}