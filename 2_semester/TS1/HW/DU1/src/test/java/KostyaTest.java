import cz.fel.cvut.ts1.Kostya;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class KostyaTest {
    @Test
    public void factorialTest() {
        Assertions.assertEquals(2, Kostya.factorialRecursive(2));
    }
    @Test
    public void factorialTest2() {
        Assertions.assertEquals(120, Kostya.factorialRecursive(5));
    }
}