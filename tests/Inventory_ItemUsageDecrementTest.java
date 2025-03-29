package com.mycompany.group30.tests;

import com.mycompany.petiverse.Inventory;
import com.mycompany.petiverse.Item;
import com.mycompany.petiverse.Item.ItemType;
import com.mycompany.petiverse.Pet;
import com.mycompany.petiverse.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Ensures that using an item (feed or gift) decreases the corresponding item count in the inventory.
 */
public class Inventory_ItemUsageDecrementTest {

    @Test
    public void testItemUsageReducesInventoryCount() {
        // Create an Inventory with Apple=3, Ball=2
        Inventory inv = new Inventory();
        inv.updateInventory(Item.getItem("Apple", ItemType.FOOD), 3);
        inv.updateInventory(Item.getItem("Ball", ItemType.GIFT), 2);

        // Create a Pet & Player so we can feed the pet.
        Pet pet = new Pet("TestPet", 100, 80, 50, 50, true, "NORMAL");
        Player player = new Player("TestPlayer", inv, false, pet);

        // Use one apple to feed the pet
        // Typically you'd do something like: pet.feed(...) or player.feedPet(...), 
        // but for demonstration let's just remove it from inventory:
        int applesBefore = inv.getQuantity(Item.getItem("Apple", ItemType.FOOD));
        pet.feed(Item.getItem("Apple", ItemType.FOOD)); 
        // Or possibly you'd do: inv.removeItem(Item.getItem("Apple", ItemType.FOOD), 1);

        int applesAfter = inv.getQuantity(Item.getItem("Apple", ItemType.FOOD));

        // We expect the count to drop by 1
        assertEquals(applesBefore - 1, applesAfter, "Apple count should decrease after feeding");
    }
}
