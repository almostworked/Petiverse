package src;

/**
 * Represents the pet Foxy, an extension of the Pet class with an additional weight attribute.
 */
public class Foxy extends Pet {

    /** The weight of the Foxy pet. */
    private int weight;

    /**
     * Constructs a Foxy pet with the specified attributes.
     *
     * @param name      The name of the pet.
     * @param health    The current health level of the pet.
     * @param sleep     The current sleep level of the pet.
     * @param happiness The current happiness level of the pet.
     * @param hunger    The current hunger level of the pet.
     * @param alive     The status of alive or dead
     * @param weight    The weight of the Foxy pet.
     * @param fullness  The fullness level of the pet
     * @param state     The state of the pet
     */
    public Foxy(String name, int health, int sleep, int happiness, int hunger, int weight, boolean alive, int fullness, String state) {
        super(name, health, sleep, happiness, hunger, alive, fullness, state);
        this.weight = weight;
    }

    /**
     * Retrieves the weight of the Foxy pet.
     *
     * @return The Foxy's weight.
     */
    public int getWeight() {
        return this.weight;
    }

    /**
     * Sets the weight of the Foxy pet.
     *
     * @param weight The new weight to assign.
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }
}
