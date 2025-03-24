import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class NewGameGUI extends JFrame {
    private List<Pet> availablePets;
    private JTextField playerNameBox;
    private JComboBox<String> petSelection;
    private JComboBox<Integer> saveSlotBox;
    private JButton startButton;
    private JLabel backButton;

    public NewGameGUI(Pet pet1, Pet pet2, Pet pet3) {
        availablePets = new ArrayList<>();

        availablePets.add(pet1);
        availablePets.add(pet2);
        availablePets.add(pet3);

        setTitle("Petiverse");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Allow player to return to the Main Menu
        backButton = new JLabel("  < Main Menu  ");
        backButton.setForeground(Color.BLUE);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new MainMenu();  // Return to the Main Menu
            }
        });

        add(backButton);
        add(new JLabel(""));
        add(new JLabel("  Player Name:  "));
        playerNameBox = new JTextField();
        add(playerNameBox);

        add(new JLabel("  Choose a Pet:  "));
        petSelection = new JComboBox<>();

        // Display the pet names
        for (Pet pet : availablePets) petSelection.addItem(pet.getName());
        add(petSelection);

        add(new JLabel("  Save Slot:  "));
        Integer[] slots = {1, 2, 3, 4, 5, 6, 7, 8}; // Save slots
        saveSlotBox = new JComboBox<>(slots);
        add(saveSlotBox);

        add(new JLabel(""));
        startButton = new JButton("Start Game");
        add(startButton);

        playerNameBox.setForeground(Color.decode("#6C5297"));
        playerNameBox.setBackground(Color.decode("#D9D9D9"));

        petSelection.setForeground(Color.decode("#6C5297"));
        petSelection.setBackground(Color.decode("#D9D9D9"));

        saveSlotBox.setForeground(Color.decode("#6C5297"));
        saveSlotBox.setBackground(Color.decode("#D9D9D9"));

        startButton.setForeground(Color.decode("#6C5297"));
        startButton.setBackground(Color.decode("#D9D9D9"));

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = playerNameBox.getText();
                int saveSlot = (int) saveSlotBox.getSelectedItem();
                String petName = (String) petSelection.getSelectedItem();

                Pet selectedPet = null;
                for (Pet pet : availablePets) {
                    if (pet.getName().equals(petName)) selectedPet = pet;
                }

                // If a pet is found, set their initial stats to 100
                if (selectedPet != null) {
                    selectedPet.setHealth(100);
                    selectedPet.setSleep(100);
                    selectedPet.setHunger(100);
                    selectedPet.setHappiness(100);
                }

                saveGame(playerName, saveSlot, petName);
                openPlayGameInterface(playerName, selectedPet, saveSlot);


                JOptionPane.showMessageDialog(null, "New game created with " + petName);
                dispose();
            }
        });
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void openPlayGameInterface(String playerName, Pet selectedPet, int saveSlot) {
        // You can pass the player and pet into the PlayGame interface here.
        // Assuming you have a class that implements PlayGame (like your Player class)
        
        Inventory inventory = new Inventory();  // Initialize inventory for the player
        Player player = new Player(playerName, inventory, false, selectedPet); // Assuming false means not a parent
    
        // Assuming PlayGameGUI is your game window that needs the Player object
        PlayGameGUI playGameGUI = new PlayGameGUI(player, saveSlot, playerName); 
        playGameGUI.setVisible(true); // Make the play game window visible
    }
    

    private void saveGame(String playerName, int saveSlot, String petName) {
        try (FileWriter writer = new FileWriter("game_save.csv", true)) {
            // Get the selected pet object based on its name
            Pet selectedPet = null;
            for (Pet pet : availablePets) {
                if (pet.getName().equals(petName)) {
                    selectedPet = pet;
                    break;
                }
            }
    
            // If the pet is found, write all fields into the CSV
            if (selectedPet != null) {
                writer.write(saveSlot + "," + playerName + "," + petName + ","
                        + selectedPet.getHealth() + ","
                        + selectedPet.getSleep() + ","
                        + selectedPet.getHunger() + ","
                        + selectedPet.getHappiness() + "," + selectedPet.isAlive() + "," + selectedPet.getState() + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    

    public static void main(String[] args) {
        Pet pet1 = new Pet("Foxy", 100, 100, 100, 100, true, 100, "Normal" );
        Pet pet2 = new Pet("Roscoe", 100, 100, 100, 100, true, 100, "Normal" );
        Pet pet3 = new Pet("Sterling", 100, 100, 100, 100, true, 100, "Normal");

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NewGameGUI(pet1, pet2, pet3);
            }
        });
    }
}


