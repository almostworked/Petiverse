
/**
 * Represents the Roscoe pet character in the virtual pet game.
 * This class extends the Sprite class and initializes Roscoe with a set of
 * predefined properties including dimensions, position, visibility, and the
 * initial state "NORMAL".
 * 
 * @author Adrian
 * @version 1.0
 */
public class Roscoe extends Sprite {

    /**
     * Constructs a new Roscoe pet with the given name.
     *
     * @param name the name of the pet
     */
    public Roscoe(String name) {
        super(name, 85, 90, 60, 85, true, "NORMAL");
    }
}