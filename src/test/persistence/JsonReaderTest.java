package persistence;
import exceptions.WrongQubitNumberException;

import model.OneQubitQuantumCircuit;
import model.TwoQubitQuantumCircuit;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {
    OneQubitQuantumCircuit qc1;
    TwoQubitQuantumCircuit qc2;
    String part1;
    String part2;

    // Citation: Taken from JsonSerializationDemo
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            qc1 = reader.readOne();
            fail("IOException expected, but method ran.");
        } catch (IOException e) {
            // pass
        } catch (WrongQubitNumberException e) {
            fail("IOException expected, not WrongQubitNumberException");
        }
    }

    @Test
    void testReaderOneQubitReaderOnTwoQubitFile() {
        JsonReader reader = new JsonReader("./data/testReaderTwoQubitQuantumCircuitNoGates.json");
        try {
            qc1 = reader.readOne();
            fail("WrongQubitNumberException expected, but method ran.");
        } catch (IOException e) {
            fail("WrongQubitNumberException expected, not IOException");
        } catch (WrongQubitNumberException e) {
            // pass
        }
    }

    @Test
    void testReaderTwoQubitReaderOnOneQubitFile() {
        JsonReader reader = new JsonReader("./data/testReaderOneQubitQuantumCircuitNoGates.json");
        try {
            qc2 = reader.readTwo();
            fail("WrongQubitNumberException expected, but method ran.");
        } catch (IOException e) {
            fail("WrongQubitNumberException expected, not IOException");
        } catch (WrongQubitNumberException e) {
            // pass
        }
    }

    @Test
    void testReaderOneQubitQuantumCircuitNoGates() {
        JsonReader reader = new JsonReader("./data/testReaderOneQubitQuantumCircuitNoGates.json");
        try {
            qc1 = reader.readOne();
            assertEquals("The current state is (0.000 + 0.000i)|0> + (0.000 + 1.000i)|1>.", qc1.returnState());
            assertEquals("No gates in list.", qc1.seeFirstGate());
        } catch (IOException e) {
            fail("IOException should not be thrown.");
        } catch (WrongQubitNumberException e) {
            fail("WrongQubitNumberException should not be thrown.");
        }

    }

    @Test
    void testReaderOneQubitQuantumCircuitWithGates() {
        JsonReader reader = new JsonReader("./data/testReaderOneQubitQuantumCircuitWithGates.json");
        try {
            OneQubitQuantumCircuit qc1 = reader.readOne();
            assertEquals("The current state is (0.707 + 0.000i)|0> + (0.707 + 0.000i)|1>.", qc1.returnState());
            assertEquals("Removed H gate.", qc1.removeGate());
            assertEquals("Removed T gate.", qc1.removeGate());
            assertEquals("No gates in list.", qc1.seeFirstGate());
        } catch (IOException e) {
            fail("IOException should not be thrown.");
        } catch (WrongQubitNumberException e) {
            fail("WrongQubitNumberException should not be thrown.");
        }

    }

    @Test
    void testReaderTwoQubitQuantumCircuitNoGates() {
        JsonReader reader = new JsonReader("./data/testReaderTwoQubitQuantumCircuitNoGates.json");
        try {
            TwoQubitQuantumCircuit qc2 = reader.readTwo();
            part1 = "The current state is (0.000 + 1.000i)|00> + (0.000 + 0.000i)|01>";
            part2 = " + (0.000 + 0.000i)|10> + (0.000 + 0.000i)|11>.";
            assertEquals(part1 + part2, qc2.returnState());
            assertEquals("No gates in list.", qc2.seeFirstGate());
        } catch (IOException e) {
            fail("IOException should not be thrown.");
        } catch (WrongQubitNumberException e) {
            fail("WrongQubitNumberException should not be thrown.");
        }

    }

    @Test
    void testReaderTwoQubitQuantumCircuitWithGates() {
        JsonReader reader = new JsonReader("./data/testReaderTwoQubitQuantumCircuitWithGates.json");
        try {
            TwoQubitQuantumCircuit qc2 = reader.readTwo();
            part1 = "The current state is (0.500 + 0.000i)|00> + (0.500 + 0.000i)|01>";
            part2 = " + (0.500 + 0.000i)|10> + (0.500 + 0.000i)|11>.";
            assertEquals(part1 + part2, qc2.returnState());
            assertEquals("Removed XY gate.", qc2.removeGate());
            assertEquals("Removed CN gate.", qc2.removeGate());
            assertEquals("No gates in list.", qc2.seeFirstGate());
        } catch (IOException e) {
            fail("IOException should not be thrown.");
        } catch (WrongQubitNumberException e) {
            fail("WrongQubitNumberException should not be thrown.");
        }

    }


}
