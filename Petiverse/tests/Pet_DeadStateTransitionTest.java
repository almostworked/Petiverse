package com.mycompany.group30.tests;

import com.mycompany.petiverse.Pet;
import com.mycompany.petiverse.Item;
import com.mycompany.petiverse.Inventory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Verifies that the pet transitions to Dead when health reaches 0,
 * and that commands become unavailable in this state.
 */
public class Pet_DeadStateTransitionTest {

    @Test
    public void testPetTransitionsToDead() {
        // Create an Inventory and add an Apple for the feed method to work.
        Inventory inv = new Inventory();
        inv.updateInventory(Item.getItem("Apple"), 1);

        // Create a dummy Score object (assuming Score has the required methods).
        Score score = new Score();

        // Create a Player with the inventory and score.
        Player player = new Player("TestPlayer", inv, false, null);
        player.setScore(score);

        // Set the active player so that Pet.feed() and other methods can access it.
        Pet.setActivePlayer(player);

        // Create a Pet with health=10, fullness=50, happiness=50, and alive status true.
        Pet pet = new Pet("TestPet", 10, 80, 50, 50, true, "NORMAL");

        // Decrement health repeatedly until it hits 0.
        while (pet.getHealth() > 0) {
            int newHealth = pet.getHealth() - 1;
            pet.setHealth(newHealth);
        }

        // Assert that the pet's health is now 0 and state is "DEAD".
        assertEquals(0, pet.getHealth(), "Health should be 0");
        assertEquals("DEAD", pet.getState(), "Pet's state should be DEAD once health=0");

        // Attempt to feed the pet - should not change the state because the pet is dead.
        pet.feed(Item.getItem("Apple"));
        assertEquals("DEAD", pet.getState(), "State should remain DEAD after attempting feed");

        // Attempt to play with the pet - state should remain "DEAD".
        pet.play();
        assertEquals("DEAD", pet.getState(), "State should remain DEAD after attempting play");
    }
}
