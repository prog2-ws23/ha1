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

        calc.pressDigitKey(2);
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
    //Test der  funktioniert
    @Test
    @DisplayName("should be able to subtract numbers")
    void testSubtractNumbers() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(6);
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(3);

        String expected = "3";
        String actual = calc.readScreen();

        assertEquals(expected, actual);


    }
    @Test
    @DisplayName("should display Error for a very long number")
    void testsTooLongNumbers() {
        Calculator calc = new Calculator();


        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(1);
        calc.pressDigitKey(1);
        calc.pressDigitKey(1);
        calc.pressDigitKey(1);
        calc.pressDigitKey(1);
        calc.pressDigitKey(1);
        calc.pressDigitKey(1);
        calc.pressDigitKey(1);
        calc.pressDigitKey(1);
        calc.pressDigitKey(1);

        //darf ich hier auch eine Schleife anwenden ?
        //Hier die Eingabe von 10 Ziffern und einem Dezimaltrennzeichen um zu gucken ob Error erscheint

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);

        }

    @Test
    @DisplayName("should display Error when you try to take the inverse of zero")
    void testInverseOfZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(0);
        calc.pressUnaryOperationKey("1/x");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }


}










