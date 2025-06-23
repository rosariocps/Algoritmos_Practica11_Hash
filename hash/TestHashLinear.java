package hash;

/**
 * prueba del ejercicio:
 * tamaño 6, h(x)=x%6, insertar 12,18,24,30 
 * mostrando la tabla paso a paso.
 */
public class TestHashLinear {
    public static void main(String[] args) {
        HashLinear tabla = new HashLinear(6);

        System.out.println("insertar 12:");
        tabla.insert(12);
        tabla.printTable();

        System.out.println("\ninsertar 18:");
        tabla.insert(18);
        tabla.printTable();

        System.out.println("\ninsertar 24:");
        tabla.insert(24);
        tabla.printTable();

        System.out.println("\ninsertar 30:");
        tabla.insert(30);
        tabla.printTable();

        System.out.println("\nexplicacion:");
        System.out.println("cuando x%6 da un indice ya ocupado (colision),");
        System.out.println("el algoritmo avanza linea por linea hasta encontrar");
        System.out.println("la primera celda libre y alli inserta el valor.");
    }
}
