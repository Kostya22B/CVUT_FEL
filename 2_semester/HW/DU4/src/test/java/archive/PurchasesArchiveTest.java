package archive;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import shop.Item;
import shop.Order;
import shop.StandardItem;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PurchasesArchiveTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private StandardItem mockedItem;
    private Order mockedOrder;
    private ItemPurchaseArchiveEntry mockedItemPurchaseArchiveEntry;
    private HashMap<Integer, ItemPurchaseArchiveEntry> itemArchive;
    private ArrayList<Order> orderArchive;


    @BeforeEach
    public void initTest() {
        mockedItem = Mockito.mock(StandardItem.class);
        mockedItemPurchaseArchiveEntry = Mockito.mock(ItemPurchaseArchiveEntry.class);
        mockedOrder = Mockito.mock(Order.class);
        orderArchive = new ArrayList<>();
        itemArchive = new HashMap<>();
        ArrayList<Item> items = new ArrayList<>();
        items.add(mockedItem);

        Mockito.when(mockedItem.getID()).thenReturn(1);

        Mockito.when(mockedOrder.getItems()).thenReturn(items);// Mock order

        Mockito.when(mockedItemPurchaseArchiveEntry.getCountHowManyTimesHasBeenSold()).thenReturn(1); //Item archive with count 1
        itemArchive.put(1, mockedItemPurchaseArchiveEntry);

        orderArchive.add(mockedOrder); //Order Archive
    }

    @Test
    public void printItemPurchaseStatisticsEmptyTest() {

        System.setOut(new PrintStream(outContent)); //BEFORE
        System.setErr(new PrintStream(errContent));

        PurchasesArchive emptyPurchasesArchive = new PurchasesArchive(); //ARRANGE
        String expected = "ITEM PURCHASE STATISTICS:" + System.lineSeparator();

        emptyPurchasesArchive.printItemPurchaseStatistics(); //ACT

        assertEquals(expected, outContent.toString()); //ASSERT

        System.setOut(originalOut); //AFTER
        System.setErr(originalErr);
    }

    @Test
    public void printItemPurchaseStatisticsOneItemTest() {

        System.setOut(new PrintStream(outContent)); //BEFORE
        System.setErr(new PrintStream(errContent));

        Mockito.when(mockedItemPurchaseArchiveEntry.toString()).thenReturn("Some string"); //ARRANGE
        itemArchive.put(1, mockedItemPurchaseArchiveEntry);
        PurchasesArchive purchasesArchive = new PurchasesArchive(itemArchive, null);
        String expected = "ITEM PURCHASE STATISTICS:" + System.lineSeparator() + "Some string" + System.lineSeparator();

        purchasesArchive.printItemPurchaseStatistics(); //ACT

        assertEquals(expected, outContent.toString()); //ASSERT

        System.setErr(originalErr); //AFTER
    }

    @Test
    public void getHowManyTimesHasBeenItemSoldPurchaseEmptyArchiveTest() {

        Mockito.when(mockedItem.getID()).thenReturn(1); //ARRANGE
        PurchasesArchive emptyPurchasesArchive = new PurchasesArchive();

        assertEquals(0, emptyPurchasesArchive.getHowManyTimesHasBeenItemSold(mockedItem)); // ACT + ASSERT
    }

    @Test
    public void getHowManyTimesHasBeenItemSoldTest() {

        Mockito.when(mockedItem.getID()).thenReturn(10); //ARRANGE
        Mockito.when(mockedItemPurchaseArchiveEntry.getCountHowManyTimesHasBeenSold()).thenReturn(30);
        itemArchive.put(10, mockedItemPurchaseArchiveEntry);
        PurchasesArchive purchasesArchive = new PurchasesArchive(itemArchive, null);

        assertEquals(30, purchasesArchive.getHowManyTimesHasBeenItemSold(mockedItem)); // ACT + ASSERT
    }

    @Test
    public void putOrderToPurchasesArchiveExistingItemTest() {

        PurchasesArchive purchasesArchive = new PurchasesArchive(itemArchive, orderArchive); //ARRANGE

        purchasesArchive.putOrderToPurchasesArchive(mockedOrder); // Act

        Mockito.verify(mockedItemPurchaseArchiveEntry).increaseCountHowManyTimesHasBeenSold(1); //ASSERT
    }

    @Test
    public void putOrderToPurchasesArchiveNonExistingItemTest() {

        StandardItem newMockedItem = Mockito.mock(StandardItem.class); // ARRANGE

        ArrayList<Item> items = new ArrayList<>(); // New Order
        items.add(newMockedItem);
        Order newMockedOrder = Mockito.mock(Order.class);

        Mockito.when(newMockedItem.getID()).thenReturn(2);
        Mockito.when(newMockedOrder.getItems()).thenReturn(items);

        PurchasesArchive purchasesArchive = new PurchasesArchive(itemArchive, orderArchive);

        purchasesArchive.putOrderToPurchasesArchive(newMockedOrder); // Act

        assertTrue(itemArchive.containsKey(2)); // ASSERT
        assertEquals(2, itemArchive.get(2).getRefItem().getID());
        assertEquals(1, itemArchive.get(2).getCountHowManyTimesHasBeenSold());
    }
}
