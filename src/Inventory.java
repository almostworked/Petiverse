import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages a collection of items for a player, including the ability to add, remove,
 * or update item quantities. By default, it starts with 5 Apples, 3 Steaks, and 1 Ball.
 */
public class Inventory {
    /**
     * Maps each Item to the quantity the player has.
     */
    public Map<Item, Integer> itemMap;
    private boolean hasGifts;
    
    /**
     * Default constructor that initializes the inventory with
     * 5 Apples, 3 Steaks, and 1 Ball.
     */
    public Inventory() {
        this.itemMap = new HashMap<>();
        this.hasGifts = true;

        // Populate with default items:
        itemMap.put(Item.APPLE, 5);
        itemMap.put(Item.STEAK, 3);
        itemMap.put(Item.BALL, 1);
    } 

    /**
     * Constructor that initializes the inventory with existing items (Entry objects)
     * and sets whether or not it contains gifts.
     *
     * @param entries  A list of Entry objects to initialize the inventory
     * @param hasGifts A boolean indicating if the inventory contains gifts
     */
    public Inventory(List<Entry> entries, boolean hasGifts) {
        this.itemMap = new HashMap<>();
        for (Entry e : entries) {
            this.itemMap.put(e.item, e.quantity);
        }
        this.hasGifts = hasGifts;
    }

    /**
     * Retrieves the quantity of a specific item in the inventory.
     *
     * @param item the Item to look up
     * @return the quantity, or 0 if not found
     */
    public int getQuantity(Item item) {
        return itemMap.getOrDefault(item, 0);
    }

    /**
     * Sets the quantity for a given item.
     * If the item doesn't exist yet, it is created; otherwise, its quantity is updated.
     *
     * @param item     the Item to set
     * @param quantity how many of this item the player holds
     */
    public void setQuantity(Item item, int quantity) {
        itemMap.put(item, quantity);
    }

    /**
     * Prints out the inventory contents for debugging/visual reference.
     *
     * @return this inventory object (for chaining, if desired)
     */
    public Inventory displayInventory() {
        System.out.println("Inventory:");
        if (itemMap.isEmpty()) {
            System.out.println("No items in inventory.");
        } else {
            for (Map.Entry<Item, Integer> entry : itemMap.entrySet()) {
                System.out.println("Item object " + entry.getKey()
                                   + " - Quantity: " + entry.getValue());
            }
        }
        return this;
    }

    /**
     * Displays the available inventory management methods to the console.
     */
    public void displayMethods() {
        System.out.println("Here are the available inventory methods:");
        System.out.println("1. You can display inventory");
        System.out.println("2. You can update inventory");
        System.out.println("3. Remove an item");
    }

    /**
     * Increases or adds to the quantity of a given item.
     * If the item doesn't exist, it's created with the given quantity.
     *
     * @param item     the Item to update
     * @param quantity how many of this item to add
     */
    public void updateInventory(Item item, int quantity) {
        int oldQty = itemMap.getOrDefault(item, 0);
        itemMap.put(item, oldQty + quantity);
    }

    /**
     * Removes a certain number of an item from the inventory.
     * If the resulting quantity is zero or negative, the item is removed entirely.
     *
     * @param item     the Item to remove
     * @param quantity how many of this item to remove
     */
    public void removeItem(Item item, int quantity) {
        if (!itemMap.containsKey(item)) {
            System.out.println("Item not found in inventory.");
            return;
        }
        int oldQty = itemMap.get(item);
        int newQty = oldQty - quantity;
        if (newQty <= 0) {
            itemMap.remove(item);
        } else {
            itemMap.put(item, newQty);
        }
    }

    /**
     * Represents a key-value pair of (Item, quantity) for constructing an Inventory.
     */
    public static class Entry {
        public final Item item;
        public final int quantity;

        public Entry(Item item, int quantity) {
            this.item = item;
            this.quantity = quantity;
        }
    }

    public List<Entry> getItems() {
        List<Entry> items = new ArrayList<>();
        for (Map.Entry<Item, Integer> e : itemMap.entrySet()) {
            items.add(new Entry(e.getKey(), e.getValue()));
            System.out.println(e.getKey());
        }
        return items;
    }

    public List<Item> getFoodItems() {
        List<Item> foodItems = new ArrayList<>();
        for (Item item : itemMap.keySet()) {
            if (item.getType() == Item.ItemType.FOOD) {
                foodItems.add(item);
            }
        }
        return foodItems;
    }
    public List<Item> getGiftItems() {
        List<Item> giftItems = new ArrayList<>();
        for (Item item : itemMap.keySet()) {
            if (item.getType() == Item.ItemType.GIFT) {
                giftItems.add(item);
            }
        }
        return giftItems;
    }
}
