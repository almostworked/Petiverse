public class GameLogic {
    private Pet pet;
    private Inventory inventory;
    private Score score;

    public GameLogic(Pet pet, Inventory inventory, Score score) {
        this.pet = pet;
        this.inventory = inventory;
        this.score = score;
    }

    public void update() {
        // Decay pet stats.
        pet.setSleep(pet.getSleep() - 5);
        pet.setFullness(pet.getFullness() - 3);
        pet.setHappiness(pet.getHappiness() - 2);
    }

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
