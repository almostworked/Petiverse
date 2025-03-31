import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyStore.Entry;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The purpose of this class is to present a graphical interface when creating a new game and
 * implementing logic from class NewGame
 *
 * When a new game is seleced, the player is presented with pet options and is promted to enter information
 * such as pet selection and save slot
 * This information is saved and gameplay then begins
 */
public class NewGameGUI extends JFrame {
    private List<Pet> availablePets;
    private JTextField playerNameBox;
    private JComboBox<String> petSelection;
    private JComboBox<Integer> saveSlotBox;
    private JButton startButton;
    private JLabel backButton;
    private JLabel imageLabel;
    private Inventory inventory = new Inventory();

    public NewGameGUI(Pet pet1, Pet pet2, Pet pet3) {
        availablePets = new ArrayList<>();
        availablePets.add(pet1);
        availablePets.add(pet2);
        availablePets.add(pet3);

        setTitle("Petiverse");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Create the JPanel with a custom paintComponent method for the background
        JPanel newGamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("temp_assets/Background2.jpg"); // Ensure the correct path
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Jersey25-Regular.ttf"));
            font = font.deriveFont(Font.PLAIN, 30);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set layout to null so we can position components manually
        newGamePanel.setLayout(new BorderLayout());

        // Allow player to return to the Main Menu
        backButton = new JLabel("  < Main Menu  ");
        backButton.setForeground(Color.WHITE);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setFont(font);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Back to main menu button clicked");
                MainMenu newMenu = new MainMenu();
                dispose();
                newMenu.setVisible(true);
            }
        });

        JLabel nameLabel = new JLabel("  Player Name:  ");
        nameLabel.setFont(font);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(50, 100, 150, 30);
        
        playerNameBox = new JTextField();

        JLabel petLabel = new JLabel("  Choose a Pet:  ");
        petLabel.setFont(font);
        petLabel.setForeground(Color.WHITE);

        petSelection = new JComboBox<>();
        for (Pet pet : availablePets) petSelection.addItem(pet.getName());
        


        JLabel saveSlotLabel = new JLabel("  Save Slot:       ");
        saveSlotLabel.setFont(font);
        saveSlotLabel.setForeground(Color.WHITE);

        Integer[] slots = {1, 2, 3, 4, 5, 6, 7, 8};
        saveSlotBox = new JComboBox<>(slots);

        startButton = new JButton("Start Game");
        startButton.setFont(font);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.setAlignmentY(Component.TOP_ALIGNMENT);

        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerNameBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        petSelection.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveSlotLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveSlotBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        playerNameBox.setForeground(Color.decode("#6C5297"));
        playerNameBox.setBackground(Color.decode("#D9D9D9"));
        playerNameBox.setBorder(new EmptyBorder(10,30,30,10));

        petSelection.setForeground(Color.decode("#6C5297"));
        petSelection.setBackground(Color.decode("#D9D9D9"));

        saveSlotBox.setForeground(Color.decode("#6C5297"));
        saveSlotBox.setBackground(Color.decode("#D9D9D9"));

        startButton.setForeground(Color.decode("#D9D9D9"));
        startButton.setBackground(Color.decode("#6C5297"));
        font = font.deriveFont(Font.PLAIN, 35);
        startButton.setFont(font);
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Border border = BorderFactory.createLineBorder(Color.decode("#8B73B2"), 5);
        playerNameBox.setBorder(border);
        petSelection.setBorder(border);
        saveSlotBox.setBorder(border);
        startButton.setBorder(border = (BorderFactory.createLineBorder(Color.decode("#D9D9D9"),5)));
        
        playerNameBox.setPreferredSize(new Dimension(300, 50));
        petSelection.setPreferredSize(new Dimension(300, 50));
        saveSlotBox.setPreferredSize(new Dimension(300, 50));
        startButton.setPreferredSize(new Dimension(200, 50));

        JLabel titleText = new JLabel("Start New Game");
        font = font.deriveFont(Font.PLAIN, 70);
        titleText.setFont(font);
        titleText.setForeground(Color.WHITE);

        JPanel startButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        startButtonPanel.setOpaque(false); 
        startButtonPanel.add(startButton);
        imageLabel = new JLabel();
        String initialPetName = (String) petSelection.getSelectedItem();
        imageLabel.setIcon(getPetImage(initialPetName));
        imageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 40)); 


        petSelection.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedPetName = (String) petSelection.getSelectedItem();
            ImageIcon petIcon = getPetImage(selectedPetName);
            imageLabel.setIcon(petIcon);
        }
    });
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setOpaque(false); 

        inputPanel.add(Box.createVerticalStrut(30)); 
        inputPanel.add(titleText);
        inputPanel.add(Box.createVerticalStrut(40)); 
        inputPanel.add(createInputPanel(nameLabel, playerNameBox));
        inputPanel.add(Box.createVerticalStrut(10)); 
        inputPanel.add(createInputPanel(petLabel, petSelection));
        inputPanel.add(Box.createVerticalStrut(10)); 
        inputPanel.add(createInputPanel(saveSlotLabel, saveSlotBox));
        inputPanel.add(Box.createVerticalStrut(20));
        inputPanel.add(startButtonPanel);
        inputPanel.add(Box.createVerticalStrut(20));

        JPanel inputAndImagePanel = new JPanel(new BorderLayout());
        inputAndImagePanel.setOpaque(false);

        inputAndImagePanel.add(inputPanel, BorderLayout.WEST);
        inputAndImagePanel.add(imageLabel, BorderLayout.EAST);

        newGamePanel.add(backButton, BorderLayout.NORTH);
        newGamePanel.add(inputAndImagePanel, BorderLayout.CENTER);

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

                if (selectedPet != null) {
                    selectedPet.setHealth(100);
                    selectedPet.setSleep(100);
                    selectedPet.setFullness(100);
                    selectedPet.setHappiness(100);
                }

                saveGame(playerName, saveSlot, petName);
                saveInventory(inventory, saveSlot);
                openPlayGameInterface(playerName, selectedPet, saveSlot);

                JOptionPane.showMessageDialog(null, "New game created with " + petName);
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setContentPane(newGamePanel);
        setVisible(true); 
    }
    private JPanel createInputPanel(JComponent label, JComponent input) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT)); 
        panel.setOpaque(false); 

        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        input.setAlignmentX(Component.CENTER_ALIGNMENT);

        input.setPreferredSize(new Dimension(250, 30)); 

        panel.add(label);
        panel.add(input);
        return panel;
    }
    public ImageIcon getPetImage(String petName) {
        ImageIcon petIcon;
        switch (petName) {
            case "Foxy":
                petIcon = new ImageIcon("temp_assets/" + petName + ".png");
                break;
            case "Roscoe":
                petIcon = new ImageIcon("temp_assets/" + petName  +".png");
                break;
            case "Sterling":
                petIcon = new ImageIcon("temp_assets/" + petName + ".png");
                break;
            default:
                petIcon = new ImageIcon("temp_assets/" +  ".png");
                break;
        }

        Image petImage = petIcon.getImage();
        Image resized = petImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        return new ImageIcon(resized);
    }

    /**
     * This method opens the PlayGame interface, allowing for the initialization of gameplay
     *
     * @param playerName is the String player's name
     * @param selectedPet is the Pet the player has chosen
     * @param saveSlot is the int save slot where the game will be saved
     */
    public void openPlayGameInterface(String playerName, Pet selectedPet, int saveSlot) { // Added saveSLot argument - Daniella
       // Inventory inventory = new Inventory(); // Create an inventory with the list of items, no gifts
        Player player = new Player(playerName, inventory, false, selectedPet); // Assuming false means not a parent
        selectedPet.setActivePlayer(player);

        PlayGameGUI playGameGUI = new PlayGameGUI(player, saveSlot, playerName); // Added arguments for playgame gui - Daniella
        playGameGUI.setVisible(true);
    }
    
     /**
     * Method to save initial game information
     *
     * @param playerName is the String player's name
     * @param saveSlot is the int save slot where the game will be saved
     * @param petName is the String name of the pet
     */
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
                        + selectedPet.getFullness() + ","
                        + selectedPet.getHappiness() + "," + selectedPet.isAlive() + "," + selectedPet.getState() + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    private void saveInventory(Inventory inventory, int saveSlot) {
        String filename = "inventory.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            StringBuilder inventoryData = new StringBuilder();
            for (Map.Entry<Item, Integer> entry : inventory.itemMap.entrySet()) {
            inventoryData.append(entry.getKey().getName()).append(":").append(entry.getValue()).append(";");
            }
            writer.println(saveSlot + "," + inventoryData.toString());
            } catch (IOException e) {
            System.out.println("Error saving inventory data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Pet foxy = new Foxy("Foxy");
        Pet roscoe = new Roscoe("Roscoe");
        Pet sterling = new Sterling("Sterling");

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NewGameGUI(foxy, roscoe, sterling);
            }
        });
    }
}


