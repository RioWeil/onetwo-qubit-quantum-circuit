package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test Class for FourByFourMatrix
public class FourByFourMatrixTest {
    FourByFourMatrix matrix;
    Complex element;

    @BeforeEach
    public void runBefore() {
        matrix = new FourByFourMatrix();
    }

    @Test
    public void testInitialZeroMatrix() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                element = matrix.getMatrixElement(i, j);
                assertEquals(0, element.getReal());
                assertEquals(0, element.getImaginary());
            }

        }
    }

    @Test
    public void testSetMatrixElements() {
        Complex c0 = new Complex(2, 5);
        Complex c1 = new Complex(0, 1);
        matrix.setMatrixElement(2, 3, c0);
        matrix.setMatrixElement(0, 1, c1);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 2 && j == 3) {
                    assertEquals(c0, matrix.getMatrixElement(i, j));
                } else if (i == 0 && j == 1) {
                    assertEquals(c1, matrix.getMatrixElement(i, j));
                } else {
                    element = matrix.getMatrixElement(i, j);
                    assertEquals(0, element.getReal());
                    assertEquals(0, element.getImaginary());
                }
            }
        }
    }

    @Test
    public void testSetCNOTGate() {
        matrix.setCnotGate();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                element = matrix.getMatrixElement(i, j);
                if ((i == 0 && j == 0) || (i == 1 && j == 1) || (i == 2 && j == 3) || (i == 3 && j == 2)) {
                    assertEquals(1, element.getReal());
                    assertEquals(0, element.getImaginary());
                } else {
                    assertEquals(0, element.getReal());
                    assertEquals(0, element.getImaginary());
                }
            }
        }
    }
}
