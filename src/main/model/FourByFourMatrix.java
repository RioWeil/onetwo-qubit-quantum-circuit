package model;

// Represents a 4x4 matrix.
public class FourByFourMatrix {
    protected Complex[][] matrix = new Complex[4][4];

    // EFFECTS: Creates a 4x4 matrix of (complex) zeros.
    public FourByFourMatrix() {
        Complex zero = new Complex(0, 0);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = zero;
            }
        }
    }

    // REQUIRES: row, col are integers between 0 and 3.
    // EFFECTS: Produces complex value stored in the specified row/column (0 based indexing).
    public Complex getMatrixElement(int row, int col) {
        return matrix[row][col];
    }

    // REQUIRES: row, col are integers between 0 and 3.
    // MODIFIES: this
    // EFFECTS: Sets matrix at the specified row/column (0 based indexing) to be the specified complex number.
    public void setMatrixElement(int row, int col, Complex element) {
        matrix[row][col] = element;
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
