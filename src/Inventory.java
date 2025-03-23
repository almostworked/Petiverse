import java.util.ArrayList;
import java.util.List;

public class Inventory {
	private List<Item> items; //Made a list to store the items
	private boolean hasGifts;
		private String itemName;
		
		//2 constructors
		//empty constructor
		public Inventory() {
			this.items = new ArrayList<>();
			this.hasGifts = false;
		}
		
		//Constructor with existing items
		public Inventory(List<Item> items, boolean hasGifts) {
			this.items = new ArrayList<>(items);
			this.hasGifts = hasGifts;
		}
		
		public int getQuantity(String itemName) {
			for (Item item : items) {
				if (item.getItem().equalsIgnoreCase(itemName)) {
					return item.getQuantity(itemName);
				}
			}
			return 0; //This returns 0 if item is not found
		}
		
		//Display inventory contents
		public Inventory displayInventory() {
			System.out.println("Inventory:");
			if (items.isEmpty()) {
				System.out.println("No items in inventory.");
			} else {
				for (Item item: items) {
					System.out.println(item.getItem() + " - Quantity: " + item.getQuantity(itemName));
			}
		}
		return this;
	}
	
	public void displayMethods() {
		System.out.println("Here are the available inventory methods:");
        System.out.println("1. You can display inventory");
        System.out.println("2. You can refreshUpdate Inventory");
        System.out.println("3. Remove Item");
	}
	
	public void updateInventory (String itemName, int quantity) {
		for (Item item : items) {
            if (item.getItem().equalsIgnoreCase(itemName)) {
                item.setQuantity(item.getQuantity(itemName) + quantity);
                return;
            }
        }
        // If item does not exist, add it
        items.add(new Item(itemName, itemName, quantity));
	}
	
	public void removeItem(String itemName, int quantity) {
		for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item.getItem().equalsIgnoreCase(itemName)) {
                int newQuantity = item.getQuantity(itemName) - quantity;
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
