package model;

// Represents a 4x1 column vector.
public class FourByOneVector extends Vector {

    // EFFECTS: Creates 4x1 column vector with entries a0, a1, a2, a3.
    protected FourByOneVector(Complex a0, Complex a1, Complex a2, Complex a3) {
        vector = new Complex[4];
        vector[0] = a0;
        vector[1] = a1;
        vector[2] = a2;
        vector[3] = a3;
    }

    // REQUIRES: Matrix is a FourByFourMatrix
    // MODIFIES: this
    // EFFECTS: Matrix multiplies this.vector by the provided 4x4 matrix, sets vector to result.
    protected void multiplyMatrix(Matrix matrix) {
        Complex temp1;
        Complex temp2;
        Complex temp3;
        Complex temp4;
        Complex oldv0 = vector[0];
        Complex oldv1 = vector[1];
        Complex oldv2 = vector[2];
        Complex oldv3 = vector[3];
        for (int i = 0; i < 4; i++) {
            temp1 = oldv0.complexMultiply(matrix.getMatrixElement(i, 0));
            temp2 = oldv1.complexMultiply(matrix.getMatrixElement(i, 1));
            temp3 = oldv2.complexMultiply(matrix.getMatrixElement(i, 2));
            temp4 = oldv3.complexMultiply(matrix.getMatrixElement(i, 3));
            vector[i] = temp1.complexAdd(temp2).complexAdd(temp3).complexAdd(temp4);
        }
    }
}
