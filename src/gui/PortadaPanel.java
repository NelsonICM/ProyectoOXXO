package gui;

import javax.swing.*;
import java.awt.*;

public class PortadaPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public PortadaPanel(MainGUI frame) {
        setLayout(new BorderLayout());

        setBackground(Color.WHITE); 

        // Panel para organizar los textos
        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(Color.WHITE); 
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));

        // Título principal
        JLabel titulo = new JLabel("Proyecto Final", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        titulo.setAlignmentX(CENTER_ALIGNMENT);

        // Subtítulo
        JLabel subtitulo = new JLabel("Estructuras de Datos y Algoritmos", SwingConstants.CENTER);
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 24));
        subtitulo.setAlignmentX(CENTER_ALIGNMENT);

        panelCentro.add(Box.createVerticalStrut(50));
        panelCentro.add(titulo);
        panelCentro.add(Box.createVerticalStrut(20));
        panelCentro.add(subtitulo);

        // Información personal
        JLabel infoPersonal = new JLabel("<html><center>Nelson Caceres<br>Matrícula: 00354305<br>Profesor: Ing. Luis Abel Calcáneo Murillo</center></html>", SwingConstants.CENTER);
        infoPersonal.setFont(new Font("Arial", Font.PLAIN, 18));
        infoPersonal.setAlignmentX(CENTER_ALIGNMENT);
        panelCentro.add(Box.createVerticalStrut(50));
        panelCentro.add(infoPersonal);

        JButton btnComenzar = new JButton("EMPEZAR >");
        btnComenzar.setFont(new Font("Arial", Font.BOLD, 18));
        btnComenzar.setBackground(new Color(0, 122, 204)); 
        btnComenzar.setForeground(Color.WHITE);
        btnComenzar.setFocusPainted(false);
        btnComenzar.setAlignmentX(CENTER_ALIGNMENT);
        btnComenzar.addActionListener(e -> frame.mostrarPanel("MenuPrincipal"));
        panelCentro.add(Box.createVerticalStrut(50));
        panelCentro.add(btnComenzar);

        panelCentro.add(Box.createVerticalStrut(50));

        add(panelCentro, BorderLayout.CENTER);
    }
}
