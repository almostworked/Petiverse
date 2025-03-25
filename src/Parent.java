/**
 * Parent class
 * @author Daniella
 */
public class Parent extends Player {
    private String password;
    private float time;
    float maxTime;
    private boolean restrictions;

<<<<<<< HEAD
    public Parent(Inventory inventory, Boolean isParent, Pet activePet) {
        super(inventory, isParent, activePet);
=======
    public Parent(String name, Inventory inventory, Boolean isParent, Pet activePet) {
        super(name, inventory, isParent, activePet);
>>>>>>> 01bf411f0e541dac96a75ce41ceb2dd4ea0dee43
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
