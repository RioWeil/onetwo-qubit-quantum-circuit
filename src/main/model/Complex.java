package model;

// Represents a complex number a + ib, where a, b are real numbers and i is sqrt(-1).
public class Complex {
    private double re;
    private double im;


    // EFFECTS: Real part of complex number set to re, Imaginary part set to im
    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    // EFFECTS: Produces real part of complex number
    public double getReal() {
        return re;
    }

    // EFFECTS: Produces imaginary part of complex number
    public double getImaginary() {
        return im;
    }

    // EFFECTS: Produces modulus of complex number, i.e. sqrt(a^2 + b^2)
    public double modulus() {
        return Math.pow(Math.pow(re, 2) + Math.pow(im, 2), 0.5);
    }


    // EFFECTS: Adds this to another complex number and produces resulting complex number.
    public Complex complexAdd(Complex c) {
        double real = re + c.getReal();
        double imaginary = im + c.getImaginary();
        return new Complex(real, imaginary);
    }

    // EFFECTS: Multiplies this with another complex number and produces resulting complex number.
    public Complex complexMultiply(Complex c) {
        double real = re * c.getReal() - im * c.getImaginary();
        double imaginary = re * c.getImaginary() + im * c.getReal();
        return new Complex(real, imaginary);
    }


}
