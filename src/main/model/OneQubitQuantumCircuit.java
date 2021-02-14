package model;

import java.util.Random;

// Represents a quantum circuit of a Qubit, containing a qubit and the list of gates that will be applied to the qubit.
public class OneQubitQuantumCircuit {
    OneQubit qubit;
    Gates gates;
    Random random;

    // REQUIRES: The sum of the squared moduli of a0, a1 must be 1, i.e. |a0|^2 + |a1|^2 = 1.
    // EFFECTS: Creates new simulation with qubit set to initial state, an empty list of gates, and randomized seed.
    public OneQubitQuantumCircuit(Complex a0, Complex a1) {
        qubit = new OneQubit(a0, a1);
        gates = new Gates();
        random = new Random();
    }

    // REQUIRES: The sum of the squared moduli of a0, a1 must be 1, i.e. |a0|^2 + |a1|^2 = 1.
    // EFFECTS: Creates new simulation with qubit set to initial state, an empty list of gates, and set random seed.
    //          This alternate constructor is for consistent testing of the random functionality.
    public OneQubitQuantumCircuit(Complex a0, Complex a1, long seed) {
        qubit = new OneQubit(a0, a1);
        gates = new Gates();
        random = new Random(seed);
    }

    // REQUIRES: gate is one of "X", "Y", "Z", "H", "T", "S", "I".
    // MODIFIES: this
    // EFFECTS: Adds a gate to gates.
    public void addGate(String gate) {

    }

    // EFFECTS: Produces the first gate in gates, or returns "No gates in list" if empty.
    public String seeFirstGate() {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: Removes first gate from gates and returns "removed <> gate", or "No gates in list to remove" if empty.
    public String removeGate() {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: Applies the first gate in list to the qubit, and removes the gate from gates.
    //          Returns "Applied <> gate", or if empty, does nothing and returns "No gates in list to apply."
    public String applyGate() {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: Applies all gates in the list to the qubit, and clears gates to be empty.
    //          Returns "Applied all gates in list.", or if empty, does nothing and returns "No gates in list to apply."
    public String applyAllGates() {
        return null;
    }

    // EFFECTS: Returns current qubit state in the form x|0> + y|1>, x and y are amplitudes of the 0 and 1 states.
    public String returnState() {
        return null;
    }


    // EFFECTS: Returns the probabilities of the measurement outcomes of |0> and |1> for the current qubit state.
    public String returnProbabilities() {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: Measures the qubit and collapses it to pure state |0> or |1>, and return report of measurement outcome.
    public String makeMeasurement() {
        return null;
    }





}
