package ui.gui;

import model.Complex;
import model.OneQubitQuantumCircuit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuantumCircuitAppOneQubitGUI extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    private String[] oneQubitGates = {"Add _ gate to gate list...",
            "Pauli X", "Pauli Y", "Pauli Z", "S", "T", "Identity", "Hadamard"};
    private OneQubitQuantumCircuit oneQubit;
    private JLabel statusLabel;
    private JLabel qubitStateLabel;
    private JLabel probabilitiesLabel;
    private FlowLayout layout;

    // EFFECTS: Creates a new OneQubit Quantum Circuit GUI
    public QuantumCircuitAppOneQubitGUI(String initialState) {
        super("One Qubit Quantum Circuit Simulation");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        layout = new FlowLayout();
        this.setLayout(layout);
        initializeCircuit(initialState);
        addLabels();
        addButtons();
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
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





}
