package model;

// Represents a single qubit (a linear combination of the |0> and |1> pure states).
public class OneQubit extends TwoByOneVector {

    // REQUIRES: The sum of the squared moduli of a0, a1 must be 1, i.e. |a0|^2 + |a1|^2 = 1.
    // EFFECTS: Creates a single qubit (a 2x1 column vector with entries a0, a1).
    public OneQubit(Complex a0, Complex a1) {
        super(a0, a1);
    }

    // REQUIRES: gate must be set to one of PauliX, PauliY, PauliZ, S, T, Identity, or Hadamard.
    // MODIFIES: this
    // EFFECTS: Applies the provided gate to the qubit.
    public void applyGate(TwoByTwoMatrix gate) {
        multiplyMatrix(gate);
    }

    // REQUIRES: i must be either 0 or 1
    // EFFECTS: Returns the complex amplitude of the 0 or 1 state of the qubit.
    public Complex getAmplitude(int i) {
        return getVectorElement(i);
    }

    // REQUIRES: i must either be 0 or 1
    // EFFECTS: Returns the probability of measuring the 0 or 1 state if one was to measure the qubit.
    public double getProbability(int i) {
        return Math.pow(getVectorElement(i).modulus(), 2);
    }

    // REQUIRES: i must be either 0 or 1.
    // MODIFIES: this
    // EFFECTS: Sets the qubit to be either the pure 0 or 1 state.
    public void collapseAfterMeasurement(int i) {
        if (i == 0) {
            setVectorElement(0, new Complex(1, 0));
            setVectorElement(1, new Complex(0, 0));
        } else {
            setVectorElement(0, new Complex(0, 0));
            setVectorElement(1, new Complex(1, 0));
        }
    }

}
