import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * The purpose of this class is to save the data of a game in progress
 * Game states are stored in respective csv files and are available to be loaded
 * in future play sessions
 */
public class SaveGame {
    /** attributes */
    private int saveSlot;
    private boolean isParent;
    private String savedName;

    /**
     * Assigns the parameters to the class attributes
     *
     * @param saveSlot
     * @param isParent
     */
    public SaveGame(int saveSlot, boolean isParent) {
        this.saveSlot = saveSlot;
        this.isParent = isParent;
    }

    /**
     * Assigns the savedName parameter to the class attribute
     *
     * @param savedName is the String name to be saved
     */
    public void setSavedName(String savedName) {
        this.savedName = savedName;
    }

    /**
     * Appends the pet information to a csv file
     *
     * @param pet is the current pet (pet state) in this session
     */
    public void savePet(Pet pet) {
        String filename = "game_save.csv"; // Match LoadGame's file
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            // Format: saveSlot, playerName, petName, health, sleep, happiness, hunger, alive, state, creationDate
            String creationDate = LocalDateTime.now().toString(); // Add creation date
            writer.println(String.format("%d,%s,%s,%d,%d,%d,%d,%b,%s,%s",
                    saveSlot, savedName, pet.getName(), pet.getHealth(), pet.getSleep(),
                    pet.getHappiness(), pet.getFullness(), pet.isAlive(), pet.getState(), creationDate));
        } catch (IOException e) {
            System.out.println("Error saving pet data: " + e.getMessage());
        }
    }

    /**
     * Appends the inventory contents to a csv file
     * @param inventory holds the game's inventory contents
     */
    // Save inventory details (if necessary)
    private void saveInventory(Inventory inventory) {
        String filename = "inventory.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(saveSlot + "," + String.join(";", inventory.getItems()));
        } catch (IOException e) {
            System.out.println("Error saving inventory data: " + e.getMessage());
        }
    }

    // FIXME
    /**
     * Appends the parental controls & restrictions to a csv file
     *
     * @param parentalControls contains the parental settings for this game
     */
    // Save parental control data (if applicable)
    private void saveParentalControls(ParentalControls parentalControls) {
        if (!isParent) return; // Skip if not in parental mode

        String filename = "parental.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(saveSlot + "," + parentalControls.isRestrictionsEnabled() + "," + parentalControls.getRestrictions());
        } catch (IOException e) {
            System.out.println("Error saving parental controls: " + e.getMessage());
        }
    }

    /**
     * Appends the save slot, along with the savedName, to a csv file
     */
    // Save the player's name with their save slot (if necessary)
    private void saveSaveSlot() {
        String filename = "save_slots.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(saveSlot + "," + savedName);
        } catch (IOException e) {
            System.out.println("Error saving save slot data: " + e.getMessage());
        }
    }

    /**
     * This method saves game states and information, calling upon the appropriate helper methods
     * If all information is saved as expected, a success message is output to the terminal
     *
     * @param pet is the Pet data to be saved
     * @param inventory is the Inventory data to be saved
     * @param parentalControls are the ParentalControls to be saved
     */
    public void save(Pet pet, Inventory inventory, ParentalControls parentalControls) {
        savePet(pet); // Save pet data
        saveInventory(inventory); // Save inventory data
        saveParentalControls(parentalControls); // Save parental controls if applicable
        saveSaveSlot(); // Save the player's name with their save slot

        System.out.println("Game saved successfully in slot " + saveSlot);
    }
}