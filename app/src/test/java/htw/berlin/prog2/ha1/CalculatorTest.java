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
     * 1.Teilaufgabe
     * neuer zusätzlicher grüner Test, der eine bisher nicht getestete Funktionalität abdeckt, aber bereits funktioniert
     * testet Subtraktion zweier Zahlen
     */

    @Test
    @DisplayName("should display result after subtracting two positive multi-digit numbers")
    void testSubtraction() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(5);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "30";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    /**
     * Teilaufgabe 2
     * zwei rote Tests, die Fehler im Code aufdecken
     * 1. roter Test = Inversion mit 0 muss Error zeigen, da Division durch 0 nicht möglich ist
     */

    @Test
    @DisplayName("should display error when doing inversion by zero")
    void testInversionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(0);
        calc.pressUnaryOperationKey("1/x");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    /**
     * 2. roter Test = beim Wurzeln ziehen wird das Ergebnis immer mit einer Dezimalstelle angezeigt also .0
     */

    @Test
    @DisplayName("should display the result after getting the square root of a number but without a decimal point")
    void testASquareRoot() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDigitKey(9);
        calc.pressDigitKey(6);
        calc.pressUnaryOperationKey("√");

        String expected = "14";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }








}

