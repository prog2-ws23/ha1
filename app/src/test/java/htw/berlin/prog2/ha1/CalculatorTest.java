package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Retro calculator")
class CalculatorTest {

    @Test
    @DisplayName("should display result after adding two positive multi-digit numbers")
    void testPositiveAddition() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "40";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display result after getting the square root of two")
    void testSquareRoot() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressUnaryOperationKey("√");

        String expected = "1.41421356";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when dividing by zero")
    void testDivisionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when drawing the square root of a negative number")
    void testSquareRootOfNegative() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressNegativeKey();
        calc.pressUnaryOperationKey("√");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should not allow multiple decimal dots")
    void testMultipleDecimalDots() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(7);
        calc.pressDotKey();
        calc.pressDigitKey(8);

        String expected = "1.78";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    //TODO hier weitere Tests erstellen

    /**
     * Regression Test
     */
    @Test
    @DisplayName("should display result after subtraction two positive multi-digit numbers")
    void testPositiveSubtraction() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "0";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
    /** Test-Driven 1
     *
     */
    @Test
    @DisplayName("should only clear and delete latest Calculations if the clear key is twice, if not it should just clear the screen and continue with the calculation.")
    void testClearKeyOperatedOnce() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressBinaryOperationKey("+");
        calc.pressClearKey(1);

        String expected = "0";
        String actual = calc.readScreen();

        assertEquals(expected, actual);

        calc.pressDigitKey(2);
        calc.pressEqualsKey();

        String secondExpected = "4";
        String secondActual = calc.readScreen();

        assertEquals(secondExpected, secondActual);
    }
    @Test
    @DisplayName("should only clear and delete latest Calculations if the clear key is twice, if not it should just clear the screen and continue with the calculation.")
    void testClearKeyOperatedTwice() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressBinaryOperationKey("+");
        calc.pressClearKey(2);

        String expected = "0";
        String actual = calc.readScreen();

        assertEquals(expected, actual);

        calc.pressDigitKey(2);

        String secondExpected = "2";
        String secondActual = calc.readScreen();

        assertEquals(secondExpected, secondActual);
    }



    /** Test-Driven 2
     *
     */

    @Test
    @DisplayName("should display a Zero before the decimal point if the decimal key is operated before a digit key.")
    void testOperationOfDotKeyFirst() {
        Calculator calc = new Calculator();

        calc.pressDotKey();
        calc.pressDigitKey(2);
        calc.pressDigitKey(3);
        String expected = "0.23";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
}

