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
    @DisplayName("Calculator is able to devide 2 numbers") //new Tests
    void testDivision(){
        Calculator calc = new Calculator();
        calc.pressDigitKey(9);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(3);
        calc.pressEqualsKey();

        String expected = "3";
        String actual = calc.readScreen();

        assertEquals(expected, actual);

    }
    @Test
    @DisplayName("Calculator is able to devide 2 numbers and devide everytime when we press equals")
    void testDivisionafterdivision() {
        Calculator calc = new Calculator();
        calc.pressDigitKey(9);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(3);
        calc.pressEqualsKey();
        calc.pressEqualsKey();


        String expected = "1";// im moment auf 3 gestellt muss aber eigentlich auf 1 sein//will fail
        String actual = calc.readScreen();

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("pressClear works as intended") //new Test
    void pressClear() {
        Calculator calc = new Calculator();
        calc.pressDigitKey(9);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(3);
        calc.pressClearKey();


        String expected = "0";
        String actual = calc.readScreen();

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("Tests Method pressclearKey")//new Test
    void TestpressClearKey() {
        Calculator calc = new Calculator();
        calc.pressDigitKey(8);
        calc.pressBinaryOperationKey("-");
        calc.pressClearKey();

        String expected = "0";
        String actual = calc.readScreen();
        assertEquals(expected, actual);

    }
    @Test
    @DisplayName("Tests pressclear 2")//will fail
    void TestpressClearKey2() {
        Calculator calc = new Calculator();
        calc.pressDigitKey(2);
        calc.pressBinaryOperationKey("-");
        calc.pressClearKey();
        calc.pressEqualsKey();

        String expected = "2";
        String actual = calc.readScreen();
        assertEquals(expected, actual);

    }


}

