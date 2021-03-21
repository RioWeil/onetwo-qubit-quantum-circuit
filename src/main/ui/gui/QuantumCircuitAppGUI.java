package ui.gui;

import model.QuantumCircuit;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Represents a GUI for the Quantum Circuit Application
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

    // MODIFIES: this
    // EFFECTS: Initializes the Quantum circuit with the specified state.
    protected abstract void initializeCircuit(String initialState);

    // MODIFIES: this
    // EFFECTS: Adds labels to display application status and qubit state/measurement probabilities.
    private void addLabels() {
        addStatusLabel();
        addQubitStateLabel();
        addProbabilitiesLabel();
    }

    // MODIFIES: this
    // EFFECTS: Adds combobox for adding gates, and buttons for removing/applying gates and making a measurement.
    private void addButtons() {
        addChooseGatesBox();
        addRemoveGateButton();
        addApplyGateButton();
        addApplyAllGatesButton();
        addMeasureButton();
    }

    // MODIFIES: this
    // EFFECTS: Adds text fields for saving and loading the qubit(s) states.
    private void addSaveLoad() {
        addSaveTextField();
        addLoadTextField();
    }

    // MODIFIES: this
    // EFFECTS: Adds a label to display the current state of the application.
    public void addStatusLabel() {
        statusLabel = new JLabel("Welcome to the One Qubit Simulation!", (int) CENTER_ALIGNMENT);
        add(statusLabel);
    }

    // MODIFIES: this
    // EFFECTS: Adds a label to display the current state of the qubit(s).
    public void addQubitStateLabel() {
        String initialQubitStateString = qubits.returnState();
        qubitStateLabel = new JLabel(initialQubitStateString);
        add(qubitStateLabel);
    }

    // MODIFIES: this
    // EFFECTS: Adds a label to display the current measurement probabilities of the qubit(s).
    public void addProbabilitiesLabel() {
        String initialProbabilitiesString = qubits.returnProbabilities();
        probabilitiesLabel = new JLabel(initialProbabilitiesString);
        add(probabilitiesLabel);
    }

    // MODIFIES: this
    // EFFECTS: Creates a combination box to add quantum gates to the list.
    protected abstract void addChooseGatesBox();

    // MODIFIES: this
    // EFFECTS: Adds a button to remove the first quantum gate in the list.
    private void addRemoveGateButton() {
        JButton removeGateButton = new JButton("Remove first gate in gate list");
        removeGateButton.addActionListener(new ActionListener() {
            // MODFIES: this
            // EFFECTS: When button is clicked, remove first quantum gate from list.
            public void actionPerformed(ActionEvent e) {
                String removedGateMessage;
                removedGateMessage = qubits.removeGate();
                statusLabel.setText(removedGateMessage);
            }
        });
        add(removeGateButton);
    }

    // MODIFIES: this
    // EFFECTS: Adds a button to apply the first quantum gate in the list to the qubit(s).
    private void addApplyGateButton() {
        JButton applyGateButton = new JButton("Apply first gate in gate list to qubit(s)");
        applyGateButton.addActionListener(new ActionListener() {
            // MODIFIES: this
            // EFFECTS: When button is clicked, apply first quantum gate in list to qubit(s).
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

    // MODIFIES: this
    // EFFECTS: Adds a button to apply all quantum gates in the list to the qubit(s).
    private void addApplyAllGatesButton() {
        JButton applyAllGatesButton = new JButton("Apply all gates in list to qubit(s)");
        applyAllGatesButton.addActionListener(new ActionListener() {
            // MODIFIES: this
            // EFFECTS: When button is clicked, apply all quantum gates in list to qubit(s).
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

    // MODIFIES: this
    // EFFECTS: Adds a button to measure the qubit(s).
    private void addMeasureButton() {
        JButton measureButton = new JButton("Measure the qubit(s)");
        measureButton.addActionListener(new ActionListener() {
            // MODIFIES: this
            // EFFECTS: When button is clicked, measure the qubit(s).
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

    // MODIFIES: this
    // EFFECTS: Adds a text field for saving the qubit state and gates to a JSON file.
    private void addSaveTextField() {
        JTextField saveStateTextField = new JTextField("Save current qubit state as _.json");
        saveStateTextField.addActionListener(new ActionListener() {
            // EFFECTS: When enter is pressed, saves current state and gates to _.json where _ is text in textfield.
            public void actionPerformed(ActionEvent e) {
                String filename = saveStateTextField.getText();
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

    // MODIFIES: this
    // EFFECTS: Adds a text field for loading in a qubit state and gates from a JSON file.
    protected abstract void addLoadTextField();


}
