/*************************************
 Programación Concurrente
 Ejercicio Feedback 2
 Pablo Tornero Casas
 *************************************/
/*
Clase Tablero:

    - Es la representación visual del proceso (Swing)
    - Es usada por la clase Fábrica para mostrar el progreso de fabricación
    - También la usan las clases de EstaciónDeTrabajo y LineaDeEnsamblaje
    - Se encarga de tener un conteo de las remesas de bolas de producción y actualizar la visualización.

 */
package com.uax.campanaGauss;

import javax.swing.*;
import java.awt.*;

public class Tablero extends JPanel {
    private int[] tandas;
    private int cuenta;

    public Tablero(int numTandas) {
        tandas = new int[numTandas];
        cuenta = 1;
    }
    // cuando acaba la remesa (tanda) se pinta
    public void incrementSlot(int tanda) {
        tandas[tanda]++;
        cuenta = Math.max(cuenta, tandas[tanda]);
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (tandas == null) return;

        int width = getWidth();
        int height = getHeight();
        int slotWidth = width / tandas.length;

        g.setColor(Color.BLACK);
        for (int i = 0; i < tandas.length; i++) {
            int barHeight = (int) ((double) tandas[i] / cuenta * height);
            g.fillRect(i * slotWidth, height - barHeight, slotWidth, barHeight);
        }
    }
    public Componente take() {
        return null;
    }
}