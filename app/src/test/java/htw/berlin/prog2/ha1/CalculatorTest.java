package htw.berlin.prog2.ha1;

import org.checkerframework.checker.units.qual.C;
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
    @DisplayName("sign of number on screen should be negative")
    void testPressNegativeKey() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(3);
        calc.pressDigitKey(4);
        calc.pressDotKey();
        calc.pressDigitKey(5);
        calc.pressNegativeKey();

        String expected = "-34.5";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

     // Dafür getter angelegt
    @Test
    @DisplayName("pressing one time should just clear screen, two times clear latestOperation & latestValues as well")
    void testPressClearKey() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(3);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(5);

        /**
         * first press with Values and Operation C
         */
        calc.pressClearKey();

        String expectedScreen = "0";
        String actualScreen = calc.readScreen();
        assertEquals(expectedScreen, actualScreen);

        double expectedLV = 3.0;
        double actualLV = calc.getLatestValue();
        assertEquals(expectedLV, actualLV);
        //Fehler! actual = 0.0 statt expected = 3.0

        String expectedLO = "+";
        String actualLO = calc.getLatestOperation();
        assertEquals(expectedLO, actualLO);
        //Fehler! actual = "" statt expected = "+"

        /**
         * second press CE
         */
        calc.pressClearKey();

        expectedScreen = "0";
        actualScreen = calc.readScreen();
        assertEquals(expectedScreen, actualScreen);

        expectedLV = 0.0;
        actualLV = calc.getLatestValue();
        assertEquals(expectedLV, actualLV);

        expectedLO = "";
        actualLO = calc.getLatestOperation();
        assertEquals(expectedLO, actualLO);
    }

    @Test
    @DisplayName("should put interim result on screen after pressing second time after second digit")
    void testInterimResultBinaryOperationKey() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(3);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(4);
        calc.pressBinaryOperationKey("+");

        String expected = "7";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
        //Fehler! actual = "4" statt expected "7"
    }

}

