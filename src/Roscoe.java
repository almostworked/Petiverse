package src;

/**
 * Represents the pet Roscoe, an extension of the Pet class with an additional weight attribute.
 */
public class Roscoe extends Pet {

    /** The weight of the Roscoe pet. */
    private int weight;

    /**
     * Constructs a Roscoe pet with the specified attributes.
     *
     * @param name      The name of the pet.
     * @param health    The current health level of the pet.
     * @param sleep     The current sleep level of the pet.
     * @param happiness The current happiness level of the pet.
     * @param hunger    The current hunger level of the pet.
     * @param alive     The status of alive or dead
     * @param weight    The weight of the Roscoe pet.
     */
    public Roscoe(String name, int health, int sleep, int happiness, int hunger, int weight, boolean alive) {
        super(name, health, sleep, happiness, hunger, alive);
        this.weight = weight;
    }

    /**
     * Retrieves the weight of the Roscoe pet.
     *
     * @return The Roscoe's weight.
     */
    public int getWeight() {
        return this.weight;
    }

    /**
     * Sets the weight of the Roscoe pet.
     *
     * @param weight The new weight to assign.
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }
}
