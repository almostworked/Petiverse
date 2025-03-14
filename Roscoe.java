public class Roscoe extends Pet {

    private int weight;

    public Roscoe(String name, int health, int sleep, int happiness, int hunger, boolean alive, int weight) {
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