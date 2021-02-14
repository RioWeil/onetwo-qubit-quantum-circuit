package model;

import java.util.Random;

// Represents a quantum circuit of two Qubits, containing 2 qubits and list of gates that will be applied to the qubits.
public class TwoQubitQuantumCircuit extends QuantumCircuit {
    TwoQubit qubits;

    // REQUIRES: The sum of the squared moduli of a0/a1/a2/a3 must be 1.
    // EFFECTS: Creates new simulation with qubit set to initial state, an empty list of gates, and randomized seed.
    public TwoQubitQuantumCircuit(Complex a0, Complex a1, Complex a2, Complex a3) {
        qubits = new TwoQubit(a0, a1, a2, a3);
        gates = new Gates();
        random = new Random();
    }

    // REQUIRES: The sum of the squared moduli of a0/a1/a2/a3 must be 1.
    // EFFECTS: Creates new simulation with qubit set to initial state, an empty list of gates, and set random seed.
    //          This alternate constructor is for consistent testing of the random functionality.
    public TwoQubitQuantumCircuit(Complex a0, Complex a1, Complex a2, Complex a3, long seed) {
        qubits = new TwoQubit(a0, a1, a2, a3);
        gates = new Gates();
        random = new Random(seed);
    }


    // MODIFIES: this
    // EFFECTS: Applies the first gate in list to the qubit, and removes the gate from gates.
    //          Returns "Applied <> gate", or if empty, does nothing and returns "No gates in list to apply."
    @Override
    protected String applyGate() {
        return null;
    }


    // EFFECTS: Returns two-qubit state in form x|00> + y|01> + z|10> + w|11>, where the letters are the amplitudes.
    @Override
    protected String returnState() {
        return null;
    }

    // EFFECTS: Returns the probabilities of measurement of |00>, |01> , |10> , and |11> for current state of qubits.
    @Override
    protected String returnProbabilities() {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: Measures qubits and collapses system to one of four pure states |00>, |01> , |10> , and |11>.
    //          Returns report of the measurement outcome.
    @Override
    protected String makeMeasurement() {
        return null;
    }
}
