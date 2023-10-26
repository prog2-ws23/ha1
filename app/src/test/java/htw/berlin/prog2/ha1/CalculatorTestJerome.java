package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTestJerome {

    @Test
        //"should change the sign of a positive number to negative"
    void testChangeSignPositiveToNegative() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(5);
        calc.pressNegativeKey();

        String expected = "-5";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

}