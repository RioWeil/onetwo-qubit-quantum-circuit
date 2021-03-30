package model;

public abstract class Vector {
    protected Complex[] vector;

    // EFFECTS: Produces complex value stored in specified row of vector (0 based indexing)
    protected Complex getVectorElement(int row) {
        return vector[row];
    }

    // MODIFIES: this
    // EFFECTS: Sets vector at specified entry (0 based indexing) to be the specified complex number.
    protected void setVectorElement(int row, Complex element) {
        vector[row] = element;
    }

    // MODIFIES: this
    // EFFECTS: Matrix multiplies this.vector by the provided matrix, sets vector to result.
    protected abstract void multiplyMatrix(Matrix matrix);

}
