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
    private int size; // Tamaño de la tabla

}
