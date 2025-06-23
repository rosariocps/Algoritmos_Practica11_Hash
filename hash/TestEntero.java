package hash;

public class TestEntero {
    public static void main(String[] args) {

        HashEntero tabla = new HashEntero(7); 
        // creo una tabla hash de tamaño 7

        // insertar valores sin colisiones (aunque aqui todos colisionan)
        tabla.insert(3);    // 3 % 7 = 3
        tabla.insert(4);   // 4 % 7 = 4
        tabla.insert(5);   // 5 % 7 = 5
        tabla.insert(6);   // 6 % 7 = 6

        // mostrar tabla hash
        System.out.println("Tabla hash final:"); 
        // imprimo un mensaje para saber que se mostrará la tabla

        tabla.printTable(); 
        // llamo a la funcion que imprime el contenido de la tabla
    }
}
