package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import modelos.Sucursal;

public class SucursalesPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private ArrayList<Sucursal> sucursales;
    private DefaultListModel<String> modeloLista;
    private static final int LIMITE_SUCURSALES = 10; // Límite máximo de sucursales
    private static final List<Sucursal> SUCURSALES_PREDEFINIDAS = List.of(
        new Sucursal("001", "OXXO Reforma"),
        new Sucursal("002", "OXXO Insurgentes"),
        new Sucursal("003", "OXXO Polanco"),
        new Sucursal("004", "OXXO Roma Norte"),
        new Sucursal("005", "OXXO Condesa"),
        new Sucursal("006", "OXXO Chapultepec"),
        new Sucursal("007", "OXXO Centro Histórico"),
        new Sucursal("008", "OXXO Narvarte"),
        new Sucursal("009", "OXXO Satélite"),
        new Sucursal("010", "OXXO Lindavista")
    );
    public SucursalesPanel(MainGUI frame, ArrayList<Sucursal> sucursales) {
        this.sucursales = sucursales;

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Gestión de Sucursales", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        modeloLista = new DefaultListModel<>();
        actualizarLista();

        JList<String> lista = new JList<>(modeloLista);
        add(new JScrollPane(lista), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 10, 10));

        JButton btnAgregar = new JButton("Agregar Sucursal");
        btnAgregar.addActionListener(e -> agregarSucursal());

        JButton btnEliminar = new JButton("Eliminar Sucursal");
        btnEliminar.addActionListener(e -> eliminarSucursal(lista));

        JButton btnVolver = new JButton("Volver al Menú Principal");
        btnVolver.addActionListener(e -> frame.mostrarPanel("MenuPrincipal"));

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void actualizarLista() {
        modeloLista.clear();
        for (Sucursal sucursal : sucursales) {
            modeloLista.addElement(sucursal.getId() + " - " + sucursal.getNombre());
        }
    }

    private void agregarSucursal() {
        // Verificar si la lista está llena
        if (sucursales.size() >= LIMITE_SUCURSALES) {
            JOptionPane.showMessageDialog(this, "Ya has alcanzado el límite máximo de sucursales.");
            return;
        }

        for (Sucursal predefinida : SUCURSALES_PREDEFINIDAS) {
            boolean yaExiste = sucursales.stream()
                    .anyMatch(sucursal -> sucursal.getId().equals(predefinida.getId()));

            // Si no existe en la lista actual, agregarla
            if (!yaExiste) {
                sucursales.add(predefinida);
                actualizarLista();
                JOptionPane.showMessageDialog(this, "Sucursal agregada: " + predefinida.getNombre());
                return;
            }
        }

        // Mensaje en caso de que todas las sucursales ya existan (de nuevo.. esto no debería pasar si el límite es respetado)
        JOptionPane.showMessageDialog(this, "Todas las sucursales predefinidas ya están agregadas.");
    }

    private void eliminarSucursal(JList<String> lista) {
        int index = lista.getSelectedIndex();
        if (index >= 0) {
            Sucursal eliminada = sucursales.remove(index);
            actualizarLista();
            JOptionPane.showMessageDialog(this, "Sucursal eliminada: " + eliminada.getNombre());
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una sucursal para eliminar.");
        }
    }
}
