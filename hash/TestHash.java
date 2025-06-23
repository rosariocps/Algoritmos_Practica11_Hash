package hash;
// este archivo pertenece al paquete hash

public class TestHash {
    public static void main(String[] args) {
        System.out.println("=== hash cerrado (sondeo lineal) ===");
        // imprimo titulo para la prueba de hash cerrado

        HashC hc = new HashC(5);
        // creo una tabla hash cerrada de tamaño 5

        hc.insert(new Register(10, "Jani"));
        // inserto registro con clave 10 y nombre jani

        hc.insert(new Register(15, "Rosi"));
        // inserto registro con clave 15 y nombre rosi

        hc.insert(new Register(7, "Kahori"));
        // inserto registro con clave 7 y nombre kahori

        hc.insert(new Register(12, "Marisol"));
        // inserto registro con clave 12 y nombre marisol

        hc.insert(new Register(22, "Mafer"));
        // inserto registro con clave 22 y nombre mafer

        System.out.println("tabla cerrada:");
        // indico que voy a mostrar el estado de la tabla cerrada

        hc.printTable();
        // imprimo cada celda de la tabla cerrada

        System.out.println("\nbusqueda clave 15: " + hc.search(15));
        // busco el registro con clave 15 e imprimo el resultado

        hc.delete(12);
        // elimino de forma logica el registro con clave 12

        System.out.println("tabla cerrada despues de eliminar clave 12:");
        // indico que muestro la tabla cerrada tras la eliminacion

        hc.printTable();
        // imprimo la tabla cerrada despues de la eliminacion

        System.out.println("\n=== hash abierto (encadenamiento) ===");
        // imprimo titulo para la prueba de hash abierto

        HashO ho = new HashO(5);
        // creo una tabla hash abierta de tamaño 5

        ho.insert(new Register(10, "Jani"));
        // inserto registro con clave 10 y nombre jani

        ho.insert(new Register(15, "Rosi"));
        // inserto registro con clave 15 y nombre rosi

        ho.insert(new Register(7, "Kahori"));
        // inserto registro con clave 7 y nombre kahori

        ho.insert(new Register(12, "Marisol"));
        // inserto registro con clave 12 y nombre marisol

        ho.insert(new Register(22, "Mafer"));
        // inserto registro con clave 22 y nombre mafer

        System.out.println("tabla abierta:");
        // indico que voy a mostrar la tabla abierta

        ho.printTable();
        // imprimo cada lista de la tabla abierta

        System.out.println("\nbusqueda clave 15: " + ho.search(15));
        // busco el registro con clave 15 en la tabla abierta e imprimo

        ho.delete(12);
        // elimino el registro con clave 12 de la tabla abierta

        System.out.println("tabla abierta despues de eliminar clave 12:");
        // indico que muestro la tabla abierta tras la eliminacion

        ho.printTable();
        // imprimo la tabla abierta despues de la eliminacion
    }
}
