package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author faniyi
 * The {@code Inventory } class is to manage a collection of items
 * which can add, remove and update items in inventory
 */
public class Inventory {
    /**
     * We store each Item along with an integer quantity
     * using a Map that associates an Item to its quantity.
     * 
     * A flag to check if the inventory contains gifts is also provided.
     */
    private Map<Item, Integer> itemMap;
    private boolean hasGifts;
    
    /**
     * default constructor that initializes an empty inventory
     */
    public Inventory() {
        this.itemMap = new HashMap<>();
        this.hasGifts = false;
    }
    
    /**
     * A constructor that initializes the inventory with existing items
     * (wrapped in a Map of Item -> quantity) 
     * @param entries   A list of Entry objects to initialize the inventory
     * @param hasGifts  A boolean indicating if the inventory contains gifts
     */
    public Inventory(List<Entry> entries, boolean hasGifts) {
        this.itemMap = new HashMap<>();
        for (Entry e : entries) {
            this.itemMap.put(e.item, e.quantity);
        }
        this.hasGifts = hasGifts;
    }
    
    /**
     * This retrieves the quantity of an item in the inventory
     * @param item The item whose quantity is being requested
     * @return The amount of the specified item or 0 if it is not found
     */
    public int getQuantity(Item item) {
        return itemMap.getOrDefault(item, 0);
    }
    
    /**
     * Sets the quantity for an item in the inventory.
     * If the item doesn't exist, it is created and set to the specified quantity.
     * @param item     The item to set
     * @param quantity The quantity to set
     */
    public void setQuantity(Item item, int quantity) {
        itemMap.put(item, quantity);
    }
    
    /**
     * Display the inventory contents
     * @return the current inventory (this) and the items in it
     */
    public Inventory displayInventory() {
        System.out.println("Inventory:");
        if (itemMap.isEmpty()) {
            System.out.println("No items in inventory.");
        } else {
            for (Map.Entry<Item, Integer> entry : itemMap.entrySet()) {
                // We'll just show a reference to the item object and the quantity.
                // (We do NOT call item methods like getName() or getEffectValue().)
                System.out.println("Item object " + entry.getKey() 
                                   + " - Quantity: " + entry.getValue());
            }
        }
        return this;
    }
    
    /**
     * Displays the available inventory methods 
     */
    public void displayMethods() {
        System.out.println("Here are the available inventory methods:");
        System.out.println("1. You can display inventory");
        System.out.println("2. You can Update Inventory");
        System.out.println("3. Remove Item");
    }
    
    /**
     * Updates the inventory by adding an amount of an item.
     * If it exists, it is increased; otherwise, it is created.
     * @param item     The item to update
     * @param quantity The amount of the item to add
     */
    public void updateInventory(Item item, int quantity) {
        int oldQty = itemMap.getOrDefault(item, 0);
        itemMap.put(item, oldQty + quantity);
    }
    
    /**
     * Removes a specified amount of an item from the inventory.
     * If the resulting quantity is zero or negative, 
     * the item is removed entirely from the Map.
     * @param item     The item to remove
     * @param quantity The quantity to remove
     */
    public void removeItem(Item item, int quantity) {
        if (!itemMap.containsKey(item)) {
            System.out.println("Item not found in inventory.");
            return;
        }
        int oldQty = itemMap.get(item);
        int newQty = oldQty - quantity;
        if (newQty <= 0) {
            itemMap.remove(item); // Remove item if quantity becomes zero or negative
        } else {
            itemMap.put(item, newQty);
        }
    }
    
    /**
     * Simple inner class for the constructor that takes a List<Entry>:
     * Allows passing in an item/quantity pair.
     */
    public static class Entry {
        public final Item item;
        public final int quantity;

        public Entry(Item item, int quantity) {
            this.item = item;
            this.quantity = quantity;
        }
    }
}
