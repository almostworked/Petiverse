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
        // Create a Pet with health=10, fullness=50, happiness=50, isAlive=true.
        Pet pet = new Pet("TestPet", 10, 80, 50, 50, true, "NORMAL");
        
        // Decrement health repeatedly until it hits 0.
        while (pet.getHealth() > 0) {
            int newHealth = pet.getHealth() - 1;
            pet.setHealth(newHealth);
        }

        // Assert that the pet’s state is now "DEAD".
        assertEquals(0, pet.getHealth(), "Health should be 0");
        assertEquals("DEAD", pet.getState(), "Pet's state should be DEAD once health=0");

        // Attempt to feed the pet or play with the pet
        pet.feed(new Item("Apple", Item.ItemType.FOOD, 10));
        assertEquals("DEAD", pet.getState(), "State should remain DEAD after attempting feed");
        // or if your code raises an exception or logs an error for a dead pet, you can test that logic accordingly.

        // Attempt to play
        pet.play();
        assertEquals("DEAD", pet.getState(), "State should remain DEAD after attempting play");
    }
}
