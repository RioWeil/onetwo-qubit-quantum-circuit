package model;

// Represents a 2x1 column vector.
public abstract class TwoByOneVector {
    protected Complex[] vector = new Complex[2];

    // EFFECTS: Creates 2x1 column vector with entries a0, a1.
    protected TwoByOneVector(Complex a0, Complex a1) {
        vector[0] = a0;
        vector[1] = a1;
    }

    // REQUIRES: row is 0 or 1.
    // EFFECTS: Produces complex value stored in specified row of vector (0 based indexing)
    protected Complex getVectorElement(int row) {
        return vector[row];
    }

    // REQUIRES: row is 0 or 1.
    // MODIFIES: this
    // EFFECTS: Sets vector at specified entry (0 based indexing) to be the specified complex number.
    protected void setVectorElement(int row, Complex element) {
        vector[row] = element;
    }

    // MODIFIES: this
    // EFFECTS: Matrix multiplies this.vector by the provided matrix, sets vector to result.
    protected void multiplyMatrix(TwoByTwoMatrix matrix) {
        Complex temp1;
        Complex temp2;
        Complex oldv0 = vector[0];
        Complex oldv1 = vector[1];
        for (int i = 0; i < 2; i++) {
            temp1 = oldv0.complexMultiply(matrix.getMatrixElement(i, 0));
            temp2 = oldv1.complexMultiply(matrix.getMatrixElement(i, 1));
            vector[i] = temp1.complexAdd(temp2);
        }
    }
}
