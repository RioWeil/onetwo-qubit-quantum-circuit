package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class TwoByTwoMatrixTest {
    TwoByTwoMatrix m1;
    TwoByTwoMatrix m2;
    FourByFourMatrix result;
    Complex element;

    @BeforeEach
    public void runBefore() {
        m1 = new TwoByTwoMatrix();
        m2 = new TwoByTwoMatrix();
    }

    @Test
    public void testInitialZero() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                element = m1.getMatrixElement(i, j);
                assertEquals(0, element.getReal());
                assertEquals(0, element.getImaginary());
            }
        }
    }

    @Test
    public void testSetMatrixElements() {
        Complex c0 = new Complex(0, 2);
        Complex c1 = new Complex(5, 10);
        m1.setMatrixElement(0, 0, c0);
        m1.setMatrixElement(1, 1, c1);
        assertEquals(c0, m1.getMatrixElement(0, 0));
        assertEquals(c1, m1.getMatrixElement(1, 1));
        element = m1.getMatrixElement(0, 1);
        assertEquals(0, element.getReal());
        assertEquals(0, element.getImaginary());
        element = m1.getMatrixElement(1, 0);
        assertEquals(0, element.getReal());
        assertEquals(0, element.getImaginary());
    }

    @Test
    public void testSetPauliXGate() {
        m1.setPauliXGate();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                element = m1.getMatrixElement(i, j);
                assertEquals(0, element.getImaginary());
                if ((i == 0 && j == 1) || (i == 1 && j == 0)) {
                    assertEquals(1, element.getReal());
                } else {
                    assertEquals(0, element.getReal());
                }
            }
        }
    }

    @Test
    public void testSetPauliYGate() {
        m1.setPauliYGate();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                element = m1.getMatrixElement(i, j);
                assertEquals(0, element.getReal());
                if (i == 0 && j == 1) {
                    assertEquals(-1, element.getImaginary());
                } else if (i == 1 && j == 0) {
                    assertEquals(1, element.getImaginary());
                } else {
                    assertEquals(0, element.getImaginary());
                }
            }
        }
    }

    @Test
    public void testSetPauliZGate() {
        m1.setPauliZGate();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                element = m1.getMatrixElement(i, j);
                assertEquals(0, element.getImaginary());
                if (i == 0 && j == 0) {
                    assertEquals(1, element.getReal());
                } else if (i == 1 && j == 1) {
                    assertEquals(-1, element.getReal());
                } else {
                    assertEquals(0, element.getReal());
                }
            }
        }
    }

    @Test
    public void testSetSGate() {
        m1.setSGate();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                element = m1.getMatrixElement(i, j);
                if (i == 0 && j == 0) {
                    assertEquals(1, element.getReal());
                    assertEquals(0, element.getImaginary());
                } else if (i == 1 && j == 1) {
                    assertEquals(0, element.getReal());
                    assertEquals(1, element.getImaginary());
                } else {
                    assertEquals(0, element.getReal());
                    assertEquals(0, element.getImaginary());
                }
            }
        }
    }

    @Test
    public void testSetTGate() {
        m1.setTGate();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                element = m1.getMatrixElement(i, j);
                if (i == 0 && j == 0) {
                    assertEquals(1, element.getReal());
                    assertEquals(0, element.getImaginary());
                } else if (i == 1 && j == 1) {
                    assertEquals(Math.pow(0.5, 0.5), element.getReal());
                    assertEquals(Math.pow(0.5, 0.5), element.getImaginary());
                } else {
                    assertEquals(0, element.getReal());
                    assertEquals(0, element.getImaginary());
                }
            }
        }
    }

    @Test
    public void testSetIdentityGate() {
        m1.setIdentityGate();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                element = m1.getMatrixElement(i, j);
                assertEquals(0, element.getImaginary());
                if ((i == 0 && j == 0) || (i == 1 && j == 1)) {
                    assertEquals(1, element.getReal());
                } else {
                    assertEquals(0, element.getReal());
                }
            }
        }
    }

    @Test
    public void testSetHadamardGate() {
        m1.setHadamardGate();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                element = m1.getMatrixElement(i, j);
                assertEquals(0, element.getImaginary());
                if (i == 1 && j == 1) {
                    assertEquals(-Math.pow(0.5, 0.5), element.getReal());
                } else {
                    assertEquals(Math.pow(0.5, 0.5), element.getReal());
                }
            }
        }
    }

    @Test
    public void testTensorProductTwoIdentity() {
        m1.setIdentityGate();
        m2.setIdentityGate();
        result = m1.tensorProduct(m2);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                element = result.getMatrixElement(i, j);
                assertEquals(0, element.getImaginary());
                if (i == j) {
                    assertEquals(1, element.getReal());
                } else {
                    assertEquals(0, element.getImaginary());
                }
            }
        }
    }

    @Test
    public void testTensorProductIdentityNonIdentity() {
        m1.setIdentityGate();
        m2.setPauliYGate();
        result = m1.tensorProduct(m2);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                element = result.getMatrixElement(i, j);
                assertEquals(0, element.getReal());
                if ((i == 0 && j == 1) || (i == 2 && j == 3)) {
                    assertEquals(-1, element.getImaginary());
                } else if ((i == 1 && j == 0) || (i == 3 && j == 2)) {
                    assertEquals(1, element.getImaginary());
                } else {
                    assertEquals(0, element.getImaginary());
                }
            }
        }
    }

    @Test
    public void testTensorProductNonIdentityIdentity() {
        m1.setIdentityGate();
        m2.setPauliYGate();
        result = m2.tensorProduct(m1);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                element = result.getMatrixElement(i, j);
                assertEquals(0, element.getReal());
                if ((i == 0 && j == 2) || (i == 1 && j == 3)) {
                    assertEquals(-1, element.getImaginary());
                } else if ((i == 2 && j == 0) || (i == 3 && j == 1)) {
                    assertEquals(1, element.getImaginary());
                } else {
                    assertEquals(0, element.getImaginary());
                }
            }
        }
    }

    @Test
    public void testTensorProductBothNonIdentity() {
        m1.setSGate();
        m2.setPauliYGate();
        result = m1.tensorProduct(m2);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                element = result.getMatrixElement(i, j);
                if (i == 0 && j == 1) {
                    assertEquals(0, element.getReal());
                    assertEquals(-1, element.getImaginary());
                } else if (i == 1 && j == 0) {
                    assertEquals(0, element.getReal());
                    assertEquals(1, element.getImaginary());
                } else if (i == 2 && j == 3) {
                    assertEquals(1, element.getReal());
                    assertEquals(0, element.getImaginary());
                } else if (i == 3 & j == 2) {
                    assertEquals(-1, element.getReal());
                    assertEquals(0, element.getImaginary());
                } else {
                    assertEquals(0, element.getReal());
                    assertEquals(0, element.getImaginary());
                }
            }
        }
    }

}
