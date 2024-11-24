package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuPrincipalPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public MenuPrincipalPanel(MainGUI frame) {
        setLayout(new GridLayout(5, 1, 10, 10));

        JButton btnProductos = new JButton("Gestión de Productos");
        btnProductos.addActionListener(e -> frame.mostrarPanel("Productos"));

        JButton btnSucursales = new JButton("Gestión de Sucursales");
        btnSucursales.addActionListener(e -> frame.mostrarPanel("Sucursales"));

        JButton btnInventario = new JButton("Gestión de Inventario");
        btnInventario.addActionListener(e -> frame.mostrarPanel("Inventario"));

        // Botón para Árbol Binario
        JButton btnArbol = new JButton("Gestión de Árbol Binario");
        btnArbol.addActionListener(e -> frame.mostrarPanel("Arbol"));

        // Botón para Grafos (Kruskal)
        JButton btnGrafos = new JButton("Gestión de Grafos");
        btnGrafos.addActionListener(e -> frame.mostrarPanel("Grafo"));

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener((ActionEvent e) -> System.exit(0));

        add(btnProductos);
        add(btnSucursales);
        add(btnInventario);
        add(btnArbol);
        add(btnGrafos);
        add(btnSalir);
    }
}
