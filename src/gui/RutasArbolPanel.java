package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import estructuras.NodoArbol;

public class RutasArbolPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private NodoArbol raizArbol;
    private ArrayList<NodoArbol> nodosPredefinidos; 
    private int indiceNodoActual; // Índice del nodo actual para controlar las inserciones

    public RutasArbolPanel(MainGUI frame, NodoArbol raizArbol) {
        this.raizArbol = raizArbol;
        this.nodosPredefinidos = new ArrayList<>();
        this.indiceNodoActual = 0;

        inicializarNodosPredefinidos();

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Gestión de Árbol Binario", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(2, 3, 10, 10));

        JButton btnCrearNodo = new JButton("Crear Nodo");
        btnCrearNodo.addActionListener(e -> crearNodo());

        JButton btnBuscarNodo = new JButton("Buscar Nodo");
        btnBuscarNodo.addActionListener(e -> buscarNodo());

        JButton btnEliminarNodo = new JButton("Eliminar Nodo");
        btnEliminarNodo.addActionListener(e -> eliminarNodo());

        JButton btnRecorridoPreorden = new JButton("Recorrido Preorden");
        btnRecorridoPreorden.addActionListener(e -> mostrarRecorrido("preorden"));

        JButton btnRecorridoInorden = new JButton("Recorrido Inorden");
        btnRecorridoInorden.addActionListener(e -> mostrarRecorrido("inorden"));

        JButton btnRecorridoPostorden = new JButton("Recorrido Postorden");
        btnRecorridoPostorden.addActionListener(e -> mostrarRecorrido("postorden"));

        panelBotones.add(btnCrearNodo);
        panelBotones.add(btnBuscarNodo);
        panelBotones.add(btnEliminarNodo);
        panelBotones.add(btnRecorridoPreorden);
        panelBotones.add(btnRecorridoInorden);
        panelBotones.add(btnRecorridoPostorden);

        add(panelBotones, BorderLayout.CENTER);

        JButton btnVolver = new JButton("Volver al Menú Principal");
        btnVolver.addActionListener(e -> frame.mostrarPanel("MenuPrincipal"));
        add(btnVolver, BorderLayout.SOUTH);
    }

    private void inicializarNodosPredefinidos() {
        // Agregar nodos predefinidos en relación al tema de OXXO
        nodosPredefinidos.add(new NodoArbol("001", "Sucursal Reforma"));
        nodosPredefinidos.add(new NodoArbol("002", "Sucursal Insurgentes"));
        nodosPredefinidos.add(new NodoArbol("003", "Sucursal Polanco"));
        nodosPredefinidos.add(new NodoArbol("004", "Sucursal Roma Norte"));
        nodosPredefinidos.add(new NodoArbol("005", "Sucursal Condesa"));
        nodosPredefinidos.add(new NodoArbol("006", "Sucursal Coyoacán"));
        nodosPredefinidos.add(new NodoArbol("007", "Sucursal Satélite"));
        nodosPredefinidos.add(new NodoArbol("008", "Sucursal Tlalpan"));
        nodosPredefinidos.add(new NodoArbol("009", "Sucursal Narvarte"));
        nodosPredefinidos.add(new NodoArbol("010", "Sucursal Centro Histórico"));
        nodosPredefinidos.add(new NodoArbol("011", "Sucursal La Viga"));
        nodosPredefinidos.add(new NodoArbol("012", "Sucursal Xochimilco"));
        nodosPredefinidos.add(new NodoArbol("013", "Sucursal Perisur"));
        nodosPredefinidos.add(new NodoArbol("014", "Sucursal Pedregal"));
        nodosPredefinidos.add(new NodoArbol("015", "Sucursal Lindavista"));
    }

    private void crearNodo() {
        if (indiceNodoActual == 0) {
            // Balancear nodos antes de la primera inserción
            nodosPredefinidos = balancearNodos(nodosPredefinidos);
        }

        if (indiceNodoActual < nodosPredefinidos.size()) {
            NodoArbol nodo = nodosPredefinidos.get(indiceNodoActual);

            if (NodoArbol.buscar(raizArbol, nodo.getId()) != null) {
                JOptionPane.showMessageDialog(this, "El nodo ya existe: " + nodo.getId() + " - " + nodo.getNombre());
            } else {
                raizArbol = NodoArbol.insertar(raizArbol, nodo.getId(), nodo.getNombre());
                JOptionPane.showMessageDialog(this, "Nodo creado: " + nodo.getId() + " - " + nodo.getNombre());
                indiceNodoActual++;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Todos los nodos predefinidos han sido agregados.");
        }
    }

    private ArrayList<NodoArbol> balancearNodos(ArrayList<NodoArbol> nodos) {
        return balancearNodosAux(nodos, 0, nodos.size() - 1);
    }

    private ArrayList<NodoArbol> balancearNodosAux(ArrayList<NodoArbol> nodos, int inicio, int fin) {
        if (inicio > fin) {
            return new ArrayList<>();
        }

        int medio = (inicio + fin) / 2;
        ArrayList<NodoArbol> balanceados = new ArrayList<>();

        // Agregar el nodo del medio
        balanceados.add(nodos.get(medio));

        // Balancear la izquierda y la derecha recursivamente
        balanceados.addAll(balancearNodosAux(nodos, inicio, medio - 1));
        balanceados.addAll(balancearNodosAux(nodos, medio + 1, fin));

        return balanceados;
    }

    private void buscarNodo() {
        String id = JOptionPane.showInputDialog("Ingrese el ID del nodo a buscar:");
        if (id != null) {
            NodoArbol encontrado = NodoArbol.buscar(raizArbol, id);
            if (encontrado != null) {
                JOptionPane.showMessageDialog(this, "Nodo encontrado: " + encontrado.getId() + " - " + encontrado.getNombre());
            } else {
                JOptionPane.showMessageDialog(this, "Nodo no encontrado.");
            }
        }
    }

    private void eliminarNodo() {
        String id = JOptionPane.showInputDialog("Ingrese el ID del nodo a eliminar:");

        if (id != null) { // Si el usuario no canceló el diálogo
            NodoArbol nodoExistente = NodoArbol.buscar(raizArbol, id); // Verificar si existe

            if (nodoExistente == null) {
                // Nodo no encontrado
                JOptionPane.showMessageDialog(this, "Nodo con ID " + id + " no encontrado. No se realizó ninguna eliminación.");
            } else {
                // Nodo encontrado, proceder a eliminar
                raizArbol = NodoArbol.eliminar(raizArbol, id);
                JOptionPane.showMessageDialog(this, "Nodo eliminado: " + nodoExistente.getId() + " - " + nodoExistente.getNombre());
            }
        }
    }

    private void mostrarRecorrido(String tipo) {
        String recorrido = switch (tipo) {
            case "preorden" -> NodoArbol.recorridoPreorden(raizArbol);
            case "inorden" -> NodoArbol.recorridoInorden(raizArbol);
            case "postorden" -> NodoArbol.recorridoPostorden(raizArbol);
            default -> "";
        };
        JOptionPane.showMessageDialog(this, tipo.toUpperCase() + ": " + recorrido);
    }
}
