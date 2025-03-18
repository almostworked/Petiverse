import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author faniyi
 * The {@code Inventory } class is to manage a collection of items
 * which can add, remove and update items in inventory
 */
public class Inventory {
        /**
         * A list to store the inventory items
         * A flag to check if the inventory contains gifts
         */
	private List<Item> items; 
	private boolean hasGifts;
	
        /**
         * default constructor that initializes an empty inventory
         */
	public Inventory() {
        this.items = new ArrayList<>();
        this.hasGifts = false;
    }
	
	/**
         * A constructor that initializes the inventory with existing items
         * @param items A list of items to initialize the inventory
         * @param hasGifts A boolean indicates if the inventory contains gifts
         */
	public Inventory(List<Item> items, boolean hasGifts) {
        this.items = new ArrayList<>(items);
        this.hasGifts = hasGifts;
    }
	
        /**
         * This retrieves the quantity of an item in the inventory
         * @param itemName The name of the item 
         * @return The amount of the specified item or 0 if it is not found
         */
        
	public int getQuantity(String itemName) {
		for (Item item : items) {
			if (item.getName().equalsIgnoreCase(itemName)) {
				return item.getQuantity();
			}
		}
		return 0; //This returns 0 if an item is not found
	}
	
        public void setQuantity(String itemName, int quantity){
            for (Item item : items){
                if (item.getName().equalsIgnoreCase(itemName)){
                    item.setQuantity(quantity);
                    return;
                }
            }
            // And if the item does not exist, we will add a quantity
            items.add(new Item(itemName, quantity));
        }
	/**
         * Display the inventory contents
         * @return the current inventory and the items in it
         */
	public Inventory displayInventory() {
		System.out.println("Inventory:");
		if (items.isEmpty()) {
			System.out.println("No items in inventory.");
		} else {
			for (Item item: items) {
				System.out.println(item.getName() + " - Quantity: " + item.getQuantity());
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
         * Updates the inventory by adding an amount of an item
         * If it exists, it will be increased else it will be created
         * @param itemName The name of the item to update 
         * @param quantity The amount of item to add
         */
	public void updateInventory (String itemName, int quantity) {
		for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        // If item does not exist, add it
        items.add(new Item(itemName, quantity));
	}
	
        /**
         * Removes a specified amount of an item from the inventory
         * If the quantity is zero or negative then it will be removed
         * @param itemName The name of the item to remove
         * @param quantity The quantity to remove
         */
	public void removeItem(String itemName, int quantity) {
		for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item.getName().equalsIgnoreCase(itemName)) {
                int newQuantity = item.getQuantity() - quantity;
                if (newQuantity <= 0) {
                    items.remove(i); // Remove item if quantity is zero or negative
                } else {
                    item.setQuantity(newQuantity);
                }
                return;
            }
        }
        System.out.println("Item not found in inventory.");
	}
	
	
	
	
}
