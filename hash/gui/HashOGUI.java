// src/hash/gui/HashOGUI.java
package hash.gui;

import hash.HashO;
import hash.Register;
import javax.swing.*;
import java.awt.*;
import linkedlist.Nodo;

/**
 * ventana que muestra, en una fila por cada índice,
 * el contenido de tu tabla hash abierta (HashO).
 */
public class HashOGUI extends JFrame {
    private final HashO tabla;

    public HashOGUI(HashO tabla) {
        super("Hash Abierto - Swing");
        this.tabla = tabla;
        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(tabla.getSize(), 1, 5, 5));

        // para cada bucket, creamos un JList con sus elementos
        for (int i = 0; i < tabla.getSize(); i++) {
            DefaultListModel<String> modelo = new DefaultListModel<>();
            Nodo<Register> nodo = tabla.getBucket(i).getFirst();

            while (nodo != null) {
                modelo.addElement(nodo.getData().toString());
                nodo = nodo.getNext();
            }
            if (modelo.isEmpty()) {
                modelo.addElement("(vacío)");
            }

            JList<String> lista = new JList<>(modelo);
            lista.setBorder(BorderFactory.createTitledBorder("índice " + i));
            add(new JScrollPane(lista));
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // método main para lanzar esta GUI
    public static void main(String[] args) {
        // preparas tu HashO con algunos registros
        HashO tabla = new HashO(5);
        tabla.insert(new Register(10, "Ana"));
        tabla.insert(new Register(15, "Luis"));
        tabla.insert(new Register(7, "Carlos"));
        tabla.insert(new Register(12, "Sofía"));
        tabla.insert(new Register(22, "Marta"));

        // lo levantas en el hilo de Swing
        SwingUtilities.invokeLater(() -> new HashOGUI(tabla));
    }
}
