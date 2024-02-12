/*************************************
 Programación Concurrente
 Ejercicio Feedback 2
 Pablo Tornero Casas
 *************************************/
/*
Clase Componente:

    - Definición de un componente de producción.
    - Lo usan las clases EstaciónDeTrabajo y Buffer para la creación y gestionar los componentes producidos y consumidos.

 */
package com.uax.campanaGauss;

class Componente {
    private final String type;
    private final int id;
    public Componente(String type, int id) {
        this.type = type;
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public int getId() {
        return id;
    }
}