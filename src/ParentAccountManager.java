/**
 * ParentAccountManager.java: Create a .dat file to store parent info. Saves and load parent password. 
 * <b>The parent is global across all pets and instances.</b> Delete the .dat file if new parent account must be
 * created.
 * 
 * @author Daniella
 * @version 1.0
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ParentAccountManager {

    private static final String FILE_PATH = "parent_account.dat";
    /**
     * Method to save the parent account to the .dat file. 
     * @param parent
     */

    public static void saveParentAccount(Parent parent) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(parent);
            System.out.println("Parent account saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Method to load the parent account from the .dat file.
     * @return
     */
    
    public static Parent loadParentAccount() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No parent account file found.");
            return null;
        }
    
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Parent parent = (Parent) in.readObject();
            System.out.println("Parent account loaded.");
            return parent;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Boolean method to check if the parent account already exists.
     * @return
     */
    public static boolean parentAccountExists() {
        File file = new File(FILE_PATH);
        return file.exists();
    }
}
