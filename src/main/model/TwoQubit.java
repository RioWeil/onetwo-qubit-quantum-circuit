package model;

// Represents a system of two qubits (a linear combination of the |00>, |01>, |10>, and and |11> pure states).
public class TwoQubit extends FourByOneVector {

    // REQUIRES: The sum of the squared moduli of a0, a1, a2, a3 must be 1, i.e. |a0|^2 + |a1|^2 + |a2|^2 + |a3|^2 = 1.
    // EFFECTS: Creates a two-qubit system (a 4x1 column vector with entries a0, a1, a2, a3).
    public TwoQubit(Complex a0, Complex a1, Complex a2, Complex a3) {
        super(a0, a1, a2, a3);
    }

    // REQUIRES: m1, m2 must be one of PauliX, PauliY, PauliZ, Hadamard, S, T, or Identity.
    // MODIFIES: this
    // EFFECTS: Applies the two supplied gates to the two qubits (multiplication under composition of tensor product).
    public void applyGates(TwoByTwoMatrix m1, TwoByTwoMatrix m2) {
        FourByFourMatrix combinedGates = m1.tensorProduct(m2);
        multiplyMatrix(combinedGates);
    }

}
