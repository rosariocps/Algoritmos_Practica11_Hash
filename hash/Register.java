package hash;

/**
 * Clase que representa un registro con una clave y un nombre.
 * El estudiante no necesita modificar esta clase.
 */
public class Register<E> {
    private int key; // Clave que se usará como índice en la tabla hash
    private E name; // Nombre asociado al registro

    // Constructor para inicializar el registro con clave y nombre
    public Register(int key, E name) {
        this.key = key;
        this.name = name;
    }

    // Retorna la clave del registro
    public int getKey() {
        return key;
    }

    // Retorna el nombre del registro
    public E getName() {
        return name;
    }
    @Override
    // Representación en texto del objeto Register
    public String toString() {
        return "(" + key + ", " + name + ")";
    }
}
