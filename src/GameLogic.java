public class GameLogic {
    private Pet pet;
    private Inventory inventory;
    private Score score;

    public GameLogic(Pet pet, Inventory inventory, Score score) {
        this.pet = pet;
        this.inventory = inventory;
        this.score = score;
    }

    // --- Delegated Pet Actions ---
    public void feed(Item food) {
        // Instead of inventory.useItem(food), we check if the item is available,
        // then reduce its quantity manually.
        if (inventory.getQuantity(food) > 0) {
            inventory.setQuantity(food, inventory.getQuantity(food) - 1);
            pet.feed(food);
            score.increaseScore(10);
            System.out.println("No " + food.getName() + " left in inventory.");
        }
    }

    public void playWithPet() {
        pet.play();
        score.increaseScore(10);
    }

    public void giveGift(Item gift) {
        // Same adjustment as in feed()
        if (inventory.getQuantity(gift) > 0) {
            inventory.setQuantity(gift, inventory.getQuantity(gift) - 1);
            pet.giveGift(gift);
            score.increaseScore(10);
        } else {
            System.out.println("No " + gift.getName() + " left in inventory.");
        }
    }

    public void putPetToBed() {
        pet.sleep();
    }

    public void exercisePet() {
        pet.exercise();
        score.increaseScore(15);
    }

    public void takePetToVet() {
        pet.takeToVet();
        score.decreaseScore(10);
    }

    public void update() {
        // Decay pet stats.
        // Note: To make this work, you'll need to add public setter methods in Pet for sleep, fullness, and happiness.
        pet.setSleep(pet.getSleep() - 5);
        pet.setFullness(pet.getFullness() - 3);
        pet.setHappiness(pet.getHappiness() - 2);
    }

    public boolean isGameOver() {
        return pet.getState().equals("DEAD");
    }

    // --- Inventory/Score ---
    public void addItemToInventory(Item item) {
        // Since Inventory doesn't have an addItem method, we use updateInventory to add one.
        inventory.updateInventory(item, 1);
    }

    public int getScore() {
        return score.getScore();
    }

}
