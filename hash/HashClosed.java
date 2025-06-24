package hash;

/**
 * tabla hash cerrada de enteros con sondeo lineal y eliminación lógica.
 */
public class HashClosed {
    // clase interna para cada celda
    private static class Cell {
        Integer key;    // clave insertada, o null si está vacía o borrada
        boolean deleted;// true si aquí había algo y fue borrado(tombstone) - false si nunca se uso o esta ocupada
        //constructor qinicializa los datos
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
            table[i] = new Cell(); //cada celda inicia vacia
        }
    }

    //calcula el bucket inicial como el resto de x / size, garantizando un indice entre 0 y size-1
    private int hash(int x) {
        return x % size;
    }

    /**
     * inserta una clave usando sondeo lineal.
     * aprovecha la primera celda borrada si la hay
     */
    public void insert(int x) {
        int start = hash(x), idx = start; //se calcula el indice incial en start y idx como copia
        int firstDeleted = -1; //recordara la 1ra celda borrada (tombstone) si la hay
        //bucle para asegurar al menos una iteracion
        do {
            Cell c = table[idx]; //c guarda cleda en la posicion actual idx 
            if (c.key == null) { //si la celda no esta vacia
                //y es un espacio nunca usado
                if (!c.deleted) {
                    // si antes ya habiamos visto un tombstone (firstDeleted >= 0), reusamos esa celda primeramente
                    // si no, usamos el espacio virgen actual (idx)
                    int target = firstDeleted >= 0 ? firstDeleted : idx;
                    //
                    table[target].key     = x;
                    table[target].deleted = false; //maraca que ahora esta ocupada
                    return; //para salir
                }
                //Si llegamos aqui, significa que es un tombstone (c.key == null y c.deleted == true)
                //Si firstDeleted no habia registrado ningun tombstone entonces lo ponemos como el primer encontardo
                if (firstDeleted < 0) firstDeleted = idx;
            }
            idx = (idx + 1) % size; //avanzamos al siguiente índice de forma cíclica
        } while (idx != start); //repetimos hasta que idx vuelva a start, osea hubieramos recorrido toda la tabla sin hallar un espacio virgen

        // si el ciclo acabo y hubo una celda borrada
        if (firstDeleted >= 0) {
            table[firstDeleted].key     = x; //coloca la nueva clave x en esa casilla para reutilizarla
            table[firstDeleted].deleted = false; //cambia el marcador de tombstone a “ocupado”
        }
        // si no, la tabla esta llena y descartamos la insercion
    }

    /**
     * busca una clave; devuelve true si la encuentra,
     * false si llega a un espacio virgen (nunca usado).
     */
    public boolean search(int x) { //recibe clave x
        int start = hash(x), idx = start; //se calcula el indice incial en start y idx como copia
        //bucle para asegurar al menos una iteracion
        do {
            Cell c = table[idx]; //c guarda celda en la posicion actual idx 
            //si la celda no esta vacia y no esta maracada como borrada (tombstone) y la clave coincide con la buscada
            if (c.key != null && !c.deleted && c.key == x) {
                return true; //retornamos true xq si la encontramos
            }
            //sino entonces
            //si no hay ninigun valor y nunca fue borrado espacio nunca usado
            if (c.key == null && !c.deleted) {
                return false; //ya no esta en ninguna posición futura,  la clave no esta
            }
            idx = (idx + 1) % size; //avanzamos al siguiente indice de manera ciclica
            //garantiza que al llegar al final del array, volvemos al incio
        } while (idx != start); //repetimos el bucle hasta que idx regrese a start

        return false; //si no lo hallamos, false
    }

    /**
     * eliminacion logica: busca la clave y marca la celda como "deleted"
     */
    public void delete(int x) { //recibe clave x
        int start = hash(x), idx = start; //se calcula el indice incial en start y idx como copia
        //bucle para asegurar al menos una iteracion
        do {
            Cell c = table[idx]; //c guarda celda en la posicion actual idx 
            //si la celda no esta vacia y no esta maracada como borrada (tombstone) y la clave coincide 
            if (c.key != null && !c.deleted && c.key == x) {
                c.key     = null; //borramos la clave
                c.deleted = true; //marca la celda como tombstone
                return;
            }
            //sino entonces
            //si no hay ninigun valor y nunca fue borrado espacio nunca usado
            if (c.key == null && !c.deleted) {
                return; //salimos
            }
            idx = (idx + 1) % size; //avanzamos al siguiente indice de manera ciclica
            //garantiza que al llegar al final del array, volvemos al incio
        } while (idx != start); //repetimos el bucle hasta que idx regrese a start
    }

    /** imprime estado de cada celda: valor, vacío o borrado */
    public void printTable() {
        System.out.println("tabla hash (size=" + size + "):");
        //recorre cada indice i desde 0 hasta size − 1
        for (int i = 0; i < size; i++) {
            Cell c = table[i]; //Captura la celda Cell en la posición i del arreglo table
            //imprime el indice formateado entre corchetes, por ejemplo [0]:
            System.out.printf("[%d]: ", i);
            //Caso ocupado: si la celda tiene una clave y no esta marcada como borrada
            if (c.key != null && !c.deleted) {
                System.out.println(c.key); //imprime el valor de la clave
            //Caso celda nunca usada: si no hay clave y tampoco esta borrada 
            } else if (c.key == null && !c.deleted) {
                System.out.println("(vacío)"); //imprime (vacio)
            //Caso tombstone: indica un espacio que fue ocupado y luego borrado
            } else {
                System.out.println("(borrado)"); //imprime (borrado)
            }
        }
    }
}
