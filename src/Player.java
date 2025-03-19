package src;

/**
 * Represents a player in the game, who can interact with pets and manage inventory.
 * Implements the PlayGame interface.
 *
 * Updated to handle Pet state transitions using Pet's getState() and setState().
 * Pet states include: DEAD, SLEEPING, HUNGRY, ANGRY, NORMAL
 *
 * @author Adrian
 * @version 1.1
 */
public class Player implements PlayGame {

    private Inventory inventory;
    private Boolean isParent;
    private Pet activePet;
    private Score score;

    public Player(Inventory inventory, Boolean isParent, Pet activePet) {
        this.inventory = inventory;
        this.isParent = isParent;
        this.activePet = activePet;
        score = new Score();
    }

    @Override
    public void displayPetState() {
        if (this.activePet == null) {
            System.out.println("No active pet available");
            return;
        }
        System.out.println("Pet Status:");
        System.out.println("State: " + this.activePet.getState());    // Shows e.g. "NORMAL", "ANGRY", etc.
        System.out.println("Health: " + this.activePet.getHealth());
        System.out.println("Sleep: " + this.activePet.getSleep());
        System.out.println("Fullness: " + this.activePet.getFullness());
        System.out.println("Happiness: " + this.activePet.getHappiness());
    }

    @Override
    public void displayAllCommands() {
        System.out.println("Available Commands:");
        // Some of these may be disallowed depending on the current Pet state.
        System.out.println("1. Feed");
        System.out.println("2. Give Gift");
        System.out.println("3. Play");
        System.out.println("4. Exercise");
        System.out.println("5. Go to Bed");
        System.out.println("6. Visit Vet");
    }

    @Override
    public void displayGiftCommands() {
        System.out.println("Available Gifts:");
        inventory.displayInventory();
    }

    @Override
    public void back() {
        // IMPLEMENT LATER (e.g., go back to a previous menu)
    }

    public void displayStats() {
        System.out.println("=== Player Stats ===");
        score.displayScore();
        inventory.displayInventory();
    }

    public Score getScore() {
        return this.score;
    }

    public void viewSettings() {
        System.out.println("Displaying Game Settings:");
    }

    /**
     * Helper function that determines whether a given command is allowed based
     * on the current pet state. Allows easy extension if your states or commands evolve.
     */
    private boolean canExecuteCommand(String command) {
        if (this.activePet == null) {
            System.out.println("No active pet available.");
            return false;
        }

        String petState = this.activePet.getState();

        // If the pet is DEAD or SLEEPING, no commands are allowed
        if (petState.equals("DEAD")) {
            System.out.println("Your pet has died. No commands are allowed.");
            return false;
        }
        if (petState.equals("SLEEPING")) {
            System.out.println("Your pet is sleeping. Please wait for it to wake up.");
            return false;
        }

        // If the pet is ANGRY, only "Give Gift" or "Play" are allowed
        if (petState.equals("ANGRY")) {
            if (command.equalsIgnoreCase("giveGift") || command.equalsIgnoreCase("play")) {
                // This command is allowed
                return true;
            } else {
                System.out.println("Your pet is angry! Only 'Give Gift' or 'Play' is allowed.");
                return false;
            }
        }

        // For HUNGRY or NORMAL, all commands are allowed:
        // (If you want more special rules for HUNGRY, you can handle that below.)
        return true;
    }

    /**
     * Command to put the pet to bed. This forcibly sets the pet's sleep to 0 (triggering SLEEPING).
     * If your design does something different (e.g. letting the pet drift to 0), adjust as needed.
     */
    public int goToBed() {
        if (!canExecuteCommand("goToBed")) {
            return -1;
        }
        // Go to bed means forcibly set the pet's state to SLEEPING if it isn't dead, etc.
        this.activePet.setSleep(0);
        this.activePet.setState("SLEEPING");

        System.out.println(this.activePet.getName() + " is now going to bed...");
        return this.activePet.getSleep();
    }

    /**
     * Feeds the pet with the specified food item (if allowed by current state).
     */
    public void feed(String food) {
        if (!canExecuteCommand("feed")) {
            return;
        }
        if (inventory.getQuantity(food) <= 0) {
            System.out.println("You have no " + food + " left");
            return;
        }

        // Consume the item
        inventory.removeItem(food, 1);

        // Improve fullness/hunger
        int newHunger = Math.max(this.activePet.getHunger() - 10, 0);
        int newFullness = Math.min(this.activePet.getFullness() + 10, 100);
        this.activePet.setHunger(newHunger);
        this.activePet.setFullness(newFullness);

        // Score boost
        score.increaseScore(5);

        System.out.println("Fed " + food + " to " + activePet.getName()
                + ". Hunger is now: " + activePet.getHunger());

        // After changes, decide if the pet is still HUNGRY, or maybe NORMAL, etc.
        updatePetState();
    }

    /**
     * Gives a gift to the pet (if allowed).
     */
    public void giveGift(String gift) {
        if (!canExecuteCommand("giveGift")) {
            return;
        }
        if (inventory.getQuantity(gift) <= 0) {
            System.out.println("You have no more " + gift + " left in your inventory");
            return;
        }

        // Consume the item
        inventory.removeItem(gift, 1);

        // Increase happiness
        int newHappiness = Math.min(this.activePet.getHappiness() + 10, 100);
        this.activePet.setHappiness(newHappiness);

        // Score boost
        score.increaseScore(3);

        System.out.println("Gave " + gift + " to " + this.activePet.getName()
                + ". Happiness is now: " + this.activePet.getHappiness());

        // Possibly update the pet’s state
        updatePetState();
    }

    /**
     * Takes the pet to the vet, if the player has sufficient score. Increases health.
     */
    public int visitVet() {
        if (!canExecuteCommand("visitVet")) {
            return -1;
        }
        if (score.getScore() < 10) {
            System.out.println("You do not have a high enough score to visit the vet");
            return this.activePet.getHealth();
        }

        // Increase health by 20
        int newHealth = this.activePet.getHealth() + 20;
        this.activePet.setHealth(newHealth);

        // Reduce score by 10
        score.setScore(score.getScore() - 10);

        System.out.println("Visited the vet. " + this.activePet.getName()
                + "'s health is now " + this.activePet.getHealth()
                + ". Score is now " + score.getScore());

        // Possibly update the pet’s state
        updatePetState();
        return this.activePet.getHealth();
    }

    /**
     * Play with the pet (if allowed), boosting happiness but increasing hunger slightly.
     */
    public void play() {
        if (!canExecuteCommand("play")) {
            return;
        }

        int newHunger = Math.min(this.activePet.getHunger() + 5, 100);
        int newHappiness = Math.min(this.activePet.getHappiness() + 15, 100);
        this.activePet.setHunger(newHunger);
        this.activePet.setHappiness(newHappiness);

        score.increaseScore(2);

        System.out.println("Played with " + activePet.getName()
                + ". Happiness is now: " + activePet.getHappiness());

        // Possibly update the pet’s state
        updatePetState();
    }

    /**
     * Exercises the pet, if allowed. Increases health/happiness, but uses up some sleep
     * and might also increase hunger.
     */
    public int exercise() {
        if (!canExecuteCommand("exercise")) {
            return -1;
        }

        this.activePet.setHealth(this.activePet.getHealth() + 10);
        this.activePet.setHappiness(Math.min(this.activePet.getHappiness() + 15, 100));
        // Possibly the pet uses up some sleep or gets hungrier:
        this.activePet.setSleep(Math.max(this.activePet.getSleep() - 10, 0));
        this.activePet.setHunger(Math.min(this.activePet.getHunger() + 5, 100));

        score.increaseScore(2);

        System.out.println("Exercised with " + this.activePet.getName()
                + ". Health is now " + this.activePet.getHealth());

        // Possibly update the pet’s state
        updatePetState();
        return this.activePet.getHealth();
    }

    /**
     * Checks the pet's vital stats and sets its state accordingly.
     * Call this method whenever stats have changed.
     */
    private void updatePetState() {
        if (this.activePet == null) return;

        // If health is 0 => DEAD
        if (this.activePet.getHealth() <= 0) {
            this.activePet.setState("DEAD");
            System.out.println(activePet.getName() + " has died.");
            return; // No other states matter once dead
        }

        // If sleep is 0 => SLEEPING
        if (this.activePet.getSleep() <= 0) {
            this.activePet.setState("SLEEPING");
            System.out.println(activePet.getName() + " is now sleeping...");
            return;
        }

        // If fullness is 0 => HUNGRY
        if (this.activePet.getFullness() <= 0) {
            // Usually also degrade health or happiness here if wanted
            this.activePet.setState("HUNGRY");
            System.out.println(activePet.getName() + " is hungry!");
            return;
        }

        // If happiness is 0 => ANGRY
        if (this.activePet.getHappiness() <= 0) {
            this.activePet.setState("ANGRY");
            System.out.println(activePet.getName() + " is angry!");
            return;
        }

        // Otherwise => NORMAL
        this.activePet.setState("NORMAL");
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}

