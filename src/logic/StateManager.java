package logic;

import src.Pet;
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
    private Timer sleepRecoveryTimer;
    private final int[] decayRates; // [sleep, fullness, happiness]

    public StateManager(Pet pet, int[] decayRates) {
        this.pet = pet;
        this.decayRates = decayRates;
        initializeTimers();
    }

    private void initializeTimers() {
        // Main stat decay timer (1 second interval)
        decayTimer = new Timer(1000, e -> {
            if (!pet.getState().equals("SLEEPING")) {
                decayStats();
            }
            checkStateTransitions();
        });

        // Separate timer for sleep recovery when sleeping
        sleepRecoveryTimer = new Timer(1000, e -> {
            if (pet.getState().equals("SLEEPING")) {
                int newSleep = Math.min(pet.getSleep() + decayRates[0], 100);
                pet.setSleep(newSleep);
                
                if (newSleep >= 100) {
                    sleepRecoveryTimer.stop();
                    decayTimer.start();
                    notifyStateChange(pet.getState());
                }
            }
        });
    }

    public void setPetState(String newState) {
        pet.setState( newState);
        notifyStateChange(newState);
    }

    private void decayStats() {
        pet.setSleep(Math.max(pet.getSleep() - decayRates[0], 0));
        pet.setFullness(Math.max(pet.getFullness() - decayRates[1], 0));
        pet.setHappiness(Math.max(pet.getHappiness() - decayRates[2], 0));
        
        checkWarnings();
        enforceStateRules();
    }

    private void checkWarnings() {
        checkStatWarning("SLEEP", pet.getSleep(), 100);
        checkStatWarning("FULLNESS", pet.getFullness(), 100);
        checkStatWarning("HAPPINESS", pet.getHappiness(), 100);
    }

    private void checkStatWarning(String stat, int current, int max) {
        boolean isWarning = current < max * 0.25;
        listeners.forEach(listener -> listener.onStatWarning(stat, isWarning));
    }

    private void enforceStateRules() {
        String state = pet.getState();
        switch (state) {
            case "DEAD":
                handleDeadState();
                break;
            case "SLEEPING":
                handleSleepingState();
                break;
            case "ANGRY":
                handleAngryState();
                break;
            case "HUNGRY":
                handleHungryState();
                break;
        }
    }

    private void handleDeadState() {
        decayTimer.stop();
        sleepRecoveryTimer.stop();
        notifyStateChange("DEAD");
    }

    private void handleSleepingState() {
        decayTimer.stop();
        sleepRecoveryTimer.start();
        applyHealthPenalty(5);
        notifyStateChange("SLEEPING");
    }

    private void handleAngryState() {
        pet.setHappiness(pet.getHappiness() - 3);
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

    private void checkStateTransitions() {
        String newState = pet.getState();
        listeners.forEach(listener -> listener.onStateChange(newState));
    }

    public void addStateChangeListener(StateChangeListener listener) {
        listeners.add(listener);
    }

    private void notifyStateChange(String newState) {
        listeners.forEach(listener -> listener.onStateChange(newState));
    }

    public void start() {
        decayTimer.start();
    }

    public void stop() {
        decayTimer.stop();
        sleepRecoveryTimer.stop();
    }

    // For parental controls revival
    public void revivePet() {
        pet.setHealth(100);
        pet.setSleep(100);
        pet.setFullness(100);
        pet.setHappiness(100);
        start();
        notifyStateChange("NORMAL");
    }
}