import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.List;
import java.util.Map;
import java.util.Map;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


public class PlayGameGUI extends JFrame implements StateManager.StateChangeListener {
    private static PlayGameGUI instance;
    private Player player;
    private Pet pet;
    private JLabel healthLabel, sleepLabel, fullnessLabel, happinessLabel;
    private JLabel petImageLabel = new JLabel();
    private CustomProgressBar healthBar, sleepBar, happinessBar, hungerBar;
    private JLabel petNameLabel;
    private JLabel stateLabel;
    private SaveGame saveGame;
    private String playerName;
    private int saveSlot;
    private Sprite petSprite;
    private Timer animationTimer;
    private boolean warningShown = false;
    private Score score;
    
    
    public PlayGameGUI(Player player, int saveSlot, String playerName) {
        int[] decay = {1,2,3};
        StateManager stateManager = new StateManager(player.getActivePet(), decay);
        stateManager.addStateChangeListener(this); 
        
        instance = this;
        this.saveSlot = saveSlot;
        this.playerName = playerName;
        this.player = player;
        this.saveGame = new SaveGame(saveSlot, false);
        this.saveGame.setSavedName(playerName);
        this.pet = player.getActivePet();
        this.score = player.getScore();
        Pet.setActivePlayer(player);

        GameLoop gameLoop = new GameLoop(pet, player, stateManager, saveGame);
        gameLoop.start();

        JPanel mainContentPanel = new JPanel(new BorderLayout());
        mainContentPanel.setOpaque(false);

        stateLabel = new JLabel("Current State: NORMAL");
        
        petSprite = (Sprite) pet;
        petSprite.setCurrentState(pet.getState());
        

        startAnimation(petSprite, petImageLabel);
        petSprite.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("prop event triggered");
                System.out.println("name: " + evt.getPropertyName());
                if (evt.getPropertyName().equals("spriteState")) {
                    System.out.println("entered property");
                    petSprite.resetAnimation();  // Reset animation when state changes
                    updatePetSprite();  // Update image immediately based on the new state
                }
            }
        });

        setTitle("Petiverse - Play Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(700, 600); // Set the size of the frame
        setLocationRelativeTo(null); // Centres the frame on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close window when X button pressed

        JPanel background = new JPanel() { // Override the paintComponent method to add the custom background
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundPic = new ImageIcon("temp_assets/Background1.jpg");
                g.drawImage(backgroundPic.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

        petNameLabel = createPetTitle();
        createVitalBars();
        JPanel vitalPanel = createVitalPanel();

        Font font = null;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Jersey25-Regular.ttf"));
            font = font.deriveFont(Font.PLAIN, 25);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        JButton back = new JButton("< Main Menu");
        back.setFont(font);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setOpaque(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.setForeground(Color.WHITE);
        back.setAlignmentX(LEFT_ALIGNMENT);
        back.setAlignmentY(TOP_ALIGNMENT);

        JButton inventory = new JButton("Inventory");
        inventory.setFont(font);
        inventory.setBorderPainted(false);
        inventory.setContentAreaFilled(false);
        inventory.setCursor(new Cursor(Cursor.HAND_CURSOR));
        inventory.setOpaque(false);
        inventory.setForeground(Color.WHITE);
        inventory.setAlignmentX(RIGHT_ALIGNMENT);
        inventory.setAlignmentY(TOP_ALIGNMENT);

        JPanel scorePanel = new JPanel();
        JLabel playerScore = new JLabel("Score: " + score.getScore());
        playerScore.setForeground(Color.WHITE);
        playerScore.setFont(font);
        scorePanel.setOpaque(false);
        scorePanel.add(playerScore);

        player.getScore().addPropertyChangeListener(evt -> {
            if ("score".equals(evt.getPropertyName())) {
                playerScore.setText("Score: " + evt.getNewValue());
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JPanel navRow = new JPanel();

        navRow.setLayout(new BorderLayout());
        navRow.setOpaque(false);
       
        petNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        petNameLabel.setAlignmentY(Component.TOP_ALIGNMENT);
        JPanel titleRow = new JPanel();
        titleRow.setLayout(new BoxLayout(titleRow, BoxLayout.X_AXIS));
        titleRow.setOpaque(false);
        titleRow.add(Box.createHorizontalGlue()); // push label center
        titleRow.add(petNameLabel);
        titleRow.add(Box.createHorizontalGlue()); // keep centered
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        navRow.add(back, BorderLayout.WEST);
        navRow.add(titleRow, BorderLayout.CENTER);
        navRow.add(inventory, BorderLayout.EAST);

        topPanel.add(navRow);
        topPanel.add(Box.createRigidArea(new Dimension(0, -5)));
        topPanel.add(scorePanel, BorderLayout.WEST);

        mainContentPanel.add(topPanel, BorderLayout.NORTH);

        JPanel commandPanel = new JPanel();
        commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.Y_AXIS));
        commandPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,15));
        commandPanel.setOpaque(false);
       // commandPanel.add(Box.createRigidArea(new Dimension(0, -10)));

        JLabel commandLabel = new JLabel("Commands");
        JButton feedButton = new JButton("feed");
        JButton playButton = new JButton("play");
        JButton bedButton = new JButton("go to bed");
        JButton giftButton = new JButton("give gift");
        JButton exerciseButton = new JButton("exercise");
        JButton vetButton = new JButton("visit vet");

       
        feedButton.setFont(font);
        feedButton.setForeground(Color.BLACK);
        playButton.setFont(font);
        playButton.setForeground(Color.BLACK);
        bedButton.setFont(font);
        bedButton.setForeground(Color.BLACK);
        giftButton.setFont(font);
        giftButton.setForeground(Color.BLACK);
        exerciseButton.setFont(font);
        exerciseButton.setForeground(Color.BLACK);
        vetButton.setFont(font);
        vetButton.setForeground(Color.BLACK);
    
        font = font.deriveFont(Font.PLAIN, 35); 

        commandLabel.setFont(font);
        commandLabel.setForeground(Color.WHITE);

        commandPanel.add(commandLabel);
        commandPanel.add(Box.createRigidArea(new Dimension(0, 10))); // 10px space
        commandPanel.add(feedButton);
        commandPanel.add(Box.createRigidArea(new Dimension(0, 10))); // 10px space
        commandPanel.add(playButton);
        commandPanel.add(Box.createRigidArea(new Dimension(0, 10))); // 10px space
        commandPanel.add(bedButton);
        commandPanel.add(Box.createRigidArea(new Dimension(0, 10))); // 10px space
        commandPanel.add(giftButton);
        commandPanel.add(Box.createRigidArea(new Dimension(0, 10))); // 10px space
        commandPanel.add(exerciseButton);
        commandPanel.add(Box.createRigidArea(new Dimension(0, 10))); // 10px space
        commandPanel.add(vetButton);        

        JPanel healthBarPanel = new JPanel();
        healthBarPanel.setLayout(new BorderLayout());
        healthLabel = new JLabel("HEALTH");
        healthLabel.setFont(font);
        healthLabel.setForeground(Color.WHITE);
        font = font.deriveFont(Font.PLAIN, 25); 
        stateLabel = new JLabel("Current State: " + player.getActivePet().getState());
        stateLabel.setFont(font);
        stateLabel.setForeground(Color.WHITE);

        Dimension healthBarSize = new Dimension(600, 30);
        healthBar.setPreferredSize(healthBarSize);
        healthBar.setMaximumSize(healthBarSize);

        healthBarPanel.add(healthLabel, BorderLayout.NORTH);
        healthBarPanel.add(healthBar, BorderLayout.CENTER);

        healthBarPanel.add(stateLabel, BorderLayout.SOUTH);
        healthBarPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 20, 15));
        healthBarPanel.setOpaque(false);

        JPanel petPanel = new JPanel();
        petPanel.add(petImageLabel, BorderLayout.CENTER);
        petPanel.setOpaque(false);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(0,0,50,15));
        leftPanel.add(vitalPanel);
        leftPanel.setOpaque(false);

        mainContentPanel.add(topPanel, BorderLayout.NORTH);
        mainContentPanel.add(leftPanel, BorderLayout.WEST);
        mainContentPanel.add(petPanel, BorderLayout.CENTER);
        mainContentPanel.add(commandPanel, BorderLayout.EAST);
        mainContentPanel.add(healthBarPanel, BorderLayout.SOUTH);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bg = new ImageIcon("temp_assets/Background2.jpg");
                g.drawImage(bg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(mainContentPanel, BorderLayout.CENTER);
        setContentPane(backgroundPanel);
        

        setLocationRelativeTo(null);
        setVisible(true);
            feedButton.addActionListener(e -> {
                List<Item> foodItems = player.getInventory().getFoodItems();

                if (foodItems.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You have no food items!");
                    return;
                }
                Item selectedFood = (Item) JOptionPane.showInputDialog(
                    null,
                    "Select a food item:",
                    "Feed Pet",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    foodItems.toArray(),
                    foodItems.get(0)
                );

                if (selectedFood != null) {
                    player.getActivePet().feed(selectedFood);
                    petSprite.setCurrentState(pet.getState());

                    updateVitalBars(); 
                }
            });

    
            playButton.addActionListener(e -> {
                pet.play();
                petSprite.setCurrentState(pet.getState());
                updateVitalBars();

            });
    
            bedButton.addActionListener(e -> {
                if (pet.getState() != "DEAD") {
                    pet.sleep();
                    petSprite.setCurrentState(pet.getState());
                    stateManager.setPetState("SLEEPING");
                    updateVitalBars();

                }    

            });
    
            giftButton.addActionListener(e -> {
                List<Item> giftItems = player.getInventory().getGiftItems();

                if (giftItems.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You have no gift items!");
                    return;
                }
                Item selectedGift = (Item) JOptionPane.showInputDialog(
                    null,
                    "Select a gift item:",
                    "Give gift",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    giftItems.toArray(),
                    giftItems.get(0)
                );

                if (selectedGift != null) {
                    player.getActivePet().giveGift(selectedGift);
                    petSprite.setCurrentState(pet.getState());

                    updateVitalBars(); 
                }

            });
    
            exerciseButton.addActionListener(e -> {
                petSprite.setCurrentState(pet.getState());

                player.getActivePet().exercise();
                updateVitalBars();

            });
    
            vetButton.addActionListener(e -> {
                petSprite.setCurrentState(pet.getState());

                player.getActivePet().takeToVet();
                updateVitalBars();
               
            });
            back.addActionListener(e -> {
                System.out.println("Back to main menu button clicked");
                SaveGame saveFile = new SaveGame(saveSlot, player.isParent());
                saveFile.savePet(player.getActivePet());

               // IMPLEMENT SAVE LOGIC BEFORE USER GOES BACK TO MAIN MENU
               // saveGame.save(player.getActivePet(), player.getInventory(), null);
                dispose();
                new MainMenu().setVisible(true);
            });
            inventory.addActionListener(e -> {
                System.out.println("Inventory button clicked");
                displayInventory(player.getInventory());

            });
    
    
            updateVitalBars();
        }
        public static void updatePetState(String petState) {
            if (instance != null ) {
                instance.stateLabel.setText("Current State: " + petState);
            }
        }
        
    
        private void createVitalBars() {

            healthBar = new CustomProgressBar();
            sleepBar = new CustomProgressBar();
            happinessBar = new CustomProgressBar();
            hungerBar = new CustomProgressBar();
    
            Dimension progressBarSize = new Dimension(100, 50);
            sleepBar.setPreferredSize(progressBarSize);
            happinessBar.setPreferredSize(progressBarSize);
            hungerBar.setPreferredSize(progressBarSize);

            hungerBar.setForeground(Color.decode("#FF6B00"));
            sleepBar.setForeground(Color.decode("#00C4E7"));
            happinessBar.setForeground(Color.decode("#FFB008"));
            healthBar.setForeground(Color.decode("#5DBC51"));
    
            updateVitalBars();
    
        }
        private JPanel createVitalPanel() {
            JPanel vitalPanel = new JPanel();
            vitalPanel.setLayout(new BoxLayout(vitalPanel, BoxLayout.Y_AXIS)); // Vertical layout
            vitalPanel.setOpaque(false);
            Font font = null;
            try {
                font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Jersey25-Regular.ttf"));
                font = font.deriveFont(Font.PLAIN, 35); // Title text size
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
            // Sleep
            JLabel vitalLabel = new JLabel("Vitals");
            vitalLabel.setFont(font);
            vitalLabel.setForeground(Color.WHITE);
            vitalPanel.add(vitalLabel);
            vitalPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add some space between components

            font = font.deriveFont(Font.PLAIN, 25);

            JPanel sleepPanel = new JPanel();
            sleepPanel.setOpaque(false);
            sleepPanel.setLayout(new BoxLayout(sleepPanel, BoxLayout.Y_AXIS)); // Vertical layout
            sleepLabel = new JLabel("sleep",SwingConstants.CENTER);
            sleepLabel.setFont(font);
            sleepLabel.setForeground(Color.WHITE);
            sleepPanel.add(sleepLabel);
            sleepPanel.add(sleepBar);
            vitalPanel.add(sleepPanel);
            vitalPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some space between components
        
            // Fullness (Hunger)
            JPanel fullnessPanel = new JPanel();
            fullnessPanel.setOpaque(false);
            fullnessPanel.setLayout(new BoxLayout(fullnessPanel, BoxLayout.Y_AXIS)); // Vertical layout
            fullnessLabel = new JLabel("food", SwingConstants.CENTER);
            fullnessLabel.setFont(font);
            fullnessLabel.setForeground(Color.WHITE);
            fullnessPanel.add(fullnessLabel);
            fullnessPanel.add(hungerBar); // Assuming fullness corresponds to hunger
            vitalPanel.add(fullnessPanel);
            vitalPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some space between components
        
            // Happiness
            JPanel happinessPanel = new JPanel();
            happinessPanel.setOpaque(false);
            happinessPanel.setLayout(new BoxLayout(happinessPanel, BoxLayout.Y_AXIS)); // Vertical layout
            happinessLabel = new JLabel("happiness",SwingConstants.CENTER);
            happinessLabel.setFont(font);
            happinessLabel.setForeground(Color.WHITE);
            happinessPanel.add(happinessLabel);
            happinessPanel.add(happinessBar);
            vitalPanel.add(happinessPanel);
            vitalPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some space between components
        
           // vitalPanel.setPreferredSize(new Dimension(200, 400)); // Adjust as needed
           
            return vitalPanel;
        }
        private JLabel createPetTitle() {
            petNameLabel = new JLabel(player.getActivePet().getName(), SwingConstants.CENTER);
            petNameLabel.setForeground(Color.WHITE);
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Jersey25-Regular.ttf"));
            font = font.deriveFont(Font.PLAIN, 75); // Title text size
            petNameLabel.setFont(font);
        
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        petNameLabel.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        return petNameLabel;
    }
    
    
    private void updateVitalBars() {
        healthBar.setValue(pet.getHealth());
        if (pet.getHealth() < 50) {
            healthBar.setForeground(Color.RED);
        } else {
            healthBar.setForeground(Color.decode("#5DBC51"));
        }
        sleepBar.setValue(pet.getSleep());
        happinessBar.setValue(pet.getHappiness());
        hungerBar.setValue(pet.getFullness());

        if (pet.getHealth() <= 0) {
            happinessBar.setForeground(Color.decode("#7F7F7F"));
            sleepBar.setForeground(Color.decode("#7F7F7F"));
            hungerBar.setForeground(Color.decode("#7F7F7F"));

        }


    }
    private void displayInventory(Inventory inventory) {
        JDialog inventoryList = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Inventory", true);
        inventoryList.setSize(350, 400);
        inventoryList.setLocationRelativeTo(this);
    
        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS));
        inventoryPanel.setBackground(new Color(255, 255, 255, 220));
    
        // Iterate over the itemMap instead of using getItems()
        for (Map.Entry<Item, Integer> entry : inventory.itemMap.entrySet()) {
    
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.PAGE_AXIS));
            card.setBackground(new Color(255, 255, 255, 80));
            card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            card.setMaximumSize(new Dimension(300, 300));
            card.setAlignmentX(Component.CENTER_ALIGNMENT);
    
            JLabel itemName = new JLabel(entry.getKey().getName());
            JLabel quantity = new JLabel("x" + entry.getValue());
    
            itemName.setForeground(Color.BLACK);
            // You can add an icon related to the item here if needed
            card.add(itemName);
            card.add(quantity);
            inventoryPanel.add(card);
        }
    
        JScrollPane scrollPane = new JScrollPane(inventoryPanel);
    
        inventoryList.add(scrollPane);
        inventoryList.setVisible(true);
    }
    


    // Run for testing with a predefined pet
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Example usage
        Pet pet = new Sterling("Sterling");
        Inventory inventory = new Inventory();
        Player player = new Player(null, inventory, false, pet);

        SwingUtilities.invokeLater(() -> new PlayGameGUI(player, 1, "name"));
    }
    @Override
    public void onStateChange(String newState) {
        stateLabel.setText("Current State: " + newState);
        updateVitalBars();
    }

    public class CustomProgressBar extends JProgressBar {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            String text = getValue() + "%";
            int width = getWidth();
            int height = getHeight();

            Font font= null;

            try {
                font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Jersey25-Regular.ttf"));
                font = font.deriveFont(Font.PLAIN, 20);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
            g.setColor(Color.BLACK);
            g.setFont(font);

            FontMetrics fm = g.getFontMetrics();
            int x = (width - fm.stringWidth(text)) / 2;
            int y = (height + fm.getHeight()) / 2 - 2;

            g.drawString(text, x, y);
        }
    }

    @Override
    public void onStatWarning(String stat, boolean isWarning) {
        if (stat.equals("HEALTH") && isWarning && !warningShown) {
            JOptionPane.showMessageDialog(this, "Warning: Your pet's health is below 50! This can lower your score.", "Health Warning", JOptionPane.WARNING_MESSAGE);
            warningShown = true;
        } else if (stat.equals("SLEEP") && isWarning && !warningShown) {
            JOptionPane.showMessageDialog(this, "Warning: Your pet is very sleepy!", "Sleep Warning", JOptionPane.WARNING_MESSAGE);
            warningShown = true;

        } else if (stat.equals("FULLNESS") && isWarning &&!warningShown) {
            JOptionPane.showMessageDialog(this, "Warning: Your pet is hungry!", "Hunger Warning", JOptionPane.WARNING_MESSAGE);
            warningShown = true;

        } else if (stat.equals("HAPPINESS") && isWarning && !warningShown) {
            JOptionPane.showMessageDialog(this, "Warning: Your pet is sad!", "Happiness Warning", JOptionPane.WARNING_MESSAGE);
            warningShown = true;

        }
    }
    private void animate(Sprite sprite, JLabel petImageLabel) {
        // Get the current frame image from the sprite object
        String imagePath = sprite.getFrame();
        
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            // Load the image and scale it
            ImageIcon petIcon = new ImageIcon(imagePath);
            Image image = petIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            petImageLabel.setIcon(new ImageIcon(image));  // Update the JLabel with the new pet image
        } else {
            petImageLabel.setText("Image not found");
            String defaultImagePath = sprite.getName() + ".png";  // Replace with the default image path
            ImageIcon defaultIcon = new ImageIcon(defaultImagePath);
            Image defaultImage = defaultIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            petImageLabel.setIcon(new ImageIcon(defaultImage));
            sprite.resetAnimation();

        }
    
        // Move to the next frame for animation
        sprite.nextFrame();
    }
    private void startAnimation(Sprite sprite, JLabel petImageLabel) {
        animationTimer = new Timer(150, e -> animate(sprite, petImageLabel));  // 150ms delay between frame changes
        animationTimer.start();  // Start the animation loop
    }

    private void stopAnimation() {
        if (animationTimer != null) {
            animationTimer.stop();
        }
    }

    private void updatePetSprite() {
        // Update the pet's image immediately when state changes
        // Force a state change after 2 seconds for testing


        String imagePath = petSprite.getFrame();
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            // Load the image and scale it
            ImageIcon petIcon = new ImageIcon(imagePath);
            Image image = petIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            petImageLabel.setIcon(new ImageIcon(image));
        }
    }
}

