package model;

// Represents a 2x1 column vector.
public class TwoByOneVector extends Vector {

    // EFFECTS: Creates 2x1 column vector with entries a0, a1.
    protected TwoByOneVector(Complex a0, Complex a1) {
        vector = new Complex[2];
        vector[0] = a0;
        vector[1] = a1;
    }

    // REQUIRES: Matrix is a TwoByTwoMatrix
    // MODIFIES: this
    // EFFECTS: Matrix multiplies this.vector by the provided 2x2 matrix, sets vector to result.
    protected void multiplyMatrix(Matrix matrix) {
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
