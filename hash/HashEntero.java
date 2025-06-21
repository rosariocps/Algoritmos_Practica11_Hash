package hash;

import linkedlist.ListaEnlazada;

public class HashEntero {
    private ListaEnlazada<Integer>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashEntero(int size) {
        this.size = size;
        this.table = new ListaEnlazada[size];
        for (int i = 0; i < size; i++) {
            table[i] = new ListaEnlazada<>();
        }
    }

    private int hash(int value) {
        return value % size;
    }

    public void insert(int value) {
        int index = hash(value);
        table[index].insertLast(value);
    }

    public void printTable() {
        for (int i = 0; i < size; i++) {
            System.out.println("Índice " + i + ":");
            table[i].recorrer();
            System.out.println();
        }
    }
}
