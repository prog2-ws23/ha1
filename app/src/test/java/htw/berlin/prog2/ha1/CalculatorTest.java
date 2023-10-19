package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName("Should throw Exception if Press digit is Higher then 9")
    void testPressClearKeyTwoTimes() {

        assertThrows(IllegalArgumentException.class, () -> {
            Calculator calculator = new Calculator();
            calculator.pressDigitKey(15);
        });
    }

    @Test
    @DisplayName("Should use the latest Operation and latest Operand if EqualsKey is press two times")
    void testTwoTimesEqualsKey()  {

        Calculator calculator = new Calculator();
        calculator.pressDigitKey(5);
        calculator.pressBinaryOperationKey("+");
        calculator.pressDigitKey(9);
        calculator.pressEqualsKey();
        calculator.pressEqualsKey();

        String expected = "23";
        String actual = calculator.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should save the previous operation and previous operands when the ClearKey is pressed once")
    void testPressClearKeyOneTime() {

        Calculator calculator = new Calculator();
        calculator.pressDigitKey(6);
        calculator.pressBinaryOperationKey("x");
        calculator.pressDigitKey(5);
        calculator.pressClearKey();
        calculator.pressDigitKey(6);
        calculator.pressEqualsKey();

        String expected = "36";
        String actual = calculator.readScreen();

        assertEquals(expected, actual);

    }
}

