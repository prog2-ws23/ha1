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
    @Test
    @DisplayName("should display result after adding two positive multi-digit numbers")
    void testClearScreen() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressClearKey();

        String expected = "0";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display result after adding two positive multi-digit numbers")
    void testDivision() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(8);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(2);
        calc.pressEqualsKey();


        String expected = "4";
        String actual = calc.readScreen();

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("should round correctly")
    void testRounding() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(5);
        calc.pressUnaryOperationKey("√");




        String expected = "2.23606798";
        String actual = calc.readScreen();

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("should display result after applying a UnaryOperation on both Nubers of a Binary Operation AND Round correctly")
    void testDobleRoot() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(8);
        calc.pressUnaryOperationKey("√");
        calc.pressBinaryOperationKey("x");
        calc.pressDigitKey(3);
        calc.pressUnaryOperationKey("√");
        calc.pressEqualsKey();


        String expected = "4.89897949";
        String actual = calc.readScreen();

        assertEquals(expected, actual);

        //Something stange! according to another calculator the awnser is 4.89897948557 so it should round, BUT here is calculated that 4.898979484221968 the the 10th digit should not be rounded, idk what is going on but I am not fixing THAT


    }

    @Test
    @DisplayName("should display result after applying a UnaryOperation on both Nubers of a Binary Operation")
    void testDobleRoot2() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(9);
        calc.pressUnaryOperationKey("√");
        calc.pressBinaryOperationKey("x");
        calc.pressDigitKey(4);
        calc.pressUnaryOperationKey("√");
        calc.pressEqualsKey();


        String expected = "6";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
}

