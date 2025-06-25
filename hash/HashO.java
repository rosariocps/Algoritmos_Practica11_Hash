package hash;

import linkedlist.ListaEnlazada;
import linkedlist.Nodo;

/**
 * Clase que implementa una tabla hash con encadenamiento (listas enlazadas).
 */
public class HashO<E> {

    private ListaEnlazada<Register<E>>[] table;
    // Arreglo de listas enlazadas de registros genéricos

    private int size;
    // Tamaño de la tabla hash

    // Constructor
    @SuppressWarnings("unchecked") // Suprime advertencia por arreglo genérico
    public HashO(int size) {
        this.size = size;
        this.table = (ListaEnlazada<Register<E>>[]) new ListaEnlazada[size];
        for (int i = 0; i < size; i++) {
            table[i] = new ListaEnlazada<>();
        }
    }

    // Getter del tamaño de la tabla
    public int getSize() {
        return size;
    }

    // Getter de bucket (cada posición del arreglo)
    public ListaEnlazada<Register<E>> getBucket(int idx) {
        return table[idx];
    }

    // Función hash (clave % tamaño)
    private int hash(int key) {
        return key % size;
    }

    // Insertar un registro
    public void insert(Register<E> reg) {
        int index = hash(reg.getKey());
        table[index].insertLast(reg);
    }

    // Buscar un registro por clave
    public Register<E> search(int key) {
        int index = hash(key);
        Nodo<Register<E>> current = table[index].getFirst();
        while (current != null) {
            if (current.getData().getKey() == key) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    // Eliminar un registro por clave
    public void delete(int key) {
        int index = hash(key);
        Nodo<Register<E>> current = table[index].getFirst();
        while (current != null) {
            if (current.getData().getKey() == key) {
                table[index].removeNodo(current.getData());
                return;
            }
            current = current.getNext();
        }
    }

    // Imprimir la tabla hash
    public void printTable() {
        for (int i = 0; i < size; i++) {
            System.out.println("Índice " + i + ":");
            table[i].recorrer(); // Debe imprimir todos los elementos del bucket
            System.out.println();
        }
    }
}
