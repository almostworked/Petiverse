import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoadGame {
    public List<String> loadSavedGames() {
        List<String> savedGames = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader("game_save.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) savedGames.add("Slot " + data[0] + ": " + data[1] + "'s pet " + data[2]);
            }
        } catch (IOException e) {
            System.out.println("An error occured");
        }

        return savedGames;
    }
    public void loadGame(int slotNumber) {
        // have to load and open the game!
        // doesnt load anything rn
        System.out.println("Loading game...");

    }
}
