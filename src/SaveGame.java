import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

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

    private void savePet(Pet pet) {
        String filename = "pets.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(saveSlot + "," + pet.getName() + "," + pet.getHappiness() + "," + pet.getHunger() + "," + pet.getHealth());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveInventory(Inventory inventory) {
        String filename = "inventory.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(saveSlot + "," + String.join(";", inventory.getItems()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // FIXME
    private void saveParentalControls(ParentalControls parentalControls) {
        if (!isParent) return;

        String filename = "parental.csv";
//        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
//            writer.println(saveSlot + "," + parentalControls.isEnabled() + "," + parentalControls.getRestrictions());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void saveSaveSlot() {
        String filename = "save_slots.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(saveSlot + "," + savedName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(Pet pet, Inventory inventory, ParentalControls parentalControls) {
        savePet(pet);
        saveInventory(inventory);
        saveParentalControls(parentalControls);
        saveSaveSlot();

        System.out.println("Game saved successfully");
    }
}
