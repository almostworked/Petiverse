package src;
public class Sterling extends Pet {

    private int weight;

    public Sterling(String name, int health, int sleep, int happiness, int hunger, boolean alive, int weight) {
        super(name, health, sleep, happiness, hunger, alive);
        this.weight = weight; 
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
