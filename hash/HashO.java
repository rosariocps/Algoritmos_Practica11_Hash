package hash;

import linkedlist.ListaEnlazada;
import linkedlist.Nodo;

/**
 * Clase que implementa una tabla hash con encadenamiento (listas enlazadas).
 * Es genérica: puede almacenar registros con cualquier tipo de dato asociado.
 */
public class HashO<E> {

    private ListaEnlazada<Register<E>>[] table;
    // arreglo de listas enlazadas para los buckets (cada posición puede tener varios registros)

    private int size; // tamaño de la tabla hash (cantidad de buckets)

    // constructor
    @SuppressWarnings("unchecked") // suprime la advertencia por uso de arreglo genérico
    public HashO(int size) {
        this.size = size;
        this.table = (ListaEnlazada<Register<E>>[]) new ListaEnlazada[size];
        for (int i = 0; i < size; i++) {
            table[i] = new ListaEnlazada<>(); // en cada posición inicializo una lista vacía
        }
    }

    // retorna el tamaño de la tabla
    public int getSize() {
        return size;
    }

    // retorna la lista en la posición indicada
    public ListaEnlazada<Register<E>> getBucket(int idx) {
        return table[idx];
    }

    // función hash simple (clave % tamaño de la tabla)
    private int hash(int key) {
        return key % size;
    }

    // insertar un registro usando hashing cerrado con encadenamiento
    public void insert(Register<E> reg) {
        int index = hash(reg.getKey()); // obtenemos índice aplicando la función hash
        table[index].insertLast(reg);   // insertamos al final de la lista en esa posición
    }

    // buscar un registro por su clave
    public Register<E> search(int key) {
        int index = hash(key); // obtenemos índice
        Nodo<Register<E>> current = table[index].getFirst(); // apuntamos al inicio de la lista
        while (current != null) { // recorremos la lista
            if (current.getData().getKey() == key) {
                return current.getData(); // si coincide, lo retornamos
            }
            current = current.getNext(); // avanzamos al siguiente nodo
        }
        return null; // si no lo encontramos
    }

    // eliminar un registro por su clave
    public void delete(int key) {
        int index = hash(key); // obtenemos índice
        Nodo<Register<E>> current = table[index].getFirst(); // apuntamos al inicio de la lista
        while (current != null) {
            if (current.getData().getKey() == key) {
                table[index].removeNodo(current.getData()); // eliminamos si coincide
                return;
            }
            current = current.getNext(); // seguimos recorriendo
        }
    }

    // imprimir el contenido de la tabla hash
    public void printTable() {
        for (int i = 0; i < size; i++) {
            System.out.println("Índice " + i + ":");
            table[i].recorrer(); // recorre e imprime los datos del bucket
            System.out.println(); // salto de línea
        }
    }
}
