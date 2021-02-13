package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplexTest {
    Complex c0;
    Complex c1;
    Complex c2;
    Complex result;

    @BeforeEach
    public void runBefore() {
        c0 = new Complex(0,0);
        c1 = new Complex(1, 1);
        c2 = new Complex(3, -4);
    }

    @Test
    public void testModulusZero() {
        assertEquals(0, c0.modulus());
    }

    @Test
    public void testModulusNonZero() {
        assertEquals(5, c2.modulus());
    }

    @Test
    public void testAddZeroZero() {
        result = c0.complexAdd(c0);
        assertEquals(0, result.getReal());
        assertEquals(0, result.getImaginary());
    }

    @Test
    public void testAddZeroNonZero() {
        result = c0.complexAdd(c2);
        assertEquals(3, result.getReal());
        assertEquals(-4, result.getImaginary());
    }

    @Test
    public void testAddNonZeroNonZero() {
        result = c1.complexAdd(c2);
        assertEquals(4, result.getReal());
        assertEquals(-3, result.getImaginary());
    }

    @Test
    public void testMultiplyNonZeroZero() {
        result = c0.complexMultiply(c2);
        assertEquals(0, result.getReal());
        assertEquals(0, result.getImaginary());
    }

    @Test
    public void testMultiplyNonZeroOne() {
        Complex one = new Complex(1, 0);
        result = one.complexMultiply(c2);
        assertEquals(3, result.getReal());
        assertEquals(-4, result.getImaginary());
    }

    @Test
    public void testMultiplyNonZeroI() {
        Complex i = new Complex(0, 1);
        result = i.complexMultiply(c2);
        assertEquals(4, result.getReal());
        assertEquals(3, result.getImaginary());
    }

    @Test
    public void testMultiplyNonZeroNonZero() {
        result = c1.complexMultiply(c2);
        assertEquals(7, result.getReal());
        assertEquals(-1, result.getImaginary());
    }
}