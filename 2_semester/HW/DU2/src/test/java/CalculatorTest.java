import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator c = new Calculator();

    @Test
    void addition() {
        double actual = c.addition(5, 7);
        double expected = 12;
        assertEquals(actual, expected);
    }

    @Test
    void subtraction() {
        double actual = c.subtraction(8, 7);
        double expected = 1;
        assertEquals(actual, expected);
    }

    @Test
    void multiplication() {
        double actual = c.multiplication(5, 7);
        double expected = 35;
        assertEquals(actual, expected);
    }

    @Test
    void divisionCase1() {
        double actual = c.division(10, 5);
        double expected = 2;
        assertEquals(actual, expected);
    }

    @Test
    void divisionCase2() {
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () ->c.division(1, 0));
        Assertions.assertEquals("Dividing by 0", exception.getMessage());
    }
}