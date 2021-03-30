package model;

// Represents a 4x4 matrix.
public class FourByFourMatrix extends Matrix {

    // EFFECTS: Creates a 4x4 matrix of (complex) zeros.
    public FourByFourMatrix() {
        matrix = new Complex[4][4];
        Complex zero = new Complex(0, 0);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = zero;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Sets matrix to be the CNOT gate (entangles the two qubits in the system).
    public void setCnotGate() {
        matrix[0][0] = new Complex(1, 0);
        matrix[1][1] = new Complex(1, 0);
        matrix[2][3] = new Complex(1, 0);
        matrix[3][2] = new Complex(1, 0);
    }
}
