package com.example.a3lab;

import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorTest {

    @Test
    public void testAddition() {
        Calculator calculator = new Calculator();
        double result = calculator.compute(2, 3, '+');
        assertEquals(5.0, result, 0.0001);
    }

    @Test
    public void testSubtraction() {
        Calculator calculator = new Calculator();
        double result = calculator.compute(5, 3, '-');
        assertEquals(2.0, result, 0.0001);
    }

    @Test
    public void testMultiplication() {
        Calculator calculator = new Calculator();
        double result = calculator.compute(2, 3, '*');
        assertEquals(6.0, result, 0.0001);
    }

    @Test
    public void testDivision() {
        Calculator calculator = new Calculator();
        double result = calculator.compute(6, 3, '/');
        assertEquals(2.0, result, 0.0001);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivisionByZero() {
        Calculator calculator = new Calculator();
        calculator.compute(6, 0, '/');
    }
}
