/**
 * Parent class
 * @author Daniella
 */
public class Parent extends Player {
    private String password;
    private float time;
    float maxTime;
    private boolean restrictions;

    public Parent(Inventory inventory, Boolean isParent, Pet activePet) {
        super(inventory, isParent, activePet);
    }
    public String getPassword(){
        return password;
    }


    public void setPassword(String newPassword) {
        this.password = newPassword;

    }

    public void displayControls() {

    }

    public boolean getRestrictions() {
        return restrictions;

    }

    public void setRestrictions(boolean restrictions, float maxTime) {
        this.restrictions = restrictions;
        this.maxTime = maxTime;
    }

    public float getTotalPlayTime() {
        return time;
    }

    public float getAveragePlayTime(int sessionCount) {
        if (sessionCount == 0) return 0;
        return time / sessionCount;
        
    }

    public void revivePet(String filename) {

    }

    
}
