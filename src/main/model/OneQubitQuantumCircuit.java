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
    // EFFECTS: Adds a gate to gates, and returns "Added <> gate to list."
    public String addGate(String gate) {
        gates.addGate(gate);
        return "Added " + gate + " gate to list.";
    }

    // EFFECTS: Produces the first gate in gates, or returns "No gates in list" if empty.
    public String seeFirstGate() {
        if (gates.isEmpty()) {
            return "No gates in list.";
        } else {
            return "The next gate is " + gates.seeNextGate() + ".";
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes first gate from gates and returns "Removed <> gate.", or "No gates in list to remove" if empty.
    public String removeGate() {
        if (gates.isEmpty()) {
            return "No gates in list to remove.";
        } else {
            String nextgate = gates.seeNextGate();
            gates.removeNextGate();
            return "Removed " + nextgate + " gate.";
        }
    }

    // MODIFIES: this
    // EFFECTS: Applies the first gate in list to the qubit, and removes the gate from gates.
    //          Returns "Applied <> gate", or if empty, does nothing and returns "No gates in list to apply."
    public String applyGate() {
        if (gates.isEmpty()) {
            return "No gates in list to apply.";
        } else {
            TwoByTwoMatrix matrix = new TwoByTwoMatrix();
            String nextgate = gates.getNextGate();
            if (nextgate.equals("X")) {
                matrix.setPauliXGate();
            } else if (nextgate.equals("Y")) {
                matrix.setPauliYGate();
            } else if (nextgate.equals("Z")) {
                matrix.setPauliZGate();
            } else if (nextgate.equals("S")) {
                matrix.setSGate();
            } else if (nextgate.equals("T")) {
                matrix.setTGate();
            } else if (nextgate.equals("I")) {
                matrix.setIdentityGate();
            } else if (nextgate.equals("H")) {
                matrix.setHadamardGate();
            }
            qubit.applyGate(matrix);
            return "Applied " + nextgate + " gate.";
        }
    }

    // MODIFIES: this
    // EFFECTS: Applies all gates in the list to the qubit, and clears gates to be empty.
    //          Returns "Applied all gates in list.", or if empty, does nothing and returns "No gates in list to apply."
    public String applyAllGates() {
        if (gates.isEmpty()) {
            return "No gates in list to apply.";
        } else {
            while (! gates.isEmpty()) {
                applyGate();
            }
            return "Applied all gates in list.";
        }
    }

    // EFFECTS: Returns current qubit state in the form x|0> + y|1>, x and y are amplitudes of the 0 and 1 states.
    public String returnState() {
        double zeroreal = qubit.getAmplitude(0).getReal();
        double zeroimag = qubit.getAmplitude(0).getImaginary();
        double onereal = qubit.getAmplitude(1).getReal();
        double oneimag = qubit.getAmplitude(1).getImaginary();
        String zerorealstr = formatDoubleToString(zeroreal);
        String zeroimagstr = formatDoubleToString(zeroimag);
        String onerealstr = formatDoubleToString(onereal);
        String oneimagstr = formatDoubleToString(oneimag);
        String zeropart = "The current state is (" + zerorealstr + " + " + zeroimagstr + "i)|0>";
        String onepart = " + (" + onerealstr + " + " + oneimagstr + "i)|1>.";
        return  zeropart + onepart;
    }


    // EFFECTS: Returns the probabilities of the measurement outcomes of |0> and |1> for the current qubit state.
    public String returnProbabilities() {
        double zeroprob = qubit.getProbability(0);
        double oneprob = qubit.getProbability(1);
        String zeroprobstr = formatDoubleToString(zeroprob);
        String oneprobstr = formatDoubleToString(oneprob);
        return "The measurement probabilities are " + zeroprobstr + " for |0>, " + oneprobstr  + " for |1>.";
    }

    // EFFECTS: Produces string of the form "#.###" from double (or "-#.###" if negative).
    // String formatting from https://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
    private String formatDoubleToString(double d) {
        if (d == 0 || d == 1) {
            return String.format("%.4g", d);
        } else {
            return String.format("%.3g", d);
        }
    }

    // MODIFIES: this
    // EFFECTS: Measures the qubit and collapses it to pure state |0> or |1>, and return report of measurement outcome.
    public String makeMeasurement() {
        double cutoff = random.nextDouble();
        double zeroprob = qubit.getProbability(0);
        if (cutoff < zeroprob) {
            qubit.collapseAfterMeasurement(0);
            return "You measured the system to be in the |0> state!";
        } else {
            qubit.collapseAfterMeasurement(1);
            return "You measured the system to be in the |1> state!";
        }
    }





}
