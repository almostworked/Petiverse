/**
 * Represents the Foxy sprite character in the virtual pet game.
 * This class extends the Sprite class and initializes Foxy with a set of
 * predefined properties including dimensions, position, visibility, and the
 * initial state "NORMAL". 
 * 
 * @author Adrian
 * @version 1.0
 */
public class Foxy extends Sprite {

    /**
     * Constructs a new Foxy sprite with the specified name.
     *
     * @param name the name assigned to this Foxy sprite.
     */
    public Foxy(String name) {
        super(name, 80, 90, 120, 80, true, "NORMAL");
    }
}
