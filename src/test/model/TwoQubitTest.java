package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for TwoQubit
public class TwoQubitTest {
    double delta = 0.0000001;
    Complex zero = new Complex(0, 0);
    Complex one = new Complex(1, 0);
    Complex half = new Complex(0.5, 0);
    Complex minushalf = new Complex(-0.5, 0);
    Complex halfi = new Complex(0, 0.5);
    Complex minushalfi = new Complex (0, -0.5);
    TwoByTwoMatrix m1;
    TwoByTwoMatrix m2;
    FourByFourMatrix matrix;
    TwoQubit q1;
    TwoQubit q2;

    @BeforeEach
    public void runBefore() {
        q1 = new TwoQubit(one, zero, zero, zero);
        q2 = new TwoQubit(halfi, minushalf, half, minushalfi);
        m1 = new TwoByTwoMatrix();
        m2 = new TwoByTwoMatrix();
    }

    @Test
    public void testPureStateAmplitudeAndProbability() {
        assertEquals(one, q1.getAmplitude(0));
        assertEquals(zero, q1.getAmplitude(1));
        assertEquals(zero, q1.getAmplitude(2));
        assertEquals(zero, q1.getAmplitude(3));
        assertEquals(1, q1.getProbability(0));
        assertEquals(0, q1.getProbability(1));
        assertEquals(0, q1.getProbability(2));
        assertEquals(0, q1.getProbability(3));
    }

    @Test
    public void testMixedStateAmplitudeAndProbability() {
        assertEquals(halfi, q2.getAmplitude(0));
        assertEquals(minushalf, q2.getAmplitude(1));
        assertEquals(half, q2.getAmplitude(2));
        assertEquals(minushalfi, q2.getAmplitude(3));
        for (int i = 0; i < 4; i++) {
            assertEquals(0.25, q2.getProbability(i), delta);
        }
    }

    @Test
    public void testCNOTPureState() {
        q1.applyCnot();
        for (int i = 0; i < 4; i++) {
            assertEquals(0, q1.getAmplitude(i).getImaginary());
            if (i == 0) {
                assertEquals(1, q1.getAmplitude(i).getReal());
            } else {
                assertEquals(0, q1.getAmplitude(i).getReal());
            }
        }
        assertEquals(1, q1.getProbability(0));
        assertEquals(0, q1.getProbability(1));
        assertEquals(0, q1.getProbability(2));
        assertEquals(0, q1.getProbability(3));
    }

    @Test
    public void testCNOTMixedState() {
        q2.applyCnot();
        assertEquals(0, q2.getAmplitude(0).getReal());
        assertEquals(0.5, q2.getAmplitude(0).getImaginary());
        assertEquals(-0.5, q2.getAmplitude(1).getReal());
        assertEquals(0, q2.getAmplitude(1).getImaginary());
        assertEquals(0, q2.getAmplitude(2).getReal());
        assertEquals(-0.5, q2.getAmplitude(2).getImaginary());
        assertEquals(0.5, q2.getAmplitude(3).getReal());
        assertEquals(0, q2.getAmplitude(3).getImaginary());
        for (int i = 0; i < 4; i++) {
            assertEquals(0.25, q2.getProbability(i), delta);
        }
    }

    @Test
    public void testIdentityIdentityPureState() {
        m1.setIdentityGate();
        q1.applyGates(m1, m1);
        for (int i = 0; i < 4; i++) {
            assertEquals(0, q1.getAmplitude(i).getImaginary());
            if (i == 0) {
                assertEquals(1, q1.getAmplitude(i).getReal());
            } else {
                assertEquals(0, q1.getAmplitude(i).getReal());
            }
        }
        assertEquals(1, q1.getProbability(0));
        assertEquals(0, q1.getProbability(1));
        assertEquals(0, q1.getProbability(2));
        assertEquals(0, q1.getProbability(3));
    }

    @Test
    public void testIdentityIdentityMixedState() {
        m1.setIdentityGate();
        q2.applyGates(m1, m1);
        assertEquals(0, q2.getAmplitude(0).getReal());
        assertEquals(0.5, q2.getAmplitude(0).getImaginary());
        assertEquals(-0.5, q2.getAmplitude(1).getReal());
        assertEquals(0, q2.getAmplitude(1).getImaginary());
        assertEquals(0.5, q2.getAmplitude(2).getReal());
        assertEquals(0, q2.getAmplitude(2).getImaginary());
        assertEquals(0, q2.getAmplitude(3).getReal());
        assertEquals(-0.5, q2.getAmplitude(3).getImaginary());
        for (int i = 0; i < 4; i++) {
            assertEquals(0.25, q2.getProbability(i), delta);
        }
    }

    @Test
    public void testIdentityPauliXPureState() {
        m1.setIdentityGate();
        m2.setPauliXGate();
        q1.applyGates(m1, m2);
        assertEquals(0, q1.getAmplitude(0).getReal());
        assertEquals(0, q1.getAmplitude(0).getImaginary());
        assertEquals(1, q1.getAmplitude(1).getReal());
        assertEquals(0, q1.getAmplitude(1).getImaginary());
        assertEquals(0, q1.getAmplitude(2).getReal());
        assertEquals(0, q1.getAmplitude(2).getImaginary());
        assertEquals(0, q1.getAmplitude(3).getReal());
        assertEquals(0, q1.getAmplitude(3).getImaginary());
        assertEquals(0, q1.getProbability(0));
        assertEquals(1, q1.getProbability(1));
        assertEquals(0, q1.getProbability(2));
        assertEquals(0, q1.getProbability(3));
    }


    @Test
    public void testIdentityPauliXMixedState() {
        m1.setIdentityGate();
        m2.setPauliXGate();
        q2.applyGates(m1, m2);
        assertEquals(-0.5, q2.getAmplitude(0).getReal());
        assertEquals(0, q2.getAmplitude(0).getImaginary());
        assertEquals(0, q2.getAmplitude(1).getReal());
        assertEquals(0.5, q2.getAmplitude(1).getImaginary());
        assertEquals(0, q2.getAmplitude(2).getReal());
        assertEquals(-0.5, q2.getAmplitude(2).getImaginary());
        assertEquals(0.5, q2.getAmplitude(3).getReal());
        assertEquals(0, q2.getAmplitude(3).getImaginary());
        for (int i = 0; i < 4; i++) {
            assertEquals(0.25, q2.getProbability(i), delta);
        }

    }

    @Test
    public void testHadamardSgatePureState() {
        m1.setHadamardGate();
        m2.setSGate();
        q1.applyGates(m1, m2);
        assertEquals(Math.pow(0.5, 0.5), q1.getAmplitude(0).getReal());
        assertEquals(0, q1.getAmplitude(0).getImaginary());
        assertEquals(0, q1.getAmplitude(1).getReal());
        assertEquals(0, q1.getAmplitude(1).getImaginary());
        assertEquals(Math.pow(0.5, 0.5), q1.getAmplitude(2).getReal());
        assertEquals(0, q1.getAmplitude(2).getImaginary());
        assertEquals(0, q1.getAmplitude(3).getReal());
        assertEquals(0, q1.getAmplitude(3).getImaginary());
        assertEquals(0.5, q1.getProbability(0), delta);
        assertEquals(0, q1.getProbability(1));
        assertEquals(0.5, q1.getProbability(2), delta);
        assertEquals(0, q1.getProbability(3));

    }

    @Test
    public void testHadamardSgateMixedState() {
        m1.setHadamardGate();
        m2.setSGate();
        q2.applyGates(m1, m2);
        assertEquals(Math.pow(0.5, 0.5)/2, q2.getAmplitude(0).getReal(), delta);
        assertEquals(Math.pow(0.5, 0.5)/2, q2.getAmplitude(0).getImaginary(), delta);
        assertEquals(Math.pow(0.5, 0.5)/2, q2.getAmplitude(1).getReal(), delta);
        assertEquals(-Math.pow(0.5, 0.5)/2, q2.getAmplitude(1).getImaginary(), delta);
        assertEquals(-Math.pow(0.5, 0.5)/2, q2.getAmplitude(2).getReal(), delta);
        assertEquals(Math.pow(0.5, 0.5)/2, q2.getAmplitude(2).getImaginary(), delta);
        assertEquals(-Math.pow(0.5, 0.5)/2, q2.getAmplitude(3).getReal(), delta);
        assertEquals(-Math.pow(0.5, 0.5)/2, q2.getAmplitude(3).getImaginary(), delta);
        for (int i = 0; i < 4; i++) {
            assertEquals(0.25, q2.getProbability(i), delta);
        }
    }

    @Test
    public void testCollapseToZeroZeroStateMeasurement() {
        q1.collapseAfterMeasurement(0);
        q2.collapseAfterMeasurement(0);
        TwoQubit[] qubitlist = {q1, q2};
        for (TwoQubit q: qubitlist) {
            for (int i = 0; i < 4; i++) {
                assertEquals(0, q.getAmplitude(i).getImaginary());
                if (i == 0) {
                    assertEquals(1, q.getAmplitude(i).getReal());
                } else {
                    assertEquals(0, q.getAmplitude(i).getReal());
                }
            }
            assertEquals(1, q.getProbability(0));
            assertEquals(0, q.getProbability(1));
            assertEquals(0, q.getProbability(2));
            assertEquals(0, q.getProbability(3));
        }
    }

    @Test
    public void testCollapseToZeroOneStateMeasurement() {
        q1.collapseAfterMeasurement(1);
        q2.collapseAfterMeasurement(1);
        TwoQubit[] qubitlist = {q1, q2};
        for (TwoQubit q: qubitlist) {
            for (int i = 0; i < 4; i++) {
                assertEquals(0, q.getAmplitude(i).getImaginary());
                if (i == 1) {
                    assertEquals(1, q.getAmplitude(i).getReal());
                } else {
                    assertEquals(0, q.getAmplitude(i).getReal());
                }
            }
            assertEquals(0, q.getProbability(0));
            assertEquals(1, q.getProbability(1));
            assertEquals(0, q.getProbability(2));
            assertEquals(0, q.getProbability(3));
        }
    }

    @Test
    public void testCollapseToOneZeroStateMeasurement() {
        q1.collapseAfterMeasurement(2);
        q2.collapseAfterMeasurement(2);
        TwoQubit[] qubitlist = {q1, q2};
        for (TwoQubit q: qubitlist) {
            for (int i = 0; i < 4; i++) {
                assertEquals(0, q.getAmplitude(i).getImaginary());
                if (i == 2) {
                    assertEquals(1, q.getAmplitude(i).getReal());
                } else {
                    assertEquals(0, q.getAmplitude(i).getReal());
                }
            }
            assertEquals(0, q.getProbability(0));
            assertEquals(0, q.getProbability(1));
            assertEquals(1, q.getProbability(2));
            assertEquals(0, q.getProbability(3));
        }
    }

    @Test
    public void testCollapseToOneOneStateMeasurement() {
        q1.collapseAfterMeasurement(3);
        q2.collapseAfterMeasurement(3);
        TwoQubit[] qubitlist = {q1, q2};
        for (TwoQubit q: qubitlist) {
            for (int i = 0; i < 4; i++) {
                assertEquals(0, q.getAmplitude(i).getImaginary());
                if (i == 3) {
                    assertEquals(1, q.getAmplitude(i).getReal());
                } else {
                    assertEquals(0, q.getAmplitude(i).getReal());
                }
            }
            assertEquals(0, q.getProbability(0));
            assertEquals(0, q.getProbability(1));
            assertEquals(0, q.getProbability(2));
            assertEquals(1, q.getProbability(3));
        }
    }


}
