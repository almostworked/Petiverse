package src.logic;

import src.Player;
import src.Pet;
import src.Inventory;
import src.Item;
import src.logic.StateManager;
import src.Score;
import src.logic.GameLoop;
import src.ParentalControls;

public class GameLogic {
    
    private Pet pet;
    private Player player;
    private Inventory inventory;
    private Item item;
    private StateManager stateManager;
    private Score score;
    private GameLoop gameLoop;
    private ParentalControls parentalControls;

    public GameLogic(Pet pet, Player player) {
        this.pet = pet;
        this.player = player;
        this.stateManager = new StateManager();
        this.score = new Score();
        this.parentalControls = new ParentalControls();
    }

    public void goToBed() {

        if (pet.getState().equals("DEAD") || pet.getState().equals("SLEEPING")) {
            System.out.println("Cannot change sleep state; pet is " + pet.getState() + ".");
            return;
        }
        
        pet.setState("SLEEPING");
        System.out.println("Pet is now sleeping.");

    }

    public void feedPet(String food) {
        if (pet.getState().equals("DEAD") || pet.getState().equals("SLEEPING") || pet.getState().equals("ANGRY")) {
            System.out.println("Cannot feed pet; pet is " + pet.getState() + ".");
            return;
        }

        inventory.setQuantity(food, inventory.getQuantity(food) - 1);
        pet.setFullness(pet.getFullness() + item.getEffectValue());
        score.setScore(score.getScore() + 10) // Change later

         if (pet.getFullness() >= 0) {
            pet.setState("NORMAL");
         }

    }

    public void giveGift(String gift) {

        if (pet.getState().equals("DEAD") || pet.getState().equals("SLEEPING")) {
            System.out.println("Cannot give gift to pet; pet is " + pet.getState() + ".");
            return;
        }

        inventory.setQuantity(gift, inventory.getQuantity(gift) - 1);
        pet.setHappiness(pet.getHappiness() + item.getEffectValue());
        score.setScore(score.getScore() + 10); // Change later

    }

    public void takePetToVet() {

        if (pet.getState().equals("DEAD") || pet.getState().equals("SLEEPING") || pet.getState().equals("ANGRY")) {
            System.out.println("Cannot take pet to vet; pet is " + pet.getState() + ".");
            return;
        }

        pet.setHealth(100);
        score.setScore(score.getScore() - 10); // Change later

    }

    public void playWithPet() {

        if (pet.getState().equals("DEAD") || pet.getState().equals("SLEEPING")) {
            System.out.println("Cannot give gift to pet; pet is " + pet.getState() + ".");
            return;
        }

        pet.setHappiness(pet.getHappiness() + 40);  // Change later
        score.setScore(score.getScore() + 10);

    }

    public void exercisePet() {

        if (pet.getState().equals("DEAD") || pet.getState().equals("SLEEPING") || pet.getState().equals("ANGRY")) {
            System.out.println("Cannot take pet to vet; pet is " + pet.getState() + ".");
            return;
        }

        pet.setSleep(pet.getSleep() - 30); // Change later
        pet.setFullness(pet.getFullness() + item.getEffectValue());
        pet.setHealth(pet.getHealth() + item.getEffectValue());
        score.setScore(score.getScore() + 15);

    }

}
