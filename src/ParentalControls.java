import java.time.LocalTime;

/**
 * The ParentalControls class provides functionality for managing parental restrictions
 * in the game. It includes password authentication, configurable play hours, session tracking,
 * and pet revival capabilities. Parents can enable or disable restrictions and set
 * allowed play time windows to manage how long and when the game can be played.
 * It also tracks the total play time and the number of sessions for averaging purposes.
 * 
 * Author: Adrian  
 * @version 1.0
 */
public class ParentalControls {

    private String password;
    private boolean restrictionsEnabled;
    private int allowedStartHour;
    private int allowedEndHour;
    private float totalPlayTime;
    private int sessionCount;

    /**
     * Constructs a new ParentalControls instance with a given password.
     *
     * @param password the password used for authentication
     */
    public ParentalControls(String password) {
        this.password = password;
        this.restrictionsEnabled = false;
        this.allowedStartHour = 0;
        this.allowedEndHour = 24;
        this.totalPlayTime = 0;
        this.sessionCount = 0;
    }

    /**
     * Authenticates an attempted password entry.
     *
     * @param attempt the password entered by the user
     * @return true if the password matches, false otherwise
     */
    public boolean authenticate(String attempt) {
        return (attempt != null && attempt.equals(this.password));
    }

    /**
     * Sets a new password for parental controls.
     *
     * @param newPassword the new password to set
     */
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    /**
     * Enables or disables play restrictions.
     *
     * @param enabled true to enable restrictions, false to disable
     */
    public void setRestrictionsEnabled(boolean enabled) {
        this.restrictionsEnabled = enabled;
    }

    /**
     * Sets the allowed hours for gameplay.
     * Start hour must be between 0–23, end hour between 1–24.
     *
     * @param startHour the start of allowed playtime (inclusive)
     * @param endHour the end of allowed playtime (exclusive)
     */
    public void setAllowedPlayHours(int startHour, int endHour) {
        this.allowedStartHour = Math.max(0, Math.min(23, startHour));
        this.allowedEndHour = Math.max(1, Math.min(24, endHour));
    }

    /**
     * Checks whether gameplay is allowed at the current system time.
     *
     * @return true if within allowed hours or if restrictions are disabled
     */
    public boolean canPlayNow() {
        if (!restrictionsEnabled) return true;
        int currentHour = LocalTime.now().getHour();
        return (currentHour >= allowedStartHour && currentHour < allowedEndHour);
    }

    /**
     * Adds playtime (in minutes) to the total tracked time.
     *
     * @param minutesPlayed the number of minutes played in the session
     */
    public void addPlayTime(float minutesPlayed) {
        this.totalPlayTime += minutesPlayed;
    }

    /**
     * Increments the session count (each time a session occurs).
     */
    public void incrementSessionCount() {
        this.sessionCount++;
    }

    /**
     * Gets the total playtime in minutes.
     *
     * @return the total time played
     */
    public float getTotalPlayTime() {
        return totalPlayTime;
    }

    /**
     * Calculates the average playtime per session.
     *
     * @return the average playtime, or 0 if no sessions have occurred
     */
    public float getAveragePlayTime() {
        if (sessionCount == 0) return 0;
        return totalPlayTime / sessionCount;
    }

    /**
     * Resets playtime and session statistics to zero.
     */
    public void resetPlayTimeStats() {
        this.totalPlayTime = 0;
        this.sessionCount = 0;
    }

    /**
     * Revives the specified pet by restoring all stats and setting its state to NORMAL.
     *
     * @param pet the pet to revive
     */
    public void revivePet(Pet pet) {
        if (pet == null) return;
        pet.setHealth(100);
        pet.setFullness(100);
        pet.setHappiness(100);
        pet.setSleep(100);
        pet.setState("NORMAL");
    }

    /**
     * Checks if restrictions are currently enabled.
     *
     * @return true if enabled, false otherwise
     */
    public boolean isRestrictionsEnabled() {
        return restrictionsEnabled;
    }

    /**
     * Returns a string describing the current restriction settings.
     *
     * @return a string description of active play hour limits, or "No restrictions" if disabled
     */
    public String getRestrictions() {
        if (!restrictionsEnabled) {
            return "No restrictions";
        }
        return "Allowed play hours: " + allowedStartHour + " to " + allowedEndHour;
    }
}
