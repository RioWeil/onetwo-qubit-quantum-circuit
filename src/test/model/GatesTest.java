package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

// Test class for Gates
public class GatesTest {
    Gates gates;

    @BeforeEach
    public void runBefore() {
        gates = new Gates();
    }

    @Test
    public void testEmptyEmpty() {
        assertTrue(gates.isEmpty());
    }

    @Test
    public void testEmptyNonEmpty() {
        gates.addGate("CN");
        assertFalse(gates.isEmpty());
    }

    @Test
    public void testLengthEmpty() {
        assertEquals(0, gates.length());
    }

    @Test
    public void testLengthNonEmpty() {
        gates.addGate("X");
        gates.addGate("Y");
        gates.addGate("Z");
        assertEquals(3, gates.length());
    }

    @Test
    public void testSeeGateSingleton() {
        gates.addGate("X");
        assertEquals("X", gates.seeNextGate());
    }

    @Test
    public void testSeeGateNonSingleton() {
        gates.addGate("CN");
        gates.addGate("XY");
        gates.addGate("IS");
        assertEquals("CN", gates.seeNextGate());
    }

    @Test
    public void testRemoveGateFromSingleton() {
        gates.addGate("TS");
        assertEquals(1, gates.length());
        gates.removeNextGate();
        assertTrue(gates.isEmpty());
    }

    @Test
    public void testRemoveGateFromNonSingleton() {
        gates.addGate("X");
        gates.addGate("Z");
        assertEquals(2, gates.length());
        assertEquals("X", gates.seeNextGate());
        gates.removeNextGate();
        assertEquals(1, gates.length());
        assertEquals("Z", gates.seeNextGate());
        gates.removeNextGate();
        assertTrue(gates.isEmpty());
    }

    @Test
    public void testGetGateFromSingleton() {
        gates.addGate("XY");
        assertEquals(1, gates.length());
        assertEquals("XY", gates.getNextGate());
        assertTrue(gates.isEmpty());
    }

    @Test
    public void testGetGateFromNonSingleton() {
        gates.addGate("S");
        gates.addGate("X");
        gates.addGate("H");
        assertEquals(3, gates.length());
        assertEquals("S", gates.getNextGate());
        assertEquals(2, gates.length());
        assertEquals("X", gates.getNextGate());
        assertEquals(1, gates.length());
        assertEquals("H", gates.getNextGate());
        assertTrue(gates.isEmpty());
    }

    @Test
    public void testGetGateList() {
        gates.addGate("H");
        gates.addGate("X");
        gates.addGate("Y");
        LinkedList<String> test = new LinkedList<>();
        test.add("H");
        test.add("X");
        test.add("Y");
        assertEquals(test, gates.getGateList());
    }
}
