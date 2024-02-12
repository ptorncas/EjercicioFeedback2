/*************************************
 Programación Concurrente
 Ejercicio Feedback 2
 Pablo Tornero Casas
 *************************************/
/*
Clase Buffer:

    - Implementación del buffer para guardar los componentes producidos.
    - Usa un mecanismo de sincronicazión para gestión de la concurrencia
    - La usa la clases EstaciónDeTrabajo para colocar el componente producido y la clase LineaDeEnsamblaje para consumir el componente guardado.
    - Es el "almacen" temporal de componentes controlando su distribución entre los hilos.

 */
package com.uax.campanaGauss;

class Buffer {
    private final Componente[] buffer;
    private int putPointer, takePointer, count;

    public Buffer(int capacity) {
        this.buffer = new Componente[capacity];
    }

    // los métodos siguientes son los que gestionan y almacenan los componentes para la concurrencia

    // put: agrega el componente al buffer, con synchronized aseguramos que solo un hilo lo pueda ejecutar a la vez
    public synchronized void put(Componente componente) throws InterruptedException {
        // verificación si está lleno el buffer, si lo está, deja al hilo en espera.
        while (count == buffer.length) {
            wait();
        }
        // se guarda el componente
        buffer[putPointer] = componente;
        // se coloca el índice en la siguiente posición (circular)
        putPointer = (putPointer + 1) % buffer.length;
        // suma un objeto mas al buffer para control de elementos.
        ++count;
        // revisión de condiciones de espera para los hilos.
        notifyAll();
    }

    // take: quita un componente del buffer, con synchronized aseguramos que un solo hilo ejecuta a la vez.
    public synchronized Componente take() throws InterruptedException {
        // verifica si el buffer está vacio, si lo está, deja al hilo en espera.
        while (count == 0) {
            wait();
        }
        // asigna el objeto que hay en el buffer a tipo componente
        Componente componente = buffer[takePointer];
        // coloca el índice en la siguiente posición (circular)
        takePointer = (takePointer + 1) % buffer.length;
        // resta un objeto del buffer
        --count;
        // revisión de condiciones de espera de los hilos.
        notifyAll();
        return componente;
    }
}
