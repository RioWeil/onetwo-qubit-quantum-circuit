package model;

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
        return tensorproduct;
    }

    public void setPauliX() {
        matrix[0][1] = new Complex(1, 0);
        matrix[1][0] = new Complex(1, 0);
    }

    public void setPauliY() {
        matrix[0][1] = new Complex(0, -1);
        matrix[1][0] = new Complex(0, 1);
    }

    public void setPauliZ() {
        matrix[0][0] = new Complex(1, 0);
        matrix[1][1] = new Complex(-1, 0);
    }

    public void setSGate() {
        matrix[0][0] = new Complex(1, 0);
        matrix[1][1] = new Complex(0, 1);
    }

    public void setTGate() {
        matrix[0][0] = new Complex(1, 0);
        matrix[1][1] = new Complex(Math.pow(0.5, 0.5), Math.pow(0.5, 0.5));
    }

    public void setIdentityGate() {
        matrix[0][0] = new Complex(1, 0);
        matrix[1][1] = new Complex(1, 0);
    }

    public void setHadamardGate() {
        matrix[0][0] = new Complex(Math.pow(0.5, 0.5), 0);
        matrix[0][1] = new Complex(Math.pow(0.5, 0.5), 0);
        matrix[1][0] = new Complex(Math.pow(0.5, 0.5), 0);
        matrix[1][1] = new Complex(-Math.pow(0.5, 0.5), 0);
    }
}
