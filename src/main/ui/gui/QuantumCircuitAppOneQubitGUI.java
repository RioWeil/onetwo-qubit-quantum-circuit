package ui.gui;

import exceptions.WrongQubitNumberException;
import model.Complex;
import model.OneQubitQuantumCircuit;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Represents the GUI for a one qubit quantum circuit
public class QuantumCircuitAppOneQubitGUI extends QuantumCircuitAppGUI {

    // EFFECTS: Creates a new OneQubit Quantum Circuit GUI
    public QuantumCircuitAppOneQubitGUI(String initialState) {
        super(initialState, "One Qubit Quantum Circuit Simulation");
    }

    // REQUIRES: initialState is "|0>" or "|1>"
    // MODIFIES: this
    // EFFECTS: Initializes the One Qubit Quantum circuit with the specified pure 1-qubit state.
    @Override
    protected void initializeCircuit(String initialState) {
        if (initialState.equals("|0>")) {
            qubits = new OneQubitQuantumCircuit(new Complex(1, 0), new Complex(0, 0));
        } else {
            qubits = new OneQubitQuantumCircuit(new Complex(0, 0), new Complex(1, 0));
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates a combination box for adding one-qubit gates
    @Override
    protected void addChooseGatesBox() {
        final String[] oneQubitGates = {"Add _ gate to gate list...",
                "Pauli X", "Pauli Y", "Pauli Z", "S", "T", "Identity", "Hadamard"};
        final JComboBox addChooseGatesBox = new JComboBox(oneQubitGates);
        addChooseGatesBox.addActionListener(new ActionListener() {
            // MODIFIES: this
            // EFFECTS: When option in combobox is clicked, save the clicked gate to the list.
            public void actionPerformed(ActionEvent e) {
                String gate = (String) addChooseGatesBox.getSelectedItem();
                if (gate != null) {
                    addGateToGateList(gate);
                }
                gatedrawer.repaint();
            }
        });
        add(addChooseGatesBox);
    }


    // MODIFIES: this
    // EFFECTS: Adds gate to list based on user input from combobox, and update statusLabel to indicate response.
    private void addGateToGateList(String gate) {
        String addedGateMessage;
        if (gate.equals("Pauli X")) {
            addedGateMessage = qubits.addGate("X");
        } else if (gate.equals("Pauli Y")) {
            addedGateMessage = qubits.addGate("Y");
        } else if (gate.equals("Pauli Z")) {
            addedGateMessage = qubits.addGate("Z");
        } else if (gate.equals("S")) {
            addedGateMessage = qubits.addGate("S");
        } else if (gate.equals("T")) {
            addedGateMessage = qubits.addGate("T");
        } else if (gate.equals("Identity")) {
            addedGateMessage = qubits.addGate("I");
        } else if (gate.equals("Hadamard")) {
            addedGateMessage = qubits.addGate("H");
        } else {
            addedGateMessage = null;
        }
        if (addedGateMessage != null) {
            statusLabel.setText(addedGateMessage);
        }

    }

    // MODIFIES: this
    // EFFECTS: Adds a text field for loading in a 1-qubit state and gates from a JSON file.
    @Override
    protected void addLoadTextField() {
        JTextField loadStateTextField = new JTextField("Load qubit state from _.json");
        loadStateTextField.addActionListener(new ActionListener() {
            // EFFECTS: When press enter, (if exists) loads in 1-qubit state and gates from _.json where _ is textfield.
            public void actionPerformed(ActionEvent e) {
                String filename = loadStateTextField.getText();
                String abort = "Could not load qubit state from file.";
                try {
                    JsonReader reader = new JsonReader(relPath + filename + extension);
                    qubits = reader.readOne();
                    statusLabel.setText(filename + extension + " was loaded in successfully.");
                    String updatedQubitStateString = qubits.returnState();
                    qubitStateLabel.setText(updatedQubitStateString);
                    String updatedProbabilitiesString = qubits.returnProbabilities();
                    probabilitiesLabel.setText(updatedProbabilitiesString);
                    gatedrawer.setGates(qubits.getGateList());
                    gatedrawer.repaint();
                } catch (IOException ex) {
                    statusLabel.setText("File could not be found." + abort);
                } catch (WrongQubitNumberException ex) {
                    statusLabel.setText("File is for 2-qubit system." + abort);
                }
            }
        });
        add(loadStateTextField);
    }





}
