
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The purpose of this class is to parse the csv file containing saved games
 * and load the selected game
 *
 * @author marcus
 */

    /**
     * This method parses through the game_save.csv file, scanning slot number, player name and pet name
     *
     * @return savedGames, a list of saved games
     */
public class LoadGame {
    private static final String SAVE_FILE = "game_save.csv";

    public List<String> loadSavedGames() {
        List<String> savedGames = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader("game_save.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) savedGames.add("Slot " + data[0] + ": " + data[1] + "'s pet " + data[2]);
            }
        } catch (IOException e) {
            System.out.println("An error occurred");
        }

        return savedGames;
    }

    /**
     * This method loads and opens the game based on the slot number passed as a parameter
     *
     * @param slotNumber represent the integer game slot to be loaded
     */
    public void loadGame(int slotNumber) {

        // made this actually work i think - Daniella

        Pet loadedPet = null;
        Player loadedPlayer = null;
        Inventory loadedInventory = new Inventory();
        boolean isParent = false; // Assuming player is not a parent
        System.out.println("Unloaded info initialized");

        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            String line;
            int slotCounter = 0;
            System.out.println("String line and slot count initialized");
            while ((line = reader.readLine()) != null) {
                System.out.println("Line confirmed not null");
                String[] data = line.split(","); // Put individual fields into an array
                System.out.println("Data length: " + data.length);
                System.out.println("Slot counter: " + slotCounter);
                System.out.println("Slot number: " + slotNumber);

                if (data.length == 9 && slotCounter == slotNumber) { // Find the correct slot number
                    System.out.println("Data length confirm = 9 and slotcounter = slot number");
                    String playerName = data[1]; // Assuming this is the username
                    String petName = data[2];
                    int health = Integer.parseInt(data[3]);
                    int sleep = Integer.parseInt(data[4]);
                    int hunger = Integer.parseInt(data[5]);
                    int happiness = Integer.parseInt(data[6]);
                    boolean alive = Boolean.parseBoolean(data[7]);
                    String state = data[8];
                    
                    loadedPet = new Pet(petName, health, sleep, happiness, alive, state);
                    loadedPlayer = new Player(playerName, loadedInventory, isParent, loadedPet);
                    //loadedInventory = loadInventory(slotNumber); TO DO when inventory is configured
                    System.out.println("PET LOADED");
                    PlayGameGUI playGameGUI = new PlayGameGUI(loadedPlayer, slotNumber, playerName); 
                    playGameGUI.setVisible(true);
                    
                    break;
                }
                slotCounter++;
            }

        } catch (IOException e) {
                System.out.println("Error occurred when trying to load game");
        }

    }
}
