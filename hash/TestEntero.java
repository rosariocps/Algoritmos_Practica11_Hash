package hash;

public class TestEntero {
    public static void main(String[] args) {
        HashEntero tabla = new HashEntero(7); // Tamaño 7

        // Insertar valores sin colisiones
        tabla.insert(3);   // 3 % 7 = 3
        tabla.insert(10);  // 10 % 7 = 3
        tabla.insert(17);  // 17 % 7 = 3
        tabla.insert(24);  // 24 % 7 = 3

        // Mostrar tabla hash
        System.out.println("Tabla hash final:");
        tabla.printTable();
    }
}
