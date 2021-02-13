package model;

public class OneQubit extends TwoByOneVector {

    // REQUIRES: The sum of the squared moduli of a0, a1 must be 1, i.e. |a0|^2 + |a1|^2 = 1.
    // EFFECTS: Creates a single cubit (a 2x1 column vector with entries a0, a1).
    public OneQubit(Complex a0, Complex a1) {
        super(a0, a1);
    }

    // REQUIRES: gate must be set to one of PauliX, PauliY, PauliZ, S, T, Identity, or Hadamard.
    // MODIFIES: this
    // EFFECTS: Applies the provided gate to the qubit.
    public void applyGate(TwoByTwoMatrix gate) {
        multiplyMatrix(gate);
    }
}
