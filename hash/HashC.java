package hash;

/**
 * Implementación de una tabla hash usando hash cerrado (sondeo lineal).
 */
public class HashC {
    
    //Clase interna para representar una celda de la tabla hash.
    private static class Element {
        Register register; // Registro guardado
        boolean isAvailable; // Indica si la celda está disponible

        public Element() {
            this.register = null;
            this.isAvailable = true;
        }
    }

    private Element[] table; // Arreglo de elementos (la tabla hash)
    private int size; // Tamaño de la tabla (se recomienda que sea un número primo)

    /**
     * Constructor que inicializa la tabla hash con el tamaño indicado.
     * Cada celda es creada como un Element vacío y disponible.
     */    
    public HashC(int size) {
        this.size = size;
        this.table = new Element[size];
        for (int i = 0; i < size; i++) {
            table[i] = new Element();
        }
    } 

}
