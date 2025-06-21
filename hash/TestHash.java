package hash;

public class TestHash {
    public static void main(String[] args) {
        HashO tabla = new HashO(5); // Tamaño reducido para generar colisiones

        // Insertar registros
        tabla.insert(new Register(10, "Jani"));   // 10 % 5 = 0
        tabla.insert(new Register(15, "Rosi"));  // 15 % 5 = 0
        tabla.insert(new Register(7, "Kahori")); // 7 % 5 = 2
        tabla.insert(new Register(12, "Marisol")); // 12 % 5 = 2
        tabla.insert(new Register(22, "Mafer")); // 22 % 5 = 2

        // Mostrar la tabla
        System.out.println("Tabla hash con colisiones:");
        tabla.printTable();

        // Buscar un elemento
        Register encontrado = tabla.search(15);
        System.out.println("Resultado de búsqueda para clave 15: " + (encontrado != null ? encontrado : "No encontrado"));

        // Eliminar un elemento
        tabla.delete(12);
        System.out.println("\nTabla después de eliminar clave 12:");
        tabla.printTable();
    }
}
