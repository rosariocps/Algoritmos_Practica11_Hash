package hash;

public class TestHash {
    public static void main(String[] args) {

        HashO tabla = new HashO(5); 
        // creo una tabla hash con tamaño 5 para que haya colisiones

        // insertar registros
        tabla.insert(new Register(10, "Jani"));    
        // 10 % 5 = 0, se guarda en el indice 0

        tabla.insert(new Register(15, "Rosi"));    
        // 15 % 5 = 0, tambien va al indice 0 (colision)

        tabla.insert(new Register(7, "Kahori"));    
        // 7 % 5 = 2, va al indice 2

        tabla.insert(new Register(12, "Marisol"));  
        // 12 % 5 = 2, colision con el anterior en el indice 2

        tabla.insert(new Register(22, "Mafer"));    
        // 22 % 5 = 2, otra colision en el mismo indice

        // mostrar la tabla
        System.out.println("Tabla hash con colisiones:"); 
        // imprimo un mensaje para identificar la tabla

        tabla.printTable(); 
        // llamo a la funcion que imprime toda la tabla

        // buscar un elemento
        Register encontrado = tabla.search(15); 
        // busco el registro que tiene clave 15

        System.out.println("Resultado de búsqueda para clave 15: " + (encontrado != null ? encontrado : "No encontrado"));
        // si lo encontro lo muestra, si no, dice "no encontrado"

        // eliminar un elemento
        tabla.delete(12); 
        // elimino el registro con clave 12

        System.out.println("\nTabla después de eliminar clave 12:");
        // muestro mensaje de que la tabla se va a volver a imprimir

        tabla.printTable(); 
        // vuelvo a imprimir la tabla para ver el cambio
    }
}
