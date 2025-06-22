package hash;


public class TestBuscarHash {
    public static void main(String[] args) {

        HashO tabla = new HashO(5); 
        // creo una tabla hash con tamaño 5

        // insertar elementos
        tabla.insert(new Register(10, "Juan")); 
        // 10 % 5 = 0, se guarda en el indice 0

        tabla.insert(new Register(15, "Ana"));  
        // 15 % 5 = 0, colision, va al mismo indice 0

        tabla.insert(new Register(20, "Luis")); 
        // 20 % 5 = 0, otra colision en el indice 0

        tabla.insert(new Register(25, "Rosa")); 
        // 25 % 5 = 0, otra colision en el indice 0

        // mostrar tabla
        System.out.println("Tabla Hash:"); 
        // imprimo un mensaje indicando que se mostrara la tabla

        tabla.printTable(); 
        // llamo a la funcion para imprimir toda la tabla hash

        // buscar clave 20
        Register resultado = tabla.search(20); 
        // busco el registro que tenga clave 20

        if (resultado != null) {
            System.out.println("\nNombre asociado a la clave 20: " + resultado.getName()); 
            // si lo encuentra, imprimo su nombre
        } else {
            System.out.println("\nClave 20 no encontrada."); 
            // si no lo encuentra, muestro mensaje
        }

        // buscar clave 30
        Register resultado2 = tabla.search(30); 
        // busco el registro que tenga clave 30

        if (resultado2 != null) {
            System.out.println("Nombre asociado a la clave 30: " + resultado2.getName()); 
            // si lo encuentra, imprimo su nombre
        } else {
            System.out.println("Clave 30 no encontrada en la tabla."); 
            // si no lo encuentra, imprimo que no se encontro
        }
    }
}