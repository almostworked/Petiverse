
/**
 * Represents an item that can be stored and managed within an inventory system.
 * Each item has a name, type, and effectValue (e.g. how much fullness or happiness it provides).
 */
public class Item {

    public enum ItemType { FOOD, GIFT }

    private String name;
    private ItemType type;
    private int effectValue;
    private int quantity;

    public Item(String name, ItemType type, int effectValue) {
        this.name = name;
        this.type = type;
        this.effectValue = effectValue;
    }

    // Predefined items (example)
    public static final Item APPLE    = new Item("Apple", ItemType.FOOD, 20);
    public static final Item FISH     = new Item("Fish", ItemType.FOOD, 30);
    public static final Item STEAK    = new Item("Steak", ItemType.FOOD, 50);
    public static final Item BALL     = new Item("Ball", ItemType.GIFT, 25);
    public static final Item COLLAR   = new Item("Collar", ItemType.GIFT, 40);
    public static final Item TOY_MOUSE= new Item("Toy Mouse", ItemType.GIFT, 35);

    public String getName() {
        return name;
    }
    public ItemType getType() {
        return type;
    }
    public int getEffectValue() {
        return effectValue;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }

    public static Item getItem(String name) {
        for (Item item : new Item[]{APPLE, FISH, STEAK, BALL, COLLAR, TOY_MOUSE}) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;  // Ignore type and just return if name matches
            }
        }
        return null;
    }
   
}



