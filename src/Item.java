package src;
public class Item {
    
    private String itemName;
    private String type;
    private int quantity;

    public Item(String itemName, String type, int quantity) {
        this.itemName = itemName;
        this.type = type;
        this.quantity = quantity;
    }

    public String getItem() {
        return this.itemName;
    }

    public void removeItem(String itemName, int quantity) {
        this.quantity -= quantity;

        if (this.quantity < 0) {
            this.quantity = 0;
        } 

    }

    public int getQuantity(String itemName) {
        return this.quantity;
    }










}
