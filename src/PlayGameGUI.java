import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;


public class PlayGameGUI extends JFrame {
    private static PlayGameGUI instance;
    private Player player;
    private JLabel healthLabel, sleepLabel, fullnessLabel, happinessLabel, petImageLabel;
    private JProgressBar healthBar, sleepBar, happinessBar, hungerBar;
    private JLabel petNameLabel;
    private JLabel stateLabel;
    private SaveGame saveGame;
    private String playerName;
    private int saveSlot;
    
    
    public PlayGameGUI(Player player, int saveSlot, String playerName) {
        int[] decay = {1,2,3};
        StateManager stateManager = new StateManager(player.getActivePet(), decay);
        
        instance = this;
        this.saveSlot = saveSlot;
        this.playerName = playerName;
        this.player = player;
        this.saveGame = new SaveGame(saveSlot, false);
        this.saveGame.setSavedName(playerName);
        JPanel mainContentPanel = new JPanel(new BorderLayout());
        mainContentPanel.setOpaque(false);

        stateLabel = new JLabel("Current State: NORMAL");

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
        loadPetImage();
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

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JPanel navRow = new JPanel();

        navRow.setLayout(new BorderLayout());
        navRow.setOpaque(false);
        navRow.add(back, BorderLayout.WEST);

        petNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel titleRow = new JPanel();
        titleRow.setLayout(new BoxLayout(titleRow, BoxLayout.X_AXIS));

        titleRow.setOpaque(false);
        titleRow.add(Box.createHorizontalGlue()); // push label center
        titleRow.add(petNameLabel);
        titleRow.add(Box.createHorizontalGlue()); // keep centered
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        topPanel.add(navRow);
        topPanel.add(titleRow);

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
    
            // Add action listeners
            feedButton.addActionListener(e -> {
                player.feed(null); // Replace with actual selection logic
                updateVitalBars();

            });
    
            playButton.addActionListener(e -> {
                player.play();
                updateVitalBars();

            });
    
            bedButton.addActionListener(e -> {
                player.goToBed();
                player.getActivePet().setState("SLEEPING");
                updateVitalBars();

            });
    
            giftButton.addActionListener(e -> {
                player.giveGift(null);
                stateLabel.setText("Current State: " + player.getActivePet().getState());
                updateVitalBars();

            });
    
            exerciseButton.addActionListener(e -> {
                player.exercise();
                updateVitalBars();

            });
    
            vetButton.addActionListener(e -> {
                player.visitVet();
                updateVitalBars();
               
            });
            back.addActionListener(e -> {
                System.out.println("Back to main menu button clicked");
               // IMPLEMENT SAVE LOGIC BEFORE USER GOES BACK TO MAIN MENU
               // saveGame.save(player.getActivePet(), player.getInventory(), null);
                dispose();
                new MainMenu().setVisible(true);
            });
            
    
    
            updateVitalBars();
        }
        public static void updatePetState(String petState) {
            if (instance != null ) {
                instance.stateLabel.setText("Current State: " + petState);
            }
        }
    
        private void loadPetImage() {
            String petName = player.getActivePet().getName();
            String path = "temp_assets/" + petName + ".png";
            ImageIcon petIcon = new ImageIcon(path);
            Image image = petIcon.getImage();
            Image resized = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            petIcon = new ImageIcon(resized);
            petImageLabel = new JLabel(petIcon);
            petImageLabel.setHorizontalAlignment(JLabel.CENTER);
    
        }
    
        private void createVitalBars() {
            healthBar = new JProgressBar(0, 100);
            sleepBar = new JProgressBar(0, 100);
            happinessBar = new JProgressBar(0, 100);
            hungerBar = new JProgressBar(0, 100);
    
            Dimension progressBarSize = new Dimension(100, 50);
            sleepBar.setPreferredSize(progressBarSize);
            happinessBar.setPreferredSize(progressBarSize);
            hungerBar.setPreferredSize(progressBarSize);

            hungerBar.setStringPainted(true);
            sleepBar.setStringPainted(true);
            happinessBar.setStringPainted(true);
            healthBar.setStringPainted(true);

            hungerBar.setForeground(Color.ORANGE);
            sleepBar.setForeground(Color.CYAN);
            happinessBar.setForeground(Color.YELLOW);
            healthBar.setForeground(Color.GREEN);
    
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
        Pet activePet = player.getActivePet();
        healthBar.setValue(activePet.getHealth());
        sleepBar.setValue(activePet.getSleep());
        happinessBar.setValue(activePet.getHappiness());
        hungerBar.setValue(activePet.getHunger());

    }

    // Run for testing with a predefined pet
    public static void main(String[] args) {
        // Example usage
        Pet pet = new Pet("Foxy", 100, 100, 100, 100, true, 100, "Normal");
        Inventory inventory = new Inventory();
        Player player = new Player(null, inventory, false, pet);

        SwingUtilities.invokeLater(() -> new PlayGameGUI(player, 1, "name"));
    }
    
}

