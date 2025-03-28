/**
 * Interface that defines the methods for interacting with the game.
 * These methods allow the player to view the state of their pet, 
 * available commands, gift options, and go back to a previous screen.
 */
public interface PlayGame {

    /**
     * Displays the current state of the pet, including health, sleep, fullness, happiness, and any other relevant data.
     */
    void displayPetState();

    /**
     * Displays a list of all available commands that the player can execute during gameplay.
     */
    void displayAllCommands();

    /**
     * Displays the available gift commands that the player can give to their pet.
     */
    void displayGiftCommands();

    /**
     * Simulates going back to a previous screen or menu in the game.
     */
    void back();
    

}
