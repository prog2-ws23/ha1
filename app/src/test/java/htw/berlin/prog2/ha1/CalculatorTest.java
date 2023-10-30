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

    /**
     * Dieser Test überprüft, ob der Taschenrechner nach der Subtraktion von zwei positiven mehrstelligen Zahlen
     * das korrekte Ergebnis auf dem Bildschirm anzeigt.
     */
    @Test
    @DisplayName("should display result after subtracting two positive multi-digit numbers")
    void test(){
            Calculator calc = new Calculator();

            calc.pressDigitKey(3);
            calc.pressDigitKey(0);
            calc.pressBinaryOperationKey("-");
            calc.pressDigitKey(2);
            calc.pressDigitKey(0);
            calc.pressEqualsKey();

            String expected = "10";
            String actual = calc.readScreen();

            assertEquals(expected, actual);
        }

    /**
     * Dieser Test überprüft, ob der Taschenrechner nach dem Drücken der Clear-Taste (CE) den Bildschirm auf "0" zurücksetzt.
     */

    @Test
    @DisplayName("should display 0 after pressing Clear key")
    void testClearKey(){
        Calculator calc = new Calculator();

        calc.pressDigitKey(3);
        calc.pressDigitKey(2);
        calc.pressClearKey();

        String expected = "0";
        String actual = calc.readScreen();

        assertEquals(expected, actual);

    }

    /**
     *  Dieser Test überprüft, ob der Taschenrechner nach dem Drücken der unären Operationstaste "1/x"
     *  den Fehler "Error" auf dem Bildschirm anzeigt
     */

    @Test
    @DisplayName("should display Error after pressing zero and the unary operation key 1/x ")
    void testZeroInversion(){
        Calculator calc = new Calculator();

        calc.pressDigitKey(0);
        calc.pressUnaryOperationKey("1/x");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);

    }

    /**
     * Dieser Test überprüft, ob der Taschenrechner nach dem Drücken der Dezimaltrennzeichen-Taste
     * und anschließender Division korrekt das Ergebnis auf dem Bildschirm anzeigt.
     */

    @Test
    @DisplayName("should display 1 after pressing dot key and performing division")
    void testDotDivision()  {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(3);
        calc.pressBinaryOperationKey("/");

        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(3);
        calc.pressEqualsKey();

        String expected = "1";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

}

