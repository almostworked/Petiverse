package src.logic;

import src.Player;
import src.Pet;
import src.Inventory;
import src.StateManager;
import src.ScoreManager;
import src.GameLoop;
import src.ParentalControls;

public class GameLogic {
    
    private Pet pet;
    private Player player;
    private Inventory inventory;
    private StateManager stateManager;
    private ScoreManager scoreManager;
    private GameLoop gameLoop;
    private ParentalControls parentalControls;

    public GameLogic(Pet pet, Player player) {
        this.pet = pet;
        this.player = player;
        this.stateManager = new StateManager();
        this.scoreManager = new ScoreManager();
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

    public void feedPet() {

    }

    public void giveGift() {

    }

    public void takePetToVet() {

    }

    public void playWithPet() {

    }

    public void exercisePet() {

    }




}
