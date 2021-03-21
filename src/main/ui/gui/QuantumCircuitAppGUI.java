package ui.gui;

import model.QuantumCircuit;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public abstract class QuantumCircuitAppGUI extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    protected QuantumCircuit qubits;
    protected JLabel statusLabel;
    protected JLabel qubitStateLabel;
    protected JLabel probabilitiesLabel;
    private GridLayout layout;
    final String relPath = "./data/";
    final String extension = ".json";
    protected final String[] oneQubitGates = {"Add _ gate to gate list...",
            "Pauli X", "Pauli Y", "Pauli Z", "S", "T", "Identity", "Hadamard"};

    // EFFECTS: Creates a new Quantum Circuit GUI
    public QuantumCircuitAppGUI(String initialState, String quantumCircuitType) {
        super(quantumCircuitType);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        layout = new GridLayout(0,1);
        this.setLayout(layout);
        initializeCircuit(initialState);
        addLabels();
        addButtons();
        addSaveLoad();
        pack();
        setVisible(true);
    }

    protected abstract void initializeCircuit(String initialState);

    private void addLabels() {
        addStatusLabel();
        addQubitStateLabel();
        addProbabilitiesLabel();
    }

    private void addButtons() {
        addChooseGatesBox();
        addRemoveGateButton();
        addApplyGateButton();
        addApplyAllGatesButton();
        addMeasureButton();
    }

    private void addSaveLoad() {
        addSaveTextField();
        addLoadTextField();
    }

    public void addStatusLabel() {
        statusLabel = new JLabel("Welcome to the One Qubit Simulation!", (int) CENTER_ALIGNMENT);
        add(statusLabel);
    }

    public void addQubitStateLabel() {
        String initialQubitStateString = qubits.returnState();
        qubitStateLabel = new JLabel(initialQubitStateString);
        add(qubitStateLabel);
    }

    public void addProbabilitiesLabel() {
        String initialProbabilitiesString = qubits.returnProbabilities();
        probabilitiesLabel = new JLabel(initialProbabilitiesString);
        add(probabilitiesLabel);
    }

    protected abstract void addChooseGatesBox();

    private void addRemoveGateButton() {
        JButton removeGateButton = new JButton("Remove first gate in gate list");
        removeGateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String removedGateMessage;
                removedGateMessage = qubits.removeGate();
                statusLabel.setText(removedGateMessage);
            }
        });
        add(removeGateButton);
    }

    private void addApplyGateButton() {
        JButton applyGateButton = new JButton("Apply first gate in gate list to qubit(s)");
        applyGateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String appliedGateMessage;
                String updatedQubitStateString;
                String updatedProbabilitiesString;
                appliedGateMessage = qubits.applyGate();
                statusLabel.setText(appliedGateMessage);
                updatedQubitStateString = qubits.returnState();
                qubitStateLabel.setText(updatedQubitStateString);
                updatedProbabilitiesString = qubits.returnProbabilities();
                probabilitiesLabel.setText(updatedProbabilitiesString);
            }
        });
        add(applyGateButton);
    }

    private void addApplyAllGatesButton() {
        JButton applyAllGatesButton = new JButton("Apply all gates in list to qubit(s)");
        applyAllGatesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String appliedAllGatesMessage;
                String updatedQubitStateString;
                String updatedProbabilitiesString;
                appliedAllGatesMessage = qubits.applyAllGates();
                statusLabel.setText(appliedAllGatesMessage);
                updatedQubitStateString = qubits.returnState();
                qubitStateLabel.setText(updatedQubitStateString);
                updatedProbabilitiesString = qubits.returnProbabilities();
                probabilitiesLabel.setText(updatedProbabilitiesString);
            }
        });
        add(applyAllGatesButton);

    }

    private void addMeasureButton() {
        JButton measureButton = new JButton("Measure the qubit(s)");
        measureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String measuredQubitMessage;
                String updatedQubitStateString;
                String updatedProbabilitiesString;
                measuredQubitMessage = qubits.makeMeasurement();
                statusLabel.setText(measuredQubitMessage);
                updatedQubitStateString = qubits.returnState();
                qubitStateLabel.setText(updatedQubitStateString);
                updatedProbabilitiesString = qubits.returnProbabilities();
                probabilitiesLabel.setText(updatedProbabilitiesString);
            }
        });
        add(measureButton);
    }

    private void addSaveTextField() {
        JTextField saveStateTextField = new JTextField("Save current qubit state as _.json");
        saveStateTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filename = (String) saveStateTextField.getText();
                try {
                    JsonWriter writer = new JsonWriter(relPath + filename + extension);
                    writer.open();
                    writer.write(qubits);
                    writer.close();
                    statusLabel.setText(filename + extension + " saved successfully.");
                } catch (IOException ex) {
                    statusLabel.setText("Invalid filename. Qubit state could not be saved.");
                }
            }
        });
        add(saveStateTextField);
    }

    protected abstract void addLoadTextField();


}
