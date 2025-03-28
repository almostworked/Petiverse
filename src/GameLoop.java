import javax.swing.Timer;

/**
 * Manages the main game loop for the virtual pet game.
 * It periodically triggers updates such as checking the pet’s state,
 * issuing warnings, autosaving progress, updating the player’s score,
 * and adding inventory items on timed cycles.
 * Specifically, it adds one food item every 3 minutes and one gift item every 5 minutes,
 * cycling through predefined arrays for each category.
 * The actual stat decay is handled by the {@link StateManager}.
 * 
 * This class ensures that the game state is consistently updated every second,
 * safely handles game-over conditions, and rewards the player with new items.
 * 
 * @author Adrian
 * @version 1.0
 */
public class GameLoop {
    private final Timer gameTimer;
    private final Pet activePet;
    private final Player player;
    private final StateManager stateManager;
    private final SaveGame saveSystem;
    private int autoSaveCounter;
    private boolean isRunning;
    
    // New fields for timed inventory rewards for food items:
    private int foodItemCounter = 0; // Counts seconds for food inventory reward.
    private int foodCycleIndex = 0;  // Tracks the current index in the food cycle.
    private final Item[] foodCycleItems = {Item.APPLE, Item.STEAK, Item.FISH};
    
    // New fields for timed inventory rewards for gift items:
    private int giftItemCounter = 0; 
    private int giftCycleIndex = 0;  
    private final Item[] giftCycleItems = {Item.BALL, Item.COLLAR, Item.TOY_MOUSE};

    /**
     * Constructs a new GameLoop instance.
     *
     * @param pet          the active pet being managed
     * @param player       the player object associated with the game
     * @param stateManager the manager responsible for stat decay and other timed changes
     * @param saveSystem   the save system responsible for persisting game data
     * @throws IllegalArgumentException if any argument is null
     */
    public GameLoop(Pet pet, Player player, StateManager stateManager, SaveGame saveSystem) {
        if (pet == null || player == null || stateManager == null || saveSystem == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        this.activePet = pet;
        this.player = player;
        this.stateManager = stateManager;
        this.saveSystem = saveSystem;
        this.autoSaveCounter = 0;
        this.isRunning = false;

        // Configure timer to trigger every second (1000ms)
        this.gameTimer = new Timer(1000, e -> performGameUpdate());
    }

    /**
     * Starts the game loop if it isn't already running.
     * Begins the stat decay, timed updates, and inventory rewards.
     */
    public void start() {
        if (!isRunning) {
            stateManager.start();  // Let StateManager handle all stat decay.
            gameTimer.start();
            isRunning = true;
            System.out.println("Game Loop started");
        }
    }

    /**
     * Stops the game loop and halts stat decay.
     */
    public void stop() {
        if (isRunning) {
            gameTimer.stop();
            stateManager.stop();
            isRunning = false;
            System.out.println("Game loop stopped");
        }
    }

    /**
     * Executes recurring game logic such as autosave, score update,
     * checking for pet status or warnings, and adding inventory items.
     */
    private void performGameUpdate() {
        if (activePet.getState().equals("DEAD")) {
            handleGameOver();
            return;
        }

        checkStateWarnings();
        handleAutoSave();
        updatePersistentScore();

        // Handle food item reward: every 3 minutes (180 seconds)
        foodItemCounter++;
        if (foodItemCounter >= 180) {
            Item foodReward = foodCycleItems[foodCycleIndex];
            player.getInventory().updateInventory(foodReward, 1);
            System.out.println("Inventory Reward: Received 1 " + foodReward.getName() + " (Food)");
            foodCycleIndex = (foodCycleIndex + 1) % foodCycleItems.length;
            foodItemCounter = 0;
        }
        
        // Handle gift item reward: every 5 minutes (300 seconds)
        giftItemCounter++;
        if (giftItemCounter >= 300) {
            Item giftReward = giftCycleItems[giftCycleIndex];
            player.getInventory().updateInventory(giftReward, 1);
            System.out.println("Inventory Reward: Received 1 " + giftReward.getName() + " (Gift)");
            giftCycleIndex = (giftCycleIndex + 1) % giftCycleItems.length;
            giftItemCounter = 0;
        }
    }

    /**
     * Handles end-of-game logic when the pet dies.
     */
    private void handleGameOver() {
        stop();
        System.out.println("Game Over! " + activePet.getName() + " has died.");
    }

    /**
     * Displays a warning if any of the pet's stats drop below a set threshold.
     */
    private void checkStateWarnings() {
        final int WARNING_THRESHOLD = 25;
        if (activePet.getHealth() < WARNING_THRESHOLD ||
            activePet.getSleep() < WARNING_THRESHOLD ||
            activePet.getFullness() < WARNING_THRESHOLD ||
            activePet.getHappiness() < WARNING_THRESHOLD) {
            System.out.println("Warning: " + activePet.getName() + " needs attention!");
        }
    }

    /**
     * Increments the player's persistent score.
     */
    private void updatePersistentScore() {
        player.getScore().increaseScore(1); 
    }

    /**
     * Periodically autosaves the game every 60 seconds.
     */
    private void handleAutoSave() {
        autoSaveCounter++;
        if (autoSaveCounter >= 60) {
            saveSystem.save(activePet, player.getInventory(), null);
            autoSaveCounter = 0;
            System.out.println("Progress autosaved");
        }
    }

    /**
     * Checks whether the game loop is currently running.
     *
     * @return true if the game loop is active, false otherwise
     */
    public boolean isRunning() {
        return isRunning;
    }
}


