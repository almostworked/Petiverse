package com.mycompany.group30.tests;

import com.mycompany.petiverse.Item;
import com.mycompany.petiverse.Pet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Confirms that Feed raises fullness, and Take to Vet raises health,
 * with correct cooldown or penalties if applicable.
 */
public class Commands_Feed_And_VetTest {

    @Test
public void testFeedIncreasesFullness() {
    // Create Inventory and add at least one apple.
    Inventory inv = new Inventory();
    inv.updateInventory(Item.getItem("Apple"), 1);
    
    // Create a Player with the inventory and a dummy Score object.
    // (Assume you have a Score class; if not, create a stub that supports increaseScore)
    Player player = new Player("TestPlayer", inv, false, null);
    
    // Set the active player so that Pet.feed() can access it.
    Pet.setActivePlayer(player);
    
    // Create a pet and an apple item.
    Pet pet = new Pet("TestPet", 60, 80, 50, 30, true, "NORMAL");
    Item apple = new Item("Apple", Item.ItemType.FOOD, 10);
    
    pet.feed(apple);
    // We expect fullness from 30 -> 40
    assertEquals(40, pet.getFullness(), "Feeding +10 fullness from Apple");
}

@Test
public void testTakeToVetRestoresHealth() {
    // Create Inventory (score handling might be necessary if the method decreases score)
    Inventory inv = new Inventory();
    // Create a dummy Score instance (assuming Score has a decreaseScore method)
    Score score = new Score();
    
    // Create a Player and set as active.
    Player player = new Player("TestPlayer", inv, false, null);
    player.setScore(score);
    Pet.setActivePlayer(player);
    
    // Create a pet with reduced health and a non-normal state.
    Pet pet = new Pet("TestPet", 40, 80, 50, 70, true, "SICK");
    
    pet.takeToVet();
    assertEquals(100, pet.getHealth(), "Health should be 100 after vet visit");
    assertEquals("NORMAL", pet.getState(), "State should be NORMAL after vet");
}

}
