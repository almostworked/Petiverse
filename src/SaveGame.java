package src;

import src.Inventory;
import src.Pet;
import src.logic.ParentalControls;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

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
    private void savePet(Pet pet) {
        String filename = "pets.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(saveSlot + "," + pet.getName() + "," + pet.getHappiness() + "," + pet.getHunger() + "," + pet.getHealth());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Appends the inventory contents to a csv file
     * @param inventory holds the game's inventory contents
     */
    private void saveInventory(Inventory inventory) {
        String filename = "inventory.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(saveSlot + "," + String.join(";", inventory.getItems()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // FIXME
    /**
     * Appends the parental controls & restrictions to a csv file
     *
     * @param parentalControls contains the parental settings for this game
     */
    private void saveParentalControls(ParentalControls parentalControls) {
        if (!isParent) return;

        String filename = "parental.csv";
//        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
//            writer.println(saveSlot + "," + parentalControls.isEnabled() + "," + parentalControls.getRestrictions());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Appends the save slot, along with the savedName, to a csv file
     */
    private void saveSaveSlot() {
        String filename = "save_slots.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(saveSlot + "," + savedName);
        } catch (IOException e) {
            e.printStackTrace();
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
        savePet(pet);
        saveInventory(inventory);
        saveParentalControls(parentalControls);
        saveSaveSlot();

        System.out.println("Game saved successfully");
    }
}
