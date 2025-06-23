package hash;

/**
 * tabla hash cerrada de enteros con sondeo lineal y eliminación lógica.
 */
public class HashClosed {
    // clase interna para cada celda
    private static class Cell {
        Integer key;    // clave insertada, o null si está vacía o borrada
        boolean deleted;// true si aquí había algo y fue borrado

        Cell() {
            this.key     = null;
            this.deleted = false;
        }
    }

    private Cell[] table; // arreglo de celdas
    private int size;     // tamaño de la tabla

    /** constructor que crea la tabla de tamaño dado */
    public HashClosed(int size) {
        this.size  = size;
        this.table = new Cell[size];
        for (int i = 0; i < size; i++) {
            table[i] = new Cell();
        }
    }

    /** función hash básica: x % size */
    private int hash(int x) {
        return x % size;
    }

    /**
     * inserta una clave usando sondeo lineal.
     * aprovecha la primera celda borrada si la hay.
     */
    public void insert(int x) {
        int start = hash(x), idx = start;
        int firstDeleted = -1;

        do {
            Cell c = table[idx];
            if (c.key == null) {
                // si es espacio virgen, inserto aquí o en el primer "deleted"
                if (!c.deleted) {
                    int target = firstDeleted >= 0 ? firstDeleted : idx;
                    table[target].key     = x;
                    table[target].deleted = false;
                    return;
                }
                // si es "deleted", lo marco por si no encuentro espacio virgen
                if (firstDeleted < 0) firstDeleted = idx;
            }
            idx = (idx + 1) % size;
        } while (idx != start);

        // si el ciclo acabó y había una celda borrada, inserto allí
        if (firstDeleted >= 0) {
            table[firstDeleted].key     = x;
            table[firstDeleted].deleted = false;
        }
        // si no, la tabla está llena y descartamos la inserción
    }

    /**
     * busca una clave; devuelve true si la encuentra,
     * false si llega a un espacio virgen (nunca usado).
     */
    public boolean search(int x) {
        int start = hash(x), idx = start;

        do {
            Cell c = table[idx];
            if (c.key != null && !c.deleted && c.key == x) {
                return true;
            }
            if (c.key == null && !c.deleted) {
                // espacio virgen: la clave no está
                return false;
            }
            idx = (idx + 1) % size;
        } while (idx != start);

        return false;
    }

    /**
     * eliminación lógica: busca la clave y marca la celda como "deleted".
     */
    public void delete(int x) {
        int start = hash(x), idx = start;

        do {
            Cell c = table[idx];
            if (c.key != null && !c.deleted && c.key == x) {
                c.key     = null;
                c.deleted = true;
                return;
            }
            if (c.key == null && !c.deleted) {
                // si llegamos a virgin cell sin hallar, salimos
                return;
            }
            idx = (idx + 1) % size;
        } while (idx != start);
    }

    /** imprime estado de cada celda: valor, vacío o borrado */
    public void printTable() {
        System.out.println("tabla hash (size=" + size + "):");
        for (int i = 0; i < size; i++) {
            Cell c = table[i];
            System.out.printf("[%d]: ", i);
            if (c.key != null && !c.deleted) {
                System.out.println(c.key);
            } else if (c.key == null && !c.deleted) {
                System.out.println("(vacío)");
            } else {
                System.out.println("(borrado)");
            }
        }
    }
}
