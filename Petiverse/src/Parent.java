import java.io.Serializable;

/**
 * Parent class representing a player with parental controls.
 * This class extends Player and encapsulates the additional functionality
 * for managing parental restrictions such as authentication, playtime tracking,
 * setting play limits, and pet revival. It implements the Serializable interface
 * for parent account creation purposes.
 * 
 * @author Daniella
 * @version 1.5
 */
public class Parent extends Player implements Serializable {
    private ParentalControls controls;

    /**
     * Constructs a new Parent object with the given parameters.
     *
     * @param name the name of the parent player
     * @param inventory the inventory associated with the player
     * @param isParent a flag indicating that the player is a parent (should be true)
     * @param activePet the active pet associated with the player
     * @param defaultPassword the default password for parental controls
     */
    public Parent() {
        super();
        this.controls = new ParentalControls("");
    }
    public Parent(String name, Inventory inventory, boolean isParent, Pet activePet, String defaultPassword) {
        super(name, inventory, isParent, activePet);
        this.controls = new ParentalControls(defaultPassword);
    }

    /**
     * Authenticates the parent using the given password attempt.
     *
     * @param attempt the password entered by the user
     * @return true if the password matches, false otherwise
     */
    public boolean authenticate(String attempt) {
        return controls != null && controls.authenticate(attempt);
    }
    

    /**
     * Sets a new password for the parental controls.
     *
     * @param newPassword the new password to set
     */
    public void setPassword(String newPassword) {
        controls.setPassword(newPassword);
    }

    /**
     * Checks if the parental restrictions are currently enabled.
     *
     * @return true if restrictions are enabled, false otherwise
     */
    public boolean isRestrictionsEnabled() {
        return controls.isRestrictionsEnabled();
    }

    /**
     * Configures the parental restrictions by enabling/disabling them and setting the allowed play hours.
     *
     * @param enabled   true to enable restrictions, false to disable
     * @param startHour the start hour for allowed playtime (inclusive)
     * @param endHour   the end hour for allowed playtime (exclusive)
     */
    public void setRestrictions(boolean enabled, int startHour, int endHour) {
        controls.setRestrictionsEnabled(enabled);
        controls.setAllowedPlayHours(startHour, endHour);
    }

    /**
     * Retrieves the total playtime in minutes accumulated by the parent.
     *
     * @return the total playtime in minutes
     */
    public float getTotalPlayTime() {
        return controls.getTotalPlayTime();
    }

    /**
     * Calculates and returns the average playtime per session.
     *
     * @return the average playtime per session in minutes
     */
    public float getAveragePlayTime() {
        return controls.getAveragePlayTime();
    }

    /**
     * Adds the specified number of minutes to the total playtime.
     *
     * @param minutesPlayed the number of minutes to add
     */
    public void addPlayTime(float minutesPlayed) {
        controls.addPlayTime(minutesPlayed);
    }

    /**
     * Increments the session count, indicating a new play session.
     */
    public void incrementSessionCount() {
        controls.incrementSessionCount();
    }

    /**
     * Resets the playtime statistics including total playtime and session count.
     */
    public void resetPlayTimeStats() {
        controls.resetPlayTimeStats();
    }

    /**
     * Revives the active pet by restoring its vital statistics to maximum values.
     */
    public void revivePet() {
        controls.revivePet(getActivePet());
    }

    /**
     * Returns the ParentalControls instance associated with this parent.
     *
     * @return the ParentalControls object
     */
    public ParentalControls getControls() {
        return controls;
    }
}

