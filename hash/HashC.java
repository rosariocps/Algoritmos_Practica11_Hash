package hash;

/**
 * implementación de una tabla hash usando hash cerrado (sondeo lineal).
 */
public class HashC {

    // clase interna para representar una celda de la tabla
    private static class Element {
        private Register register;   // registro que guardo aquí (o null)
        private boolean isAvailable; // true = libre para insertar

        public Element() {
            this.register    = null;  // arranco sin nada
            this.isAvailable = true;  // arranco libre
        }
    }

    private Element[] table; // arreglo de celdas
    private int size;        // tamaño de la tabla (debe ser primo idealmente)

    /**
     * constructor: inicializa la tabla con 'size' celdas vacías.
     */
    public HashC(int size) {
        this.size  = size;
        this.table = new Element[size];
        for (int i = 0; i < size; i++) {
            table[i] = new Element();
        }
    }

    /**
     * función hash: mapea la clave a un índice [0..size-1].
     */
    private int hash(int key) {
        return key % size;
    }

    /**
     * inserta un nuevo registro con sondeo lineal.
     * si la tabla está llena, simplemente no inserta.
     */
    public void insert(Register reg) {
        int start = hash(reg.getKey());
        int idx   = start;
        do {
            Element e = table[idx];
            // si la celda está libre o borrada
            if (e.register == null || e.isAvailable) {
                e.register    = reg;      // guardo el registro
                e.isAvailable = false;    // marco como ocupado
                return;
            }
            idx = (idx + 1) % size;      // avanzo al siguiente slot
        } while (idx != start);
        // si vuelvo al inicio, la tabla está llena y no inserto
    }

    /**
     * busca un registro por su clave.
     * devuelve el Register o null si no está.
     */
    public Register search(int key) {
        int start = hash(key);
        int idx   = start;
        do {
            Element e = table[idx];
            // si encuentro el registro buscado
            if (e.register != null && !e.isAvailable
                && e.register.getKey() == key) {
                return e.register;
            }
            // si llego a una celda nunca usada, ya no existe
            if (e.register == null && e.isAvailable) {
                break;
            }
            idx = (idx + 1) % size;
        } while (idx != start);
        return null;
    }

    /**
     * eliminación lógica: marca la celda como disponible de nuevo.
     */
    public void delete(int key) {
        int start = hash(key);
        int idx   = start;
        do {
            Element e = table[idx];
            if (e.register != null && !e.isAvailable
                && e.register.getKey() == key) {
                e.register    = null;
                e.isAvailable = true;
                return;
            }
            if (e.register == null && e.isAvailable) {
                break;
            }
            idx = (idx + 1) % size;
        } while (idx != start);
    }

    /**
     * imprime el estado actual de la tabla.
     * para cada índice muestra: (key, name)  o '(vacío)' o '(borrado)'.
     */
    public void printTable() {
        for (int i = 0; i < size; i++) {
            Element e = table[i];
            System.out.printf("[%2d]: ", i);
            if (e.register != null && !e.isAvailable) {
                System.out.println(e.register);
            } else if (e.register == null && e.isAvailable) {
                System.out.println("(vacío)");
            } else {
                System.out.println("(borrado)");
            }
        }
    }
}
