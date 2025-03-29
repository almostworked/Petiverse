import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

/**
 * The StateManager class handles automatic stat decay and pet state transitions over time.
 * It uses timers to decrement sleep, fullness, and happiness periodically,
 * enforces behavior rules for specific states (e.g., HUNGRY, ANGRY, DEAD),
 * and notifies registered listeners of state changes or stat warnings.
 * 
 * It also manages sleeping behavior using a separate one-shot timer and allows for pet revival.
 * 
 * Author: Adrian  
 * @version 1.0
 */
public class StateManager {

    /**
     * Interface to listen for pet state changes and warnings (e.g., low stat alerts).
     */
    public interface StateChangeListener {
        void onStateChange(String newState);
        void onStatWarning(String stat, boolean isWarning);
    }

    private final Pet pet;
    private final List<StateChangeListener> listeners = new ArrayList<>();
    private Timer decayTimer;
    private Timer sleepTimer;
    private final int[] decayRates; // [sleep, fullness, happiness]
    private int tickCounter = 0;

    /**
     * Constructs a StateManager to handle stat decay and state transitions for a given pet.
     *
     * @param pet        the pet to manage
     * @param decayRates an array representing the decay rates for sleep, fullness, and happiness
     */
    public StateManager(Pet pet, int[] decayRates) {
        this.pet = pet;
        this.decayRates = decayRates;
        initializeTimers();
    }

    /**
     * Initializes both the main decay timer and the sleep timer.
     */
    private void initializeTimers() {
        decayTimer = new Timer(1000, e -> {
            if (!"SLEEPING".equals(pet.getState())) {
                decayStats();
            }
            notifyStateChange(pet.getState());
        });
        decayTimer.start();

        sleepTimer = new Timer(10_000, e -> {
            pet.setSleep(100);
            pet.setState("NORMAL");
            notifyStateChange("NORMAL");
            decayTimer.start();
            sleepTimer.stop();
        });
        sleepTimer.setRepeats(false);
    }

    /**
     * Manually sets the pet's state and triggers behavior based on that state.
     *
     * @param newState the new state to set
     */
    public void setPetState(String newState) {
        pet.setState(newState);
        if ("SLEEPING".equals(newState)) {
            handleSleepingState();
        } else {
            enforceStateRules();
        }
        notifyStateChange(newState);
    }

    /**
     * Decreases pet stats at regular intervals and checks for warnings or transitions.
     */
    private void decayStats() {
        tickCounter++;
        
        // Every 5 seconds: drop sleep, fullness, and happiness
        if (tickCounter % 5 == 0) {
            pet.setSleep(Math.max(pet.getSleep() - 1, 0));
            pet.setFullness(Math.max(pet.getFullness() - 1, 0));
            pet.setHappiness(Math.max(pet.getHappiness() - 1, 0));
        }
        
        // Every 10 seconds: decrease health by 1
        if (tickCounter % 10 == 0) {
            pet.setHealth(Math.max(pet.getHealth() - 1, 0));
        }
        
        checkWarnings();
        enforceStateRules();
    }
    

    /**
     * Checks if any stats are below the warning threshold and notifies listeners.
     */
    private void checkWarnings() {
        checkStatWarning("SLEEP", pet.getSleep(), 100);
        checkStatWarning("FULLNESS", pet.getFullness(), 100);
        checkStatWarning("HAPPINESS", pet.getHappiness(), 100);
    }

    /**
     * Sends warning notification for a given stat if it's below 25% of max.
     *
     * @param stat    the name of the stat
     * @param current the current value
     * @param max     the maximum value
     */
    private void checkStatWarning(String stat, int current, int max) {
        boolean isWarning = current < max * 0.25;
        for (StateChangeListener listener : listeners) {
            listener.onStatWarning(stat, isWarning);
        }
    }

    /**
     * Enforces the rules for specific pet states like DEAD, HUNGRY, and ANGRY.
     */
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
            default:
                break;
        }
    }

    /**
     * Handles logic for when the pet dies.
     * Stops all timers and notifies listeners.
     */
    private void handleDeadState() {
        decayTimer.stop();
        sleepTimer.stop();
        notifyStateChange("DEAD");
    }

    /**
     * Handles behavior for when the pet is put to sleep.
     * Applies immediate health penalty and starts a timer to wake up.
     */
    private void handleSleepingState() {
        decayTimer.stop();
        applyHealthPenalty(5);
        if (!sleepTimer.isRunning()) {
            sleepTimer.restart();
        }
    }

    /**
     * Handles effects of the ANGRY state by lowering happiness.
     */
    private void handleAngryState() {
        pet.setHappiness(Math.max(pet.getHappiness() - 3, 0));
        notifyStateChange("ANGRY");
    }

    /**
     * Handles the HUNGRY state by reducing health and accelerating happiness decay.
     */
    private void handleHungryState() {
        applyHealthPenalty(3);
        increaseHappinessDecay(2);
        notifyStateChange("HUNGRY");
    }

    /**
     * Applies a penalty to the pet's health.
     *
     * @param penalty the number of points to subtract
     */
    private void applyHealthPenalty(int penalty) {
        pet.setHealth(Math.max(pet.getHealth() - penalty, 0));
    }

    /**
     * Increases the decay rate for happiness by a given multiplier.
     *
     * @param multiplier the amount to multiply the happiness decay rate by
     */
    private void increaseHappinessDecay(int multiplier) {
        decayRates[2] *= multiplier;
    }

    /**
     * Registers a listener to receive state change and warning notifications.
     *
     * @param listener the listener to register
     */
    public void addStateChangeListener(StateChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * Notifies all listeners of a state change.
     *
     * @param newState the new state to notify
     */
    private void notifyStateChange(String newState) {
        for (StateChangeListener listener : listeners) {
            listener.onStateChange(newState);
        }
    }

    /**
     * Starts the stat decay process.
     */
    public void start() {
        decayTimer.start();
    }

    /**
     * Stops all running timers.
     */
    public void stop() {
        decayTimer.stop();
        sleepTimer.stop();
    }

    /**
     * Revives the pet by restoring all stats and resetting its state to NORMAL.
     */
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

