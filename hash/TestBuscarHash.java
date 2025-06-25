package hash;

public class TestBuscarHash {
    public static void main(String[] args) {

        HashO<String> tabla = new HashO<>(5); 
        // creo una tabla hash abierta de tamaño 5 que guarda Register<String>

        // insertar elementos
        tabla.insert(new Register<>(10, "Juan"));  // 10 % 5 = 0
        tabla.insert(new Register<>(15, "Ana"));   // 15 % 5 = 0, colisión
        tabla.insert(new Register<>(20, "Luis"));  // 20 % 5 = 0, colisión
        tabla.insert(new Register<>(25, "Rosa"));  // 25 % 5 = 0, colisión

        // mostrar tabla
        System.out.println("Tabla Hash:");
        tabla.printTable();

        // buscar clave 20
        Register<String> resultado = tabla.search(20); 

        if (resultado != null) {
            System.out.println("\nNombre asociado a la clave 20: " + resultado.getName());
        } else {
            System.out.println("\nClave 20 no encontrada.");
        }

        // buscar clave 30
        Register<String> resultado2 = tabla.search(30); 

        if (resultado2 != null) {
            System.out.println("Nombre asociado a la clave 30: " + resultado2.getName());
        } else {
            System.out.println("Clave 30 no encontrada en la tabla.");
        }
    }
}
