package hash;

public class TestEntero {
    public static void main(String[] args) {

        HashEntero tabla = new HashEntero(7); 
        // creo una tabla hash de tamaño 7

        // insertar valores sin colisiones (aunque aqui todos colisionan)
        tabla.insert(3);    // 3 % 7 = 3, va al indice 3
        tabla.insert(10);   // 10 % 7 = 3, colision, va al mismo indice 3
        tabla.insert(17);   // 17 % 7 = 3, otra colision en el indice 3
        tabla.insert(24);   // 24 % 7 = 3, tambien va al indice 3

        // mostrar tabla hash
        System.out.println("Tabla hash final:"); 
        // imprimo un mensaje para saber que se mostrará la tabla

        tabla.printTable(); 
        // llamo a la funcion que imprime el contenido de la tabla
    }
}
