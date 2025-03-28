/**
 * 
 * @author Fin Faniyi
 * @author Marcus Cameron
 *
 * Represents a pet in the game, with attributes such as health, sleep, happiness, fullness, and state.
 * The pet can perform various actions like eating, sleeping, playing, and exercising, which impact its state.
 */
public class Pet {
    private String name;
    private int health;
    private int sleep;
    private int happiness;
    private int fullness;
    private String state;
    private boolean alive;
    private static Player activePlayer;
    private String musicpath = "temp_assets/game-button-click.wav";


    /**
     * 
     * @param name The name of the Pet
     * @param health The initial health of the Pet
     * @param sleep The initial sleep level of the pet
     * @param happiness The initial happiness level of the pet.
     * @param fullness The initial fullness level of the pet.
     * @param alive The initial alive state of the pet.
     * @param state The initial state of the pet.
     */    private Runnable stateChangeListener;

    public Pet(String name, int health, int sleep, int happiness, int fullness, boolean alive, String state) {
        this.name = name;
        this.health = health;
        this.sleep = sleep;
        this.happiness = happiness;
        this.fullness = fullness;
        this.alive = true;
        this.state = "NORMAL";
    }

    /**
     * Sets the active player
     * @param player The player to set as active
     */
    public static void setActivePlayer(Player player) {
        activePlayer = player;
    }

    /**
     * Gets the active player
     * @return The active player 
     */
    public static Player getActivePlayer() {
        return activePlayer;
    }

    /**
     * Feeds the pet within the given food item, increasing fullness
     * @param food The food item to feed the pet
     */
    public void feed(Item food) {
        if (!canExecuteAction("feed")) return;
        if (activePlayer.getInventory().getQuantity(food) > 0 && food.getEffectValue() > 0) {
            activePlayer.getInventory().removeItem(food, 1);
            fullness = Math.min(fullness + food.getEffectValue(), 100);
            activePlayer.getScore().increaseScore(food.getEffectValue());
        } else {
            System.out.println("You don't have enough " + food.getName() + "s to give to your pet.");
        }
        updateState();
    }

    /**
     * Allows the pet to play, increasing happiness.
     */
    public void play() {
        if (!canExecuteAction("play")) return;
        //Sound sound = new Sound();
        //sound.playEffect(musicpath);
        happiness = Math.min(happiness + 40, 100);
        activePlayer.getScore().increaseScore(15);
        updateState();
    }

    /**
     * Gives a gift to the pet, increasing happiness.
     *
     * @param gift The gift item to give to the pet.
     */
    public void giveGift(Item gift) {
        if (!canExecuteAction("giveGift")) return;
        if (activePlayer.getInventory().getQuantity(gift) > 0 && gift.getEffectValue() > 0) {
            activePlayer.getInventory().removeItem(gift, 1);
            happiness = Math.min(happiness + gift.getEffectValue(), 100);
            activePlayer.getScore().increaseScore(gift.getEffectValue());

        } else {
            System.out.println("You dont have any " + gift.getName() + "s to give to your pet.");
        }
        
        updateState();
    }

    /**
     * Puts the pet to sleep, changing its state to "SLEEPING"
     */
    public void sleep() {
        if (!canExecuteAction("sleep")) return;
        //Sound sound = new Sound();
        //sound.playEffect(musicpath);
        state = "SLEEPING";
        activePlayer.getScore().increaseScore(10);
        
    }
    
    
    /**
     * Exercises the pet, improving health but reducing sleep and fullness.
     */
    public void exercise() {
        if (!canExecuteAction("exercise")) return;
        //Sound sound = new Sound();
        //sound.playEffect(musicpath);
        health = Math.min(health + 10, 100);
        sleep = Math.max(sleep - 30, 0);
        fullness = Math.max(fullness - 10, 0);
        activePlayer.getScore().increaseScore(10);
        updateState();
    }

    
    /**
     * Takes the pet to the vet, fully restoring health.
     */
    public void takeToVet() {
        if (!canExecuteAction("vet")) return;
        health = 100;
        state = "NORMAL";
        activePlayer.getScore().decreaseScore(15);
    }

    /**
     * Updates the pet's state based on its attributes.
     */
    private void updateState() {
        if (health <= 0) {
            state = "DEAD";
        } else if (sleep <= 20) {
            state = "ANGRY";
        } else if (happiness <= 20) {
            state = "ANGRY";
        } else if (fullness <= 30) {
            state = "HUNGRY";
        } else {
            state = "NORMAL";
        }
    }

    
    /**
     * Determines if an action can be executed based on the pet's current state.
     *
     * @param action The action to check.
     * @return {@code true} if the action can be executed, {@code false} otherwise.
     */
    private boolean canExecuteAction(String action) {
        if (state.equals("DEAD")) return false;
        if (state.equals("SLEEPING")) return false;
        if (state.equals("ANGRY") && !(action.equals("giveGift") || action.equals("play"))) return false;
        return true;
    }

    /**
     * Gets the pet's name
     * @return The pet's name
     */
    public String getName() { 
        return name; 
    }

    /**
     * Sets the pet's name.
     *
     * @param name The new name for the pet.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the pet's health.
     *
     * @return The pet's health.
     */
    public int getHealth() { 
        return health; 
    }

    /**
     * Sets the pet's health.
     *
     * @param health The new health value.
     */
    public void setHealth(int health) {
        this.health = health;
        if (this.health == 0) {
            this.setState("DEAD");
        }
    }
 
    /**
     * Gets the pet's sleep level.
     *
     * @return The pet's sleep level.
     */
    public int getSleep() { 
        return sleep; 
    }

    /**
     * Sets the pet's sleep level.
     *
     * @param sleep The new sleep level.
     */
    public void setSleep(int sleep) {
        this.sleep = sleep;
    }

     /**
     * Gets the pet's happiness level.
     *
     * @return The pet's happiness level.
     */
    public int getHappiness() { 
        return happiness; 
    }

    /**
     * Sets the pet's happiness level.
     *
     * @param happiness The new happiness level.
     */
    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    /**
     * Gets the pet's fullness level.
     *
     * @return The pet's fullness level.
     */
    public int getFullness() { 
        return fullness; 
    }

    /**
     * Sets the pet's fullness level.
     *
     * @param fullness The new fullness level.
     */
    public void setFullness(int fullness) {
        this.fullness = fullness;
    }

    /**
     * Gets the pet's current state.
     *
     * @return The pet's state.
     */
    public String getState() { 
        return state; 
    }

    /**
     * Sets the pet's state.
     *
     * @param state The new state.
     */
    public void setState(String state) {
        this.state = state;
        if (stateChangeListener != null) {
            stateChangeListener.run();
        }

    }
    
    /**
     * Checks if the pet is alive.
     *
     * @return {@code true} if the pet is alive, {@code false} otherwise.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Sets the alive status of the pet.
     *
     * @param alive The new alive status.
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public void setStateChangeListener(Runnable listener) {
        this.stateChangeListener = listener;
    }
    
}
