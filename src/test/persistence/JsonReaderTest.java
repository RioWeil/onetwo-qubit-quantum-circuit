package persistence;
import exceptions.WrongQubitNumberException;

import model.OneQubitQuantumCircuit;
import model.TwoQubitQuantumCircuit;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    // Citation: Taken from JsonSerializationDemo
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            OneQubitQuantumCircuit oqqc = reader.readOne();
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
            OneQubitQuantumCircuit oqqc = reader.readOne();
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
            TwoQubitQuantumCircuit oqqc = reader.readTwo();
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
            OneQubitQuantumCircuit oqqc = reader.readOne();
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
            OneQubitQuantumCircuit oqqc = reader.readOne();
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
            TwoQubitQuantumCircuit tqqc = reader.readTwo();
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
            TwoQubitQuantumCircuit tqqc = reader.readTwo();
        } catch (IOException e) {
            fail("IOException should not be thrown.");
        } catch (WrongQubitNumberException e) {
            fail("WrongQubitNumberException should not be thrown.");
        }

    }


}
