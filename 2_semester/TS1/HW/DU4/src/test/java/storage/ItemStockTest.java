package storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import shop.StandardItem;

import static org.junit.jupiter.api.Assertions.*;

public class ItemStockTest {
    StandardItem standardItem;

    @BeforeEach
    public void initTest() {
        standardItem = new StandardItem(1, "Test item", (float) 70.3, "Test category", 10);
    }

    @Test
    public void constructorTest() {
        // LENGTH
        int expectedCount = 0;
        // ACTION
        ItemStock itemStock = new ItemStock(standardItem);
        // ASSERTION
        assertEquals(expectedCount, itemStock.getCount());
        assertSame(standardItem, itemStock.getItem());
    }
    @Test
    public void increaseItemArgumentLessThanOne() {
        // ACTION + ASSERTION
        assertThrows(IllegalArgumentException.class, () -> new ItemStock(standardItem).IncreaseItemCount(-3));
    }

    @Test
    public void decreaseItemArgumentLessThanOne() {
        // ACTION + ASSERTION
        assertThrows(IllegalArgumentException.class, () -> new ItemStock(standardItem).decreaseItemCount(-3));
    }

    @ParameterizedTest()
    @CsvSource(value = {"0:1:2", "1:2:3", "1:1:2", "3:33:333"}, delimiter = ':')
    public void increaseItemCountTest(int initialCount, int count, int expected) {
        // ACTION
        ItemStock itemStock = new ItemStock(standardItem);
        itemStock.setCount(initialCount);
        itemStock.IncreaseItemCount(count);
        // ASSERTION
        assertEquals(expected, itemStock.getCount());
    }

    @ParameterizedTest()
    @CsvSource(value = {"0:1:0", "0:2:0", "1:10:0", "10:5:5"}, delimiter = ':')
    public void decreaseItemCountTest(int initialCount, int count, int expected) {
        // ACTION
        ItemStock itemStock = new ItemStock(standardItem);
        itemStock.setCount(initialCount);
        itemStock.decreaseItemCount(count);

        // ASSERTION
        assertEquals(expected, itemStock.getCount());
    }
}
