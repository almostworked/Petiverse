/**
 * Represents an item that can be stored and managed within an inventory system.
 * Each item has a name, type, and effectValue (e.g. how much fullness or happiness it provides).
 */
public class Item {
    
    /**
     * Defines the types of items: FOOD and GIFT.
     */
    public enum ItemType { FOOD, GIFT }

    private String name;
    private ItemType type;
        private int effectValue;
        private int quantity;
    
        /**
         * Constructs a new Item.
         *
         * @param name        The name of the item (e.g., "Apple", "Fish").
         * @param type        The type of the item (e.g., FOOD or GIFT).
         * @param effectValue The effect value of the item (e.g., how much it increases fullness or happiness).
         */
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
    
        /**
         * Retrieves the name of the item.
         *
         * @return The name of the item.
         */
        public String getName() {
            return name;
        }
        
        /**
         * Retrieves the type of the item (FOOD or GIFT).
         *
         * @return The type of the item.
         */
        public ItemType getType() {
            return type;
        }
        
        /**
         * Retrieves the effect value of the item (e.g., how much it affects the pet's stats).
         *
         * @return The effect value of the item.
         */
        public int getEffectValue() {
            return effectValue;
        }
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
        public int getQuantity() {
            return quantity;
        }
    
        /**
         * Retrieves a predefined item based on its name and type.
         * 
         * @param name The name of the item.
         * @param type The type of the item (FOOD or GIFT).
         * @return The matching Item object.
         * @throws IllegalArgumentException If no item matches the given name and type.
         */
       
            // Simple matching across the predefined items
        public static Item getItem(String name) {
            for (Item item : new Item[]{APPLE, FISH, STEAK, BALL, COLLAR, TOY_MOUSE}) {
                if (item.getName().equalsIgnoreCase(name)) {
                    return item;  // Ignore type and just return if name matches
                }
            }
            throw new IllegalArgumentException("Invalid item: " + name);
    }
    @Override
        public String toString() {
            return name;
        }

    
   
   
}


