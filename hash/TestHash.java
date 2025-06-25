package hash;

public class TestHash {
    public static void main(String[] args) {
        System.out.println("=== hash cerrado (sondeo lineal) ===");

        HashC<String> hc = new HashC<>(5); // tabla hash cerrada de tamaño 5

        hc.insert(new Register<>(10, "Jani"));
        hc.insert(new Register<>(15, "Rosi"));
        hc.insert(new Register<>(7, "Kahori"));
        hc.insert(new Register<>(12, "Marisol"));
        hc.insert(new Register<>(22, "Mafer"));

        System.out.println("tabla cerrada:");
        hc.printTable();

        System.out.println("\nbusqueda clave 15: " + hc.search(15));

        hc.delete(12);
        System.out.println("tabla cerrada despues de eliminar clave 12:");
        hc.printTable();

        System.out.println("\n=== hash abierto (encadenamiento) ===");

        HashO<String> ho = new HashO<>(5); // tabla hash abierta de tamaño 5

        ho.insert(new Register<>(10, "Jani"));
        ho.insert(new Register<>(15, "Rosi"));
        ho.insert(new Register<>(7, "Kahori"));
        ho.insert(new Register<>(12, "Marisol"));
        ho.insert(new Register<>(22, "Mafer"));

        System.out.println("tabla abierta:");
        ho.printTable();

        System.out.println("\nbusqueda clave 15: " + ho.search(15));

        ho.delete(12);
        System.out.println("tabla abierta despues de eliminar clave 12:");
        ho.printTable();
    }
}
