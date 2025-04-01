/**
 * PlayGameGUI.java: The main game screen where the player can take care of their virtual pet. Includes pet vitals,
 * sprites, commands, score and inventory.
 * 
 * @author Daniella
 * @version 1.0
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class PlayGameGUI extends JFrame implements StateManager.StateChangeListener {
    private static PlayGameGUI instance;
    private Player player;
    private Parent parent;
    private Pet pet;
    private JLabel healthLabel, sleepLabel, fullnessLabel, happinessLabel;
    private JLabel petImageLabel = new JLabel();
    private CustomProgressBar healthBar, sleepBar, happinessBar, hungerBar;
    private JLabel petNameLabel;
    private JLabel stateLabel;
    private SaveGame saveGame;
    private String playerName;
    private static int saveSlot;
    private Sprite petSprite;
    private Timer animationTimer;
    private boolean warningShown = false;
    private Score score;
    private long startTime;
    private static Font customFont;
    static {
        try {
            // Load the font from the 'fonts' folder only once when the class is loaded
            InputStream is = MainMenu.class.getResourceAsStream("/fonts/Jersey25-Regular.ttf");

            if (is == null) {
                throw new IOException("Font file not found!");
            } else {
                System.out.println("font file loaded");
            }

            // Create the font from the InputStream
            customFont = Font.createFont(Font.TRUETYPE_FONT, is);

            // Register the font globally for the application
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Constructor that takes the player, save slot and player name as arguments. Initializes all
     * fields and begins the game loop from GameLoop.java. Creates command, inventory and back buttons
     * calling their respective methods. Starts animation for pet sprite and updates it accordingly.
     * @param player
     * @param saveSlot
     * @param playerName
     * @see GameLoop
     * @see GameLoop#start()
     * @see PlayGameGUI#updatePetSprite()
     */
    
    public PlayGameGUI(Player player, int saveSlot, String playerName) {
        instance = this;
        this.saveSlot = saveSlot;
        this.playerName = playerName;
        this.player = player;
        this.saveGame = new SaveGame(saveSlot, false);
        this.saveGame.setSavedName(playerName);
        this.pet = player.getActivePet();
        this.score = player.getScore();
        Pet.setActivePlayer(player);
        this.player.getInventory().displayInventory();
        this.saveGame.save(this.pet, this.player.getInventory());
        startTime = System.currentTimeMillis();
        parent = ParentAccountManager.loadParentAccount();
        
        int[] decay = {1,2,3};
        StateManager stateManager = new StateManager(player.getActivePet(), decay);
        stateManager.addStateChangeListener(this); 
        
        
        GameLoop gameLoop = new GameLoop(pet, player, stateManager, saveGame);
        gameLoop.start();

        JPanel mainContentPanel = new JPanel(new BorderLayout());
        mainContentPanel.setOpaque(false);

        stateLabel = new JLabel("Current State: NORMAL");
        
        if (pet instanceof Pet) {
            petSprite = new Sprite(pet.getName(), pet.getHealth(), pet.getSleep(), pet.getHappiness(), 
                                          pet.getFullness(), pet.isAlive(), pet.getState());
            // Now you can use the new petSprite object
        } else {
            // Handle if pet is not a valid Pet type (this shouldn't happen if Pet is the superclass)
            System.out.println("The pet is not valid.");
        }
        

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

        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                ImageIcon backgroundPic = new ImageIcon(getClass().getResource("/temp_assets/Background1.jpg"));
                
                if (backgroundPic.getImage() != null) {
                    g.drawImage(backgroundPic.getImage(), 0, 0, getWidth(), getHeight(), this);
                } else {
                    System.out.println("Background image not found!");
                }
            }
        };
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

        petNameLabel = createPetTitle();
        createVitalBars();
        JPanel vitalPanel = createVitalPanel();


            customFont = customFont.deriveFont(Font.PLAIN, 25);
        
        JButton back = new JButton("< Main Menu");
        back.setFont(customFont);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setOpaque(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.setForeground(Color.WHITE);
        back.setAlignmentX(LEFT_ALIGNMENT);
        back.setAlignmentY(TOP_ALIGNMENT);

        JButton inventory = new JButton("Inventory");
        inventory.setFont(customFont);
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
        playerScore.setFont(customFont);
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

       
        feedButton.setFont(customFont);
        feedButton.setForeground(Color.BLACK);
        playButton.setFont(customFont);
        playButton.setForeground(Color.BLACK);
        bedButton.setFont(customFont);
        bedButton.setForeground(Color.BLACK);
        giftButton.setFont(customFont);
        giftButton.setForeground(Color.BLACK);
        exerciseButton.setFont(customFont);
        exerciseButton.setForeground(Color.BLACK);
        vetButton.setFont(customFont);
        vetButton.setForeground(Color.BLACK);
    
        customFont = customFont.deriveFont(Font.PLAIN, 35); 

        commandLabel.setFont(customFont);
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
        healthLabel.setFont(customFont);
        healthLabel.setForeground(Color.WHITE);
        customFont = customFont.deriveFont(Font.PLAIN, 25); 
        stateLabel = new JLabel("Current State: " + player.getActivePet().getState());
        stateLabel.setFont(customFont);
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
                
                // Load the image using getResource
                ImageIcon backgroundPic = new ImageIcon(getClass().getResource("/temp_assets/Background1.jpg"));
                
                if (backgroundPic.getImage() != null) {
                    g.drawImage(backgroundPic.getImage(), 0, 0, getWidth(), getHeight(), this);
                } else {
                    System.out.println("Background image not found!");
                }
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(mainContentPanel, BorderLayout.CENTER);
        setContentPane(backgroundPanel);
        

        setLocationRelativeTo(null);
        setVisible(true);
            feedButton.addActionListener(e -> {
                if (pet.getState() != "DEAD") {
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
                    this.saveGame.save(this.pet, this.player.getInventory());


                    updateVitalBars(); 
                }

                }
                
            });

            playButton.addActionListener(e -> {
                pet.play();
                updateVitalBars();

            });
    
            bedButton.addActionListener(e -> {
                if (pet.getState() == "HUNGRY") {
                    JOptionPane.showMessageDialog(this, "Warning: Your pet is very hungry and won't sleep!", "Can't go to bed", JOptionPane.WARNING_MESSAGE);
                } else if (pet.getState() != "DEAD") {
                    pet.sleep();
                    stateManager.setPetState("SLEEPING");
                    updateVitalBars();
                }
            });
    
            giftButton.addActionListener(e -> {
                if (pet.getState() != "DEAD") {
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
                        pet.giveGift(selectedGift);
                        this.saveGame.save(this.pet, this.player.getInventory());


                        updateVitalBars(); 
                    }
                }
            });
    
            exerciseButton.addActionListener(e -> {
                player.getActivePet().exercise();
                updateVitalBars();

            });
    
            vetButton.addActionListener(e -> {
                if (player.getScore().getScore() < 300) {
                    JOptionPane.showMessageDialog(null, "You must have a score of 300 to take your pet to the vet!");

                } else {
                    player.getActivePet().takeToVet();
                    updateVitalBars();
                }
               
            });
            back.addActionListener(e -> {
                System.out.println("Back to main menu button clicked");
                SaveGame saveFile = new SaveGame(saveSlot, player.isParent());
                saveFile.savePet(player.getActivePet());
                stopTimerAndRecordPlayTime();

                dispose();
                new MainMenu().setVisible(true);
            });
            inventory.addActionListener(e -> {
                System.out.println("Inventory button clicked");
                displayInventory(player.getInventory());

            });
            if (parent.getTotalPlayTime() > parent.getControls().getMaxAllowedMinutes()) {
                JOptionPane.showMessageDialog(null, "Play time limit exceeded!");
                dispose();
                MainMenu menu = new MainMenu();
                menu.setVisible(true);
            }
            if (!parent.getControls().canPlayNow()) {
                JOptionPane.showMessageDialog(null, "Gameplay not allowed at this time.");
                dispose();
            }            
    
            updateVitalBars();
        }
        private void stopTimerAndRecordPlayTime() {
            long endTime = System.currentTimeMillis();
            float minutesPlayed = (endTime - startTime) / (1000 * 60.0f);
            parent.addPlayTime(minutesPlayed);
            parent.incrementSessionCount();
            ParentAccountManager.saveParentAccount(parent);
        }
        
        public static void updatePetState(String petState) {
            if (instance != null ) {
                instance.stateLabel.setText("Current State: " + petState);
            }
        }
        /**
         * Creates progress bars for a pet's vitals. Calls the updateVitalBars() methods to set values
         * for each bar.
         * @see PlayGameGUI#updateVitalBars()
         */
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
        /**
         * Creates panel for the vital bars to be displayed evenly with their respective labels using a 
         * box layout.
         * @return
         */
        private JPanel createVitalPanel() {
            JPanel vitalPanel = new JPanel();
            vitalPanel.setLayout(new BoxLayout(vitalPanel, BoxLayout.Y_AXIS)); // Vertical layout
            vitalPanel.setOpaque(false);
                customFont = customFont.deriveFont(Font.PLAIN, 35); 
            
            JLabel vitalLabel = new JLabel("Vitals");
            vitalLabel.setFont(customFont);
            vitalLabel.setForeground(Color.WHITE);
            vitalPanel.add(vitalLabel);
            vitalPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add some space between components

            customFont = customFont.deriveFont(Font.PLAIN, 25);

            JPanel sleepPanel = new JPanel();
            sleepPanel.setOpaque(false);
            sleepPanel.setLayout(new BoxLayout(sleepPanel, BoxLayout.Y_AXIS));
            sleepLabel = new JLabel("sleep",SwingConstants.CENTER);
            sleepLabel.setFont(customFont);
            sleepLabel.setForeground(Color.WHITE);
            sleepPanel.add(sleepLabel);
            sleepPanel.add(sleepBar);
            vitalPanel.add(sleepPanel);
            vitalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
            JPanel fullnessPanel = new JPanel();
            fullnessPanel.setOpaque(false);
            fullnessPanel.setLayout(new BoxLayout(fullnessPanel, BoxLayout.Y_AXIS));
            fullnessLabel = new JLabel("food", SwingConstants.CENTER);
            fullnessLabel.setFont(customFont);
            fullnessLabel.setForeground(Color.WHITE);
            fullnessPanel.add(fullnessLabel);
            fullnessPanel.add(hungerBar);
            vitalPanel.add(fullnessPanel);
            vitalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
            JPanel happinessPanel = new JPanel();
            happinessPanel.setOpaque(false);
            happinessPanel.setLayout(new BoxLayout(happinessPanel, BoxLayout.Y_AXIS));
            happinessLabel = new JLabel("happiness",SwingConstants.CENTER);
            happinessLabel.setFont(customFont);
            happinessLabel.setForeground(Color.WHITE);
            happinessPanel.add(happinessLabel);
            happinessPanel.add(happinessBar);
            vitalPanel.add(happinessPanel);
            vitalPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
                   
            return vitalPanel;
        }
        private JLabel createPetTitle() {
            petNameLabel = new JLabel(player.getActivePet().getCustomName(), SwingConstants.CENTER);
            petNameLabel.setForeground(Color.WHITE);
            customFont = customFont.deriveFont(Font.PLAIN, 75); // Title text size
            petNameLabel.setFont(customFont);
        
        petNameLabel.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        return petNameLabel;
    }
    
    /**
     * Method for updating the value/fullness of a pet's vital bars. Used continuously with onStateChange
     * and called whenever a player performs a command.
     * @see onStateChange
     */
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
    /**
     * Method for displaying a player's saved inventory for that particular pet. Creates an inventory list
     * using the inventory argument and makes a card for each item (name + quantity).
     * @param inventory
     */
    private void displayInventory(Inventory inventory) {
        JDialog inventoryList = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Inventory", true);
        inventoryList.setSize(350, 400);
        inventoryList.setLocationRelativeTo(this);
    
        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS));
        inventoryPanel.setBackground(new Color(255, 255, 255, 220));
    
        for (Map.Entry<Item, Integer> entry : inventory.itemMap.entrySet()) {
    
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.PAGE_AXIS));
            card.setBackground(new Color(255, 255, 255, 80));
            card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            card.setMaximumSize(new Dimension(300, 300));
            card.setAlignmentX(Component.CENTER_ALIGNMENT);
    
            JLabel itemName = new JLabel(entry.getKey().getName());
            JLabel quantity = new JLabel("x" + entry.getValue());


           
                customFont = customFont.deriveFont(Font.PLAIN, 20);
           
            itemName.setFont(customFont);
            quantity.setFont(customFont);
            quantity.setForeground(Color.decode("#4E337B"));
    
            itemName.setForeground(Color.BLACK);
            card.add(itemName);
            card.add(quantity);
            inventoryPanel.add(card);
        }
    
        JScrollPane scrollPane = new JScrollPane(inventoryPanel);
    
        inventoryList.add(scrollPane);
        inventoryList.setVisible(true);
    }
    
    /**
     * Main method used to run an example usage of a pet and performs invokeLater() method from 
     * SwingUtilities. 
     * @see Pet
     * @see Inventory
     * @see Player
     * @param args
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Pet pet = new Pet(null, 0, 0, 0, 0, false, null);
        Inventory inventory = new Inventory();
        Player player = new Player(null, inventory, false, pet);

        SwingUtilities.invokeLater(() -> new PlayGameGUI(player, saveSlot, player.getName()));
    }
    /**
     * Method to set a pet's current state text, sprite, and update vital bars based on the pet's current
     * state and new state.
     * @Override
     * @param newState
     */
    public void onStateChange(String newState) {
        stateLabel.setText("Current State: " + newState);
        petSprite.setCurrentState(pet.getState());
        System.out.println(pet.getState());
        updateVitalBars();
    }
    /**
     * Method to create progress bars with custom aesthetics.
     */
    public class CustomProgressBar extends JProgressBar {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            String text = getValue() + "%";
            int width = getWidth();
            int height = getHeight();


            
                customFont = customFont.deriveFont(Font.PLAIN, 20);
            
            g.setColor(Color.BLACK);
            g.setFont(customFont);

            FontMetrics fm = g.getFontMetrics();
            int x = (width - fm.stringWidth(text)) / 2;
            int y = (height + fm.getHeight()) / 2 - 2;

            g.drawString(text, x, y);
        }
    }
    /**
     * Method to display a warning message to the player when a vital has a warning attached to it.
     * @Override
     * @param stat
     * @param isWarning
     * 
     */
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
    /**
     * Method to animate a pet using the Sprite class, and sets the size of the pet sprite.
     * @param sprite
     * @param petImageLabel
     * @see Sprite
     * @see Sprite#getFrame()
     * @see Sprite#nextFrame()
     * @see Sprite#resetAnimation()
     */
    private void animate(Sprite sprite, JLabel petImageLabel) {
        String imagePath = sprite.getFrame();
        ImageIcon petIcon = null;
    
        // Use getResource to load the image from the resources folder or classpath
        try {
            petIcon = new ImageIcon(getClass().getResource("/" + imagePath));
            Image image = petIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            petImageLabel.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            petImageLabel.setText("Image not found");
            String defaultImagePath = sprite.getName() + ".png";
            try {
                petIcon = new ImageIcon(getClass().getResource("/" + defaultImagePath));
                Image defaultImage = petIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                petImageLabel.setIcon(new ImageIcon(defaultImage));
            } catch (Exception ex) {
                petImageLabel.setText("Default image not found");
            }
            sprite.resetAnimation();
        }
    
        sprite.nextFrame();
    }
    
    private void startAnimation(Sprite sprite, JLabel petImageLabel) {
        animationTimer = new Timer(150, e -> animate(sprite, petImageLabel)); 
        animationTimer.start();  
    }
    
    private void updatePetSprite() {
        String imagePath = petSprite.getFrame();
        try {
            ImageIcon petIcon = new ImageIcon(getClass().getResource("/" + imagePath));
            Image image = petIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            petImageLabel.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            petImageLabel.setText("Image not found");
        }
    }
    
}

