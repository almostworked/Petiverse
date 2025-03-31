/**
 * Represents the Sterling pet character in the virtual pet game.
 * This class extends the Sprite class and initializes Sterling with a set of
 * predefined properties including dimensions, position, visibility, and the
 * initial state "NORMAL".
 * 
 * @author Adrian
 * @version 1.0
 */
public class Sterling extends Sprite {

    /**
     * Constructs a new Sterling pet with the given name.
     *
     * @param name the name of the pet
     */
    public Sterling(String name) {
        super(name, 95, 90, 100, 75, true, "NORMAL");
    }
}