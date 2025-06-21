package hash;

public class TestBuscarHash {
    public static void main(String[] args) {
        HashO tabla = new HashO(5); // Tamaño 5

        // Insertar elementos
        tabla.insert(new Register(10, "Juan"));
        tabla.insert(new Register(15, "Ana"));
        tabla.insert(new Register(20, "Luis"));
        tabla.insert(new Register(25, "Rosa"));

        // Mostrar tabla
        System.out.println("Tabla Hash:");
        tabla.printTable();

        // Buscar clave 20
        Register resultado = tabla.search(20);
        if (resultado != null) {
            System.out.println("\nNombre asociado a la clave 20: " + resultado.getName());
        } else {
            System.out.println("\nClave 20 no encontrada.");
        }

        // Buscar clave 30
        Register resultado2 = tabla.search(30);
        if (resultado2 != null) {
            System.out.println("Nombre asociado a la clave 30: " + resultado2.getName());
        } else {
            System.out.println("Clave 30 no encontrada en la tabla.");
        }
    }
}
