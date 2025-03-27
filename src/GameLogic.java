public class GameLogic {
    private Pet pet;
    private Inventory inventory;
    private Score score;

    public GameLogic(Pet pet, Inventory inventory, Score score) {
        this.pet = pet;
        this.inventory = inventory;
        this.score = score;
    }

    // The update() method has been removed because StateManager is the central decay engine.
    
    public boolean isGameOver() {
        return pet.getState().equals("DEAD");
    }

    public void addItemToInventory(Item item) {
        inventory.updateInventory(item, 1);
    }

    public int getScore() {
        return score.getScore();
    }
}

