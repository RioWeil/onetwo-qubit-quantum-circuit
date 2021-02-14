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
        if (gates.isEmpty()) {
            return "No gates in list to apply.";
        } else {
            String gate = gates.getNextGate();
            if (gate.equals("CN")) {
                qubits.applyCnot();
            } else {
                String first = gate.substring(0, 1);
                String second = gate.substring(1, 2);
                qubits.applyGates(getMatrixFromString(first), getMatrixFromString(second));
            }
            return "Applied " + gate + " gate.";
        }
    }

    // REQUIRES: gate is one of "X", "Y", "Z", "S", "T", "I", "H"
    // EFFECTS: Returns 2x2 matrix corresponding to string, e.g. "X" returns Pauli X gate.
    private TwoByTwoMatrix getMatrixFromString(String gate) {
        TwoByTwoMatrix matrix = new TwoByTwoMatrix();
        if (gate.equals("X")) {
            matrix.setPauliXGate();
        } else if (gate.equals("Y")) {
            matrix.setPauliYGate();
        } else if (gate.equals("Z")) {
            matrix.setPauliZGate();
        } else if (gate.equals("S")) {
            matrix.setSGate();
        } else if (gate.equals("T")) {
            matrix.setTGate();
        } else if (gate.equals("I")) {
            matrix.setIdentityGate();
        } else {
            matrix.setHadamardGate();
        }
        return matrix;
    }


    // EFFECTS: Returns two-qubit state in form x|00> + y|01> + z|10> + w|11>, where the letters are the amplitudes.
    @Override
    protected String returnState() {
        String zzrealstr = formatDoubleToString(qubits.getAmplitude(0).getReal());
        String zzimagstr = formatDoubleToString(qubits.getAmplitude(0).getImaginary());
        String zorealstr = formatDoubleToString(qubits.getAmplitude(1).getReal());
        String zoimagstr = formatDoubleToString(qubits.getAmplitude(1).getImaginary());
        String ozrealstr = formatDoubleToString(qubits.getAmplitude(2).getReal());
        String ozimagstr = formatDoubleToString(qubits.getAmplitude(2).getImaginary());
        String oorealstr = formatDoubleToString(qubits.getAmplitude(3).getReal());
        String ooimagstr = formatDoubleToString(qubits.getAmplitude(3).getImaginary());
        String zzpart = "The current state is (" + zzrealstr + " + " + zzimagstr + "i)|00>";
        String zopart = " + (" + zorealstr + " + " + zoimagstr + "i)|01>";
        String ozpart = " + (" + ozrealstr + " + " + ozimagstr + "i)|10>";
        String oopart = " + (" + oorealstr + " + " + ooimagstr + "i)|11>.";
        return  zzpart + zopart + ozpart + oopart;
    }

    // EFFECTS: Returns the probabilities of measurement of |00>, |01> , |10> , and |11> for current state of qubits.
    @Override
    protected String returnProbabilities() {
        String zzprobstr = formatDoubleToString(qubits.getProbability(0));
        String zoprobstr = formatDoubleToString(qubits.getProbability(1));
        String ozprobstr = formatDoubleToString(qubits.getProbability(2));
        String ooprobstr = formatDoubleToString(qubits.getProbability(3));
        String firstpart = "The measurement probabilities are " + zzprobstr + " for |00>, " + zoprobstr + " for |01>,";
        String secondpart = " " + ozprobstr + " for |10>, " + ooprobstr + " for |11>.";
        return firstpart + secondpart;
    }

    // MODIFIES: this
    // EFFECTS: Measures qubits and collapses system to one of four pure states |00>, |01> , |10> , and |11>.
    //          Returns report of the measurement outcome.
    @Override
    protected String makeMeasurement() {
        double randval = random.nextDouble();
        double zzprob = qubits.getProbability(0);
        double zoprob = qubits.getProbability(1);
        double ozprob = qubits.getProbability(2);
        if (randval < zzprob) {
            qubits.collapseAfterMeasurement(0);
            return "You measured the system to be in the |00> state!";
        } else if (randval < zzprob + zoprob) {
            qubits.collapseAfterMeasurement(1);
            return "You measured the system to be in the |01> state!";
        } else if (randval < zzprob + zoprob + ozprob) {
            qubits.collapseAfterMeasurement(2);
            return "You measured the system to be in the |10> state!";
        } else {
            qubits.collapseAfterMeasurement(3);
            return "You measured the system to be in the |11> state!";
        }
    }
}
