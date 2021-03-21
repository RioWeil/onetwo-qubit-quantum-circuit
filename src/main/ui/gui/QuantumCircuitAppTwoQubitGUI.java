package ui.gui;

import model.TwoQubitQuantumCircuit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuantumCircuitAppTwoQubitGUI extends JFrame {
    private TwoQubitQuantumCircuit twoQubit;

    // EFFECTS: Creates a new OneQubit Quantum Circuit GUI
    public QuantumCircuitAppTwoQubitGUI(String initialState) {
        super("Two Qubit Quantum Circuit Simulation");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

}
