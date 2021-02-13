package model;

// Represents a 2x1 column vector.
public class TwoByOneVector {
    protected Complex[] vector = new Complex[2];

    // EFFECTS: Creates 2x1 column vector with entries a0, a1.
    public TwoByOneVector(Complex a0, Complex a1) {
        vector[0] = a0;
        vector[1] = a1;
    }

    // REQUIRES: row is 0 or 1.
    // EFFECTS: Produces complex value stored in specified row of vector (0 based indexing)
    public Complex getVectorElement(int row) {
        return vector[row];
    }

    // REQUIRES: row is 0 or 1.
    // MODIFIES: this
    // EFFECTS: Sets vector at specified entry (0 based indexing) to be the specified complex number.
    public void setMatrixElement(int row, Complex element) {
        vector[row] = element;
    }

    // MODIFIES: this
    // EFFECTS: Matrix multiplies this.vector by the provided matrix, sets vector to result.
    public void multiplyMatrix(TwoByTwoMatrix matrix) {

    }
}
