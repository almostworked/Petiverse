package src; /**
 * When a new game is selected, the player is presented with a choice of picking from at least 3 different virtual pet types.
 * An image representing each pet type should be displayed on the screen and well as some basic information about each pet type.
 * The user should be able to select one of the pets and give it a name.
 * Once named, a new save file is created, and the user is brought to the main game screen.
 */

// note to self - players should be able to give the pet a nickname
// have to display info about each prt type
import src.Pet;

import java.io.FileWriter;
import java.io.IOException;

/**
 * The purpose of this class is to create a new game and initialize pet statistics such as
 * hunger, happiness, health and sleep
 */
public class NewGame {
    /** attributes */
    private String petName;
    private String playerName;
    private int saveSlot;
    private Pet selectedPet;

    /**
     * The constructor method
     * Initializes and saves a new game, assigning all statistics the value of 100
     *
     * @param playerName is the name of the player (stored using saveGame() method)
     * @param saveSlot is the save slot integer where the game will be stored
     * @param selectedPet is the Pet the player has chosen
     */
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

    /**
     * This method attempts to append the game information (player & pet data) to a csv file
     * If there is an issue in saving the game, an IOException is thrown
     *
     */
    public void saveGame() {
        try (FileWriter writer = new FileWriter("saved_games.csv", true)) {
            writer.write(saveSlot + "," + playerName + "," + petName + ", 100, 100, 100, 100\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @return the selected pet of type Pet
     */
    public Pet getSelectedPet() {
        return this.selectedPet;
    }

    /**
     * @return the name of the pet
     */
    public String getPetName() {
        return this.petName;
    }

    /**
     * @return the name of the player
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * @return the save slot integer
     */
    public int getSaveSlot() {
        return this.saveSlot;
    }
}
