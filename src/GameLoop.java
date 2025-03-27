import javax.swing.Timer;

public class GameLoop {
    private final Timer gameTimer;
    private final Pet activePet;
    private final Player player;
    private final StateManager stateManager;
    private final SaveGame saveSystem;
    private int autoSaveCounter;
    private boolean isRunning;

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
        if (activePet.getState().equals("DEAD")) {
            handleGameOver();
            return;
        }

        // The game loop no longer decays stats—it only checks the pet's state, shows warnings, autosaves, and updates the score.
        checkStateWarnings();
        handleAutoSave();
        updatePersistentScore();
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
        player.getScore().increaseScore(1); 
    }

    private void handleAutoSave() {
        autoSaveCounter++;
        if (autoSaveCounter >= 60) { // Autosave every 60 seconds
            saveSystem.save(activePet, player.getInventory(), null);
            autoSaveCounter = 0;
            System.out.println("Progress autosaved");
        }
    }

    public boolean isRunning() {
        return isRunning;
    }
}
