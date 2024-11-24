package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import estructuras.Pila;
import estructuras.Cola;

public class InventarioPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private Pila<String> pilaInventario;
    private Cola<String> colaPedidos;
    private final List<String> productosPredefinidos;
    private final List<String> pedidosPredefinidos;

    public InventarioPanel(MainGUI frame, Pila<String> pilaInventario, Cola<String> colaPedidos) {
        this.pilaInventario = pilaInventario;
        this.colaPedidos = colaPedidos;

        productosPredefinidos = Arrays.asList(
            "Coca-Cola 600ml", "Pepsi 500ml", "Agua Bonafont 1L", "Sabritas 200g", 
            "Chetos Torciditos", "Doritos Nachos", "Galletas Oreo", "Gansito", 
            "Chocolate Hershey's", "Red Bull", "Monster Energy", "Bimbo Pan Blanco", 
            "Café Nescafé", "Chicle Trident", "Helado Magnum"
        );

        pedidosPredefinidos = Arrays.asList(
            "Pedido Coca-Cola", "Pedido Sabritas", "Pedido Galletas", "Pedido Red Bull", 
            "Pedido Agua Bonafont", "Pedido Doritos", "Pedido Chetos", "Pedido Chocolate", 
            "Pedido Helado", "Pedido Monster", "Pedido Gansito", "Pedido Pan", 
            "Pedido Café", "Pedido Chicles", "Pedido Energía Extra"
        );

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Gestión de Inventario y Pedidos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 10, 10));

        JButton btnApilar = new JButton("Agregar a Inventario (Pila)");
        btnApilar.addActionListener(e -> apilarProducto());

        JButton btnDesapilar = new JButton("Retirar del Inventario (Pila)");
        btnDesapilar.addActionListener(e -> desapilarProducto());

        JButton btnEncolar = new JButton("Agregar Pedido (Cola)");
        btnEncolar.addActionListener(e -> encolarPedido());

        JButton btnDesencolar = new JButton("Procesar Pedido (Cola)");
        btnDesencolar.addActionListener(e -> desencolarPedido());

        panelBotones.add(btnApilar);
        panelBotones.add(btnDesapilar);
        panelBotones.add(btnEncolar);
        panelBotones.add(btnDesencolar);

        add(panelBotones, BorderLayout.CENTER);

        JButton btnVolver = new JButton("Volver al Menú Principal");
        btnVolver.addActionListener(e -> frame.mostrarPanel("MenuPrincipal"));
        add(btnVolver, BorderLayout.SOUTH);
    }

    private void apilarProducto() {
    	
        for (String producto : productosPredefinidos) {
            if (!pilaInventario.contiene(producto)) {
                pilaInventario.apilar(producto);
                JOptionPane.showMessageDialog(this, "Producto agregado al inventario: " + producto);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Todos los productos ya están en el inventario.");
    }

    private void desapilarProducto() {
        String producto = pilaInventario.desapilar();
        if (producto != null) {
            JOptionPane.showMessageDialog(this, "Producto retirado del inventario: " + producto);
        } else {
            JOptionPane.showMessageDialog(this, "El inventario está vacío.");
        }
    }

    private void encolarPedido() {

        for (String pedido : pedidosPredefinidos) {
            if (!colaPedidos.contiene(pedido)) {
                colaPedidos.encolar(pedido);
                JOptionPane.showMessageDialog(this, "Pedido agregado: " + pedido);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Todos los pedidos ya están en cola.");
    }

    private void desencolarPedido() {
        String pedido = colaPedidos.desencolar();
        if (pedido != null) {
            JOptionPane.showMessageDialog(this, "Pedido procesado: " + pedido);
        } else {
            JOptionPane.showMessageDialog(this, "No hay pedidos pendientes.");
        }
    }
}
