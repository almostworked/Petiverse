package group30;/*
 TEMPORARY PLACEHOLDER CLASS
 */

public class Pet {
    private String name;
    private int happiness;
    private int hunger;
    private int sleep;
    private int health;

    public Pet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public void setHunger(int i) {
        this.hunger = i;
    }

    public void setSleep(int i) {
        this.sleep = i;
    }

    public void setHealth(int i) { this.health = i;
    }
}
