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

    @Test
//"should not display more than 16 digits"
    void testLimitDisplayToSixteenDigits() {
        Calculator calc = new Calculator();

        for (int i = 0; i < 18; i++) {
            calc.pressDigitKey(1);
        }

        String expected = "1111111111111111";  // Only 16 digits
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
    @Test
    //"should keep the screen as 0 when pressing negative key with 0 displayed")
    void testNegativeKeyWithZero() {
        Calculator calc = new Calculator();

        calc.pressNegativeKey();

        String expected = "0";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }




}