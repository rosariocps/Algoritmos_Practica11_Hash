package hash;

/**
 * prueba del ejercicio 4:
 * - tabla de tamaño 7
 * - insertar 5, 12, 19
 * - eliminar 12
 * - buscar 19 después
 * - mostrar paso a paso
 */
public class TestEliminarHashCerrado {
    public static void main(String[] args) {
        HashClosed t = new HashClosed(7);

        System.out.println("insertar 5, 12, 19:");
        t.insert(5);
        t.insert(12);
        t.insert(19);
        t.printTable();

        System.out.println("\neliminar 12:");
        t.delete(12);
        t.printTable();

        System.out.println("\nbuscar 19 tras eliminar 12:");
        boolean found = t.search(19);

        System.out.println(found
            ? "19 encontrado (busqueda continua tras tombstone)" //si found es true
            : "19 NO encontrado (hubiera fallado si fisicamente borrado)"); //si found es false
    }
}
