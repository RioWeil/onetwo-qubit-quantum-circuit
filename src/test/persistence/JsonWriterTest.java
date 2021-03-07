package persistence;

import exceptions.WrongQubitNumberException;
import model.Complex;
import model.OneQubitQuantumCircuit;
import model.TwoQubitQuantumCircuit;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Citation: code obtained from JsonWriterTest class in JsonSerializationDemo
//           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Test Class for JsonWriter
public class JsonWriterTest {
    OneQubitQuantumCircuit qc1;
    TwoQubitQuantumCircuit qc2;
    Complex zero = new Complex(0, 0);
    Complex half = new Complex(0.5, 0);
    Complex sqrt2 = new Complex(1/Math.pow(2, 0.5), 0);
    Complex one = new Complex(1, 0);
    Complex icomplex = new Complex(0, 1);
    String part1;
    String part2;

    // Citation: Taken from JsonSerializationDemo
    @Test
    void testWriterInvalidFile() {
        try {
            OneQubitQuantumCircuit qc1 = new OneQubitQuantumCircuit(one, zero);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterOneQubitQuantumCircuitNoGates() {
        try {
            qc1 = new OneQubitQuantumCircuit(zero, icomplex);
            JsonWriter writer = new JsonWriter("./data/testWriterOneQubitQuantumCircuitNoGates.json");
            writer.open();
            writer.write(qc1);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterOneQubitQuantumCircuitNoGates.json");
            qc1 = reader.readOne();
            assertEquals("The current state is (0.000 + 0.000i)|0> + (0.000 + 1.000i)|1>.", qc1.returnState());
            assertEquals("No gates in list.", qc1.seeFirstGate());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        } catch (WrongQubitNumberException e) {
            fail("WrongQubitNumberException should not be thrown.");
        }
    }

    @Test
    void testWriterOneQubitQuantumCircuitWithGates() {
        try {
            qc1 = new OneQubitQuantumCircuit(sqrt2, sqrt2);
            qc1.addGate("H");
            qc1.addGate("T");
            JsonWriter writer = new JsonWriter("./data/testWriterOneQubitQuantumCircuitWithGates.json");
            writer.open();
            writer.write(qc1);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterOneQubitQuantumCircuitWithGates.json");
            qc1 = reader.readOne();
            assertEquals("The current state is (0.707 + 0.000i)|0> + (0.707 + 0.000i)|1>.", qc1.returnState());
            assertEquals("Removed H gate.", qc1.removeGate());
            assertEquals("Removed T gate.", qc1.removeGate());
            assertEquals("No gates in list.", qc1.seeFirstGate());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        } catch (WrongQubitNumberException e) {
            fail("WrongQubitNumberException should not be thrown.");
        }
    }

    @Test
    void testWriterTwoQubitQuantumCircuitNoGates() {
        try {
            qc2 = new TwoQubitQuantumCircuit(icomplex, zero, zero, zero);
            JsonWriter writer = new JsonWriter("./data/testWriterTwoQubitQuantumCircuitNoGates.json");
            writer.open();
            writer.write(qc2);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterTwoQubitQuantumCircuitNoGates.json");
            qc2 = reader.readTwo();
            part1 = "The current state is (0.000 + 1.000i)|00> + (0.000 + 0.000i)|01>";
            part2 = " + (0.000 + 0.000i)|10> + (0.000 + 0.000i)|11>.";
            assertEquals(part1 + part2, qc2.returnState());
            assertEquals("No gates in list.", qc2.seeFirstGate());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        } catch (WrongQubitNumberException e) {
            fail("WrongQubitNumberException should not be thrown.");
        }
    }

    @Test
    void testWriterTwoQubitQuantumCircuitWithGates() {
        try {
            qc2 = new TwoQubitQuantumCircuit(half, half, half, half);
            qc2.addGate("XY");
            qc2.addGate("CN");
            JsonWriter writer = new JsonWriter("./data/testWriterTwoQubitQuantumCircuitWithGates.json");
            writer.open();
            writer.write(qc2);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterTwoQubitQuantumCircuitWithGates.json");
            qc2 = reader.readTwo();
            part1 = "The current state is (0.500 + 0.000i)|00> + (0.500 + 0.000i)|01>";
            part2 = " + (0.500 + 0.000i)|10> + (0.500 + 0.000i)|11>.";
            assertEquals(part1 + part2, qc2.returnState());
            assertEquals("Removed XY gate.", qc2.removeGate());
            assertEquals("Removed CN gate.", qc2.removeGate());
            assertEquals("No gates in list.", qc2.seeFirstGate());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        } catch (WrongQubitNumberException e) {
            fail("WrongQubitNumberException should not be thrown.");
        }
    }


}
