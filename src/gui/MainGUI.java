package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import modelos.Producto;
import modelos.Sucursal;
import estructuras.Pila;
import estructuras.Cola;
import estructuras.NodoArbol;

public class MainGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private CardLayout layout;
    private JPanel panelPrincipal;

    // Datos globales
    private ArrayList<Producto> productos;  
    private ArrayList<Sucursal> sucursales;
    private Pila<String> pilaInventario;
    private Cola<String> colaPedidos;
    private NodoArbol raizArbol;

    public MainGUI() {
        setTitle("Sistema de Gestión OXXO");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crea panel principal
        layout = new CardLayout();
        panelPrincipal = new JPanel(layout);

        inicializarDatos();

        // Agrega paneles 
        panelPrincipal.add(new PortadaPanel(this), "Portada");
        panelPrincipal.add(new MenuPrincipalPanel(this), "MenuPrincipal");
        panelPrincipal.add(new ProductosPanel(this, productos), "Productos");
        panelPrincipal.add(new SucursalesPanel(this, sucursales), "Sucursales");
        panelPrincipal.add(new InventarioPanel(this, pilaInventario, colaPedidos), "Inventario");
        panelPrincipal.add(new RutasArbolPanel(this, raizArbol), "Arbol");
        panelPrincipal.add(new KruskalPanel(this), "Grafo"); 

        getContentPane().add(panelPrincipal);

        // Muestra la portada al inicio
        layout.show(panelPrincipal, "Portada");
    }

    private void inicializarDatos() {
        productos = new ArrayList<>(); 
        sucursales = new ArrayList<>(); 
        pilaInventario = new Pila<>();
        colaPedidos = new Cola<>();
        raizArbol = null; 
        
    }

    public void mostrarPanel(String nombrePanel) {
        layout.show(panelPrincipal, nombrePanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true));
    }
}
