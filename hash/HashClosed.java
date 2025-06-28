package hash;

/**
 * tabla hash cerrada de enteros con sondeo lineal y eliminación lógica.
 */
public class HashClosed {
    // clase interna para cada celda
    private static class Cell {
        Integer key;    // clave insertada, o null si está vacía o borrada
        boolean deleted;// true si aquí había algo y fue borrado(tombstone) - false si nunca se uso o esta ocupada
        //constructor que inicializa los datos
        Cell() {
            this.key     = null;
            this.deleted = false;
        }
    }

    private Cell[] table; // arreglo de celdas
    private int size;     // tamaño de la tabla

    // constructor que crea la tabla de tamaño dado
    public HashClosed(int size) {
        this.size  = size;
        this.table = new Cell[size];
        for (int i = 0; i < size; i++) {
            table[i] = new Cell(); //cada celda inicia vacía
        }
    }

    // calcula el bucket inicial como el resto de x / size, garantizando un índice entre 0 y size-1
    private int hash(int x) {
        return x % size;
    }

    // segunda función hash para doble hashing: devuelve un paso distinto de 0
    private int hash2(int x) {
        return 1 + (x % (size - 1)); // importante: evita que sea cero
    }

    /**
     * inserta una clave usando sondeo lineal.
     * aprovecha la primera celda borrada si la hay
     */
    public void insert(int x) {
        int start = hash(x), idx = start; // se calcula el índice inicial en start y idx como copia
        int firstDeleted = -1; // recordará la 1ra celda borrada (tombstone) si la hay
        // bucle para asegurar al menos una iteración
        do {
            Cell c = table[idx]; // c guarda celda en la posición actual idx 
            if (c.key == null) { // si la celda está vacía
                // y es un espacio nunca usado
                if (!c.deleted) {
                    // si antes ya habíamos visto un tombstone (firstDeleted >= 0), reusamos esa celda primeramente
                    // si no, usamos el espacio virgen actual (idx)
                    int target = firstDeleted >= 0 ? firstDeleted : idx;
                    //
                    table[target].key     = x;
                    table[target].deleted = false; // marca que ahora está ocupada
                    return; // para salir
                }
                // si llegamos aquí, significa que es un tombstone (c.key == null y c.deleted == true)
                // si firstDeleted no había registrado ningún tombstone entonces lo ponemos como el primero encontrado
                if (firstDeleted < 0) firstDeleted = idx;
            }
            idx = (idx + 1) % size; // avanzamos al siguiente índice de forma cíclica
        } while (idx != start); // repetimos hasta que idx vuelva a start, o sea hubiéramos recorrido toda la tabla

        // si el ciclo acabó y hubo una celda borrada
        if (firstDeleted >= 0) {
            table[firstDeleted].key     = x; // coloca la nueva clave x en esa casilla para reutilizarla
            table[firstDeleted].deleted = false; // cambia el marcador de tombstone a “ocupado”
        }
        // si no, la tabla está llena y descartamos la inserción
    }

    /**
     * inserta una clave usando sondeo cuadrático
     */
    public void insertQuadratic(int x) {
        int start = hash(x); // calculamos el índice base
        for (int i = 0; i < size; i++) {
            int idx = (start + i * i) % size; // desplazamiento cuadrático
            Cell c = table[idx]; // obtenemos la celda actual
            if (c.key == null || c.deleted) { // si está vacía o fue borrada (tombstone)
                c.key = x; // insertamos la clave
                c.deleted = false; // la marcamos como ocupada
                return;
            }
        }
        // si no se encuentra espacio, se descarta la inserción
    }

    /**
     * inserta una clave usando doble hashing
     */
    public void insertDoubleHash(int x) {
        int h1 = hash(x);   // primer hash
        int h2 = hash2(x);  // segundo hash (el paso)
        int idx = h1;
        // bucle para buscar una celda vacía o borrada
        for (int i = 0; i < size; i++) {
            idx = (h1 + i * h2) % size; // doble hashing con paso h2
            Cell c = table[idx];
            if (c.key == null || c.deleted) {
                c.key = x; // insertamos la clave
                c.deleted = false; // marcamos como ocupada
                return;
            }
        }
        // tabla llena, no se pudo insertar
    }

    /**
     * busca una clave; devuelve true si la encuentra,
     * false si llega a un espacio virgen (nunca usado).
     */
    public boolean search(int x) { // recibe clave x
        int start = hash(x), idx = start; // se calcula el índice inicial
        // bucle para asegurar al menos una iteración
        do {
            Cell c = table[idx]; // obtenemos celda actual
            if (c.key != null && !c.deleted && c.key == x) {
                return true; // la encontramos
            }
            if (c.key == null && !c.deleted) {
                return false; // espacio virgen, no existe
            }
            idx = (idx + 1) % size; // avanzamos al siguiente índice de forma cíclica
        } while (idx != start); // hasta dar una vuelta completa
        return false; // si no se encontró
    }

    /**
     * eliminación lógica: busca la clave y marca la celda como "deleted"
     */
    public void delete(int x) { // recibe clave x
        int start = hash(x), idx = start; // índice inicial
        // bucle para asegurar al menos una iteración
        do {
            Cell c = table[idx]; // celda actual
            if (c.key != null && !c.deleted && c.key == x) {
                c.key     = null;     // borramos el valor
                c.deleted = true;     // marcamos como tombstone
                return;
            }
            if (c.key == null && !c.deleted) {
                return; // espacio virgen, no existe más adelante
            }
            idx = (idx + 1) % size; // siguiente índice cíclico
        } while (idx != start); // hasta dar la vuelta
    }

    /** imprime estado de cada celda: valor, vacío o borrado */
    public void printTable() {
        System.out.println("tabla hash (size=" + size + "):");
        for (int i = 0; i < size; i++) {
            Cell c = table[i]; // obtenemos celda actual
            System.out.printf("[%d]: ", i);
            if (c.key != null && !c.deleted) {
                System.out.println(c.key); // clave válida
            } else if (c.key == null && !c.deleted) {
                System.out.println("(vacío)"); // celda nunca usada
            } else {
                System.out.println("(borrado)"); // tombstone
            }
        }
    }
}
