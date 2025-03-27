import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

public class StateManager {
    public interface StateChangeListener {
        void onStateChange(String newState);
        void onStatWarning(String stat, boolean isWarning);
    }

    private final Pet pet;
    private final List<StateChangeListener> listeners = new ArrayList<>();
    private Timer decayTimer;
    private Timer sleepTimer; // One-shot timer for the 10-second sleep wait
    private final int[] decayRates; // [sleep, fullness, happiness]
    private int tickCounter = 0; // counts seconds in the normal decay loop

    public StateManager(Pet pet, int[] decayRates) {
        this.pet = pet;
        this.decayRates = decayRates;
        initializeTimers();
    }

    private void initializeTimers() {
        // Main decay timer for normal states (runs every second).
        decayTimer = new Timer(1000, e -> {
            if (!"SLEEPING".equals(pet.getState())) {
                decayStats();
            }
            notifyStateChange(pet.getState());
        });
        decayTimer.start();

        // waits 10 seconds then forces a wake-up.
        sleepTimer = new Timer(10_000, e -> {
            pet.setSleep(100);
            pet.setState("NORMAL");
            notifyStateChange("NORMAL");
            decayTimer.start(); // Restart normal decay
            sleepTimer.stop();
        });
        sleepTimer.setRepeats(false);
    }

    // Called by the UI (e.g., when the "go to bed" button is pressed)
    public void setPetState(String newState) {
        pet.setState(newState);
        if ("SLEEPING".equals(newState)) {
            handleSleepingState();
        } else {
            enforceStateRules();
        }
        notifyStateChange(newState);
    }

    private void decayStats() {
        tickCounter++;  // Count each second
    
        // Every 5 seconds: drop sleep, fullness, and happiness
        if (tickCounter % 5 == 0) {
            pet.setSleep(Math.max(pet.getSleep() - 1, 0));
            pet.setFullness(Math.max(pet.getFullness() - 1, 0));
            pet.setHappiness(Math.max(pet.getHappiness() - 1, 0));
        }
    
        checkWarnings();      // Check if any stat is below 25%
        enforceStateRules();  // Handle transitions to other pet states
    }    

    private void checkWarnings() {
        checkStatWarning("SLEEP", pet.getSleep(), 100);
        checkStatWarning("FULLNESS", pet.getFullness(), 100);
        checkStatWarning("HAPPINESS", pet.getHappiness(), 100);
    }

    private void checkStatWarning(String stat, int current, int max) {
        boolean isWarning = current < max * 0.25;
        for (StateChangeListener listener : listeners) {
            listener.onStatWarning(stat, isWarning);
        }
    }

    private void enforceStateRules() {
        String currentState = pet.getState();
        switch (currentState) {
            case "DEAD":
                handleDeadState();
                break;
            case "ANGRY":
                handleAngryState();
                break;
            case "HUNGRY":
                handleHungryState();
                break;
            // SLEEPING is managed by our sleepTimer so no extra action here.
            default:
                break;
        }
    }

    private void handleDeadState() {
        decayTimer.stop();
        sleepTimer.stop();
        notifyStateChange("DEAD");
    }

    private void handleSleepingState() {
        decayTimer.stop(); // Stop normal decay during sleep
        applyHealthPenalty(5); // Optional: apply immediate health penalty
        // Start sleepTimer if it isn’t running already.
        if (!sleepTimer.isRunning()) {
            sleepTimer.restart();
        }
    }

    private void handleAngryState() {
        pet.setHappiness(Math.max(pet.getHappiness() - 3, 0));
        notifyStateChange("ANGRY");
    }

    private void handleHungryState() {
        applyHealthPenalty(3);
        increaseHappinessDecay(2);
        notifyStateChange("HUNGRY");
    }

    private void applyHealthPenalty(int penalty) {
        pet.setHealth(Math.max(pet.getHealth() - penalty, 0));
    }

    private void increaseHappinessDecay(int multiplier) {
        decayRates[2] *= multiplier;
    }

    // Listener registration
    public void addStateChangeListener(StateChangeListener listener) {
        listeners.add(listener);
    }

    private void notifyStateChange(String newState) {
        for (StateChangeListener listener : listeners) {
            listener.onStateChange(newState);
        }
    }

    // Start the decay engine
    public void start() {
        decayTimer.start();
    }

    // Stop all timers
    public void stop() {
        decayTimer.stop();
        sleepTimer.stop();
    }

    // For parental controls: revive pet to NORMAL
    public void revivePet() {
        pet.setHealth(100);
        pet.setSleep(100);
        pet.setFullness(100);
        pet.setHappiness(100);
        pet.setState("NORMAL");
        start();
        notifyStateChange("NORMAL");
    }
}

