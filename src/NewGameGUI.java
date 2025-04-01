import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
 * 
 * @author Marcus
 */
public class NewGameGUI extends JFrame {
    private List<Pet> availablePets;
    private JTextField playerNameBox;
    private JTextField petNameBox;
    private JComboBox<String> petSelection;
    private JComboBox<Integer> saveSlotBox;
    private JButton startButton;
    private JLabel backButton;
    private JLabel imageLabel;
    private Inventory inventory = new Inventory();
    /**
     * Constructor for NewGameGUI which initializes the screen for starting a new game and choosing
     * player name, pet type, pet name and save slot.
     * @param pet1
     * @param pet2
     * @param pet3
     */

    public NewGameGUI(Pet pet1, Pet pet2, Pet pet3) {
        availablePets = new ArrayList<>();
        availablePets.add(pet1);
        availablePets.add(pet2);
        availablePets.add(pet3);

        setTitle("Petiverse");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel newGamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("temp_assets/Background1.jpg"); // Ensure the correct path
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Jersey25-Regular.ttf"));
            font = font.deriveFont(Font.PLAIN, 25);
        } catch (Exception e) {
            e.printStackTrace();
        }

        newGamePanel.setLayout(new BorderLayout());

        JButton backButton = new JButton("< Main Menu");
        backButton.setForeground(Color.WHITE);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setOpaque(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setForeground(Color.decode("#FFFFFF"));
        backButton.setAlignmentX(LEFT_ALIGNMENT);
        backButton.setAlignmentY(TOP_ALIGNMENT);
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
        

        JLabel nameLabel = new JLabel("  Player Name:     ");
        nameLabel.setFont(font);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(50, 100, 150, 30);
        
        playerNameBox = new JTextField();
        petNameBox = new JTextField();

        JLabel petLabel = new JLabel("  Choose a Pet:     ");
        petLabel.setFont(font);
        petLabel.setForeground(Color.WHITE);

        petSelection = new JComboBox<>();
        for (Pet pet : availablePets) petSelection.addItem(pet.getName());
        
        JLabel petName = new JLabel("  Name your Pet:  ");
        petName.setFont(font);
        petName.setForeground(Color.WHITE);

        JLabel saveSlotLabel = new JLabel("  Save Slot:          ");
        saveSlotLabel.setFont(font);
        saveSlotLabel.setForeground(Color.WHITE);

        Integer[] slots = {1, 2, 3, 4, 5, 6, 7, 8};
        saveSlotBox = new JComboBox<>(slots);

        startButton = new JButton("Start Game");
        startButton.setFont(font);

        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerNameBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        petSelection.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveSlotLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveSlotBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        petNameBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        playerNameBox.setForeground(Color.decode("#6C5297"));
        playerNameBox.setBackground(Color.decode("#D9D9D9"));
        playerNameBox.setBorder(new EmptyBorder(10,30,40,10));

        petSelection.setForeground(Color.decode("#6C5297"));
        petSelection.setBackground(Color.decode("#D9D9D9"));
        petSelection.setBorder(new EmptyBorder(10,30,40,10));

        petNameBox.setForeground(Color.decode("#6C5297"));
        petNameBox.setBackground(Color.decode("#D9D9D9"));
        petNameBox.setBorder(new EmptyBorder(10,30,40,10));

        saveSlotBox.setForeground(Color.decode("#6C5297"));
        saveSlotBox.setBackground(Color.decode("#D9D9D9"));

        startButton.setForeground(Color.decode("#D9D9D9"));
        startButton.setBackground(Color.decode("#6C5297"));
        font = font.deriveFont(Font.PLAIN, 35);

        startButton.setFont(font);
        font = font.deriveFont(Font.PLAIN, 20);
        playerNameBox.setFont(font);
        petNameBox.setFont(font);
        petSelection.setFont(font);
        saveSlotBox.setFont(font);
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Border border = BorderFactory.createLineBorder(Color.decode("#8B73B2"), 5);
        playerNameBox.setBorder(border);
        petSelection.setBorder(border);
        saveSlotBox.setBorder(border);
        petNameBox.setBorder(border);
        startButton.setBorder(border = (BorderFactory.createLineBorder(Color.decode("#D9D9D9"),5)));
        
        playerNameBox.setPreferredSize(new Dimension(300, 70));
        petSelection.setPreferredSize(new Dimension(300, 70));
        saveSlotBox.setPreferredSize(new Dimension(300, 70));
        startButton.setPreferredSize(new Dimension(200, 70));

        JLabel titleText = new JLabel("Start New Game");
        font = font.deriveFont(Font.PLAIN, 70);
        titleText.setFont(font);
        titleText.setForeground(Color.WHITE);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0)); // Padding
        topPanel.add(backButton, BorderLayout.WEST);
        
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
        inputPanel.add(Box.createVerticalStrut(20)); 
        inputPanel.add(createInputPanel(nameLabel, playerNameBox));
        inputPanel.add(Box.createVerticalStrut(10)); 
        inputPanel.add(createInputPanel(petLabel, petSelection));
        inputPanel.add(Box.createVerticalStrut(10)); 
        inputPanel.add(createInputPanel(petName, petNameBox));
        inputPanel.add(Box.createVerticalStrut(10)); 
        inputPanel.add(createInputPanel(saveSlotLabel, saveSlotBox));
        inputPanel.add(Box.createVerticalStrut(20));
        inputPanel.add(startButtonPanel);
        inputPanel.add(Box.createVerticalStrut(20));

        JPanel inputAndImagePanel = new JPanel(new BorderLayout());
        inputAndImagePanel.setOpaque(false);

        inputAndImagePanel.add(inputPanel, BorderLayout.WEST);
        inputAndImagePanel.add(imageLabel, BorderLayout.EAST);

        newGamePanel.add(topPanel, BorderLayout.NORTH);
        newGamePanel.add(inputAndImagePanel, BorderLayout.CENTER);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = playerNameBox.getText();
                int saveSlot = (int) saveSlotBox.getSelectedItem();
                String petType = (String) petSelection.getSelectedItem();
                String petName = (String) petNameBox.getText();

                Pet selectedPet = null;
                for (Pet pet : availablePets) {
                    if (pet.getName().equals(petType)) selectedPet = pet;
                }

                if (selectedPet != null) {
                    selectedPet.setHealth(100);
                    selectedPet.setSleep(100);
                    selectedPet.setFullness(100);
                    selectedPet.setHappiness(100);
                }

                saveGame(playerName, saveSlot, petType, petName);
                saveInventory(inventory, saveSlot);
                openPlayGameInterface(playerName, selectedPet, saveSlot, petName);

                JOptionPane.showMessageDialog(null, "New game created with " + petName);
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setContentPane(newGamePanel);
        setVisible(true); 
    }
    /**
     * Creates an organized panel for each form of input when creating a new game.
     * @param label
     * @param input
     * @return
     */
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
    /**
     * Retrieves a pet image based on default pet type, to be changed dynamically as a user selects
     * a pet from the pet dropdown menu.
     * @param petName
     * @return
     */
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
    public void openPlayGameInterface(String playerName, Pet selectedPet, int saveSlot, String petName) { // Added saveSLot argument - Daniella
       // Inventory inventory = new Inventory(); // Create an inventory with the list of items, no gifts
        selectedPet.setCustomName(petName);
        Player player = new Player(playerName, inventory, false, selectedPet); // Assuming false means not a parent
        Pet.setActivePlayer(player);

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
    private void saveGame(String playerName, int saveSlot, String petType, String petName) {
        try (FileWriter writer = new FileWriter("data/game_save.csv", true)) {
            Pet selectedPet = null;
            for (Pet pet : availablePets) {
                if (pet.getName().equals(petType)) {
                    selectedPet = pet;
                    break;
                }
            }
            if (selectedPet != null) {
                writer.write(saveSlot + "," + playerName + "," + petType + "," + petName + ","
                        + selectedPet.getHealth() + ","
                        + selectedPet.getSleep() + ","
                        + selectedPet.getFullness() + ","
                        + selectedPet.getHappiness() + "," + selectedPet.isAlive() + "," + selectedPet.getState() + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    /**
     * Creates an initial inventory file for the newly saved pet.
     * @param inventory
     * @param saveSlot
     * @see Inventory
     */
    private void saveInventory(Inventory inventory, int saveSlot) {
        String filename = "data/inventory.csv";
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
    /**
     * Main method for quick usage and SwingUtilities.invokeLater() to ensure code runs on the event
     * dispatch thread.
     * @param args
     */

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


