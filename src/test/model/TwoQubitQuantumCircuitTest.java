package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test Class for TwoQubitQuantumCircuit
public class TwoQubitQuantumCircuitTest {
    Complex zero  = new Complex(0, 0);
    Complex one = new Complex(1, 0);
    TwoQubitQuantumCircuit qc1;
    TwoQubit qc2;

    @BeforeEach
    public void runBefore() {
        qc1 = new TwoQubitQuantumCircuit(one, zero, zero, zero);
    }

    @Test
    public void testReturnStatePureState() {

    }

    @Test
    public void testReturnStateMixedState() {

    }

    @Test
    public void testReturnProbabilitiesPureState() {

    }

    @Test
    public void testReturnProbabilitiesMixedState() {

    }

    @Test
    public void testMeasurementPureZeroZeroState() {

    }

    @Test
    public void testMeasurementPureZeroOneState() {

    }

    @Test
    public void testMeasurementPureOneZeroState() {

    }

    @Test
    public void testMeasurementPureOneOneState() {

    }

    @Test
    public void testMeasurementMixedState() {

    }
}
