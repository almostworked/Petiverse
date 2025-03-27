public class Pet {
    private String name;
    private int health;
    private int sleep;
    private int happiness;
    private int fullness;
    private String state;
    private boolean alive;
    private static Player activePlayer;
    private Runnable stateChangeListener;

    public Pet(String name, int health, int sleep, int happiness, int fullness, boolean alive, String state) {
        this.name = name;
        this.health = health;
        this.sleep = sleep;
        this.happiness = happiness;
        this.fullness = fullness;
        this.alive = true;
        this.state = "NORMAL";
    }

    public static void setActivePlayer(Player player) {
        activePlayer = player;
    }

    public static Player getActivePlayer() {
        return activePlayer;
    }

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

    public void play() {
        if (!canExecuteAction("play")) return;
        happiness = Math.min(happiness + 40, 100);
        activePlayer.getScore().increaseScore(15);
        updateState();
    }

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

    public void sleep() {
        if (!canExecuteAction("sleep")) return;
        state = "SLEEPING";
        activePlayer.getScore().increaseScore(10);
        
    }
    
    
    
    public void exercise() {
        if (!canExecuteAction("exercise")) return;
        health = Math.min(health + 10, 100);
        sleep = Math.max(sleep - 30, 0);
        fullness = Math.max(fullness - 10, 0);
        activePlayer.getScore().increaseScore(10);
        updateState();
    }

    public void takeToVet() {
        if (!canExecuteAction("vet")) return;
        health = 100;
        state = "NORMAL";
        activePlayer.getScore().decreaseScore(15);
    }

    private void updateState() {
        if (health <= 0) {
            state = "DEAD";
        } else if (sleep <= 20) {
            state = "TIRED";
        } else if (happiness <= 20) {
            state = "ANGRY";
        } else if (fullness <= 30) {
            state = "HUNGRY";
        } else {
            state = "NORMAL";
        }
    }

    private boolean canExecuteAction(String action) {
        if (state.equals("DEAD")) return false;
        if (state.equals("SLEEPING")) return false;
        if (state.equals("ANGRY") && !(action.equals("giveGift") || action.equals("play"))) return false;
        return true;
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
        if (this.health == 0) {
            this.setState("DEAD");
        }
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

    public int getFullness() { 
        return fullness; 
    }

    public void setFullness(int fullness) {
        this.fullness = fullness;
    }

    public String getState() { 
        return state; 
    }

    public void setState(String state) {
        this.state = state;
        if (stateChangeListener != null) {
            stateChangeListener.run();
        }

    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public void setStateChangeListener(Runnable listener) {
        this.stateChangeListener = listener;
    }
    
}
