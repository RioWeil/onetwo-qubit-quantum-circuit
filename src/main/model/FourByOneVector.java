package model;

public class FourByOneVector {
    protected Complex[] vector = new Complex[4];

    // EFFECTS: Creates 4x1 column vector with entries a0, a1, a2, a3.
    public FourByOneVector(Complex a0, Complex a1, Complex a2, Complex a3) {
        vector[0] = a0;
        vector[1] = a1;
        vector[2] = a2;
        vector[3] = a3;
    }

    // REQUIRES: row is an integer between 0 and 3.
    // EFFECTS: Produces complex value stored in specified row of vector (0 based indexing)
    public Complex getVectorElement(int row) {
        return vector[row];
    }

    // REQUIRES: row is an integer between 0 and 3.
    // MODIFIES: this
    // EFFECTS: Sets vector at specified entry (0 based indexing) to be the specified complex number.
    public void setMatrixElement(int row, Complex element) {
        vector[row] = element;
    }

    // MODIFIES: this
    // EFFECTS: Matrix multiplies this.vector by the provided matrix, sets vector to result.
    public void multiplyMatrix(FourByFourMatrix matrix) {

    }
}
