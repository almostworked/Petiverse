package src;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The purpose of this class is to parse the csv file containing saved games
 * and load the selected game
 *
 * @author marcus
 */
 class LoadGame {

    /**
     * This method parses through the game_save.csv file, scanning slot number, player name and pet name
     *
     * @return savedGames, a list of saved games
     */
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
        // have to load and open the game!
        // doesnt load anything rn
        System.out.println("Loading game...");

    }
}
