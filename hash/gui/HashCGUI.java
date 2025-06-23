// src/hash/gui/HashCGUI.java
package hash.gui;

import hash.HashC;
import hash.Register;
import javax.swing.*;
import java.awt.*;

/**
 * ventana que muestra tu tabla hash cerrada (HashC)
 */
public class HashCGUI extends JFrame {
    private final HashC tabla;

    public HashCGUI(HashC tabla) {
        super("Hash Cerrado - Swing");
        this.tabla = tabla;
        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(tabla.getSize(), 1, 5, 5));

        for (int i = 0; i < tabla.getSize(); i++) {
            DefaultListModel<String> modelo = new DefaultListModel<>();
            HashC.Element celda = tabla.getElement(i);

            if (celda.register != null) {
                modelo.addElement(celda.register.toString());
            } else if (celda.isAvailable) {
                modelo.addElement("(vacío)");
            } else {
                modelo.addElement("(borrado)");
            }

            JList<String> lista = new JList<>(modelo);
            lista.setBorder(BorderFactory.createTitledBorder("índice " + i));
            add(new JScrollPane(lista));
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        // llenas tu HashC con algunos registros
        HashC tabla = new HashC(7);
        tabla.insert(new Register(10, "Juan"));
        tabla.insert(new Register(17, "Ana"));
        tabla.insert(new Register(24, "Luis"));
        tabla.insert(new Register(31, "Rosa"));

        SwingUtilities.invokeLater(() -> new HashCGUI(tabla));
    }
}
