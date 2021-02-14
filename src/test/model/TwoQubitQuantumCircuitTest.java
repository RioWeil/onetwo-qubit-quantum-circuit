package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

// Test Class for TwoQubitQuantumCircuit
public class TwoQubitQuantumCircuitTest {
    Complex zero  = new Complex(0, 0);
    Complex one = new Complex(1, 0);
    TwoQubitQuantumCircuit qc1;
    TwoQubitQuantumCircuit qc2;
    String part1;
    String part2;

    @BeforeEach
    public void runBefore() {
        qc1 = new TwoQubitQuantumCircuit(one, zero, zero, zero);
    }

    @Test
    public void testApplyCnot() {
        qc1.addGate("CN");
        qc1.applyGate();
        part1 = "The current state is (1.000 + 0.000i)|00> + (0.000 + 0.000i)|01>";
        part2 = " + (0.000 + 0.000i)|10> + (0.000 + 0.000i)|11>.";
        assertEquals(part1 + part2, qc1.returnState());
    }

    @Test
    public void testApplyIdentityIdentity() {
        qc1.addGate("II");
        qc1.applyGate();
        part1 = "The current state is (1.000 + 0.000i)|00> + (0.000 + 0.000i)|01>";
        part2 = " + (0.000 + 0.000i)|10> + (0.000 + 0.000i)|11>.";
        assertEquals(part1 + part2, qc1.returnState());
    }

    @Test
    public void testApplyHadamardPauliX() {
        qc1.addGate("HX");
        qc1.applyGate();
        part1 = "The current state is (0.000 + 0.000i)|00> + (0.707 + 0.000i)|01>";
        part2 = " + (0.000 + 0.000i)|10> + (0.707 + 0.000i)|11>.";
        assertEquals(part1 + part2, qc1.returnState());
    }

    @Test
    public void testApplyPauliYTGate() {
        qc1.addGate("YT");
        qc1.applyGate();
        part1 = "The current state is (0.000 + 0.000i)|00> + (0.000 + 0.000i)|01>";
        part2 = " + (0.000 + -1.000i)|10> + (0.000 + 0.000i)|11>.";
        assertEquals(part1 + part2, qc1.returnState());
    }

    @Test
    public void testApplySGatePauliZ() {
        qc1.addGate("SZ");
        qc1.applyGate();
        part1 = "The current state is (1.000 + 0.000i)|00> + (0.000 + 0.000i)|01>";
        part2 = " + (0.000 + 0.000i)|10> + (0.000 + 0.000i)|11>.";
        assertEquals(part1 + part2, qc1.returnState());
    }

    @Test
    public void testApplyMultiple() {
        qc1.addGate("HI");
        qc1.addGate("CN");
        qc1.applyAllGates();
        part1 = "The current state is (0.707 + 0.000i)|00> + (0.000 + 0.000i)|01>";
        part2 = " + (0.000 + 0.000i)|10> + (0.707 + 0.000i)|11>.";
        assertEquals(part1 + part2, qc1.returnState());
    }

    @Test
    public void testReturnStatePureState() {
        part1 = "The current state is (1.000 + 0.000i)|00> + (0.000 + 0.000i)|01>";
        part2 = " + (0.000 + 0.000i)|10> + (0.000 + 0.000i)|11>.";
        assertEquals(part1 + part2, qc1.returnState());
    }

    @Test
    public void testReturnStateMixedState() {
        Complex half = new Complex(0.5, 0);
        Complex minushalf = new Complex(-0.5, 0);
        Complex halfi = new Complex(0, 0.5);
        Complex minushalfi = new Complex (0, -0.5);
        qc2 = new TwoQubitQuantumCircuit(half, minushalf, halfi, minushalfi);
        part1 = "The current state is (0.500 + 0.000i)|00> + (-0.500 + 0.000i)|01>";
        part2 = " + (0.000 + 0.500i)|10> + (0.000 + -0.500i)|11>.";
        assertEquals(part1 + part2, qc2.returnState());
    }

    @Test
    public void testReturnProbabilitiesPureState() {
        part1 = "The measurement probabilities are 1.000 for |00>, 0.000 for |01>, ";
        part2 = "0.000 for |10>, 0.000 for |11>.";
        assertEquals(part1 + part2, qc1.returnProbabilities());
    }

    @Test
    public void testReturnProbabilitiesMixedState() {
        Complex half = new Complex(0.5, 0);
        qc2 = new TwoQubitQuantumCircuit(half, half, half, half);
        part1 = "The measurement probabilities are 0.500 for |00>, 0.500 for |01>, ";
        part2 = "0.500 for |10>, 0.500 for |11>.";
        assertEquals(part1 + part2, qc2.returnProbabilities());
    }

    @Test
    public void testMeasurementPureZeroZeroState() {
        assertEquals("You measured the system to be in the |00> state!", qc1.makeMeasurement());
        part1 = "The current state is (1.000 + 0.000i)|00> + (0.000 + 0.000i)|01>";
        part2 = " + (0.000 + 0.000i)|10> + (0.000 + 0.000i)|11>.";
        assertEquals(part1 + part2, qc1.returnState());
    }

    @Test
    public void testMeasurementPureZeroOneState() {
        qc2 = new TwoQubitQuantumCircuit(zero, one, zero, zero);
        assertEquals("You measured the system to be in the |01> state!", qc2.makeMeasurement());
        part1 = "The current state is (0.000 + 0.000i)|00> + (1.000 + 0.000i)|01>";
        part2 = " + (0.000 + 0.000i)|10> + (0.000 + 0.000i)|11>.";
        assertEquals(part1 + part2, qc2.returnState());
    }

    @Test
    public void testMeasurementPureOneZeroState() {
        qc2 = new TwoQubitQuantumCircuit(zero, zero, one, zero);
        assertEquals("You measured the system to be in the |10> state!", qc2.makeMeasurement());
        part1 = "The current state is (0.000 + 0.000i)|00> + (0.000 + 0.000i)|01>";
        part2 = " + (1.000 + 0.000i)|10> + (0.000 + 0.000i)|11>.";
        assertEquals(part1 + part2, qc2.returnState());
    }

    @Test
    public void testMeasurementPureOneOneState() {
        qc2 = new TwoQubitQuantumCircuit(zero, zero, zero, one);
        assertEquals("You measured the system to be in the |11> state!", qc2.makeMeasurement());
        part1 = "The current state is (0.000 + 0.000i)|00> + (0.000 + 0.000i)|01>";
        part2 = " + (0.000 + 0.000i)|10> + (1.000 + 0.000i)|11>.";
        assertEquals(part1 + part2, qc2.returnState());
    }

    @Test
    public void testMeasurementMixedState() {
        Complex oneroottwo = new Complex(Math.pow(0.5, 0.5), 0);
        Random r = new Random();
        int seed = r.nextInt();
        r = new Random(seed);
        double randval = r.nextDouble();
        qc2 = new TwoQubitQuantumCircuit(oneroottwo, oneroottwo, oneroottwo, oneroottwo, seed);
        double prob00 = qc2.qubits.getProbability(0);
        double prob01 = qc2.qubits.getProbability(1);
        double prob10 = qc2.qubits.getProbability(2);
        if (0 < randval && randval < prob00) {
            assertEquals("You measured the system to be in the |00> state!", qc2.makeMeasurement());
            part1 = "The current state is (1.000 + 0.000i)|00> + (0.000 + 0.000i)|01>";
            part2 = " + (0.000 + 0.000i)|10> + (0.000 + 0.000i)|11>.";
        } else if (prob00 < randval && randval < prob00 + prob01) {
            assertEquals("You measured the system to be in the |01> state!", qc2.makeMeasurement());
            part1 = "The current state is (0.000 + 0.000i)|00> + (1.000 + 0.000i)|01>";
            part2 = " + (0.000 + 0.000i)|10> + (0.000 + 0.000i)|11>.";
        } else if (prob00 + prob01 < randval && randval < prob00 + prob01 + prob10) {
            assertEquals("You measured the system to be in the |10> state!", qc2.makeMeasurement());
            part1 = "The current state is (0.000 + 0.000i)|00> + (0.000 + 0.000i)|01>";
            part2 = " + (1.000 + 0.000i)|10> + (0.000 + 0.000i)|11>.";
        } else {
            assertEquals("You measured the system to be in the |11> state!", qc2.makeMeasurement());
            part1 = "The current state is (0.000 + 0.000i)|00> + (0.000 + 0.000i)|01>";
            part2 = " + (0.000 + 0.000i)|10> + (1.000 + 0.000i)|11>.";
        }
        assertEquals(part1 + part2, qc2.returnState());
    }
}
