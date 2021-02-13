package model;

// Represents a 2x2 matrix.
public class TwoByTwoMatrix {
    protected Complex[][] matrix = new Complex[2][2];

    // EFFECTS: Creates a 2x2 matrix of (complex) zeros.
    public TwoByTwoMatrix() {
        Complex zero = new Complex(0, 0);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                matrix[i][j] = zero;
            }
        }
    }

    // REQUIRES: row, col are either 0 or 1.
    // EFFECTS: Produces complex value stored in the specified row/column (0 based indexing).
    public Complex getMatrixElement(int row, int col) {
        return matrix[row][col];
    }

    // REQUIRES: row, col are either 0 or 1.
    // MODIFIES: this
    // EFFECTS: Sets matrix at the specified row/column (0 based indexing) to be the specified complex number.
    public void setMatrixElement(int row, int col, Complex element) {
        matrix[row][col] = element;
    }

    // EFFECTS: Produces a 4x4 matrix that results from taking the tensor product of this.matrix with the provided m2.
    public FourByFourMatrix tensorProduct(TwoByTwoMatrix m2) {
        FourByFourMatrix tensorproduct = new FourByFourMatrix();
        tensorproduct.setMatrixElement(0, 0, matrix[0][0].complexMultiply(m2.getMatrixElement(0,0)));
        tensorproduct.setMatrixElement(0, 1, matrix[0][0].complexMultiply(m2.getMatrixElement(0,1)));
        tensorproduct.setMatrixElement(1, 0, matrix[0][0].complexMultiply(m2.getMatrixElement(1,0)));
        tensorproduct.setMatrixElement(1, 1, matrix[0][0].complexMultiply(m2.getMatrixElement(1,1)));
        tensorproduct.setMatrixElement(0, 2, matrix[0][1].complexMultiply(m2.getMatrixElement(0,0)));
        tensorproduct.setMatrixElement(0, 3, matrix[0][1].complexMultiply(m2.getMatrixElement(0,1)));
        tensorproduct.setMatrixElement(1, 2, matrix[0][1].complexMultiply(m2.getMatrixElement(1,0)));
        tensorproduct.setMatrixElement(1, 3, matrix[0][1].complexMultiply(m2.getMatrixElement(1,1)));
        tensorproduct.setMatrixElement(2, 0, matrix[1][0].complexMultiply(m2.getMatrixElement(0,0)));
        tensorproduct.setMatrixElement(2, 1, matrix[1][0].complexMultiply(m2.getMatrixElement(0,1)));
        tensorproduct.setMatrixElement(3, 0, matrix[1][0].complexMultiply(m2.getMatrixElement(1,0)));
        tensorproduct.setMatrixElement(3, 1, matrix[1][0].complexMultiply(m2.getMatrixElement(1,1)));
        tensorproduct.setMatrixElement(2, 2, matrix[1][1].complexMultiply(m2.getMatrixElement(0,0)));
        tensorproduct.setMatrixElement(2, 3, matrix[1][1].complexMultiply(m2.getMatrixElement(0,1)));
        tensorproduct.setMatrixElement(3, 2, matrix[1][1].complexMultiply(m2.getMatrixElement(1,0)));
        tensorproduct.setMatrixElement(3, 3, matrix[1][1].complexMultiply(m2.getMatrixElement(1,1)));

        return tensorproduct;
    }

    // MODIFIES: this
    // EFFECTS: Sets matrix to be the Pauli-X gate (bit flip, or rotation of pi rad around x axis of Bloch sphere)
    public void setPauliXGate() {
        matrix[0][1] = new Complex(1, 0);
        matrix[1][0] = new Complex(1, 0);
    }

    // MODIFIES: this
    // EFFECTS: Sets matrix to be the Pauli-Y gate (rotation of pi rad around y axis of Bloch sphere)
    public void setPauliYGate() {
        matrix[0][1] = new Complex(0, -1);
        matrix[1][0] = new Complex(0, 1);
    }

    // MODIFIES: this
    // EFFECTS: Sets matrix to be the Pauli-Z gate (rotation of pi rad around z axis of Bloch sphere)
    public void setPauliZGate() {
        matrix[0][0] = new Complex(1, 0);
        matrix[1][1] = new Complex(-1, 0);
    }

    // MODIFIES: this
    // EFFECTS: Sets matrix to be the S gate (phase gate, rotation of pi/2 rad around z-axis of Bloch sphere)
    public void setSGate() {
        matrix[0][0] = new Complex(1, 0);
        matrix[1][1] = new Complex(0, 1);
    }

    // MODIFIES: this
    // EFFECTS: Sets matrix to be the T gate (rotation of pi/4 rad around z-axis of Bloch sphere)
    public void setTGate() {
        matrix[0][0] = new Complex(1, 0);
        matrix[1][1] = new Complex(Math.pow(0.5, 0.5), Math.pow(0.5, 0.5));
    }

    // MODIFIES: This
    // EFFECTS: Sets matrix to be the identity gate (leaves qubit unchanged)
    public void setIdentityGate() {
        matrix[0][0] = new Complex(1, 0);
        matrix[1][1] = new Complex(1, 0);
    }

    // MODIFIES: this
    // EFFECTS: Sets matrix to be the Hadamard gate (Creates superposition of qubit states from pure states)
    public void setHadamardGate() {
        matrix[0][0] = new Complex(Math.pow(0.5, 0.5), 0);
        matrix[0][1] = new Complex(Math.pow(0.5, 0.5), 0);
        matrix[1][0] = new Complex(Math.pow(0.5, 0.5), 0);
        matrix[1][1] = new Complex(-Math.pow(0.5, 0.5), 0);
    }
}
