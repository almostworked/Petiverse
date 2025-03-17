 /*TEMPORARY PLACEHOLDER CLASS
 */

public class Pet {
    private String name;
	private int health;
	private int sleep;
	private int happiness;
	private int hunger;
	private boolean alive;
    private int fullness;
	    
	public Pet(String name, int health, int sleep, int happiness, int hunger, boolean alive, int fullness) {
        this.name = name;
        this.health = health;
        this.sleep = sleep;
        this.happiness = happiness;
        this.hunger = hunger;
        this.alive = alive;
        this.fullness = fullness;
    }
	
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public int getHealth() {
        return health;
    }
    
    public void setHealth(int health) {
    	this.health = health;
    }
    
    public int getSleep() {
        return sleep;
    }
    
    public void setSleep(int sleep) {
    	this.sleep = sleep;
    }
    
    public int getHappiness() {
        return happiness;
    }
    
    public void setHappiness(int happiness) {
    	this.happiness = happiness;
    }
    
    public int getHunger() {
        return hunger;
    }
    
    public void setHunger(int hunger) {
    	this.hunger = hunger;
    }
    
    public boolean isAlive() {
		return alive;
    }

    public int getFullness(){
        return fullness;
    }

    public void setFullness(int fullness){
        this.fullness = fullness;
    }
}
