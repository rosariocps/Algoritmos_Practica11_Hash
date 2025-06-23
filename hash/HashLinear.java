package hash;

/**
 * tabla hash cerrada (sondeo lineal) para enteros.
 */
public class HashLinear {
    private Integer[] table;  // arreglo que guarda los valores
    private int size;         // tamaño fijo de la tabla

    /** constructor: crea la tabla de tamaño dado */
    public HashLinear(int size) {
        this.size  = size;
        this.table = new Integer[size];
    }

    /** función hash: x % size */
    private int hash(int x) {
        return x % size;
    }

    /** inserta un valor usando sondeo lineal en caso de colisión */
    public void insert(int x) {
        int idx = hash(x);
        // mientras la celda esté ocupada, avanzo uno a la derecha
        while (table[idx] != null) {
            idx = (idx + 1) % size;
        }
        table[idx] = x;
    }

    /** imprime la tabla completa */
    public void printTable() {
        System.out.println("tabla hash (size = " + size + "):");
        for (int i = 0; i < size; i++) {
            String contenido = (table[i] == null ? "vacío" : table[i].toString());
            System.out.printf("[%d] -> %s\n", i, contenido);
        }
    }
}
