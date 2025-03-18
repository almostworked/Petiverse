package src;

/**
 * Represents an item that can be stored and managed within an inventory system.
 * Each item has a name, type, and quantity.
 */
public class Item {

    /** The name of the item. */
    private String itemName;

    /** The type/category of the item (e.g., food, toy, etc.). */
    private String type;

    /** The current quantity of this item. */
    private int quantity;

    /**
     * Constructs a new Item with a specified name, type, and quantity.
     *
     * @param itemName The name of the item.
     * @param type The category or classification of the item.
     * @param quantity The initial quantity of the item.
     */
    public Item(String itemName, String type, int quantity) {
        this.itemName = itemName;
        this.type = type;
        this.quantity = quantity;
    }

    /**
     * Retrieves the name of this item.
     *
     * @return The name of the item.
     */
    public String getItem() {
        return this.itemName;
    }

    /**
     * Removes a specified quantity of this item.
     *
     * @param itemName The name of the item to remove (currently not used internally).
     * @param quantity The amount to remove from this item's quantity.
     *                 If removal exceeds current quantity, the quantity is set to zero.
     */
    public void removeItem(String itemName, int quantity) {
        this.quantity -= quantity;
        if (this.quantity < 0) {
            this.quantity = 0;
        }
    }

    /**
     * Retrieves the quantity of the specified item.
     *
     * @param itemName The name of the item to get quantity for (currently not used internally).
     * @return The current quantity of this item.
     */
    public int getQuantity(String itemName) {
        return this.quantity;
    }
}











}
