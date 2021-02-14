package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

// Test Class for OneQubitQuantumCircuit
public class OneQubitQuantumCircuitTest {
    Complex zero;
    Complex one;
    Complex negoneroottwo;
    Complex ioneroottwo;
    OneQubitQuantumCircuit qc1;
    OneQubitQuantumCircuit qc2;

    @BeforeEach
    public void runBefore() {
        zero = new Complex(0, 0);
        one = new Complex(1, 0);
        qc1 = new OneQubitQuantumCircuit(one, zero);
    }

    @Test
    public void testaddGates() {
        assertTrue(qc1.gates.isEmpty());
        assertEquals("Added X gate to list.", qc1.addGate("X"));
        assertEquals("Added H gate to list.", qc1.addGate("H"));
        assertEquals("Added Z gate to list.", qc1.addGate("Z"));
        assertEquals(3, qc1.gates.length());
    }

    @Test
    public void testSeeFirstGateEmpty() {
        assertEquals("No gates in list.", qc1.seeFirstGate());
    }

    @Test
    public void testSeeFirstGateSingleton() {
        qc1.addGate("X");
        assertEquals("The next gate is X.", qc1.seeFirstGate());
    }

    @Test
    public void testSeeGateMultiple() {
        qc1.addGate("H");
        qc1.addGate("I");
        qc1.addGate("Y");
        assertEquals("The next gate is H.", qc1.seeFirstGate());
    }

    @Test
    public void testRemoveGateEmpty() {
        assertEquals("No gates in list to remove.", qc1.removeGate());
    }

    @Test
    public void testRemoveGateSingleton() {
        qc1.addGate("S");
        assertEquals(1, qc1.gates.length());
        assertEquals("Removed S gate.", qc1.removeGate());
        assertTrue(qc1.gates.isEmpty());
    }

    @Test
    public void testRemoveGatesMultiple() {
        qc1.addGate("X");
        qc1.addGate("Z");
        assertEquals(2, qc1.gates.length());
        assertEquals("Removed X gate.", qc1.removeGate());
        assertEquals(1, qc1.gates.length());
        assertEquals("Removed Z gate.", qc1.removeGate());
        assertTrue(qc1.gates.isEmpty());
    }

    @Test
    public void testApplyOneGateEmpty() {
        assertEquals("No gates in list to apply.", qc1.applyGate());
    }

    @Test
    public void testApplyPauliXGate() {
        qc1.addGate("X");
        assertEquals("Applied X gate.", qc1.applyGate());
        assertEquals("The current state is (0.000 + 0.000i)|0> + (1.000 + 0.000i)|1>.", qc1.returnState());
        assertTrue(qc1.gates.isEmpty());
    }

    @Test
    public void testApplyPauliYGate() {
        qc1.addGate("Y");
        assertEquals("Applied Y gate.", qc1.applyGate());
        assertEquals("The current state is (0.000 + 0.000i)|0> + (0.000 + 1.000i)|1>.", qc1.returnState());
        assertTrue(qc1.gates.isEmpty());
    }

    @Test
    public void testApplyPauliZGate() {
        qc1.addGate("Z");
        assertEquals("Applied Z gate.", qc1.applyGate());
        assertEquals("The current state is (1.000 + 0.000i)|0> + (0.000 + 0.000i)|1>.", qc1.returnState());
        assertTrue(qc1.gates.isEmpty());
    }

    @Test
    public void testApplySGate() {
        qc1.addGate("S");
        assertEquals("Applied S gate.", qc1.applyGate());
        assertEquals("The current state is (1.000 + 0.000i)|0> + (0.000 + 0.000i)|1>.", qc1.returnState());
        assertTrue(qc1.gates.isEmpty());
    }

    @Test
    public void testApplyTGate() {
        qc1.addGate("T");
        assertEquals("Applied T gate.", qc1.applyGate());
        assertEquals("The current state is (1.000 + 0.000i)|0> + (0.000 + 0.000i)|1>.", qc1.returnState());
        assertTrue(qc1.gates.isEmpty());
    }

    @Test
    public void testApplyIdentityGate() {
        qc1.addGate("T");
        assertEquals("Applied T gate.", qc1.applyGate());
        assertEquals("The current state is (1.000 + 0.000i)|0> + (0.000 + 0.000i)|1>.", qc1.returnState());
        assertTrue(qc1.gates.isEmpty());
    }

    @Test
    public void testApplyHadamardGate() {
        qc1.addGate("H");
        assertEquals("Applied H gate.", qc1.applyGate());
        assertEquals("The current state is (0.707 + 0.000i)|0> + (0.707 + 0.000i)|1>.", qc1.returnState());
        assertTrue(qc1.gates.isEmpty());
    }

    @Test
    public void testApplyMultipleGates() {
        qc1.addGate("H");
        qc1.addGate("Y");
        assertEquals("Applied H gate.", qc1.applyGate());
        assertEquals("Applied Y gate.", qc1.applyGate());
        assertEquals("The current state is (0.000 + -0.707i)|0> + (0.000 + 0.707i)|1>.", qc1.returnState());
        assertTrue(qc1.gates.isEmpty());
    }

    @Test
    public void testApplyAllGatesEmpty() {
        assertEquals("No gates in list to apply.", qc1.applyAllGates());
    }

    @Test
    public void testApplyAllGatesOneGate() {
        qc1.addGate("H");
        assertEquals("Applied all gates in list.", qc1.applyAllGates());
        assertEquals("The current state is (0.707 + 0.000i)|0> + (0.707 + 0.000i)|1>.", qc1.returnState());
        assertTrue(qc1.gates.isEmpty());
    }

    @Test
    public void testApplyAllGatesMultipleGates() {
        qc1.addGate("H");
        qc1.addGate("Y");
        assertEquals("Applied all gates in list.", qc1.applyAllGates());
        assertEquals("The current state is (0.000 + -0.707i)|0> + (0.000 + 0.707i)|1>.", qc1.returnState());
        assertTrue(qc1.gates.isEmpty());
    }

    @Test
    public void testReturnStatePureStates() {
        qc2 = new OneQubitQuantumCircuit(zero, one);
        assertEquals("The current state is (1.000 + 0.000i)|0> + (0.000 + 0.000i)|1>.", qc1.returnState());
        assertEquals("The current state is (0.000 + 0.000i)|0> + (1.000 + 0.000i)|1>.", qc2.returnState());
    }

    @Test
    public void testReturnStateMixedStates() {
        qc2 = new OneQubitQuantumCircuit(new Complex(0.5, -0.5), new Complex (-Math.pow(0.5, 0.5), 0));
        assertEquals("The current state is (0.500 + -0.500i)|0> + (-0.707 + 0.000i)|1>.", qc2.returnState());
    }


    @Test
    public void testReturnProbabilitiesPureStates() {
        qc2 = new OneQubitQuantumCircuit(zero, one);
        assertEquals("The measurement probabilities are 1.000 for |0>, 0.000 for |1>.", qc1.returnProbabilities());
        assertEquals("The measurement probabilities are 0.000 for |0>, 1.000 for |1>.", qc2.returnProbabilities());
    }

    @Test
    public void testReturnProbabilitiesMixedStates() {
        qc2 = new OneQubitQuantumCircuit(new Complex(0.5, -0.5), new Complex (-Math.pow(0.5, 0.5), 0));
        assertEquals("The measurement probabilities are 0.500 for |0>, 0.500 for |1>.", qc2.returnProbabilities());
    }

    @Test
    public void measurementPureZeroState() {
        assertEquals("You measured the system to be in the |0> state!", qc1.makeMeasurement());
        assertEquals("The current state is (1.000 + 0.000i)|0> + (0.000 + 0.000i)|1>.", qc1.returnState());
    }

    @Test
    public void measurementPureOneState() {
        qc2 = new OneQubitQuantumCircuit(zero, one);
        assertEquals("You measured the system to be in the |1> state!", qc2.makeMeasurement());
        assertEquals("The current state is (0.000 + 0.000i)|0> + (1.000 + 0.000i)|1>.", qc2.returnState());
    }

    @Test
    public void measurementMixedState() {
        Complex oneroottwo = new Complex(Math.pow(0.5, 0.5), 0);
        Random r = new Random();
        int seed = r.nextInt();
        r = new Random(seed);
        double randval = r.nextDouble();
        qc2 = new OneQubitQuantumCircuit(oneroottwo, oneroottwo, seed);
        if (randval < qc2.qubit.getProbability(0)) {
            assertEquals("You measured the system to be in the |0> state!", qc2.makeMeasurement());
            assertEquals("The current state is (1.000 + 0.000i)|0> + (0.000 + 0.000i)|1>.", qc2.returnState());
        } else {
            assertEquals("You measured the system to be in the |1> state!", qc2.makeMeasurement());
            assertEquals("The current state is (0.000 + 0.000i)|0> + (1.000 + 0.000i)|1>.", qc2.returnState());
        }
    }


}
