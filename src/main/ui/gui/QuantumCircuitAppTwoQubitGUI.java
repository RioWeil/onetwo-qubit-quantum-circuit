package ui.gui;

import exceptions.WrongQubitNumberException;
import model.Complex;
import model.TwoQubitQuantumCircuit;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuantumCircuitAppTwoQubitGUI extends QuantumCircuitAppGUI {

    // EFFECTS: Creates a new OneQubit Quantum Circuit GUI
    public QuantumCircuitAppTwoQubitGUI(String initialState) {
        super(initialState, "Two Qubit Quantum Circuit Simulation");

    }

    // REQUIRES: initialState is "|00>" or "|01>" or "|10>" or "|11>"
    // MODIFIES: this
    // EFFECTS: Initializes the Two Qubit Quantum circuit with the specified pure 2-qubit state.
    @Override
    protected void initializeCircuit(String initialState) {
        Complex zero = new Complex(0, 0);
        Complex one = new Complex(1, 0);
        switch (initialState) {
            case "|00>":
                qubits = new TwoQubitQuantumCircuit(one, zero, zero, zero);
                break;
            case "|01>":
                qubits = new TwoQubitQuantumCircuit(zero, one, zero, zero);
                break;
            case "|10>":
                qubits = new TwoQubitQuantumCircuit(zero, zero, one, zero);
                break;
            default:
                qubits = new TwoQubitQuantumCircuit(zero, zero, zero, one);
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates a combination box for adding one-qubit gates
    @Override
    protected void addChooseGatesBox() {
        Object[] twoQubitGates = getAllTwoQubitGates();
        final JComboBox addChooseGatesBox = new JComboBox(twoQubitGates);
        addChooseGatesBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String gates = (String) addChooseGatesBox.getSelectedItem();
                if (gates != null) {
                    addGatesToGateList(gates);
                }
                gatedrawer.repaint();
            }
        });
        add(addChooseGatesBox);
    }

    // EFFECTS: Produces Array of all possible two-qubit gates
    private Object[] getAllTwoQubitGates() {
        String[] oneQubitGateList = {"Pauli X", "Pauli Y", "Pauli Z", "S", "T", "Identity", "Hadamard"};
        List<String> allGates = new ArrayList<String>();
        allGates.add("Add _ gates to gate list...");
        allGates.add("CNOT");
        for (String gate1: oneQubitGateList) {
            for (String gate2: oneQubitGateList) {
                allGates.add(gate1 + " - " + gate2);
            }
        }
        Object[] allGatesArray = allGates.toArray();
        return allGates.toArray();
    }

    // MODIFIES: this
    // EFFECTS: Adds gate to list based on user input from combobox, and update statusLabel to indicate response.
    private void addGatesToGateList(String gates) {
        String addedGatesMessage;
        if (gates.equals("CNOT")) {
            addedGatesMessage = qubits.addGate("CN");
        } else if (gates.equals("Add _ gates to gate list...")) {
            addedGatesMessage = null;
        } else {
            String[] gateparts = gates.split(" - ");
            String gate1 = getGate(gateparts[0]);
            String gate2 = getGate(gateparts[1]);
            String gatesString = gate1 + gate2;
            addedGatesMessage = qubits.addGate(gatesString);
        }
        if (addedGatesMessage != null) {
            statusLabel.setText(addedGatesMessage);
        }
    }

    // EFFECTS: Produces one-letter character corresponding to given input string.
    private String getGate(String gate) {
        switch (gate) {
            case "Pauli X":
                return "X";
            case "Pauli Y":
                return "Y";
            case "Pauli Z":
                return "Z";
            case "S":
                return "S";
            case "T":
                return "T";
            case "Identity":
                return "I";
            default:
                return "H";
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds a text field for loading in a 2-qubit state and gates from a JSON file.
    @Override
    protected void addLoadTextField() {
        JTextField loadStateTextField = new JTextField("Load qubit state from _.json");
        loadStateTextField.addActionListener(new ActionListener() {
            // EFFECTS: When press enter, (if exists) loads in 2-qubit state and gates from _.json where _ is textfield
            public void actionPerformed(ActionEvent e) {
                String filename = loadStateTextField.getText();
                String abort = "Could not load qubit state from file.";
                try {
                    JsonReader reader = new JsonReader(relPath + filename + extension);
                    qubits = reader.readTwo();
                    statusLabel.setText(filename + extension + " was loaded in successfully.");
                    String updatedQubitStateString = qubits.returnState();
                    qubitStateLabel.setText(updatedQubitStateString);
                    String updatedProbabilitiesString = qubits.returnProbabilities();
                    probabilitiesLabel.setText(updatedProbabilitiesString);
                    gatedrawer.repaint();
                } catch (IOException ex) {
                    statusLabel.setText("File could not be found." + abort);
                } catch (WrongQubitNumberException ex) {
                    statusLabel.setText("File is for 1-qubit system." + abort);
                }
            }
        });
        add(loadStateTextField);
    }

}
