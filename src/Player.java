package src;

/**
 * Represents a player in the game, who can interact with pets and manage inventory.
 * Implements the PlayGame interface.
 *
 * @author Adrian
 * @version 1.0
 */
public class Player implements PlayGame {

    /** The player's inventory containing items and gifts. */
    private Inventory inventory;

    /** Indicates whether the player is a parent (Boolean flag). */
    private Boolean isParent;

    /** The currently active pet that the player interacts with. */
    private Pet activePet;

    /** The player's score, tracking progress and achievements. */
    private Score score;

    /**
     * Constructs a new Player with an inventory, parent status, and an active pet.
     *
     * @param inventory The inventory containing items.
     * @param isParent  Boolean indicating whether the player is a parent.
     * @param activePet The pet currently active for the player.
     */
    public Player(Inventory inventory, Boolean isParent, Pet activePet) {
        this.inventory = inventory;
        this.isParent = isParent;
        this.activePet = activePet;
        score = new Score();

    }

    /**
     * Displays the current state of the active pet, including health, sleep, fullness, and happiness.
     */
    @Override
    public void displayPetState() {
        if (this.activePet == null) {
            System.out.println("No active pet available");
            return;
        }
        System.out.println("Pet Status:");
        System.out.println("Health: " + this.activePet.getHealth());
        System.out.println("Sleep: " + this.activePet.getSleep());
        System.out.println("Fullness: " + this.activePet.getFullness());
        System.out.println("Happiness: " + this.activePet.getHappiness());
    }

    /**
     * Displays a list of all available commands for interacting with the pet.
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
     * Displays all gift-related commands and shows the inventory.
     */
    @Override
    public void displayGiftCommands() {
        System.out.println("Available Gifts:");
        inventory.displayInventory(); // MAY CHANGE
    }

    /**
     * Placeholder method for returning to the previous game state.
     * To be implemented later.
     */
    @Override
    public void back() {
        // IMPLEMENT LATER
    }

    /**
     * Displays the player's score and inventory items.
     */
    public void displayStats() {
        System.out.println("=== Player Stats ===");
        score.displayScore();
        inventory.displayInventory();
    }

    /**
     * Retrieves the player's score.
     *
     * @return The player's current score.
     */
    public int getScore() {
        return score.getScore();
    }

    /**
     * Displays the game settings.
     */
    public void viewSettings() {
        System.out.println("Displaying Game Settings:");
    }

    /**
     * Makes the active pet go to sleep.
     *
     * @return The pet's new sleep value, or -1 if an error occurs (e.g., pet is null or dead).
     */
    public int goToBed() {
        if (this.activePet == null) {
            System.out.println("No active pet available");
            return -1;
        }

        if (!this.activePet.isAlive()) {
            System.out.println("Pet is dead");
            return -1;
        }

        int currentSleep = this.activePet.getSleep();
        if (currentSleep == 0) {
            System.out.println("Pet is already sleeping");
            return -1;
        }

        int newSleepValue = 0; // CHANGE LATER
        this.activePet.setSleep(newSleepValue);
        System.out.println(this.activePet.getName() + " is going to bed now...");
        return this.activePet.getSleep();
    }

    /**
     * Feeds the pet with the specified food item, reducing hunger and increasing fullness.
     *
     * @param food The food item to feed the pet.
     */
    public void feed(String food) {
        if (this.activePet == null || !this.activePet.isAlive()) {
            System.out.println("No active pet available or pet is dead");
            return;
        }

        int currentSleep = this.activePet.getSleep();
        if (currentSleep == 0) {
            System.out.println("Pet is sleeping");
            return;
        }

        if (inventory.getQuantity(food) <= 0) {
            System.out.println("You have no " + food + " left");
            return;
        }

        inventory.removeItem(food, 1);
        int newHunger = Math.max(this.activePet.getHunger() - 10, 0);
        int newFullness = Math.min(this.activePet.getFullness() + 10, 100);
        this.activePet.setHunger(newHunger);
        this.activePet.setFullness(newFullness);

        score.increaseScore(5);
        System.out.println("Fed " + food + " to " + activePet.getName() + ". Hunger is now: " + activePet.getHunger());
    }

    /**
     * Gives a gift to the active pet, increasing happiness.
     *
     * @param gift The gift item.
     */
    public void giveGift(String gift) {
        if (this.activePet == null || !this.activePet.isAlive()) {
            System.out.println("No active pet available or pet is dead");
            return;
        }

        if (inventory.getQuantity(gift) <= 0) {
            System.out.println("You have no more " + gift + " left in your inventory");
            return;
        }

        inventory.removeItem(gift, 1);
        int newHappiness = Math.min(this.activePet.getHappiness() + 10, 100);
        this.activePet.setHappiness(newHappiness);
        score.increaseScore(3);

        System.out.println("Gave " + gift + " to " + this.activePet.getName()
                + ". Happiness is now: " + this.activePet.getHappiness());
    }

    /**
     * Takes the pet to the vet, increasing health at the cost of score points.
     *
     * @return The pet's new health value, or -1 if an error occurs.
     */
    public int visitVet() {
        if (this.activePet == null || !this.activePet.isAlive()) {
            System.out.println("No active pet available or pet is dead");
            return -1;
        }

        if (score.getScore() < 10) {
            System.out.println("You do not have a high enough score to visit the vet");
            return this.activePet.getHealth();
        }

        this.activePet.setHealth(this.activePet.getHealth() + 20);
        score.setScore(score.getScore() - 10);

        System.out.println("Visited the vet. " + this.activePet.getName()
                + "'s health is now " + this.activePet.getHealth() + ". Score is now " + score.getScore());

        return this.activePet.getHealth();
    }

    /**
     * Plays with the pet, increasing happiness but slightly increasing hunger.
     */
    public void play() {
        if (this.activePet == null || !this.activePet.isAlive()) {
            System.out.println("No active pet available or pet is dead");
            return;
        }

        int newHunger = Math.min(this.activePet.getHunger() + 5, 100);
        int newHappiness = Math.min(this.activePet.getHappiness() + 15, 100);
        this.activePet.setHunger(newHunger);
        this.activePet.setHappiness(newHappiness);
        score.increaseScore(2);

        System.out.println("Played with " + activePet.getName() 
                + ". Happiness is now: " + activePet.getHappiness());
    }

    /**
     * Exercises the pet, increasing health and happiness but consuming sleep.
     *
     * @return The pet's new health value.
     */
    public int exercise() {
        if (this.activePet == null || !this.activePet.isAlive()) {
            System.out.println("No active pet available or pet is dead");
            return -1;
        }

        this.activePet.setHealth(this.activePet.getHealth() + 10);
        this.activePet.setHappiness(this.activePet.getHappiness() + 15);
        this.activePet.setSleep(this.activePet.getSleep() + 15);
        score.increaseScore(2);

        System.out.println("Exercised with " + this.activePet.getName() 
                + ". Health is now " + this.activePet.getHealth());
        return this.activePet.getHealth();
    }
    public Pet getActivePet() {
        return activePet;
    }
}
