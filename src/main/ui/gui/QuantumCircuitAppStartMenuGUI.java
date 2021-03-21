package ui.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a graphical start menu for the Quantum Circuit App
public class QuantumCircuitAppStartMenuGUI extends JFrame {
    private QuantumCircuitAppOneQubitGUI oneQubitGUI;
    private QuantumCircuitAppTwoQubitGUI twoQubitGUI;
    private JLabel welcomeLabel;
    private JPanel imagePanel;
    private ImageIcon qubitImage;
    private JLabel qubitImageAsLabel;
    private JPanel choicePanel;
    private String[] qubitPureStates = { "Start a 1-Qubit circuit with...", "|0>", "|1>",
            "Start a 2-Qubit circuit with...", "|00>", "|01>", "|10>", "|11>"};

    // EFFECTS: Creates a new start menu for the Quantum Circuit app.
    public QuantumCircuitAppStartMenuGUI() {
        super("Quantum Circuit Simulation UI");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        welcomeLabel = new JLabel("Welcome! Choose a circuit type and starting state.", (int) CENTER_ALIGNMENT);
        add(welcomeLabel, BorderLayout.NORTH);

        setQubitImage();
        setQubitCombo();

        pack();
        setVisible(true);

    }

    private void setQubitImage() {
        imagePanel = new JPanel();
        add(imagePanel, BorderLayout.CENTER);
        String sep = System.getProperty("file.separator");
        qubitImage = new ImageIcon(System.getProperty("user.dir") + sep + "data" + sep + "images" + sep + "qubit.png");
        qubitImageAsLabel = new JLabel(qubitImage);
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
        if (startChoice.equals("|0>")) {
            oneQubitGUI = new QuantumCircuitAppOneQubitGUI();
        } else if (startChoice.equals("|1>")) {
            oneQubitGUI = new QuantumCircuitAppOneQubitGUI();
        } else if (startChoice.equals("|00>")) {
            twoQubitGUI = new QuantumCircuitAppTwoQubitGUI();
        } else if (startChoice.equals("|01>")) {
            twoQubitGUI = new QuantumCircuitAppTwoQubitGUI();
        } else if (startChoice.equals("|10>")) {
            twoQubitGUI = new QuantumCircuitAppTwoQubitGUI();
        } else if (startChoice.equals("|11>")) {
            twoQubitGUI = new QuantumCircuitAppTwoQubitGUI();
        }
    }

    public static void main(String[] args) {
        new QuantumCircuitAppStartMenuGUI();
    }


}
