/**
 * The Foxy class represents a pet character named Foxy.
 * It extends the Pet class and initializes Foxy with specific attributes.
 * 
 * @author Adrian
 * @version 1.0
 */
public class Foxy extends Pet {

    /**
     * Constructs a new Foxy pet with the given name.
     *
     * @param name the name of the pet
     */
    public Foxy(String name) {
        super(name, 80, 90, 120, 80, true, "NORMAL");
    }
}