package hash;

import linkedlist.ListaEnlazada;
import linkedlist.Nodo;

/**
 * Clase que implementa una tabla hash con encadenamiento (listas enlazadas).
 */
public class HashO {
    private ListaEnlazada<Register>[] table;
    private int size;

    // Constructor
    public HashO(int size) {
        this.size = size;
        this.table = new ListaEnlazada[size];
        for (int i = 0; i < size; i++) {
            table[i] = new ListaEnlazada<>();
        }
    }

    // Función hash (clave % tamaño)
    private int hash(int key) {
        return key % size;
    }

    // Insertar un registro
    public void insert(Register reg) {
        int index = hash(reg.getKey());
        table[index].insertLast(reg);
    }

    // Buscar un registro por clave
    public Register search(int key) {
        int index = hash(key);
        Nodo<Register> current = table[index].getFirst();

        while (current != null) {
            if (current.getData().getKey() == key) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null; // Si no se encuentra
    }

    // Eliminar un registro por clave
    public void delete(int key) {
        int index = hash(key);
        Nodo<Register> current = table[index].getFirst();

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
            table[i].recorrer();
            System.out.println();
        }
    }
}
