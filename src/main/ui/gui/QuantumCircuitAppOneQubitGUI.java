package ui.gui;

import exceptions.WrongQubitNumberException;
import model.Complex;
import model.OneQubitQuantumCircuit;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class QuantumCircuitAppOneQubitGUI extends QuantumCircuitAppGUI {

    // EFFECTS: Creates a new OneQubit Quantum Circuit GUI
    public QuantumCircuitAppOneQubitGUI(String initialState) {
        super(initialState, "One Qubit Quantum Circuit Simulation");
    }

    // REQUIRES: initialState is "|0>" or "|1>"
    // EFFECTS: Initializes the One Qubit Quantum circuit with the specified original state
    @Override
    protected void initializeCircuit(String initialState) {
        if (initialState.equals("|0>")) {
            qubits = new OneQubitQuantumCircuit(new Complex(1, 0), new Complex(0, 0));
        } else {
            qubits = new OneQubitQuantumCircuit(new Complex(0, 0), new Complex(1, 0));
        }
    }

    @Override
    protected void addChooseGatesBox() {
        final String[] oneQubitGates = {"Add _ gate to gate list...",
                "Pauli X", "Pauli Y", "Pauli Z", "S", "T", "Identity", "Hadamard"};
        final JComboBox addChooseGatesBox = new JComboBox(oneQubitGates);
        addChooseGatesBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String gate = (String) addChooseGatesBox.getSelectedItem();
                addGateToGateList(gate);
            }
        });
        add(addChooseGatesBox);
    }


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

    @Override
    protected void addLoadTextField() {
        JTextField loadStateTextField = new JTextField("Load qubit state from _.json");
        loadStateTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filename = (String) loadStateTextField.getText();
                String abort = "Could not load qubit state from file.";
                try {
                    JsonReader reader = new JsonReader(relPath + filename + extension);
                    qubits = reader.readOne();
                    statusLabel.setText(filename + extension + " was loaded in successfully.");
                    String updatedQubitStateString = qubits.returnState();
                    qubitStateLabel.setText(updatedQubitStateString);
                    String updatedProbabilitiesString = qubits.returnProbabilities();
                    probabilitiesLabel.setText(updatedProbabilitiesString);
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
