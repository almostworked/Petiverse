/**
 * When a new game is selected, the player is presented with a choice of picking from at least 3 different virtual pet types.
 * An image representing each pet type should be displayed on the screen and well as some basic information about each pet type.
 * The user should be able to select one of the pets and give it a name.
 * Once named, a new save file is created, and the user is brought to the main game screen.
 */

// note to self - players should be able to give the pet a nickname
// have to display info about each prt type
import java.io.FileWriter;
import java.io.IOException;

public class NewGame {
    private String petName;
    private String playerName;
    private int saveSlot;
    private Pet selectedPet;

    // Constructor
    public NewGame(String playerName, int saveSlot, Pet selectedPet) {
        this.playerName = playerName;
        this.saveSlot = saveSlot;
        this.selectedPet = selectedPet;
        this.petName = selectedPet.getName();
        this.selectedPet.setHunger(100);
        this.selectedPet.setHappiness(100);
        this.selectedPet.setHealth(100);
        this.selectedPet.setSleep(100);

        saveGame();
    }

    public void saveGame() {
        try (FileWriter writer = new FileWriter("saved_games.csv", true)) {
            writer.write(saveSlot + "," + playerName + "," + petName + ", 100, 100, 100, 100\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Pet getSelectedPet() {
        return this.selectedPet;
    }

    public String getPetName() {
        return this.petName;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public int getSaveSlot() {
        return this.saveSlot;
    }
}
