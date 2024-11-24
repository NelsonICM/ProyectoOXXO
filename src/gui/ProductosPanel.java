package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import modelos.Producto;

public class ProductosPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
    private DefaultTableModel modeloTabla;
    private ArrayList<Producto> productos;
    private Set<String> codigosExistentes; // Control para evitar duplicados
    private static final int LIMITE_PRODUCTOS = 20; // Límite máximo de productos
    private static final List<Producto> PRODUCTOS_PREDEFINIDOS = List.of(
        new Producto("001", "Coca-Cola 600ml", 18.00),
        new Producto("002", "Pepsi 500ml", 15.50),
        new Producto("003", "Sabritas 200g", 20.50),
        new Producto("004", "Galletas Oreo", 15.00),
        new Producto("005", "Agua Bonafont 1L", 12.00),
        new Producto("006", "Pan Integral", 30.00),
        new Producto("007", "Chicles Orbit", 10.50),
        new Producto("008", "Chocolate Snickers", 25.00),
        new Producto("009", "Leche Lala 1L", 22.00),
        new Producto("010", "Cereal Zucaritas", 40.00),
        new Producto("011", "Yogurt Danone", 12.50),
        new Producto("012", "Helado Häagen-Dazs", 80.00),
        new Producto("013", "Cerveza Corona 355ml", 18.00),
        new Producto("014", "Tequila José Cuervo", 250.00),
        new Producto("015", "Refresco Sprite 2L", 30.00),
        new Producto("016", "Galletas Marías", 18.00),
        new Producto("017", "Aceite Nutrioli 1L", 45.00),
        new Producto("018", "Sopa Maruchan", 12.00),
        new Producto("019", "Azúcar Estándar 2kg", 35.00),
        new Producto("020", "Café Soluble Nescafé", 95.00)
    );
    public ProductosPanel(MainGUI frame, ArrayList<Producto> productos) {
        this.productos = productos;
        this.codigosExistentes = new HashSet<>(); // Control de códigos únicos

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Gestión de Productos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new String[]{"Código", "Nombre", "Precio"}, 0);

        JTable tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(3, 2, 10, 10));

        JButton btnAgregar = new JButton("Agregar Producto");
        btnAgregar.addActionListener(e -> agregarProducto());

        JButton btnOrdenar = new JButton("Ordenar por Precio (QuickSort)");
        btnOrdenar.addActionListener(e -> ordenarProductos());

        JButton btnBuscar = new JButton("Búsqueda Binaria");
        btnBuscar.addActionListener(e -> buscarProducto());

        JButton btnEliminar = new JButton("Eliminar Producto");
        btnEliminar.addActionListener(e -> eliminarProducto(tabla));

        JButton btnVolver = new JButton("Volver al Menú Principal");
        btnVolver.addActionListener(e -> frame.mostrarPanel("MenuPrincipal"));

        panelBotones.add(btnAgregar);
        panelBotones.add(btnOrdenar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        actualizarTabla(); 
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0); // Limpia la tabla
        for (Producto p : productos) {
            modeloTabla.addRow(new Object[]{p.getCodigo(), p.getNombre(), String.format("%.2f", p.getPrecio())});
        }
    }

    private void agregarProducto() {
        // Verificar si la lista está llena
        if (productos.size() >= LIMITE_PRODUCTOS) {
            JOptionPane.showMessageDialog(this, "Ya has alcanzado el límite máximo de productos.");
            return;
        }

        for (Producto predefinido : PRODUCTOS_PREDEFINIDOS) {
            boolean yaExiste = productos.stream()
                    .anyMatch(producto -> producto.getCodigo().equals(predefinido.getCodigo()));

            // Si no existe en la lista actual, agregarlo
            if (!yaExiste) {
                productos.add(predefinido);
                modeloTabla.addRow(new Object[]{predefinido.getCodigo(), predefinido.getNombre(), predefinido.getPrecio()});
                JOptionPane.showMessageDialog(this, "Producto agregado: " + predefinido.getNombre());
                return;
            }
        }

        // Mensaje en caso de que todos los productos ya existan (esto no debería pasar si el límite es respetado)
        JOptionPane.showMessageDialog(this, "Todos los productos predefinidos ya están agregados.");
    }

    private void ordenarProductos() {
        productos.sort((p1, p2) -> Double.compare(p1.getPrecio(), p2.getPrecio()));
        actualizarTabla();
        JOptionPane.showMessageDialog(this, "Productos ordenados por precio.");
    }

    private void buscarProducto() {
        productos.sort((p1, p2) -> p1.getCodigo().compareTo(p2.getCodigo()));
        String codigo = JOptionPane.showInputDialog("Ingrese el código del producto a buscar:");
        int low = 0, high = productos.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Producto midProducto = productos.get(mid);

            if (midProducto.getCodigo().equals(codigo)) {
                JOptionPane.showMessageDialog(this, "Producto encontrado: " + midProducto);
                return;
            } else if (codigo.compareTo(midProducto.getCodigo()) < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        JOptionPane.showMessageDialog(this, "Producto no encontrado.");
    }

    private void eliminarProducto(JTable tabla) {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada >= 0) {
            Producto eliminado = productos.remove(filaSeleccionada);
            codigosExistentes.remove(eliminado.getCodigo());
            actualizarTabla();
            JOptionPane.showMessageDialog(this, "Producto eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar.");
        }
    }
}
