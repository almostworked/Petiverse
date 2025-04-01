
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
    private static final String SAVE_FILE = "data/game_save.csv";
    private static final String INVENTORY_FILE = "data/inventory.csv";
    private Inventory loadedInventory;

    public List<String> loadSavedGames() {
        List<String> savedGames = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader("data/game_save.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 10) {
                    savedGames.add("Slot " + data[0] + ": " + data[1] + "'s pet " + data[3]  + ": " + data[10] + ": " + data[9] + ": " + data[2]);
                }
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
        Pet loadedPet = null;
        Player loadedPlayer = null;
        Inventory loadedInventory = loadInventory(slotNumber);
        boolean isParent = false;
    
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            String line;
            int slotCounter = 1;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 10) { continue; }
                if (slotCounter == slotNumber) {
                    System.out.println("passed check + found game slot number");
                    String playerName = data[1];
                    String petType = data[2];
                    String petName = data[3];
                    int health = Integer.parseInt(data[4]);
                    int sleep = Integer.parseInt(data[5]);
                    int hunger = Integer.parseInt(data[6]);
                    int happiness = Integer.parseInt(data[7]);
                    boolean alive = Boolean.parseBoolean(data[8]);
                    String state = data[9];
                    int score = 0;
                    if (data.length > 11) {
                        score = Integer.parseInt(data[11]);
                    }
    
                    loadedPet = new Sprite(petType, health, sleep, happiness, hunger, alive, state);
                    loadedPet.setCustomName(petName);
                    loadedPlayer = new Player(playerName, loadedInventory, isParent, loadedPet);
                    loadedPlayer.getScore().setScore(score);
                        
                    loadedPet.setState(state);
    
                    PlayGameGUI playGameGUI = new PlayGameGUI(loadedPlayer, slotNumber, playerName); 
                    playGameGUI.setVisible(true);
                    break;
                } else {
                    slotCounter++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred when trying to load game");
        }
    }
    public Inventory loadInventory(int slotNumber) {
    
        try (BufferedReader reader = new BufferedReader(new FileReader(INVENTORY_FILE))) {
            String line;
            int slotCounter = 1;
            while ((line = reader.readLine()) != null) {
                if (slotCounter == slotNumber) {
                    String[] data = line.split(",");
                    System.out.println("found inventory slot number");
                   loadedInventory = new Inventory(true);
                   String[] itemsData = data[1].split(";");

                   for (String entry : itemsData) {
                    String[] tuple = entry.split(":");
                    String itemName = tuple[0].trim();
                    int quantity = Integer.parseInt(tuple[1]);

                    try {
                        Item item = Item.fromName(itemName);
                        loadedInventory.updateInventory(item, quantity);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid item: " + itemName);
                    }
                }
                break;
                } else {
                    slotCounter++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred when trying to load inventory");
        }
        return loadedInventory;
    }

  
    
}
