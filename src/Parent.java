/**
 * Parent class
 * @author Daniella
 */
public class Parent extends Player {

    private ParentalControls controls;

    public Parent(String name, Inventory inventory, boolean isParent, Pet activePet, String defaultPassword) {
        super(name, inventory, isParent, activePet);
        this.controls = new ParentalControls(defaultPassword);
    }

    public boolean authenticate(String attempt) {
        return controls.authenticate(attempt);
    }

    public void setPassword(String newPassword) {
        controls.setPassword(newPassword);
    }

    public void displayControls() {
        controls.displayParentalControlsScreen();
    }

    public boolean isRestrictionsEnabled() {
        return controls.isRestrictionsEnabled();
    }

    public void setRestrictions(boolean enabled, int startHour, int endHour) {
        controls.setRestrictionsEnabled(enabled);
        controls.setAllowedPlayHours(startHour, endHour);
    }

    public float getTotalPlayTime() {
        return controls.getTotalPlayTime();
    }

    public float getAveragePlayTime() {
        return controls.getAveragePlayTime();
    }

    public void addPlayTime(float minutesPlayed) {
        controls.addPlayTime(minutesPlayed);
    }

    public void incrementSessionCount() {
        controls.incrementSessionCount();
    }

    public void resetPlayTimeStats() {
        controls.resetPlayTimeStats();
    }

    public void revivePet() {
        controls.revivePet(getActivePet());
    }

    public ParentalControls getControls() {
        return controls;
    }
}

