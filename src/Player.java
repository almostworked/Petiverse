package src;
public class Player implements PlayGame {

    private Inventory inventory;
    private Boolean isParent;
    private Pet activePet;
    private Score score;

    public Player(Inventory inventory, Boolean isParent, Pet activePet) {
        this.inventory = inventory;
        this.isParent = isParent;
        score = new Score();
        this.activePet = activePet;
    }

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
        inventory.displayInventory(); // MAY CHANGE
    }

    @Override
    public void back() {
        // IMPLEMENT LATER
    }

    public void displayStats() {
        System.out.println("=== Player Stats ===");
        score.displayScore();
        inventory.displayInventory();
    }

    public int getScore() {
        return score.getScore();

    }

    public void viewSettings() {
        System.out.println("Displaying Game Settings: ");

    }

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

    public void feed(String food) {
        if (this.activePet == null) {
            System.out.println("No active pet available");
            return;
        }

        if (!this.activePet.isAlive()) {
            System.out.println("Pet is dead");
            return;
        }

        int currentSleep = this.activePet.getSleep();

        if (currentSleep == 0) {
            System.out.println("Pet is sleeping");
            return;
        }

        int quantity = inventory.getQuantity(food);
        if (quantity <= 0) {
            System.out.println("You have no " + food + " left");
            return;
        }

        inventory.removeItem(food, 1);
        int currentHunger = this.activePet.getHunger();
        int newHunger = currentHunger - 10; // CHANGE LATER
        if (newHunger < 0) {
            newHunger = 0;
        }
        this.activePet.setHunger(newHunger);

        int currentFullness = this.activePet.getFullness();
        int newFullness = currentFullness + 10;
        if (newFullness > 100) {
            newFullness = 100;
        }
        this.activePet.setFullness(newFullness);

        score.increaseScore(5);
        System.out.println("Fed " + food + " to " + activePet.getName()
                + ". Hunger is now: " + activePet.getHunger());

    }

    public void giveGift(String gift) {
        if (this.activePet == null) {
            System.out.println("No active pet available");
            return;
        }

        if (!this.activePet.isAlive()) {
            System.out.println("Pet is dead");
            return;
        }

        int currentSleep = this.activePet.getSleep();

        if (currentSleep == 0) {
            System.out.println("Pet is sleeping");
            return;
        }

        int quantity = inventory.getQuantity(gift);
        if (quantity <= 0){
            System.out.println("You have no more " + gift + " left in your inventory");
            return;
        }

        inventory.removeItem(gift, 1);

        int currentHappiness = this.activePet.getHappiness();
        int newHappiness = currentHappiness + 10; //CHANGE LATER
        if (newHappiness > 100) {
            newHappiness = 100;
        }

        this.activePet.setHappiness(newHappiness);
        score.increaseScore(3);

        System.out.println("Gave " + gift + " to " + this.activePet.getName()
                + ". Happiness is now: " + this.activePet.getHappiness());
    }

    public int visitVet() {
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
            System.out.println("Pet is sleeping");
            return -1;
        }

        int currentHealth = this.activePet.getHealth();
        int currentScore = score.getScore();


        // CHANGE POINT SYSTEM LATER
        if (currentScore >= 10){
            int newHealth = currentHealth + 20;
            this.activePet.setHealth(newHealth);

            int newScore = currentScore - 10;
            score.setScore(newScore);

            System.out.println("Visited the vet. " + this.activePet.getName()
                    + "'s health is now " + this.activePet.getHealth() + ". Score is now " + score.getScore());

            return this.activePet.getHealth();
        } else {
            System.out.println("You do not have a high enough score to visit the vet");
            return currentHealth;
        }

    }

    public void play() {
        if (this.activePet == null) {
            System.out.println("No active pet available");
            return;
        }

        if (!this.activePet.isAlive()) {
            System.out.println("Pet is dead");
            return;
        }

        int currentSleep = this.activePet.getSleep();

        if (currentSleep == 0) {
            System.out.println("Pet is sleeping");
            return;
        }

        int currentHunger = this.activePet.getHunger();
        int newHunger = currentHunger + 5;
        if (newHunger > 100) {
            newHunger = 100;
        }

        int currentHappiness = this.activePet.getHappiness();
        int newHappiness = currentHappiness + 15;
        if (newHappiness > 100) {
            newHappiness = 100;
        }

        this.activePet.setHunger(newHunger);
        this.activePet.setHappiness(newHappiness);
        score.increaseScore(2);

        System.out.println("Played with " + activePet.getName() 
                + ". Happiness is now: " + activePet.getHappiness());

        return;

    }
    // Increases health while raising hunger and sleep
    public int exercise() {
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
            System.out.println("Pet is sleeping");
            return -1;
        }

        int currentHealth = this.activePet.getHealth();
        int currentHappiness = this.activePet.getHappiness();

        int newHealth = currentHealth + 10;
        this.activePet.setHealth(newHealth);

        int newHappiness = currentHappiness + 15;
        this.activePet.setHappiness(newHappiness);

        int newSleep = currentSleep + 15;
        this.activePet.setSleep(newSleep);

        score.increaseScore(2);

        System.out.println("Exercised with " + this.activePet.getName() 
                + ". Health is now " + this.activePet.getHealth());
        return this.activePet.getHealth();

    }

}