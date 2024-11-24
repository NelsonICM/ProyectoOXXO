package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import estructuras.GrafoKruskal;

public class KruskalPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private GrafoKruskal grafo;
    private int numSucursales; // Cantidad de sucursales (vértices) elegida por el usuario
    private ArrayList<GrafoKruskal.Arista> mst; // Aristas del Árbol de Expansión Mínima
    private Image sucursalIcon; // Icono de las sucursales (OXXO)

    public KruskalPanel(MainGUI frame) {
        this.mst = new ArrayList<>();

        // Cargar imagen de sucursal
        try {

        	sucursalIcon = ImageIO.read(getClass().getResourceAsStream("/oxxo_casita.png"));
        } catch (IOException | NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar la imagen de la casita. Asegúrese de que 'resources/oxxo_casita.png' exista.");
        }

        setLayout(new BorderLayout());

        // Panel de visualización del grafo
        GraphPanel graphPanel = new GraphPanel();

        // Botones de control
        JPanel panelBotones = new JPanel(new GridLayout(5, 1, 10, 10)); 

        JButton btnCrearGrafo = new JButton("Crear Grafo");
        btnCrearGrafo.addActionListener(e -> crearGrafo(graphPanel));

        JButton btnAgregarRuta = new JButton("Agregar Ruta");
        btnAgregarRuta.addActionListener(e -> agregarRuta(graphPanel));

        JButton btnCalcularKruskal = new JButton("Calcular Mejor Ruta");
        btnCalcularKruskal.addActionListener(e -> {
            mst = grafo.calcularKruskal();
            graphPanel.repaint();

            // Le muestra un mensaje al usuario con la mejor ruta y distancia cumulativa
            int distanciaTotal = 0;
            StringBuilder mensaje = new StringBuilder("Mejor ruta:\n");
            for (GrafoKruskal.Arista arista : mst) {
                mensaje.append("Sucursal ").append(arista.origen)
                        .append(" -> Sucursal ").append(arista.destino)
                        .append(" (Distancia: ").append(arista.peso).append(")\n");
                distanciaTotal += arista.peso;
            }
            mensaje.append("Distancia total acumulativa: ").append(distanciaTotal).append(" unidades.");
            JOptionPane.showMessageDialog(this, mensaje.toString());
        });

        JButton btnResetear = new JButton("Resetear Grafo");
        btnResetear.addActionListener(e -> {
            grafo = null;
            numSucursales = 0;
            mst.clear();
            graphPanel.repaint();
        });

        JButton btnVolverMenu = new JButton("Volver al Menú Principal");
        btnVolverMenu.addActionListener(e -> frame.mostrarPanel("MenuPrincipal"));

        panelBotones.add(btnCrearGrafo);
        panelBotones.add(btnAgregarRuta);
        panelBotones.add(btnCalcularKruskal);
        panelBotones.add(btnResetear);
        panelBotones.add(btnVolverMenu); 

        JLabel titulo = new JLabel("Gestión de Rutas (Grafos - Kruskal)", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        add(titulo, BorderLayout.NORTH);
        add(graphPanel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void crearGrafo(GraphPanel graphPanel) {
        String input = JOptionPane.showInputDialog("Ingrese el número de sucursales (máximo 15):");
        try {
            int n = Integer.parseInt(input);
            if (n > 0 && n <= 15) {
                numSucursales = n;
                grafo = new GrafoKruskal(numSucursales);
                JOptionPane.showMessageDialog(this, "Grafo creado con " + numSucursales + " sucursales.");
                graphPanel.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un número entre 1 y 15.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Entrada inválida. Por favor, ingrese un número.");
        }
    }

    private void agregarRuta(GraphPanel graphPanel) {
        if (grafo == null) {
            JOptionPane.showMessageDialog(this, "Primero debe crear el grafo.");
            return;
        }

        try {
            String origen = JOptionPane.showInputDialog("Ingrese el nodo de origen (0-" + (numSucursales - 1) + "):");
            String destino = JOptionPane.showInputDialog("Ingrese el nodo de destino (0-" + (numSucursales - 1) + "):");
            String peso = JOptionPane.showInputDialog("Ingrese la distancia entre los nodos:");

            int origenInt = Integer.parseInt(origen);
            int destinoInt = Integer.parseInt(destino);
            int pesoInt = Integer.parseInt(peso);

            if (origenInt < 0 || origenInt >= numSucursales || destinoInt < 0 || destinoInt >= numSucursales) {
                JOptionPane.showMessageDialog(this, "Los nodos deben estar entre 0 y " + (numSucursales - 1));
                return;
            }

            grafo.agregarArista(origenInt, destinoInt, pesoInt);
            JOptionPane.showMessageDialog(this, "Ruta agregada exitosamente: " + origenInt + " -> " + destinoInt + " (Peso: " + pesoInt + ")");
            graphPanel.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Entrada inválida. Por favor, use valores numéricos.");
        }
    }

    private class GraphPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            if (grafo == null) {
                g2.drawString("No hay grafo creado. Use 'Crear Grafo' para iniciar.", 20, 20);
                return;
            }

            int[][] nodos = calcularPosicionesNodos(numSucursales);

            // Dibujar aristas
            for (GrafoKruskal.Arista arista : grafo.obtenerAristas()) {
                int[] origen = nodos[arista.origen];
                int[] destino = nodos[arista.destino];

                if (mst.contains(arista)) {
                    g2.setColor(Color.GREEN); // MST
                } else {
                    g2.setColor(Color.BLACK);
                }

                g2.setStroke(new BasicStroke(2));
                g2.draw(new Line2D.Float(origen[0], origen[1], destino[0], destino[1]));
                g2.setColor(Color.BLUE);
                g2.drawString(String.valueOf(arista.peso), (origen[0] + destino[0]) / 2, (origen[1] + destino[1]) / 2);
            }

            // Dibujar nodos
            for (int i = 0; i < numSucursales; i++) {
                int[] coords = nodos[i];
                g2.drawImage(sucursalIcon, coords[0] - 25, coords[1] - 25, 50, 50, null);
                g2.setColor(Color.BLACK);
                g2.drawString(String.valueOf(i), coords[0], coords[1] - 30);
            }
        }

        private int[][] calcularPosicionesNodos(int n) {
            int[][] posiciones = new int[n][2];
            double angulo = 2 * Math.PI / n;
            int radio = 150;

            for (int i = 0; i < n; i++) {
                posiciones[i][0] = 250 + (int) (radio * Math.cos(i * angulo));
                posiciones[i][1] = 250 + (int) (radio * Math.sin(i * angulo));
            }

            return posiciones;
        }
    }
}
