import javax.swing.Timer;

/**
 * Manages the main game loop for the virtual pet game.
 * It periodically triggers updates such as checking the pet’s state,
 * issuing warnings, autosaving progress, updating the player’s score,
 * and adding inventory items on timed cycles.
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
    private int foodItemCounter = 0;
    private int foodCycleIndex = 0;
    private final Item[] foodCycleItems = {Item.APPLE, Item.STEAK, Item.FISH};
    private int giftItemCounter = 0;
    private int giftCycleIndex = 0;
    private final Item[] giftCycleItems = {Item.BALL, Item.COLLAR, Item.TOY_MOUSE};
    private int sessionSeconds = 0;
    private int scoreUpdateCounter = 0;

    /**
     * Constructs a new GameLoop instance.
     *
     * @param pet          the active pet whose state is managed by the loop
     * @param player       the player controlling the game
     * @param stateManager the manager responsible for decaying pet stats and state transitions
     * @param saveSystem   the system used to save game progress
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
        this.gameTimer = new Timer(1000, _ -> performGameUpdate());
    }

    /**
     * Starts the game loop, initiating stat decay, autosaving, score updates, and inventory rewards.
     * If the player is a parent, it also increments the session count.
     */
    public void start() {
        if (!isRunning) {
            stateManager.start();  // Let StateManager handle all stat decay.
            gameTimer.start();
            isRunning = true;
            System.out.println("Game Loop started");

            // If the player is a parent, increment session count
            if (player.isParent()) {
                ((Parent) player).incrementSessionCount();
            }
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
     * Performs periodic game updates every second.
     * <ul>
     *   <li>Checks if the pet is dead and handles game over if true.</li>
     *   <li>If the player is a parent, updates playtime and stops the game if the time limit is exceeded.</li>
     *   <li>Checks state warnings, handles autosave, updates the score, and awards inventory items.</li>
     * </ul>
     */
    private void performGameUpdate() {
        // Check if pet is dead
        if (activePet.getState().equals("DEAD")) {
            handleGameOver();
            return;
        }

        if (player.isParent()) {
            sessionSeconds++;
            Parent p = (Parent) player;
            p.addPlayTime(1f / 60f);

            float maxAllowed = p.getControls().getMaxAllowedMinutes(); // e.g. 60
            if (p.getTotalPlayTime() >= maxAllowed) {
                System.out.println("Time limit reached! Stopping the game...");
                stop();
                return;
            }
        }

        checkStateWarnings();
        handleAutoSave();
        updatePersistentScore();

        foodItemCounter++;
        if (foodItemCounter >= 180) {
            Item foodReward = foodCycleItems[foodCycleIndex];
            player.getInventory().updateInventory(foodReward, 1);
            System.out.println("Inventory Reward: Received 1 " + foodReward.getName() + " (Food)");
            foodCycleIndex = (foodCycleIndex + 1) % foodCycleItems.length;
            foodItemCounter = 0;
        }

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
     * Handles the game over condition when the pet dies by stopping the game loop.
     */
    private void handleGameOver() {
        stop();
        System.out.println("Game Over! " + activePet.getName() + " has died.");
    }

    /**
     * Checks if any of the pet's vital statistics fall below a warning threshold and prints a warning message.
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
     * Updates the persistent score by increasing it by a fixed amount every second.
     */
    private void updatePersistentScore() {
        scoreUpdateCounter++;
        // Increase the score every 5 seconds
        if (scoreUpdateCounter >= 5) {
            player.getScore().increaseScore(1);
            scoreUpdateCounter = 0; // Reset the counter after updating
            System.out.println("Score updated: " + player.getScore().getScore());
        }
    }

    /**
     * Handles autosaving the game progress every 60 seconds.
     * Calls the save system to persist the current state and resets the autosave counter.
     */
    private void handleAutoSave() {
        autoSaveCounter++;
        if (autoSaveCounter >= 60) {
            // Use your save logic
            saveSystem.save(activePet, player.getInventory());
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






