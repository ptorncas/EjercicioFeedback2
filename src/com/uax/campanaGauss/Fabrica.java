/*************************************
 Programación Concurrente
 Ejercicio Feedback 2
 Pablo Tornero Casas
 *************************************/
/*
Clase Fábrica:

    - Se ocupa del control del flujo de trabajo de la simulación.
    - Inicializa el entorno gráfico
    - Gestiona los hilos de trabajo (100)

 */
package com.uax.campanaGauss;

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Fabrica {
    public static void main(String[] args) {
        int numBolas = 100000;
        int numTandas = 30;

        int numLineasDeEnsamblaje = 10;
        Buffer buffer = new Buffer(100);

        Tablero tablero = new Tablero(numTandas);

        JFrame frame = new JFrame("Fábrica de Campanas de Gauss");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setContentPane(tablero);
        frame.setVisible(true);

        // número de hilos
        ExecutorService executorService = Executors.newFixedThreadPool(100+ numLineasDeEnsamblaje);

        for (int i = 0; i < numLineasDeEnsamblaje; i++) {
            executorService.execute(new LineaDeEnsamblaje(buffer));
        }

        // gestión del proceso de bolas concurrentes, con pausa de 5 milisegundos cada 1000 componentes (bolas) gestionadas
        for (int i = 0; i < numBolas; i++) {
            executorService.execute(new EstacionDeTrabajo(tablero, numTandas));
            if (i % 1000 == 0) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        executorService.shutdown();
    }
}