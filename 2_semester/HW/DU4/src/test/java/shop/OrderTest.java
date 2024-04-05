package shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    ShoppingCart cart;

    @BeforeEach
    public void initTest() {
        cart = new ShoppingCart();
    }

    @Test
    public void constructorWithoutStateTest() {
        // ARRANGE
        int expectedState = 0;
        String expectedCustomerName = "Kostiantyn";
        String expectedCustomerAddress = "Dejvicka 9";

        // ACT
        Order order = new Order(cart, expectedCustomerName, expectedCustomerAddress);

        // ASSERT
        assertEquals(expectedState, order.getState());
        assertEquals(expectedCustomerName, order.getCustomerName());
        assertEquals(expectedCustomerAddress, order.getCustomerAddress());
    }

    @Test
    public void constructorWithStateTest() {
        // ARRANGE
        int expectedState = 2;
        String expectedCustomerName = "Kostiantyn";
        String expectedCustomerAddress = "Dejvicka 9";

        // ACT
        Order order = new Order(cart, expectedCustomerName, expectedCustomerAddress, expectedState);

        // ASSERT
        assertEquals(expectedState, order.getState());
        assertEquals(expectedCustomerName, order.getCustomerName());
        assertEquals(expectedCustomerAddress, order.getCustomerAddress());
    }

    @Test
    public void constructorWithCartNullTest() {
        // ACTION + ASSERTION
        assertThrows(NullPointerException.class, () -> new Order(null, "Kostiantyn", "Dejvicka 9"));
    }
}
