import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The purpose of this class is to save the data of a game in progress
 * Game states are stored in respective csv files and are available to be loaded
 * in future play sessions
 */
public class SaveGame {
    /** attributes */
    private static int saveSlot;
    private boolean isParent;
    private static String savedName;

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
    public static void savePet(Pet pet) {
    String filename = "game_save.csv";
    try {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(String.valueOf(saveSlot))) {
                    String updatedLine = String.format("%d,%s,%s,%d,%d,%d,%d,%b,%s,%s,%d",
                            saveSlot, savedName, pet.getName(), pet.getHealth(), pet.getSleep(),
                            pet.getHappiness(), pet.getFullness(), pet.isAlive(), pet.getState(), LocalDateTime.now().toString(), pet.getActivePlayer().getScore().getScore());
                    lines.add(updatedLine);
                } else {
                    lines.add(line); 
                }
            }
        }

        // Write back the data to the file
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (String line : lines) {
                writer.println(line);
            }
        }
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
        StringBuilder inventoryData = new StringBuilder();
        for (Map.Entry<Item, Integer> entry : inventory.itemMap.entrySet()) {
            inventoryData.append(entry.getKey().getName()).append(":").append(entry.getValue()).append(";");
        }
        writer.println(saveSlot + "," + inventoryData.toString());
    } catch (IOException e) {
        System.out.println("Error saving inventory data: " + e.getMessage());
    }
}

    

    /**
     * Appends the parental controls & restrictions to a csv file
     * Saves parental control data (if applicable)
     *
     * @param parentalControls contains the parental settings for this game
     */
    private void saveParentalControls(ParentalControls parentalControls) {
        if (!isParent) return; // Skip if not in parental mode

        String filename = "parental.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {

            // SET THE DEFAULT PASSWORD TO "password" FOR NOW
            writer.println(String.format("%d,%s,%b,%s,%s",
                    saveSlot, "password", parentalControls.isRestrictionsEnabled(),
                    parentalControls.getRestrictions(), LocalDateTime.now()));
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
       // saveInventory(inventory); // Save inventory data
        saveParentalControls(parentalControls); // Save parental controls if applicable
        saveInventory(inventory);
        saveSaveSlot(); // Save the player's name with their save slot

        System.out.println("Game saved successfully in slot " + saveSlot);
    }
}