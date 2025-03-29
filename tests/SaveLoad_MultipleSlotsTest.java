package com.mycompany.group30.tests;

import com.mycompany.petiverse.Pet;
import com.mycompany.petiverse.Inventory;
import com.mycompany.petiverse.SaveGame;
import com.mycompany.petiverse.LoadGame;
import com.mycompany.petiverse.Player;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Verifies that saving to multiple slots and loading them independently works as expected.
 * This is more of an integration test than a pure unit test.
 */
public class SaveLoad_MultipleSlotsTest {

    @Test
    public void testSaveLoadMultipleSlots() {
        // 1. Create a new game in slot 1, pet fullness=70
        Pet petSlot1 = new Pet("PetSlot1", 100, 80, 50, 70, true, "NORMAL");
        Inventory inv1 = new Inventory();
        Player player1 = new Player("Player1", inv1, false, petSlot1);
        SaveGame.save(petSlot1, inv1, 1); // Hypothetical method signature using slot=1

        // 2. Create another new game in slot 2, fullness=20
        Pet petSlot2 = new Pet("PetSlot2", 100, 80, 50, 20, true, "NORMAL");
        Inventory inv2 = new Inventory();
        Player player2 = new Player("Player2", inv2, false, petSlot2);
        SaveGame.save(petSlot2, inv2, 2); // Hypothetical method signature using slot=2

        // 3. Load slot 1
        LoadGame loader = new LoadGame();
        loader.loadGame(1);
        // Typically, you'd retrieve references to the loaded pet/player from the load method.
        // For a direct test, you might do something like:
        Pet loadedSlot1 = LoadGame.getLastLoadedPet();  // Example accessor you might provide
        assertNotNull(loadedSlot1, "Loaded pet from slot 1 should not be null");
        assertEquals(70, loadedSlot1.getFullness(), "Pet in slot 1 should have fullness=70");

        // 4. Load slot 2
        loader.loadGame(2);
        Pet loadedSlot2 = LoadGame.getLastLoadedPet();  // Example accessor
        assertNotNull(loadedSlot2, "Loaded pet from slot 2 should not be null");
        assertEquals(20, loadedSlot2.getFullness(), "Pet in slot 2 should have fullness=20");

        // Ensure slot 1 was unaffected
        // Possibly re-load slot 1 to confirm or rely on the above result.
    }
}

