import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class SaveGame {
    private int saveSlot;
    private boolean isParent;
    private String savedName;

    public SaveGame(int saveSlot, boolean isParent) {
        this.saveSlot = saveSlot;
        this.isParent = isParent;
    }

    public void setSavedName(String savedName) {
        this.savedName = savedName;
    }

    // Save pet details into the save file
    public void savePet(Pet pet) {
        String filename = "game_save.csv"; // Match LoadGame's file
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            // Format: saveSlot, playerName, petName, health, sleep, happiness, hunger, alive, state, creationDate
            String creationDate = LocalDateTime.now().toString(); // Add creation date
            writer.println(String.format("%d,%s,%s,%d,%d,%d,%d,%b,%s,%s",
                    saveSlot, savedName, pet.getName(), pet.getHealth(), pet.getSleep(),
                    pet.getHappiness(), pet.getHunger(), pet.isAlive(), pet.getState(), creationDate));
        } catch (IOException e) {
            System.out.println("Error saving pet data: " + e.getMessage());
        }
    }

    // Save inventory details (if necessary)
    private void saveInventory(Inventory inventory) {
        String filename = "inventory.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(saveSlot + "," + String.join(";", inventory.getItems()));
        } catch (IOException e) {
            System.out.println("Error saving inventory data: " + e.getMessage());
        }
    }

    // Save parental control data (if applicable)
    private void saveParentalControls(ParentalControls parentalControls) {
        if (!isParent) return; // Skip if not in parental mode

        String filename = "parental.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(saveSlot + "," + parentalControls.isEnabled() + "," + parentalControls.getRestrictions());
        } catch (IOException e) {
            System.out.println("Error saving parental controls: " + e.getMessage());
        }
    }

    // Save the player's name with their save slot (if necessary)
    private void saveSaveSlot() {
        String filename = "save_slots.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(saveSlot + "," + savedName);
        } catch (IOException e) {
            System.out.println("Error saving save slot data: " + e.getMessage());
        }
    }

    // Main method to save all game data
    public void save(Pet pet, Inventory inventory, ParentalControls parentalControls) {
        savePet(pet); // Save pet data
        saveInventory(inventory); // Save inventory data
        saveParentalControls(parentalControls); // Save parental controls if applicable
        saveSaveSlot(); // Save the player's name with their save slot

        System.out.println("Game saved successfully in slot " + saveSlot);
    }
}