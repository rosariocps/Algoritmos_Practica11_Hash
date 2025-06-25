// src/hash/gui/HashOGUI.java
package hash.gui;

import hash.HashO;
import hash.Register;
import java.awt.*;
import javax.swing.*;
import linkedlist.Nodo;

/**
 * Ventana que muestra, en una fila por cada índice,
 * el contenido de la tabla hash abierta (HashO<E>).
 */
public class HashOGUI extends JFrame {
    private final HashO<String> tabla;

    public HashOGUI(HashO<String> tabla) {
        super("Hash Abierto - Swing");
        this.tabla = tabla;
        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(tabla.getSize(), 1, 5, 5));

        // Para cada bucket, creamos un JList con sus elementos
        for (int i = 0; i < tabla.getSize(); i++) {
            DefaultListModel<String> modelo = new DefaultListModel<>();
            Nodo<Register<String>> nodo = tabla.getBucket(i).getFirst();

            while (nodo != null) {
                modelo.addElement(nodo.getData().toString());
                nodo = nodo.getNext();
            }

            if (modelo.isEmpty()) {
                modelo.addElement("(vacío)");
            }

            JList<String> lista = new JList<>(modelo);
            lista.setBorder(BorderFactory.createTitledBorder("Índice " + i));
            add(new JScrollPane(lista));
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método main para lanzar esta GUI
    public static void main(String[] args) {
        // Preparamos la tabla HashO con registros tipo <String>
        HashO<String> tabla = new HashO<>(5);
        tabla.insert(new Register<>(10, "Ana"));
        tabla.insert(new Register<>(15, "Luis"));
        tabla.insert(new Register<>(7, "Carlos"));
        tabla.insert(new Register<>(12, "Sofía"));
        tabla.insert(new Register<>(22, "Marta"));

        // Lo levantamos en el hilo de Swing
        SwingUtilities.invokeLater(() -> new HashOGUI(tabla));
    }
}
