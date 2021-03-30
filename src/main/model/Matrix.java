package model;

public abstract class Matrix {
    protected Complex[][] matrix;

    // EFFECTS: Produces complex value stored in the specified row/column (0 based indexing).
    public Complex getMatrixElement(int row, int col) {
        return matrix[row][col];
    }

    // MODIFIES: this
    // EFFECTS: Sets matrix at the specified row/column (0 based indexing) to be the specified complex number.
    public void setMatrixElement(int row, int col, Complex element) {
        matrix[row][col] = element;
    }

}
