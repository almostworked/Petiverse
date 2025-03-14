public class Player implements PlayGame {

    private Inventory inventory;
    private Boolean isParent;

    public Player(Inventory inventory, Boolean isParent) {
        this.inventory = inventory;
        this.isParent = isParent;
    }

    @Override
    public void displayPetState() {
        System.out.println("Pet Status:");
        System.out.println("Health: " + activePet.getHealth());
        System.out.println("Sleep: " + activePet.getSleep());
        System.out.println("Fullness: " + activePet.getFullness());
        System.out.println("Happiness: " + activePet.getHappiness());
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

    }

    public int getScore() {

    }

    public void viewSettings() {

    }

    public int goToBed() {

    }

    public void feed(String food) {

    }

    public void giveGift(String gift) {

    }

    public int visitVet() {

    }

    public void play() {

    }

    public int exercise() {

    }