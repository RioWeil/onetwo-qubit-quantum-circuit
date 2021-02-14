package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OneQubitTest {
    Complex zero;
    Complex one;
    OneQubit q1;
    OneQubit q2;
    OneQubit q3;
    TwoByTwoMatrix matrix;
    double delta = 0.0000001;

    @BeforeEach
    public void runBefore() {
        zero = new Complex(0, 0);
        one = new Complex(1, 0);
        q1 = new OneQubit(one, zero);
        q2 = new OneQubit(zero, one);
        q3 = new OneQubit(new Complex(-Math.pow(0.5, 0.5), 0), new Complex(0, Math.pow(0.5, 0.5)));
        matrix = new TwoByTwoMatrix();
    }

    @Test
    public void testPureStateAmplitudeAndProbability() {
        assertEquals(one, q1.getAmplitude(0));
        assertEquals(zero, q1.getAmplitude(1));
        assertEquals(1, q1.getProbability(0));
        assertEquals(0, q1.getProbability(1));
        assertEquals(zero, q2.getAmplitude(0));
        assertEquals(one, q2.getAmplitude(1));
        assertEquals(0, q2.getProbability(0));
        assertEquals(1, q2.getProbability(1));
    }

    @Test
    public void testNonPureStateAmplitudeAndProbability() {
        Complex zeroamp = q3.getAmplitude(0);
        Complex oneamp = q3.getAmplitude(1);
        assertEquals(-Math.pow(0.5, 0.5), zeroamp.getReal());
        assertEquals(0, zeroamp.getImaginary());
        assertEquals(0, oneamp.getReal());
        assertEquals(Math.pow(0.5, 0.5), oneamp.getImaginary());
        assertEquals(0.5, q3.getProbability(0), delta);
        assertEquals(0.5, q3.getProbability(1), delta);
    }

    @Test
    public void testApplyPauliXAmplitudeAndProbability() {
        matrix.setPauliXGate();
        q1.applyGate(matrix);
        q2.applyGate(matrix);
        q3.applyGate(matrix);
        assertEquals(0, q1.getAmplitude(0).getReal());
        assertEquals(0, q1.getAmplitude(0).getImaginary());
        assertEquals(1, q1.getAmplitude(1).getReal());
        assertEquals(0, q1.getAmplitude(1).getImaginary());
        assertEquals(0, q1.getProbability(0));
        assertEquals(1, q1.getProbability(1));
        assertEquals(1, q2.getAmplitude(0).getReal());
        assertEquals(0, q2.getAmplitude(0).getImaginary());
        assertEquals(0, q2.getAmplitude(1).getReal());
        assertEquals(0, q2.getAmplitude(1).getImaginary());
        assertEquals(1, q2.getProbability(0));
        assertEquals(0, q2.getProbability(1));
        assertEquals(0, q3.getAmplitude(0).getReal(), delta);
        assertEquals(Math.pow(0.5, 0.5), q3.getAmplitude(0).getImaginary(), delta);
        assertEquals(-Math.pow(0.5, 0.5), q3.getAmplitude(1).getReal(), delta);
        assertEquals(0, q3.getAmplitude(1).getImaginary(), delta);
        assertEquals(0.5, q3.getProbability(0), delta);
        assertEquals(0.5, q3.getProbability(1), delta);
    }

    @Test
    public void testApplyPauliYAmplitudeAndProbability() {
        matrix.setPauliYGate();
        q1.applyGate(matrix);
        q2.applyGate(matrix);
        q3.applyGate(matrix);
        assertEquals(0, q1.getAmplitude(0).getReal());
        assertEquals(0, q1.getAmplitude(0).getImaginary());
        assertEquals(0, q1.getAmplitude(1).getReal());
        assertEquals(1, q1.getAmplitude(1).getImaginary());
        assertEquals(0, q1.getProbability(0));
        assertEquals(1, q1.getProbability(1));
        assertEquals(0, q2.getAmplitude(0).getReal());
        assertEquals(-1, q2.getAmplitude(0).getImaginary());
        assertEquals(0, q2.getAmplitude(1).getReal());
        assertEquals(0, q2.getAmplitude(1).getImaginary());
        assertEquals(1, q2.getProbability(0));
        assertEquals(0, q2.getProbability(1));
        assertEquals(Math.pow(0.5, 0.5), q3.getAmplitude(0).getReal(), delta);
        assertEquals(0, q3.getAmplitude(0).getImaginary(), delta);
        assertEquals(0, q3.getAmplitude(1).getReal(), delta);
        assertEquals(-Math.pow(0.5, 0.5), q3.getAmplitude(1).getImaginary(), delta);
        assertEquals(0.5, q3.getProbability(0), delta);
        assertEquals(0.5, q3.getProbability(1), delta);
    }

    @Test
    public void testApplyPauliZAmplitudeAndProbability() {
        matrix.setPauliZGate();
        q1.applyGate(matrix);
        q2.applyGate(matrix);
        q3.applyGate(matrix);
        assertEquals(1, q1.getAmplitude(0).getReal());
        assertEquals(0, q1.getAmplitude(0).getImaginary());
        assertEquals(0, q1.getAmplitude(1).getReal());
        assertEquals(0, q1.getAmplitude(1).getImaginary());
        assertEquals(1, q1.getProbability(0));
        assertEquals(0, q1.getProbability(1));
        assertEquals(0, q2.getAmplitude(0).getReal());
        assertEquals(0, q2.getAmplitude(0).getImaginary());
        assertEquals(-1, q2.getAmplitude(1).getReal());
        assertEquals(0, q2.getAmplitude(1).getImaginary());
        assertEquals(0, q2.getProbability(0));
        assertEquals(1, q2.getProbability(1));
        assertEquals(-Math.pow(0.5, 0.5), q3.getAmplitude(0).getReal(), delta);
        assertEquals(0, q3.getAmplitude(0).getImaginary(), delta);
        assertEquals(0, q3.getAmplitude(1).getReal(), delta);
        assertEquals(-Math.pow(0.5, 0.5), q3.getAmplitude(1).getImaginary(), delta);
        assertEquals(0.5, q3.getProbability(0), delta);
        assertEquals(0.5, q3.getProbability(1), delta);
    }

    @Test
    public void testApplySGateAmplitudeAndProbability() {
        matrix.setSGate();
        q1.applyGate(matrix);
        q2.applyGate(matrix);
        q3.applyGate(matrix);
        assertEquals(1, q1.getAmplitude(0).getReal());
        assertEquals(0, q1.getAmplitude(0).getImaginary());
        assertEquals(0, q1.getAmplitude(1).getReal());
        assertEquals(0, q1.getAmplitude(1).getImaginary());
        assertEquals(1, q1.getProbability(0));
        assertEquals(0, q1.getProbability(1));
        assertEquals(0, q2.getAmplitude(0).getReal());
        assertEquals(0, q2.getAmplitude(0).getImaginary());
        assertEquals(0, q2.getAmplitude(1).getReal());
        assertEquals(1, q2.getAmplitude(1).getImaginary());
        assertEquals(0, q2.getProbability(0));
        assertEquals(1, q2.getProbability(1));
        assertEquals(-Math.pow(0.5, 0.5), q3.getAmplitude(0).getReal(), delta);
        assertEquals(0, q3.getAmplitude(0).getImaginary(), delta);
        assertEquals(-Math.pow(0.5, 0.5), q3.getAmplitude(1).getReal(), delta);
        assertEquals(0, q3.getAmplitude(1).getImaginary(), delta);
        assertEquals(0.5, q3.getProbability(0), delta);
        assertEquals(0.5, q3.getProbability(1), delta);
    }

    @Test
    public void testApplyTGateAmplitudeAndProbability() {
        matrix.setTGate();
        q1.applyGate(matrix);
        q2.applyGate(matrix);
        q3.applyGate(matrix);
        assertEquals(1, q1.getAmplitude(0).getReal());
        assertEquals(0, q1.getAmplitude(0).getImaginary());
        assertEquals(0, q1.getAmplitude(1).getReal());
        assertEquals(0, q1.getAmplitude(1).getImaginary());
        assertEquals(1, q1.getProbability(0));
        assertEquals(0, q1.getProbability(1));
        assertEquals(0, q2.getAmplitude(0).getReal());
        assertEquals(0, q2.getAmplitude(0).getImaginary());
        assertEquals(Math.pow(0.5, 0.5), q2.getAmplitude(1).getReal());
        assertEquals(Math.pow(0.5, 0.5), q2.getAmplitude(1).getImaginary());
        assertEquals(0, q2.getProbability(0));
        assertEquals(1, q2.getProbability(1));
        assertEquals(-Math.pow(0.5, 0.5), q3.getAmplitude(0).getReal(), delta);
        assertEquals(0, q3.getAmplitude(0).getImaginary(), delta);
        assertEquals(-0.5, q3.getAmplitude(1).getReal(), delta);
        assertEquals(0.5, q3.getAmplitude(1).getImaginary(), delta);
        assertEquals(0.5, q3.getProbability(0), delta);
        assertEquals(0.5, q3.getProbability(1), delta);
    }

    @Test
    public void testApplyIdentityGateAmplitudeAndProbability() {
        matrix.setIdentityGate();
        q1.applyGate(matrix);
        q2.applyGate(matrix);
        q3.applyGate(matrix);
        assertEquals(1, q1.getAmplitude(0).getReal());
        assertEquals(0, q1.getAmplitude(0).getImaginary());
        assertEquals(0, q1.getAmplitude(1).getReal());
        assertEquals(0, q1.getAmplitude(1).getImaginary());
        assertEquals(1, q1.getProbability(0));
        assertEquals(0, q1.getProbability(1));
        assertEquals(0, q2.getAmplitude(0).getReal());
        assertEquals(0, q2.getAmplitude(0).getImaginary());
        assertEquals(1, q2.getAmplitude(1).getReal());
        assertEquals(0, q2.getAmplitude(1).getImaginary());
        assertEquals(0, q2.getProbability(0));
        assertEquals(1, q2.getProbability(1));
        assertEquals(-Math.pow(0.5, 0.5), q3.getAmplitude(0).getReal(), delta);
        assertEquals(0, q3.getAmplitude(0).getImaginary(), delta);
        assertEquals(0, q3.getAmplitude(1).getReal(), delta);
        assertEquals(Math.pow(0.5, 0.5), q3.getAmplitude(1).getImaginary(), delta);
        assertEquals(0.5, q3.getProbability(0), delta);
        assertEquals(0.5, q3.getProbability(1), delta);
    }

    @Test
    public void testApplyHadamardGateAmplitudeAndProbability() {
        matrix.setHadamardGate();
        q1.applyGate(matrix);
        q2.applyGate(matrix);
        q3.applyGate(matrix);
        assertEquals(Math.pow(0.5, 0.5), q1.getAmplitude(0).getReal(), delta);
        assertEquals(0, q1.getAmplitude(0).getImaginary(), delta);
        assertEquals(Math.pow(0.5, 0.5), q1.getAmplitude(1).getReal(), delta);
        assertEquals(0, q1.getAmplitude(1).getImaginary(), delta);
        assertEquals(0.5, q1.getProbability(0), delta);
        assertEquals(0.5, q1.getProbability(1), delta);
        assertEquals(Math.pow(0.5, 0.5), q2.getAmplitude(0).getReal(), delta);
        assertEquals(0, q2.getAmplitude(0).getImaginary(), delta);
        assertEquals(-Math.pow(0.5, 0.5), q2.getAmplitude(1).getReal(), delta);
        assertEquals(0, q2.getAmplitude(1).getImaginary(), delta);
        assertEquals(0.5, q2.getProbability(0), delta);
        assertEquals(0.5, q2.getProbability(1), delta);
        assertEquals(-0.5, q3.getAmplitude(0).getReal(), delta);
        assertEquals(0.5, q3.getAmplitude(0).getImaginary(), delta);
        assertEquals(-0.5, q3.getAmplitude(1).getReal(), delta);
        assertEquals(-0.5, q3.getAmplitude(1).getImaginary(), delta);
        assertEquals(0.5, q3.getProbability(0), delta);
        assertEquals(0.5, q3.getProbability(1), delta);
    }

    @Test
    public void testCollapseToZeroStateMeasurement() {
        q1.collapseAfterMeasurement(0);
        q2.collapseAfterMeasurement(0);
        q3.collapseAfterMeasurement(0);
        OneQubit[] qubitlist = {q1, q2, q3};
        for (OneQubit q: qubitlist) {
            assertEquals(1, q.getAmplitude(0).getReal());
            assertEquals(0, q.getAmplitude(0).getImaginary());
            assertEquals(0, q.getAmplitude(1).getReal());
            assertEquals(0, q.getAmplitude(1).getImaginary());
        }
    }

    @Test
    public void testCollapseToOneStateMeasurement() {
        q1.collapseAfterMeasurement(1);
        q2.collapseAfterMeasurement(1);
        q3.collapseAfterMeasurement(1);
        OneQubit[] qubitlist = {q1, q2, q3};
        for (OneQubit q: qubitlist) {
            assertEquals(0, q.getAmplitude(0).getReal());
            assertEquals(0, q.getAmplitude(0).getImaginary());
            assertEquals(1, q.getAmplitude(1).getReal());
            assertEquals(0, q.getAmplitude(1).getImaginary());
        }
    }

}
