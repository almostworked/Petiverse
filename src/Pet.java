package src;

/**
 * 
 * @author faniyi
 * This is the Pet class, This is where the main characteristics of all the pet are found
 */
public class Pet {
    /**
     * These are the attributes of the Pet, It consists of name, health, sleep, happiness, alive, fullness and the state of the Pet (i.e Angry, hungry, etc.)
     */
    private String name;
    private int health;
    private int sleep;
    private int happiness;
    private boolean alive;
    private int fullness;
    private String state;
	
    /**
     * 
     * @param name is the name of the pet
     * @param health is the default starting health you want for the pet
     * @param sleep is the default starting sleep level you want for the pet
     * @param happiness is the default happiness level you want for the pet
     * @param alive is how to determine if the pet is alive or not
     * @param fullness is the default starting fullness level you want for the pet
     * @param state is the default starting state you want for the pet
     * 
     * This is the constructor of the Pet
     */
	public Pet(String name, int health, int sleep, int happiness, boolean alive, int fullness, String state) {
        this.name = name;
        this.health = health;
        this.sleep = sleep;
        this.happiness = happiness;
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
    public void setAlive(boolean alive) {
        this.alive = alive;
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
     * Updates the pet's state based on the provided state name.
     * 
     * @param state A string representing the desired state (e.g,"SLEEPING").
     */
    public void setState(String state) {
        switch (state.toUpperCase()) {
            case "DEAD":
                this.health = 0;
                break;
            case "SLEEPING":
                this.sleep = 0;
                break;
            case "ANGRY":
                this.happiness = 0;
                break;
            case "HUNGRY":
                this.fullness = 0;
                break;
            case "NORMAL":
                setDefaultValues();
                break;
            default:
                System.out.println("Wrong state provided.");
                return;
        }
        System.out.println(name + " is now " + state + ".");
    }

    /**
     * Resets pet's attributes to a default "NORMAL" state.
     */
    private void setDefaultValues() {
        this.health = 100;
        this.sleep = 100;
        this.happiness = 100;
        this.fullness = 100;
    }

}
