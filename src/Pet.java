 /*TEMPORARY PLACEHOLDER CLASS
 */
/**
 * 
 * @author faniyi
 * This is the Pet class, This is where the main characteristics of all the pet are found
 */
public class Pet {
    /**
     * These are the attributes of the Pet, It consists of name, health, sleep, happiness, hunger, alive, fullness and the state of the Pet (i.e Angry, hungry, etc.)
     */
    private String name;
    private int health;
    private int sleep;
    private int happiness;
    private int hunger;
    private boolean alive;
    private int fullness;
    private String state;
	
    /**
     * 
     * @param name is the name of the pet
     * @param health is the default starting health you want for the pet
     * @param sleep is the default starting sleep level you want for the pet
     * @param happiness is the default happiness level you want for the pet
     * @param hunger is the default starting hunger level you want for the pet
     * @param alive is how to determine if the pet is alive or not
     * @param fullness is the default starting fullness level you want for the pet
     * @param state is the default starting state you want for the pet
     * 
     * This is the constructor of the Pet
     */
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
    
    /**
     * 
     * @return the name of the pet as a string
     * This is the getter class
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * @param name of the pet will be set
     * This is the setter method
     */
    public void setName(String name) {
    	this.name = name;
    }
    
    /**
     * 
     * @return the health of the pet as an int
     * This is a getter method
     */
    public int getHealth() {
        return health;
    }
    
    /**
     * 
     * @param health of the pet will be set
     * This is the setter method for health of the pet
     */
    public void setHealth(int health) {
    	this.health = health;
    }
    
    /**
     * 
     * @return the sleep level of the pet as an int
     * This is a getter method for the pet's sleep attribute
     */
    public int getSleep() {
        return sleep;
    }
    
    /**
     * 
     * @param sleep of the pet will be set
     * This is the setter method for the sleep level of the pet
     */
    
    public void setSleep(int sleep) {
    	this.sleep = sleep;
    }
    
    /**
     * 
     * @return the happiness level of the pet as an int
     * This is a getter method for the pet's happiness level
     */
    
    public int getHappiness() {
        return happiness;
    }
    
    /**
     * 
     * @param happiness of the pet will be set
     * This is the setter method for the happiness level of the pet
     */
    public void setHappiness(int happiness) {
    	this.happiness = happiness;
    }
    
    /**
     * 
     * @return the hunger level of the pet as an int
     * This is a getter method for the pet's hunger level. It gets the hunger level
     */
    public int getHunger() {
        return hunger;
    }
    
    /**
     * 
     * @param hunger of the pet will be set
     * This is the setter method for the hunger level of the pet
     */
    public void setHunger(int hunger) {
    	this.hunger = hunger;
    }
    
    /**
     * 
     * @return the fullness level of the pet as an int
     * This is a getter method for the pet's fullness level. It lets us know the fullness level
     */
    public int getFullness(){
        return fullness;
    }

    /**
     * 
     * @param fullness of the pet will be set
     * This is the setter method for the fullness level of the pet
     */
    public void setFullness(int fullness){
        this.fullness = fullness;
    }

    /**
     * 
     * @return true or false if the pet is alive or not. It returns it as a boolean
     * 
     */
     public boolean isAlive() {
		return alive;
    } 

    /**
     * @return A {@code String} representing the pet's current state
     * It also determines the current state of the pet based on its attributes.
     * The states are prioritized in the following order:
     * <ul>
     *   <li><b>"DEAD"</b> - If health is 0, the pet is considered dead.</li>
     *   <li><b>"SLEEPING"</b> - If sleep is 0, the pet is sleeping and cannot perform actions.</li>
     *   <li><b>"ANGRY"</b> - If happiness is 0, the pet is angry and has limited interactions.</li>
     *   <li><b>"HUNGRY"</b> - If fullness is 0, the pet is hungry but can still perform actions.</li>
     *   <li><b>"NORMAL"</b> - If none of the above conditions are met, the pet is in a normal state.</li>
     * </ul>
     *
     * 
     * 
     */
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

    /**
     * Updates the pet's state by setting new values for health, sleep, happiness, and fullness.
     * @param health    The pet's health level. If 0, the pet is considered dead.
     * @param sleep     The pet's sleep level. If 0, the pet is sleeping and cannot perform actions.
     * @param happiness The pet's happiness level. If 0, the pet is angry and has limited interactions.
     * @param fullness  The pet's fullness level. If 0, the pet is hungry but can still perform actions.
     */
    public void setState(int health, int sleep, int happiness, int fullness){
        this.health = health;
        this.sleep = sleep;
        this.happiness = happiness;
        this.fullness = fullness;
    }

    /**
     * Feeds the pet, it increases the pet's fullness level.
     * If the pet is dead, it cannot be fed.
     */
    public void feed() {
        if (getState().equals("DEAD")) {
            System.out.println(name + " is dead. You can't feed them anymore.");
        } else {
            fullness++;
            System.out.println("Feeding " + name + ".");
        }
    }

    /**
     * Puts the pet to sleep by setting its sleep level to 0.
     * If the pet is dead, it cannot sleep anymore.
     */
    public void sleep() {
        if (getState().equals("DEAD")) {
            System.out.println(name + " is dead. They cannot sleep.");
        } else {
            sleep = 0;
            System.out.println(name + " is now sleeping.");
        }
    }

     /**
     * Allows the pet to play, increases the pet's happiness level.
     * If the pet is dead or sleeping, it cannot play anymore
     * If the pet is angry, playing would improve its happiness
     * Otherwise, playing increases happiness.
     * 
     */
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
