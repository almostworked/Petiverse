/**
 * Represents a player in the game, holding references to their inventory, score, and active pet.
 * Also implements the PlayGame interface for displaying pet/game information to the user.
 *
 * Pet states include: DEAD, SLEEPING, HUNGRY, ANGRY, NORMAL.
 */
public class Player implements PlayGame {

    private String name;
    private Inventory inventory;
    private Boolean isParent;
    private Pet activePet;
    private Score score;

    /** 
     * Constructs a new Player.
     * 
     * @param inventory The player's inventory
     * @param isParent  True if player is a parent
     * @param activePet The pet currently controlled by this player
     */
    public Player(String name, Inventory inventory, Boolean isParent, Pet activePet) {
        this.name = name;
        this.inventory = inventory;
        this.isParent = isParent;
        this.activePet = activePet;
        this.score = new Score();
        // You’ll initialize GameLogic from somewhere else in your code, passing this player, etc.
        // gameLogic = new GameLogic(activePet, this, inventory);
    }
    public String getName() {
        return this.name;
    }

    /**
     * Displays the current pet's state (health, sleep, fullness, happiness, etc.).
     */
    @Override
    public void displayPetState() {
        if (this.activePet == null) {
            System.out.println("No active pet available");
            return;
        }
        System.out.println("Pet Status:");
        System.out.println("State: " + this.activePet.getState());
        System.out.println("Health: " + this.activePet.getHealth());
        System.out.println("Sleep: " + this.activePet.getSleep());
        System.out.println("Fullness: " + this.activePet.getFullness());
        System.out.println("Happiness: " + this.activePet.getHappiness());
    }

    /**
     * Displays a list of commands (from the player's perspective).
     */
    @Override
    public void displayAllCommands() {
        System.out.println("Available Commands:");
        System.out.println("1. Feed");
        System.out.println("2. Give Gift");
        System.out.println("3. Play");
        System.out.println("4. Exercise");
        System.out.println("5. Go to Bed");
        System.out.println("6. Visit Vet");
    }

    /**
     * Displays the available gifts the player can give to the pet.
     */
    @Override
    public void displayGiftCommands() {
        System.out.println("Available Gifts:");
        inventory.displayInventory();
    }

    /**
     * Goes back to a previous screen (stub).
     */
    @Override
    public void back() {
        // Implementation for returning to a previous menu or screen
    }

    /**
     * Displays the player's stats, including score and inventory contents.
     */
    public void displayStats() {
        System.out.println("=== Player Stats ===");
        score.displayScore();
        inventory.displayInventory();
    }

    /**
     * @return The player's Score object.
     */
    public Score getScore() {
        return this.score;
    }

    /**
     * @return The player's Inventory object.
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Shows or modifies game settings; placeholder.
     */
    public void viewSettings() {
        System.out.println("Displaying Game Settings...");
    }

    /**
     * @return The active pet object
     */
    public Pet getActivePet() {
        return this.activePet;
    }

    /**
     * @return True if the player is a parent
     */
    public boolean isParent() {
        return this.isParent;
    }
    
}
