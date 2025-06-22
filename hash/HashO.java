package hash;

import linkedlist.ListaEnlazada;
import linkedlist.Nodo;

/**
 * Clase que implementa una tabla hash con encadenamiento (listas enlazadas).
 */

 public class HashO {

    private ListaEnlazada<Register>[] table; 
    // creo un arreglo de listas enlazadas para guardar registros

    private int size; 
    // variable para guardar el tamaño de la tabla hash

    // constructor
    public HashO(int size) {
        this.size = size; 
        // guardo el tamaño recibido

        this.table = new ListaEnlazada[size]; 
        // creo el arreglo con ese tamaño

        for (int i = 0; i < size; i++) {
            table[i] = new ListaEnlazada<>(); 
            // en cada posicion del arreglo creo una lista vacia
        }
    }

    // funcion hash (clave % tamaño)
    private int hash(int key) {
        return key % size; 
        // calculo el indice usando modulo
    }

    // insertar un registro
    public void insert(Register reg) {
        int index = hash(reg.getKey()); 
        // obtengo el indice aplicando la funcion hash

        table[index].insertLast(reg); 
        // inserto el registro al final de la lista en ese indice
    }

    // buscar un registro por clave
    public Register search(int key) {
        int index = hash(key); 
        // calculo el indice con la clave

        Nodo<Register> current = table[index].getFirst(); 
        // obtengo el primer nodo de la lista en ese indice

        while (current != null) {
            if (current.getData().getKey() == key) {
                return current.getData(); 
                // si encuentro la clave, devuelvo ese registro
            }
            current = current.getNext(); 
            // si no es, paso al siguiente nodo
        }
        return null; 
        // si termine y no encontre, devuelvo null
    }

    // eliminar un registro por clave
    public void delete(int key) {
        int index = hash(key); 
        // calculo el indice usando la clave

        Nodo<Register> current = table[index].getFirst(); 
        // empiezo a recorrer la lista de ese indice

        while (current != null) {
            if (current.getData().getKey() == key) {
                table[index].removeNodo(current.getData()); 
                // si encuentro la clave, la elimino con removeNodo
                return;
            }
            current = current.getNext(); 
            // si no es, sigo recorriendo
        }
    }

    // imprimir la tabla hash
    public void printTable() {
        for (int i = 0; i < size; i++) {
            System.out.println("Índice " + i + ":"); 
            // muestro el numero de indice

            table[i].recorrer(); 
            // recorro e imprimo los elementos de la lista en ese indice

            System.out.println(); 
            // dejo un espacio entre indices
        }
    }
}