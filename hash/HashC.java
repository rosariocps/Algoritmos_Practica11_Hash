package hash;
// este archivo pertenece al paquete hash

public class HashC {

    // clase interna para representar una celda de la tabla hash
    public static class Element {
        Register register;   // registro guardado en la celda
        boolean isAvailable; // indica si la celda esta disponible

        // constructor de la celda
        public Element() {
            this.register = null;    // no hay registro al inicio
            this.isAvailable = true; // la celda queda libre
        }
    }

    private Element[] table; // arreglo de celdas de la tabla
    private int size;        // tamano de la tabla

    // constructor que inicializa la tabla con celdas vacias
    public HashC(int size) {
        this.size = size;            // guardo el tamano que me pasan
        this.table = new Element[size]; // creo el arreglo de celdas
        for (int i = 0; i < size; i++) {
            table[i] = new Element(); // inicializo cada celda
        }
    }

    // funcion hash que calcula el indice con modulo
    private int hash(int key) {
        return key % size; // aplico modulo para distribuir
    }

    // inserta un registro usando sondeo lineal
    public void insert(Register reg) {
        int start = hash(reg.getKey()); // indice inicial
        int idx = start;                // empiezo por ahi
        do {
            Element e = table[idx]; 
            // si la celda esta libre o disponible
            if (e.register == null || e.isAvailable) {
                e.register = reg;        // guardo el registro
                e.isAvailable = false;   // marco la celda como ocupada
                return;                  // termino la insercion
            }
            idx = (idx + 1) % size;      // paso a la siguiente celda
        } while (idx != start);         // repito hasta volver al inicio
        // si recorro toda la tabla y no hay lugar, no inserto nada
    }

    // retorna el tamano de la tabla
    public int getSize() {
        return size; // devuelvo el tamano
    }

    // retorna la celda en el indice dado
    public Element getElement(int idx) {
        return table[idx]; // devuelvo la celda
    }
}
