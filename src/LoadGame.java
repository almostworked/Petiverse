
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
    private static final String PARENTAL_FILE = "parental.csv";

    public List<String> loadSavedGames() {
        List<String> savedGames = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader("game_save.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    savedGames.add("Slot " + data[0] + ": " + data[1] + "'s pet " + data[2]  + ": " + data[9]);
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
        Inventory loadedInventory = new Inventory();
        boolean isParent = false;
    
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            String line;
            int slotCounter = 0;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 11 && slotCounter == slotNumber) {
                    String playerName = data[1];
                    String petName = data[2];
                    int health = Integer.parseInt(data[3]);
                    int sleep = Integer.parseInt(data[4]);
                    int hunger = Integer.parseInt(data[5]);
                    int happiness = Integer.parseInt(data[6]);
                    boolean alive = Boolean.parseBoolean(data[7]);
                    String state = data[8];
                    String dateCreated = data[9];
                    int score = Integer.parseInt(data[10]);
    
                    loadedPet = new Sprite(petName, health, sleep, happiness, hunger, alive, state);
                    loadedPlayer = new Player(playerName, loadedInventory, isParent, loadedPet);
                    loadedPlayer.getScore().setScore(score);
                        
                    loadParentalControls(loadedPlayer, slotNumber);

                    loadedPet.setState(state);
    
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

    /**
     * Method to load parental controls for a game
     * Currently assumes data is stored as (slot,password,restrictionsEnabled,startHour,endHour)
     *
     * @param player
     * @param slotNumber
     */
    private void loadParentalControls(Player player, int slotNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(PARENTAL_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                // Currently assumes the form (slot,password,restrictionsEnabled,startHour,endHour,...)
                if (data.length >= 5 && Integer.parseInt(data[0]) == slotNumber) {
                    System.out.println("Loading parental controls");

                    String parentPassword = data[1];
                    boolean restrictionsEnabled = Boolean.parseBoolean(data[2]);
                    int startHour = Integer.parseInt(data[3]);
                    int endHour = Integer.parseInt(data[4]);

                    Parent parent = new Parent(player.getName(), player.getInventory(), true, player.getActivePet(), parentPassword);
                    parent.setRestrictions(restrictionsEnabled, startHour, endHour);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred when trying to load parental controls.");
        }
    }
    
}
