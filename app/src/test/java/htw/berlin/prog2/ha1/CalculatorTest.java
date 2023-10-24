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
    //Teilaufgabe1 ha 1   grüner Test minus 2 positive nummer
    @Test
    @DisplayName("should display result after minus two positive multi-digit numbers")
    void testPositiveMinus() {
        Calculator calc = new Calculator();
        calc.pressDigitKey(5);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(4);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "10";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
    //Teilaufgabe2 roter Test1
    @Test
    @DisplayName("should display Error when trying to calculate 1/0")
        void testInvertofZero(){
            Calculator calculator = new Calculator();
            calculator.pressDigitKey(0);
            calculator.pressUnaryOperationKey("1/x");
            String expected = "Error";
            String actual = calculator.readScreen();
            assertEquals(expected, actual);


        }
        @Test
    @DisplayName("schould display result when calculate Binary plus Unary ")
    void testResultCalculateOfBinaryplusUnary(){
            Calculator calculator = new Calculator();
            calculator.pressDigitKey(5);
            calculator.pressBinaryOperationKey("+");
            calculator.pressDigitKey(4);
            calculator.pressUnaryOperationKey("√");
            calculator.pressEqualsKey();
            String expected = "7";
            String actual = calculator.readScreen();
            assertEquals(expected, actual);
        }

}

