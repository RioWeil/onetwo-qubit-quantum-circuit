package ui.gui;

import exceptions.WrongQubitNumberException;
import model.Complex;
import model.OneQubitQuantumCircuit;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class QuantumCircuitAppOneQubitGUI extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    private String[] oneQubitGates = {"Add _ gate to gate list...",
            "Pauli X", "Pauli Y", "Pauli Z", "S", "T", "Identity", "Hadamard"};
    private OneQubitQuantumCircuit oneQubit;
    private JLabel statusLabel;
    private JLabel qubitStateLabel;
    private JLabel probabilitiesLabel;
    private GridLayout layout;
    final String relPath = "./data/";
    final String extension = ".json";

    // EFFECTS: Creates a new OneQubit Quantum Circuit GUI
    public QuantumCircuitAppOneQubitGUI(String initialState) {
        super("One Qubit Quantum Circuit Simulation");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        layout = new GridLayout(0,1);
        this.setLayout(layout);
        initializeCircuit(initialState);
        addLabels();
        addButtons();
        addSaveTextField();
        addLoadTextField();
        pack();
        setVisible(true);
    }

    // REQUIRES: initialState is "|0>" or "|1>"
    // EFFECTS: Initializes the One Qubit Quantum circuit with the specified original state
    private void initializeCircuit(String initialState) {
        if (initialState.equals("|0>")) {
            oneQubit = new OneQubitQuantumCircuit(new Complex(1, 0), new Complex(0, 0));
        } else {
            oneQubit = new OneQubitQuantumCircuit(new Complex(0, 0), new Complex(1, 0));
        }
    }

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

    public void addStatusLabel() {
        statusLabel = new JLabel("Welcome to the One Qubit Simulation!", (int) CENTER_ALIGNMENT);
        add(statusLabel);
    }

    public void addQubitStateLabel() {
        String initialQubitStateString = oneQubit.returnState();
        qubitStateLabel = new JLabel(initialQubitStateString);
        add(qubitStateLabel);
    }

    public void addProbabilitiesLabel() {
        String initialProbabilitiesString = oneQubit.returnProbabilities();
        probabilitiesLabel = new JLabel(initialProbabilitiesString);
        add(probabilitiesLabel);
    }

    private void addChooseGatesBox() {
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
        String addedGateMessage = null;
        if (gate.equals("Pauli X")) {
            addedGateMessage = oneQubit.addGate("X");
        } else if (gate.equals("Pauli Y")) {
            addedGateMessage = oneQubit.addGate("Y");
        } else if (gate.equals("Pauli Z")) {
            addedGateMessage = oneQubit.addGate("Z");
        } else if (gate.equals("S")) {
            addedGateMessage = oneQubit.addGate("S");
        } else if (gate.equals("T")) {
            addedGateMessage = oneQubit.addGate("T");
        } else if (gate.equals("Identity")) {
            addedGateMessage = oneQubit.addGate("I");
        } else if (gate.equals("Hadamard")) {
            addedGateMessage = oneQubit.addGate("H");
        }
        if (addedGateMessage != null) {
            statusLabel.setText(addedGateMessage);
        }

    }

    private void addRemoveGateButton() {
        JButton removeGateButton = new JButton("Remove first gate in gate list");
        removeGateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String removedGateMessage;
                removedGateMessage = oneQubit.removeGate();
                statusLabel.setText(removedGateMessage);
            }
        });
        add(removeGateButton);
    }

    private void addApplyGateButton() {
        JButton applyGateButton = new JButton("Apply first gate in gate list to qubit");
        applyGateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String appliedGateMessage;
                String updatedQubitStateString;
                String updatedProbabilitiesString;
                appliedGateMessage = oneQubit.applyGate();
                statusLabel.setText(appliedGateMessage);
                updatedQubitStateString = oneQubit.returnState();
                qubitStateLabel.setText(updatedQubitStateString);
                updatedProbabilitiesString = oneQubit.returnProbabilities();
                probabilitiesLabel.setText(updatedProbabilitiesString);
            }
        });
        add(applyGateButton);
    }

    private void addApplyAllGatesButton() {
        JButton applyAllGatesButton = new JButton("Apply all gates in list to qubit");
        applyAllGatesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String appliedAllGatesMessage;
                String updatedQubitStateString;
                String updatedProbabilitiesString;
                appliedAllGatesMessage = oneQubit.applyAllGates();
                statusLabel.setText(appliedAllGatesMessage);
                updatedQubitStateString = oneQubit.returnState();
                qubitStateLabel.setText(updatedQubitStateString);
                updatedProbabilitiesString = oneQubit.returnProbabilities();
                probabilitiesLabel.setText(updatedProbabilitiesString);
            }
        });
        add(applyAllGatesButton);

    }

    private void addMeasureButton() {
        JButton measureButton = new JButton("Measure the Qubit");
        measureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String measuredQubitMessage;
                String updatedQubitStateString;
                String updatedProbabilitiesString;
                measuredQubitMessage = oneQubit.makeMeasurement();
                statusLabel.setText(measuredQubitMessage);
                updatedQubitStateString = oneQubit.returnState();
                qubitStateLabel.setText(updatedQubitStateString);
                updatedProbabilitiesString = oneQubit.returnProbabilities();
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
                    writer.write(oneQubit);
                    writer.close();
                    statusLabel.setText(filename + extension + " saved successfully.");
                } catch (IOException ex) {
                    statusLabel.setText("Invalid filename. Qubit state could not be saved.");
                }
            }
        });
        add(saveStateTextField);
    }

    private void addLoadTextField() {
        JTextField loadStateTextField = new JTextField("Load qubit state from _.json");
        loadStateTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filename = (String) loadStateTextField.getText();
                String abort = "Could not load qubit state from file.";
                try {
                    JsonReader reader = new JsonReader(relPath + filename + extension);
                    oneQubit = reader.readOne();
                    statusLabel.setText(filename + extension + " was loaded in successfully.");
                    String updatedQubitStateString = oneQubit.returnState();
                    qubitStateLabel.setText(updatedQubitStateString);
                    String updatedProbabilitiesString = oneQubit.returnProbabilities();
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
