package archive;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import shop.*;


/**
 * Class for archive of the purchases
 */
public class PurchasesArchive {
    private final HashMap<Integer, ItemPurchaseArchiveEntry> itemPurchaseArchive;
    private final ArrayList<Order> orderArchive;
    
    public PurchasesArchive() {
        orderArchive = new ArrayList<>();
        itemPurchaseArchive = new HashMap<>();
    }
    
    public PurchasesArchive(HashMap<Integer, ItemPurchaseArchiveEntry> itemArchive, ArrayList<Order> orderArchive) {
        this.orderArchive = orderArchive;
        this.itemPurchaseArchive = itemArchive;
    }
    
    public void printItemPurchaseStatistics() {
        Collection<ItemPurchaseArchiveEntry> itemEntries = itemPurchaseArchive.values();
        System.out.println("ITEM PURCHASE STATISTICS:");
        for(ItemPurchaseArchiveEntry e : itemEntries) System.out.println(e.toString());
    }
    
    public int getHowManyTimesHasBeenItemSold(Item item) {
        if(itemPurchaseArchive.containsKey(item.getID())){
            return itemPurchaseArchive.get(item.getID()).getCountHowManyTimesHasBeenSold();
        }
        return 0;
    }

    public void putOrderToPurchasesArchive(Order order) {
        orderArchive.add(order);
        ArrayList<Item> orderItems = order.getItems();
        for(Item i : orderItems){
            if(itemPurchaseArchive.containsKey(i.getID())){
                ItemPurchaseArchiveEntry e = itemPurchaseArchive.get(i.getID());
                e.increaseCountHowManyTimesHasBeenSold(1);
            }else{
                itemPurchaseArchive.put(i.getID(), new ItemPurchaseArchiveEntry(i));
            }
        }
    }
}

