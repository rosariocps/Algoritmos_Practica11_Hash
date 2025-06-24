package hash;

/**
 * tabla hash cerrada (sondeo lineal) para enteros.
 */
public class HashLinear {
    private Integer[] table;  // arreglo que guarda los valores
    private int size;         // tamaño fijo de la tabla

    //constructor: crea la tabla de tamaño dado
    public HashLinear(int size) {
        this.size  = size;
        this.table = new Integer[size];
    } 

    //función hash: x % size
    private int hash(int x) {
        return x % size; //garantiza un indice en el rango [0 .. size-1]
    }

    // inserta un valor usando sondeo lineal en caso de colision 
    public void insert(int x) {
        int idx = hash(x); //calcula el indice inicial con hash(x)
        // mientras la celda este ocupada, avanzo uno a la derecha
        while (table[idx] != null) {
            idx = (idx + 1) % size; //para moverse a la siguiente casilla y envolver al 0 tras el final
            //esto se repite hasta encontrar table[idx] == null una casilla libre
        }
        //cuando si encuentras una casilla vacia ahi inserta el valor
        table[idx] = x;
    }

    // imprime la tabla completa 
    public void printTable() {
        System.out.println("tabla hash (size = " + size + "):");
        //recorre todas las posiciones de 0 a size-1
        for (int i = 0; i < size; i++) {
            //para cada i, comprueba si table[i] es null
            String contenido = (table[i] == null ? "vacio" : table[i].toString());
            System.out.printf("[%d] -> %s\n", i, contenido); //formatea la salida como [índice] -> contenido
        }
    }
}
