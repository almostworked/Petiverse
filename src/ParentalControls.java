import java.time.LocalTime;

public class ParentalControls {

    private String password;
    private boolean restrictionsEnabled;
    private int allowedStartHour;
    private int allowedEndHour;
    private float totalPlayTime;
    private int sessionCount;

    public ParentalControls(String password) {
        this.password = password;
        this.restrictionsEnabled = false;
        this.allowedStartHour = 0;
        this.allowedEndHour = 24;
        this.totalPlayTime = 0;
        this.sessionCount = 0;
    }

    public boolean authenticate(String attempt) {
        return (attempt != null && attempt.equals(this.password));
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setRestrictionsEnabled(boolean enabled) {
        this.restrictionsEnabled = enabled;
    }

    public void setAllowedPlayHours(int startHour, int endHour) {
        this.allowedStartHour = Math.max(0, Math.min(23, startHour));
        this.allowedEndHour = Math.max(1, Math.min(24, endHour));
    }

    public boolean canPlayNow() {
        if (!restrictionsEnabled) return true;
        int currentHour = LocalTime.now().getHour();
        return (currentHour >= allowedStartHour && currentHour < allowedEndHour);
    }

    public void addPlayTime(float minutesPlayed) {
        this.totalPlayTime += minutesPlayed;
    }

    public void incrementSessionCount() {
        this.sessionCount++;
    }

    public float getTotalPlayTime() {
        return totalPlayTime;
    }

    public float getAveragePlayTime() {
        if (sessionCount == 0) return 0;
        return totalPlayTime / sessionCount;
    }

    public void resetPlayTimeStats() {
        this.totalPlayTime = 0;
        this.sessionCount = 0;
    }

    public void revivePet(Pet pet) {
        if (pet == null) return;
        pet.setHealth(100);
        pet.setFullness(100);
        pet.setHappiness(100);
        pet.setSleep(100);
        pet.setState("NORMAL");
    }

    public boolean isRestrictionsEnabled() {
        return restrictionsEnabled;
    }

    public void displayParentalControlsScreen() {
        System.out.println("Parental Controls: ");
        System.out.println("1) Toggle restrictions (currently " + (restrictionsEnabled ? "ON" : "OFF") + ")");
        System.out.println("2) Set allowed play hours (currently " + allowedStartHour + " to " + allowedEndHour + ")");
        System.out.println("3) View total/average play time (total: " + getTotalPlayTime() + ", average: " + getAveragePlayTime() + ")");
        System.out.println("4) Revive pet");
        System.out.println("5) Change password");
        System.out.println("6) Reset usage stats");
        System.out.println("7) Exit Parental Controls");
    }
}
