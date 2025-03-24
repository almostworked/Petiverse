package logic;
import Inventory;
import src.Item;
import src.Pet;
import src.Player;
import src.Score;


public class GameLogic {
    private Pet pet;
    private Player player;
    private Inventory inventory;
    private Score score;  // We'll link directly to the Player's score

    // ----------------------
    // Constructor
    // ----------------------
    public GameLogic(Pet pet, Player player, Inventory inventory) {
        this.pet = pet;
        this.player = player;
        this.inventory = inventory;
        this.score = player.getScore();  // Use the player's score reference
    }

    // ----------------------
    // Actions
    // ----------------------
    public void goToBed() {
        if (pet.getState().equals("DEAD") || pet.getState().equals("SLEEPING")) {
            System.out.println("Cannot change sleep state; pet is " + pet.getState() + ".");
            return;
        }
        pet.setState("SLEEPING");
        System.out.println("Pet is now sleeping.");
    }

    public void feedPet(Item food) {
        if (pet.getState().equals("DEAD") 
            || pet.getState().equals("SLEEPING") 
            || pet.getState().equals("ANGRY")) 
        {
            System.out.println("Cannot feed pet; pet is " + pet.getState() + ".");
            return;
        }

        // Reduce item quantity in Inventory
        int currentQty = inventory.getQuantity(food);
        if (currentQty <= 0) {
            System.out.println("No " + food.getName() + " left in the inventory.");
            return;
        }
        inventory.setQuantity(food, currentQty - 1);

        // Increase pet fullness
        pet.setFullness(pet.getFullness() + food.getEffectValue());

        // Increase the Player's score
        score.setScore(score.getScore() + 10); // Adjust to your desired logic

        // If the pet's fullness is positive, ensure it isn't stuck in "HUNGRY" or "ANGRY"
        if (pet.getFullness() > 0) {
            pet.setState("NORMAL");
        }
    }

    public void giveGift(Item gift) {
        if (pet.getState().equals("DEAD") || pet.getState().equals("SLEEPING")) {
            System.out.println("Cannot give gift to pet; pet is " + pet.getState() + ".");
            return;
        }

        // Reduce item quantity in Inventory
        int currentQty = inventory.getQuantity(gift);
        if (currentQty <= 0) {
            System.out.println("No " + gift.getName() + " left in the inventory.");
            return;
        }
        inventory.setQuantity(gift, currentQty - 1);

        // Increase happiness
        pet.setHappiness(pet.getHappiness() + gift.getEffectValue());

        // Increase score
        score.setScore(score.getScore() + 10); // Adjust to your desired logic
    }

    public void takePetToVet() {
        if (pet.getState().equals("DEAD") 
            || pet.getState().equals("SLEEPING") 
            || pet.getState().equals("ANGRY")) 
        {
            System.out.println("Cannot take pet to the vet; pet is " + pet.getState() + ".");
            return;
        }

        pet.setHealth(100);
        score.setScore(score.getScore() - 10); // Adjust to your desired logic
    }

    public void playWithPet() {
        if (pet.getState().equals("DEAD") || pet.getState().equals("SLEEPING")) {
            System.out.println("Cannot play with pet; pet is " + pet.getState() + ".");
            return;
        }

        // Increase happiness
        pet.setHappiness(pet.getHappiness() + 40); // Example increment
        score.setScore(score.getScore() + 10);     // Adjust to your desired logic
    }

    public void exercisePet() {
        if (pet.getState().equals("DEAD") 
            || pet.getState().equals("SLEEPING") 
            || pet.getState().equals("ANGRY")) 
        {
            System.out.println("Cannot exercise pet; pet is " + pet.getState() + ".");
            return;
        }

        // Example logic: exercise uses up sleep and fullness but helps health
        pet.setSleep(Math.max(pet.getSleep() - 30, 0));
        pet.setFullness(Math.max(pet.getFullness() - 10, 0));
        pet.setHealth(Math.min(pet.getHealth() + 10, 100));
        
        score.setScore(score.getScore() + 15); // Adjust as needed
    }
}

