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
        Pet pet = new Pet("TestPet", 60, 80, 50, 30, true, "NORMAL");
        Item apple = new Item("Apple", Item.ItemType.FOOD, 10);
        pet.feed(apple);
        // We expect fullness from 30 -> 40
        assertEquals(40, pet.getFullness(), "Feeding +10 fullness from Apple");
    }

    @Test
    public void testTakeToVetRestoresHealth() {
        Pet pet = new Pet("TestPet", 40, 80, 50, 70, true, "SICK");
        pet.takeToVet();
        assertEquals(100, pet.getHealth(), "Health should be 100 after vet visit");
        assertEquals("NORMAL", pet.getState(), "State should be NORMAL after vet");
    }
}
