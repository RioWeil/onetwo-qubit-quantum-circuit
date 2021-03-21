package ui.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a graphical start menu for the Quantum Circuit App
public class QuantumCircuitAppStartMenuGUI extends JFrame {
    private String[] qubitPureStates = { "Start a 1-Qubit circuit with...", "|0>", "|1>",
            "Start a 2-Qubit circuit with...", "|00>", "|01>", "|10>", "|11>"};

    // EFFECTS: Creates a new start menu for the Quantum Circuit app.
    public QuantumCircuitAppStartMenuGUI() {
        super("Quantum Circuit Simulation Start Menu");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel welcomeLabel = new JLabel("Welcome! Choose a circuit type and starting state.", (int) CENTER_ALIGNMENT);
        add(welcomeLabel, BorderLayout.NORTH);

        setQubitImage();
        setQubitCombo();

        pack();
        setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: Creates and adds an image of a qubit to the menu screen.
    private void setQubitImage() {
        JPanel imagePanel = new JPanel();
        add(imagePanel, BorderLayout.CENTER);
        String sep = System.getProperty("file.separator");
        ImageIcon qubitImage;
        qubitImage = new ImageIcon(System.getProperty("user.dir") + sep + "data" + sep + "images" + sep + "qubit.png");
        JLabel qubitImageAsLabel = new JLabel(qubitImage);
        imagePanel.add(qubitImageAsLabel);
    }

    // MODIFIES: this
    // EFFECTS: Creates and Adds a combo box to select the initial state of the quantum circuit.
    private void setQubitCombo() {
        final JComboBox qubitCombo = new JComboBox(qubitPureStates);
        qubitCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String startChoice = (String) qubitCombo.getSelectedItem();
                startCircuit(startChoice);
            }
        });
        add(qubitCombo, BorderLayout.SOUTH);
    }

    // EFFECTS: Starts a new QuantumCircuitApp depending on the choice made.
    public void startCircuit(String startChoice) {
        if (startChoice.length() == 3) { // "|0>" or "|1">
            new QuantumCircuitAppOneQubitGUI(startChoice);
        } else if (startChoice.length() == 4) { // "|00>" or "|01>" or "|10>" or "|11>"
            new QuantumCircuitAppTwoQubitGUI(startChoice);
        }
    }


}
