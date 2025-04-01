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
    // Create an Inventory and add extra Apples.
    Inventory inv = new Inventory();
    inv.updateInventory(Item.getItem("Apple"), 3);
    
    // Create a Pet and a Player.
    Pet pet = new Pet("TestPet", 100, 80, 50, 50, true, "NORMAL");
    Player player = new Player("TestPlayer", inv, false, pet);
    
    // Set the active player so Pet.feed() can access the inventory.
    Pet.setActivePlayer(player);
    
    // Record the initial apple count.
    int applesBefore = inv.getQuantity(Item.getItem("Apple"));
    
    // Feed the pet with an apple.
    pet.feed(Item.getItem("Apple"));
    
    // Record the apple count after feeding.
    int applesAfter = inv.getQuantity(Item.getItem("Apple"));
    
    // Expect the apple count to decrease by 1.
    assertEquals(applesBefore - 1, applesAfter, "Apple count should decrease after feeding");
}

}
