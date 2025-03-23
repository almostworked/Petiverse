
/**
 * Represents a player in the game, who can interact with pets and manage inventory.
 * Implements the PlayGame interface.
 *
 * Pet states include: DEAD, SLEEPING, HUNGRY, ANGRY, NORMAL
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
        this.score = new Score();
    }

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

    @Override
    public void displayGiftCommands() {
        System.out.println("Available Gifts:");
        inventory.displayInventory();
    }

    @Override
    public void back() {
        // Stub to illustrate going back to a previous menu
    }

    public void displayStats() {
        System.out.println("=== Player Stats ===");
        score.displayScore();
        inventory.displayInventory();
    }

    public Score getScore() {
        return this.score;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void viewSettings() {
        System.out.println("Displaying Game Settings...");
    }

    // Example commands:

    // 1. Putting pet to bed:
    public int goToBed() {
        if (!canExecuteCommand("goToBed")) {
            return -1;
        }
        this.activePet.setSleep(0);
        this.activePet.setState("SLEEPING");
        System.out.println(this.activePet.getName() + " is now going to bed...");
        return this.activePet.getSleep();
    }

    // 2. Feed the pet
    public void feed(Item food) {
        if (!canExecuteCommand("feed")) {
            return;
        }
        if (inventory.getQuantity(food) <= 0) {
            System.out.println("You have no " + food + " left");
            return;
        }

        // Remove from inventory
        inventory.removeItem(food, 1);

        // Hard-coded effect for demonstration
        int effectValue = 10;
        int newHunger = Math.max(this.activePet.getHunger() - effectValue, 0);
        int newFullness = Math.min(this.activePet.getFullness() + effectValue, 100);
        this.activePet.setHunger(newHunger);
        this.activePet.setFullness(newFullness);

        // Score boost
        score.increaseScore(5);

        System.out.println("Fed " + food + " to " + activePet.getName()
                + ". Hunger is now: " + activePet.getHunger());

        updatePetState();
    }

    // 3. Give a gift
    public void giveGift(Item gift) {
        if (!canExecuteCommand("giveGift")) {
            return;
        }
        if (inventory.getQuantity(gift) <= 0) {
            System.out.println("You have no " + gift + " left");
            return;
        }
        inventory.removeItem(gift, 1);

        int effectValue = 10; // Hard-coded
        int newHappiness = Math.min(this.activePet.getHappiness() + effectValue, 100);
        this.activePet.setHappiness(newHappiness);
        score.increaseScore(3);

        System.out.println("Gave " + gift + " to " + this.activePet.getName()
                + ". Happiness is now: " + this.activePet.getHappiness());

        updatePetState();
    }

    // 4. Visit the Vet
    public int visitVet() {
        if (!canExecuteCommand("visitVet")) {
            return -1;
        }
        if (score.getScore() < 10) {
            System.out.println("You do not have enough score to visit the vet");
            return this.activePet.getHealth();
        }
        int newHealth = Math.min(this.activePet.getHealth() + 20, 100);
        this.activePet.setHealth(newHealth);
        score.setScore(score.getScore() - 10);

        System.out.println("Visited the vet. " + this.activePet.getName()
                + "'s health is now " + this.activePet.getHealth()
                + ". Score is now " + score.getScore());

        updatePetState();
        return this.activePet.getHealth();
    }

    // 5. Play
    public void play() {
        if (!canExecuteCommand("play")) {
            return;
        }
        int newHappiness = Math.min(this.activePet.getHappiness() + 10, 100);
        this.activePet.setHappiness(newHappiness);
        int newFullness = Math.max(this.activePet.getFullness() - 5, 0);
        this.activePet.setFullness(newFullness);

        score.increaseScore(2);
        System.out.println("You played with " + activePet.getName()
                           + "! Happiness: " + newHappiness
                           + ", Fullness: " + newFullness);

        updatePetState();
    }

    // 6. Exercise
    public void exercise() {
        if (!canExecuteCommand("exercise")) {
            return;
        }
        this.activePet.setHealth(Math.min(activePet.getHealth() + 5, 100));
        this.activePet.setFullness(Math.max(activePet.getFullness() - 5, 0));
        this.activePet.setSleep(Math.max(activePet.getSleep() - 5, 0));
        score.increaseScore(2);

        System.out.println("You exercised " + activePet.getName() + "! Health: "
                           + activePet.getHealth() + ", Fullness: "
                           + activePet.getFullness() + ", Sleep: "
                           + activePet.getSleep());
        updatePetState();
    }

    // State rules
    private boolean canExecuteCommand(String command) {
        if (this.activePet == null) {
            System.out.println("No active pet available.");
            return false;
        }
        String petState = this.activePet.getState();

        if (petState.equals("DEAD")) {
            System.out.println("Your pet has died. No commands allowed.");
            return false;
        }
        if (petState.equals("SLEEPING")) {
            System.out.println("Your pet is sleeping. Please wait.");
            return false;
        }
        if (petState.equals("ANGRY")) {
            // If the pet is ANGRY, only "giveGift" or "play" is allowed
            if (command.equalsIgnoreCase("giveGift") ||
                command.equalsIgnoreCase("play")) {
                return true;
            } else {
                System.out.println("Your pet is angry! Only 'Give Gift' or 'Play' is allowed.");
                return false;
            }
        }

        // If normal or hungry, everything is allowed
        return true;
    }

    private void updatePetState() {
        if (!activePet.isAlive()) {
            activePet.setState("DEAD");
            return;
        }
        if (activePet.getSleep() == 0) {
            activePet.setState("SLEEPING");
            return;
        }
        if (activePet.getHappiness() == 0) {
            activePet.setState("ANGRY");
        } else if (activePet.getHunger() == 0) {
            activePet.setState("HUNGRY");
        } else {
            activePet.setState("NORMAL");
        }
    }
    public Pet getActivePet() {
        return activePet;
    }
}
