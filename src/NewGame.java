import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

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

        // Set default pet stats
        this.selectedPet.setHealth(100);
        this.selectedPet.setSleep(100);
        this.selectedPet.setHappiness(100);
        this.selectedPet.setHunger(100);
        this.selectedPet.setAlive(true);
        this.selectedPet.setState("NORMAL");

        choosePetName();

        // Save the game
        saveGame();
    }

    private void choosePetName() {
        Scanner scanner = new Scanner(System.in); // Not in use yet
        System.out.println("Enter a name for your new pet (" + selectedPet.getName() + "): ");
        this.petName = scanner.nextLine().trim();

        if (this.petName.isEmpty()) {
            this.petName = selectedPet.getName();
        }
        selectedPet.setName(this.petName);
    }

    public void saveGame() {
        int health = this.selectedPet.getHealth();
        int sleep = this.selectedPet.getSleep();
        int happiness = this.selectedPet.getHappiness();
        int hunger = this.selectedPet.getHunger();
        boolean alive = this.selectedPet.isAlive();
        String state = this.selectedPet.getState();
        String creationDate = LocalDateTime.now().toString(); // Current date and time

        try (FileWriter writer = new FileWriter("game_save.csv", true)) {
            // Write the save data in the correct format
            writer.write(String.format("%d,%s,%s,%d,%d,%d,%d,%b,%s,%s%n",
                    saveSlot, playerName, petName, health, sleep, happiness, hunger, alive, state, creationDate));
        } catch (IOException e) {
            System.out.println("Error saving the game: " + e.getMessage());
        }
    }

    // Getters
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