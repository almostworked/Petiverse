public class Pet {
    private String name;
    private int health;
    private int sleep;
    private int happiness;
    private int fullness;
    private String state;
    private boolean alive;

    public Pet(String name, int health, int sleep, int happiness, int fullness, boolean alive, String state) {
        this.name = name;
        this.health = health;
        this.sleep = sleep;
        this.happiness = happiness;
        this.fullness = fullness;
        this.alive = true;
        this.state = "NORMAL";
    }

    public void feed(Item food) {
        if (!canExecuteAction("feed")) return;
        fullness = Math.min(fullness + food.getEffectValue(), 100);
        updateState();
    }

    public void play() {
        if (!canExecuteAction("play")) return;
        happiness = Math.min(happiness + 40, 100);
        updateState();
    }

    public void giveGift(Item gift) {
        if (!canExecuteAction("giveGift")) return;
        happiness = Math.min(happiness + gift.getEffectValue(), 100);
        updateState();
    }

    public void sleep() {
        if (!canExecuteAction("sleep")) return;
        state = "SLEEPING";
        while (sleep < 100) {
            sleep += 10; 
            sleep = Math.min(sleep, 100);
        }
        state = "NORMAL";
    }

    public void exercise() {
        if (!canExecuteAction("exercise")) return;
        health = Math.min(health + 10, 100);
        sleep = Math.max(sleep - 30, 0);
        fullness = Math.max(fullness - 10, 0);
        updateState();
    }

    public void takeToVet() {
        health = 100;
        state = "NORMAL";
    }

    private void updateState() {
        if (health <= 0) {
            state = "DEAD";
        } else if (sleep <= 0) {
            state = "SLEEPING";
        } else if (happiness <= 0) {
            state = "ANGRY";
        } else if (fullness <= 0) {
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
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
