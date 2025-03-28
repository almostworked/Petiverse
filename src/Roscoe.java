/**
 * The Roscoe class represents a pet character named Roscoe.
 * It extends the Pet class and initializes Roscoe with specific attributes.
 * 
 * @author Adrian
 * @version 1.0
 */
public class Roscoe extends Pet {

    /**
     * Constructs a new Roscoe pet with the given name.
     *
     * @param name the name of the pet
     */
    public Roscoe(String name) {
        super(name, 85, 90, 60, 85, true, "NORMAL");
    }
}