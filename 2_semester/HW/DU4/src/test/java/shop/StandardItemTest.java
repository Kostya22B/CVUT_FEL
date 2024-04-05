package shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StandardItemTest {
    StandardItem standardItem;

    @BeforeEach
    public void setup() {
        standardItem = new StandardItem(1, "Test item", (float) 50.2, "Test category", 8);
    }

    @Test
    public void constructorTest() {
        // LENGTH
        int expectedId = 1;
        String expectedName = "Test name";
        float expectedPrice = (float) 50.2;
        String expectedCategory = "Test category";
        int expectedLoyaltyPoints = 8;

        // ACTION
        StandardItem standardItem = new StandardItem(expectedId, expectedName, expectedPrice, expectedCategory, expectedLoyaltyPoints);

        // ASSERTEQUALS
        assertEquals(expectedId, standardItem.getID());
        assertEquals(expectedName, standardItem.getName());
        assertEquals(expectedPrice, standardItem.getPrice());
        assertEquals(expectedCategory, standardItem.getCategory());
        assertEquals(expectedLoyaltyPoints, standardItem.getLoyaltyPoints());
    }

    @Test
    public void copyTest() {
        // ACTION TEST
        StandardItem copiedStandardItem = standardItem.copy();
        // ASSERT_EQUALS,NOTSAME
        assertNotSame(standardItem, copiedStandardItem);
        assertEquals(standardItem, copiedStandardItem);
    }

    @ParameterizedTest()
    @MethodSource("generator")
    public void equalsWithSameObjetTest(StandardItem firstItem, StandardItem secondItem, boolean expected) {
        // ACTION + ASSERTIONS
        assertEquals(expected, firstItem.equals(secondItem));
    }

    @Test
    public void equalsWithAnotherObjectTest() {
        // ACTION
        Object notStandardItem = new Object();
        // ASSERTION
        assertNotEquals(standardItem, notStandardItem);
    }

    private static Stream generator() {
        return Stream.of(
                Arguments.of(
                        new StandardItem(1, "Test name", (float) 33.2, "Test category", 10),
                        new StandardItem(1, "Test name", (float) 33.2, "Test category", 10),
                        true
                ),
                Arguments.of(
                        new StandardItem(1, "Test name", (float) 30.1, "Test category", 10),
                        new StandardItem(2, "Test name", (float) 30.1, "Test category", 10),
                        false
                ),
                Arguments.of(
                        new StandardItem(1, "Test name", (float) 50.2, "Test category", 10),
                        new StandardItem(1, "Test", (float) 50.2, "Test category", 10),
                        false
                ),
                Arguments.of(
                        new StandardItem(1, "Test name", (float) 30.1, "Test category", 10),
                        new StandardItem(1, "Test name", (float) 54, "Test category", 10),
                        false
                ),
                Arguments.of(
                        new StandardItem(1, "Test name", (float) 50.2, "Test category", 10),
                        new StandardItem(1, "Test name", (float) 50.2, "Test", 10),
                        false
                ),
                Arguments.of(
                        new StandardItem(1, "Test name", (float) 50.2, "Test category", 10),
                        new StandardItem(1, "Test name", (float) 50.2, "Test category", 54),
                        false
                )
        );
    }
}
