import javax.swing.Timer;

/**
 * Manages the main game loop for the virtual pet game.
 * It periodically triggers updates such as checking the pet’s state,
 * issuing warnings, autosaving progress, updating the player’s score,
 * and adding inventory items on timed cycles.
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
    private int foodItemCounter = 0;
    private int foodCycleIndex = 0;
    private final Item[] foodCycleItems = {Item.APPLE, Item.STEAK, Item.FISH};

    // New fields for timed inventory rewards for gift items:
    private int giftItemCounter = 0;
    private int giftCycleIndex = 0;
    private final Item[] giftCycleItems = {Item.BALL, Item.COLLAR, Item.TOY_MOUSE};

    // NEW: track how many seconds have passed in *this session* to help with adding to totalPlayTime
    private int sessionSeconds = 0;

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

    public void stop() {
        if (isRunning) {
            gameTimer.stop();
            stateManager.stop();
            isRunning = false;
            System.out.println("Game loop stopped");
        }
    }

    private void performGameUpdate() {
        // 1) Check if pet is dead
        if (activePet.getState().equals("DEAD")) {
            handleGameOver();
            return;
        }

        // 2) If player is parent, track how many seconds they have played
        if (player.isParent()) {
            sessionSeconds++;
            // Convert seconds to minutes as fractional
            float minutesPlayedSoFar = sessionSeconds / 60f;

            // Update parent's total playtime in ParentalControls
            Parent p = (Parent) player;
            p.addPlayTime(1f / 60f);  // Each second, add 1/60 of a minute

            // Now check if we have exceeded the parent's limit
            float maxAllowed = p.getControls().getMaxAllowedMinutes(); // e.g. 60
            if (p.getTotalPlayTime() >= maxAllowed) {
                // If we exceed the time limit, stop the game
                System.out.println("Time limit reached! Stopping the game...");
                stop();
                return;
            }
        }

        checkStateWarnings();
        handleAutoSave();
        updatePersistentScore();

        // 3) Inventory items logic
        // Food item reward every 3 minutes (180 seconds)
        foodItemCounter++;
        if (foodItemCounter >= 180) {
            Item foodReward = foodCycleItems[foodCycleIndex];
            player.getInventory().updateInventory(foodReward, 1);
            System.out.println("Inventory Reward: Received 1 " + foodReward.getName() + " (Food)");
            foodCycleIndex = (foodCycleIndex + 1) % foodCycleItems.length;
            foodItemCounter = 0;
        }

        // Gift item reward every 5 minutes (300 seconds)
        giftItemCounter++;
        if (giftItemCounter >= 300) {
            Item giftReward = giftCycleItems[giftCycleIndex];
            player.getInventory().updateInventory(giftReward, 1);
            System.out.println("Inventory Reward: Received 1 " + giftReward.getName() + " (Gift)");
            giftCycleIndex = (giftCycleIndex + 1) % giftCycleItems.length;
            giftItemCounter = 0;
        }
    }

    private void handleGameOver() {
        stop();
        System.out.println("Game Over! " + activePet.getName() + " has died.");
    }

    private void checkStateWarnings() {
        final int WARNING_THRESHOLD = 25;
        if (activePet.getHealth() < WARNING_THRESHOLD ||
            activePet.getSleep() < WARNING_THRESHOLD ||
            activePet.getFullness() < WARNING_THRESHOLD ||
            activePet.getHappiness() < WARNING_THRESHOLD) {
            System.out.println("Warning: " + activePet.getName() + " needs attention!");
        }
    }

    private void updatePersistentScore() {
        // Just an example: each second, add 1 point
        player.getScore().increaseScore(1); 
    }

    private void handleAutoSave() {
        autoSaveCounter++;
        if (autoSaveCounter >= 60) {
            // Use your save logic
            saveSystem.save(activePet, player.getInventory(), null);
            autoSaveCounter = 0;
            System.out.println("Progress autosaved");
        }
    }

    public boolean isRunning() {
        return isRunning;
    }
}






