package src.logic;

import src.Inventory;
import src.Item;
import src.Pet;
import src.Player;
import src.Score;

/**
 * Centralized game logic class. Holds references to a Pet, Player, and Inventory,
 * and provides methods to perform actions such as feeding, sleeping, giving gifts, etc.
 */
public class GameLogic {
    private Pet pet;
    private Player player;
    private Inventory inventory;
    private Score score;  // We'll link directly to the Player's score

    public GameLogic(Pet pet, Player player, Inventory inventory) {
        this.pet = pet;
        this.player = player;
        this.inventory = inventory;
        this.score = player.getScore();  // Use the player's score reference
    }

    /**
     * Puts the pet to bed, as long as it isn't DEAD or already SLEEPING.
     */
    public void sleep() {
        if (!canExecuteCommand("goToBed")) {
            return;
        }
        // For consistency, let's set the pet to state SLEEPING, or call pet.setSleep(0) if needed
        pet.setState("SLEEPING");
        System.out.println("Pet is now sleeping.");
    }

    /**
     * Feeds the pet if it isn't dead, sleeping, or angry, and updates inventory/score.
     * @param food The item (food) the user has chosen to feed the pet with
     */
    public void feed(Item food) {
        if (!canExecuteCommand("feed")) {
            return;
        }

        int currentQty = inventory.getQuantity(food);
        if (currentQty <= 0) {
            System.out.println("No " + food.getName() + " left in inventory.");
            return;
        }
        inventory.setQuantity(food, currentQty - 1);

        pet.setFullness(pet.getFullness() + food.getEffectValue());
        score.setScore(score.getScore() + 10); // example logic
        System.out.println("Fed " + food.getName() + ". Pet fullness is now: " + pet.getFullness());

        // If the pet's fullness is positive, ensure it is not stuck in "HUNGRY" or "ANGRY"
        if (pet.getFullness() > 0 && !pet.getState().equals("DEAD")) {
            pet.setState("NORMAL");
        }
    }

    /**
     * Gives a gift to the pet if it isn't dead or sleeping, updates happiness and inventory.
     */
    public void giveGift(Item gift) {
        if (!canExecuteCommand("giveGift")) {
            return;
        }

        int currentQty = inventory.getQuantity(gift);
        if (currentQty <= 0) {
            System.out.println("No " + gift.getName() + " left in the inventory.");
            return;
        }
        inventory.setQuantity(gift, currentQty - 1);

        pet.setHappiness(pet.getHappiness() + gift.getEffectValue());
        score.setScore(score.getScore() + 10); // example logic
        System.out.println("Gave " + gift.getName() + ". Pet happiness is now: " + pet.getHappiness());
    }

    /**
     * Takes the pet to the vet if not dead, sleeping, or angry. Restores pet's health to 100,
     * but penalizes the player's score.
     */
    public void takePetToVet() {
        if (!canExecuteCommand("visitVet")) {
            return;
        }
        pet.setHealth(100);
        score.setScore(score.getScore() - 10);
        System.out.println("Pet's health is restored to 100. Score is now " + score.getScore());
    }

    /**
     * Plays with the pet if it isn't dead or sleeping. Increases happiness significantly,
     * and awards some score.
     */
    public void playWithPet() {
        if (!canExecuteCommand("play")) {
            return;
        }
        pet.setHappiness(pet.getHappiness() + 40);
        score.setScore(score.getScore() + 10);
        System.out.println("Played with pet! Happiness: " + pet.getHappiness());
    }

    /**
     * Exercises the pet if it isn't dead, sleeping, or angry. Helps health, but uses up fullness/sleep.
     */
    public void exercisePet() {
        if (!canExecuteCommand("exercise")) {
            return;
        }
        pet.setSleep(Math.max(pet.getSleep() - 30, 0));
        pet.setFullness(Math.max(pet.getFullness() - 10, 0));
        pet.setHealth(Math.min(pet.getHealth() + 10, 100));
        score.setScore(score.getScore() + 15);

        System.out.println("Exercised pet -> Health: " + pet.getHealth()
                           + ", Sleep: " + pet.getSleep()
                           + ", Fullness: " + pet.getFullness());
    }

    /**
     * Checks whether a command can be executed based on the pet's current state and other logic.
     * 
     * @param command The action user is attempting ("feed", "giveGift", "goToBed", "exercise", etc.)
     * @return True if the command is allowed; false otherwise.
     */
    private boolean canExecuteCommand(String command) {
        if (pet == null) {
            System.out.println("No pet available to interact with.");
            return false;
        }

        String petState = pet.getState();

        // Hard-coded checks based on your existing logic
        if (petState.equals("DEAD")) {
            System.out.println("Pet is dead. No commands allowed.");
            return false;
        }
        if (petState.equals("SLEEPING")) {
            System.out.println("Pet is sleeping. No commands allowed right now.");
            return false;
        }
        if (petState.equals("ANGRY")) {
            // If the pet is angry, only "giveGift" or "play" is allowed
            if (command.equalsIgnoreCase("giveGift") ||
                command.equalsIgnoreCase("play")) {
                return true;
            } else {
                System.out.println("Pet is angry. Only 'Give Gift' or 'Play' is allowed.");
                return false;
            }
        }
        // If normal or hungry, all commands are allowed
        return true;
    }
}

