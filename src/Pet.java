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
    private String state;
	    
	public Pet(String name, int health, int sleep, int happiness, int hunger, boolean alive, int fullness, String state) {
        this.name = name;
        this.health = health;
        this.sleep = sleep;
        this.happiness = happiness;
        this.hunger = hunger;
        this.alive = alive;
        this.fullness = fullness;
        this.state = state;
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

    public int getFullness(){
        return fullness;
    }

    public void setFullness(int fullness){
        this.fullness = fullness;
    }

     public boolean isAlive() {
		return alive;
    } 

    public String getState(){
        if (health == 0){
            return "DEAD";
        } else if (sleep == 0){
            return "SLEEPING";
        } else if (happiness == 0){
            return "ANGRY";
        } else if (fullness == 0){
            return "HUNGRY";
        } else {
            return "NORMAL";
        }
    }

    public void setState(int health, int sleep, int happiness, int fullness){
        this.health = health;
        this.sleep = sleep;
        this.happiness = happiness;
        this.fullness = fullness;
    }

    // Feed Method
    public void feed() {
        if (getState().equals("DEAD")) {
            System.out.println(name + " is dead. You can't feed them.");
        } else {
            fullness++;
            System.out.println("Feeding " + name + ".");
        }
    }

    // Sleep Method
    public void sleep() {
        if (getState().equals("DEAD")) {
            System.out.println(name + " is dead. They cannot sleep.");
        } else {
            sleep = 0;
            System.out.println(name + " is now sleeping.");
        }
    }

    //Play Method
    public void play() {
        if (getState().equals("SLEEPING") || getState().equals("DEAD")) {
            System.out.println(name + " can't play right now.");
        } else if (getState().equals("ANGRY")) {
            System.out.println(name + " is angry. Playing might help!");
            happiness++; // Increase happiness when playing
        } else {
            happiness++;
            System.out.println("Playing with " + name + "!");
        }
    }

}
