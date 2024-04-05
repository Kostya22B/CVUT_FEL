package shop;

import org.junit.jupiter.api.Test;
import storage.ItemStock;
import storage.NoItemInStorage;
import storage.Storage;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EShopControllerTest {
    // Покупка 1 продукта
    // 1. Заполнить склад (добавить в него 1 продукт)
    // 2. Добавить в корзину
    // 3. Оформить заказ
    // 4. Купить продукт (оплатить заказ)
    // 5. Проверить склад (чтобы продукт был вычтен со склада)
    @Test
    public void shoppingCartTestBuyingOneItemTest() throws NoItemInStorage {
        EShopController.startEShop();
        StandardItem item = new StandardItem(1, "USB", 200, "GADGETS", 10);

        // Check if count 0
        assertThrows(IllegalArgumentException.class, () -> EShopController.addItemToStorage(item, 0));

        // Adding whith count 1
        EShopController.addItemToStorage(item, 1);
        ArrayList<ItemStock> itemsFromStorage = new ArrayList<>(EShopController.getItemsFromStorage());

        assertEquals(1, itemsFromStorage.get(0).getCount());
        assertEquals(1, itemsFromStorage.size());
        assertSame(item, itemsFromStorage.get(0).getItem());

        // Cart Creating
        ShoppingCart cart = EShopController.newCart();
        assertEquals(1, EShopController.getCarts().size());
        assertTrue(EShopController.getCarts().contains(cart));
        assertEquals(0, cart.getItemsCount());

        // Adding Item to cart
        cart.addItem(item);
        assertEquals(1, cart.getItemsCount());
        assertTrue(cart.getCartItems().contains(item));

        // Creating an order
        EShopController.purchaseShoppingCart(cart, "Fifka Petr", "Karlovo namesti");

        // Count of item have to be 0
        itemsFromStorage = new ArrayList<>(EShopController.getItemsFromStorage()); // update
        assertEquals(1, itemsFromStorage.size());
        assertEquals(0, itemsFromStorage.get(0).getCount());
        assertEquals(1, EShopController.getArchive().getHowManyTimesHasBeenItemSold(item));
    }


    @Test
    public void shoppingCartTestBuyingWithNoProductInStorage() throws NoItemInStorage {
        EShopController.startEShop();

        // Adding items
        int[] itemCount = {1,1,1,1,1};

        Item[] storageItems = {
            new StandardItem(1, "USB cabel", 200, "GADGETS", 10),
            new StandardItem(2, "USB 3.0", 300, "GADGETS", 10),
            new StandardItem(3, "chainsaw", 8800, "TOOLS", 10),
            new StandardItem(4, "chainsaw with usb", 10000, "GADGETS", 5),
            new StandardItem(5, "wire", 700, "TOOLS", 5),
        };

        // Adding whit count 1
        for (int i = 0; i < storageItems.length; i++) {
            EShopController.addItemToStorage(storageItems[i], itemCount[i]);
        }

        Storage storage = EShopController.getStorage();

        for (Item storageItem : storageItems) {
            assertEquals(1, storage.getItemCount(storageItem));
        }

        // Creating the cart
        ShoppingCart cart = EShopController.newCart();
        assertEquals(1, EShopController.getCarts().size());
        assertTrue(EShopController.getCarts().contains(cart));
        assertEquals(0, cart.getItemsCount());

        // Adding 3 items to cart
        for (int j = 0; j < 3; j++) {
            cart.addItem(storageItems[j]);
        }



        assertEquals(3, cart.getItemsCount());

        assertTrue(cart.getCartItems().contains(storageItems[0]));
        assertTrue(cart.getCartItems().contains(storageItems[1]));
        assertTrue(cart.getCartItems().contains(storageItems[2]));

        // Removing 1 item from cart
        storage.removeItems(storageItems[1], 1);
        assertEquals(0, storage.getItemCount(storageItems[1]));

        //CREATING ORDER
        assertThrows(NoItemInStorage.class, () -> EShopController.purchaseShoppingCart(cart, "Fifka Petr", "Karlovo namesti"));
    }
}
