/**
 * MainMenu.java: The main entry point to the Petiverse game. Allows the user to start a new game, load a saved game, view instructions for the
 * game, access parental controls or exit the game.
 * 
 * @author Daniella
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class MainMenu extends JFrame {
    public static MainMenu menu;
    private JPanel mainMenuPanel;

    /**
     * Constructs a MainMenu object. Contains start new game, load saved games, tutorial, parental controls,
     * sound and exit buttons which call their respective methods.
     */

    public MainMenu() { 

        setTitle("Petiverse");
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS)); // Sets an organized layout for the components on screen

        JLabel title = new JLabel("Petiverse");

        String buttonClick = "temp_assets/button-click.wav";

        JButton startButton = new JButton("  Start new game  ");
        JButton loadButton = new JButton("  Load saved game  ");
        JButton instructionsButton = new JButton("  Tutorial & Instructions  ");
        JButton parentButton = new JButton("  Parental Controls  ");
        JButton soundButton = new JButton("  Sound: OFF  ");
        JButton exitButton = new JButton("  Exit  ");

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start button clicked");
                Sound.playEffect(buttonClick); 

                Pet foxy = new Foxy("Foxy");
                Pet roscoe = new Roscoe("Roscoe");
                Pet sterling = new Sterling("Sterling");
                new NewGameGUI(foxy, roscoe, sterling);
                dispose();
            }
        });   
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Load button clicked");
                Sound.playEffect(buttonClick);
                loadGame();
            }
        });
        instructionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Instructions/tutorial button clicked");
                Sound.playEffect(buttonClick);
                displayInstructions();
            }
        });
        parentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Parental controls button clicked");
                Sound.playEffect(buttonClick);
                parentalControls();
            }
        });
        soundButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Sound.toggleSound();
                soundButton.setText(Sound.isSoundEnabled() ? "  Sound: ON  " : "  Sound: OFF  ");
            }
        });
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exit button clicked");
                Sound.playEffect(buttonClick);
                exit();
            }
        });
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loadButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        instructionsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        parentButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        soundButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Jersey25-Regular.ttf"));
            font = font.deriveFont(Font.PLAIN, 100);

            title.setFont(font);
            title.setForeground(Color.decode("#FFFFFF"));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);

            font = font.deriveFont(Font.PLAIN, 25);

            startButton.setFont(font);
            startButton.setForeground(Color.decode("#6C5297"));
            startButton.setBackground(Color.decode("#D9D9D9"));

            loadButton.setFont(font);
            loadButton.setForeground(Color.decode("#6C5297"));
            loadButton.setBackground(Color.decode("#D9D9D9"));

            instructionsButton.setFont(font);
            instructionsButton.setForeground(Color.decode("#6C5297"));
            instructionsButton.setBackground(Color.decode("#D9D9D9"));

            parentButton.setFont(font);
            parentButton.setForeground(Color.decode("#6C5297"));
            parentButton.setBackground(Color.decode("#D9D9D9"));

            soundButton.setFont(font);
            soundButton.setForeground(Color.decode("#6C5297"));
            soundButton.setBackground(Color.decode("#D9D9D9"));

            exitButton.setFont(font);
            exitButton.setForeground(Color.decode("#D9D9D9"));
            exitButton.setBackground(Color.decode("#6C5297"));
            
          
            Border buttonBorder = BorderFactory.createLineBorder(Color.decode("#8B73B2"), 3);
            startButton.setBorder(buttonBorder);
            loadButton.setBorder(buttonBorder);
            instructionsButton.setBorder(buttonBorder);
            parentButton.setBorder(buttonBorder);
            soundButton.setBorder(buttonBorder);
            exitButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

            startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            instructionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            parentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            soundButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel background = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    ImageIcon backgroundPic = new ImageIcon("temp_assets/Background1.jpg");
                    g.drawImage(backgroundPic.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            };
            background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

            background.add(Box.createVerticalStrut(20)); 
            background.add(Box.createVerticalStrut(80)); 
            background.add(title);
            background.add(Box.createVerticalStrut(10)); 
            background.add(startButton);
            background.add(Box.createVerticalStrut(20)); 
            background.add(loadButton);
            background.add(Box.createVerticalStrut(20)); 
            background.add(instructionsButton);
            background.add(Box.createVerticalStrut(20)); 
            background.add(parentButton);
            background.add(Box.createVerticalStrut(20));
            background.add(soundButton);
            background.add(Box.createVerticalStrut(20)); 
            background.add(exitButton);

            mainMenuPanel = background;
            setContentPane(mainMenuPanel);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Main method: starts game and sets up main menu GUI when program is run.
     * @param args
     */
    public static void main(String args[]) {
        menu = new MainMenu();
        menu.setVisible(true);

    }
    /**
     * Displays the instructions/tutorial window containing information about vitals, commands, score,
     * inventory, pet health and pet state.
     */

    public void displayInstructions() { // Show instructions/tutorial window
        JPanel instructions = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("temp_assets/Background2.jpg");
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        instructions.setLayout(new BoxLayout(instructions, BoxLayout.Y_AXIS));
        instructions.setOpaque(false);
        JLabel title = new JLabel("Instructions/Tutorial");

        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Jersey25-Regular.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            font = font.deriveFont(Font.PLAIN, 70); // Title text size

            title.setFont(font);
            title.setForeground(Color.decode("#FFFFFF"));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);

            font = font.deriveFont(Font.PLAIN, 25);

            // Create a back button so the user can navigate back to the main menu
            JButton back = new JButton("< Main Menu");
            back.setFont(font);
            back.setContentAreaFilled(false);
            back.setBorderPainted(false);
            back.setFocusPainted(false);
            back.setOpaque(false);
            back.setCursor(new Cursor(Cursor.HAND_CURSOR));
            back.setForeground(Color.decode("#FFFFFF"));
            back.setAlignmentX(LEFT_ALIGNMENT);
            back.setAlignmentY(TOP_ALIGNMENT);

            // Move the Main Menu button to a fixed upper left space
            JPanel topPanel = new JPanel(new BorderLayout());
            topPanel.setOpaque(false);
            topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0)); // Padding
            topPanel.add(back, BorderLayout.WEST);
            instructions.add(topPanel, BorderLayout.NORTH);

            instructions.add(title);
            // Add an action listener for the back button
            back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Back to main menu button clicked");
                  //  menu.setContentPane(mainMenuPanel); // Go back to main menu panel
                    MainMenu newMenu = new MainMenu();
                    dispose();
                    newMenu.setVisible(true);
                   ;
                }
            });

            JTextPane instructionsTextPane = new JTextPane();
            instructionsTextPane.setEditable(false);
            instructionsTextPane.setOpaque(false);
            instructionsTextPane.setContentType("text/html");
            instructionsTextPane.setFont(font);

            StyledDocument doc = instructionsTextPane.getStyledDocument();

            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);

            SimpleAttributeSet instructionTitle = new SimpleAttributeSet();
            StyleConstants.setBold(instructionTitle, true);
            StyleConstants.setForeground(instructionTitle, Color.decode("#8B73B2"));
            StyleConstants.setFontFamily(instructionTitle, font.getFontName());

            SimpleAttributeSet normalStyle = new SimpleAttributeSet();
            StyleConstants.setFontSize(normalStyle, 20);
            StyleConstants.setForeground(normalStyle, Color.BLACK);
            StyleConstants.setFontFamily(normalStyle, font.getFontName());

            try {
                doc.insertString(doc.getLength(), "- VITALS -\n", instructionTitle);
                doc.insertString(doc.getLength(), "Your pet's vitals show how full, rested, and happy they are.\n", normalStyle);
                doc.insertString(doc.getLength(), "Vital bars go down over time. Taking care of your pet will keep them full.\n\n", normalStyle);

                doc.insertString(doc.getLength(), "- COMMANDS -\n", instructionTitle);
                doc.insertString(doc.getLength(), "• Feed  - Give your pet food.\n", normalStyle);
                doc.insertString(doc.getLength(), "• Go to bed   - Rest your pet to restore energy.\n", normalStyle);
                doc.insertString(doc.getLength(), "• Give gift   - Make your pet happy with a gift.\n", normalStyle);
                doc.insertString(doc.getLength(), "• Take to vet   - Heal your pet when sick.\n", normalStyle);
                doc.insertString(doc.getLength(), "• Play   - Interact with your pet for joy.\n", normalStyle);
                doc.insertString(doc.getLength(), "• Exercise - Keep your pet fit and active.\n\n", normalStyle);

                doc.insertString(doc.getLength(), "- HEALTH -\n", instructionTitle);
                doc.insertString(doc.getLength(), "The health bar shows your pet's overall wellbeing. When a vital is at zero or your pet is hungry, health will start to go down.\nYou must take your pet to the vet to fill its health up again, which requires a score of at least 300.\nWhen health is at zero, you can no longer take care of your pet.\n\n", normalStyle);

                doc.insertString(doc.getLength(), "- SCORE -\n", instructionTitle);
                doc.insertString(doc.getLength(), "As you take care of your pet, you will earn points.\nAs you earn points, you will earn food and gifts.\n\n", normalStyle);

                doc.insertString(doc.getLength(), "- PET STATE -\n", instructionTitle);
                doc.insertString(doc.getLength(), "Your pet can be in one of four possible states:\n> Normal <\nall commands available\n> Hungry <\nsleep command unavailable\n> Sleeping <\nno commands available\n> Dead <\nno commands available\n> Angry <\nsome commands available, depending on state priority\n", normalStyle);

                doc.insertString(doc.getLength(), "- INVENTORY -\n", instructionTitle);
                doc.insertString(doc.getLength(), "Your inventory contains the food and gifts you currently have for your pet. Each new game begins with a set of items; use them wisely.\nThe longer you play, the more items you earn.\n\n", normalStyle);

            } catch (BadLocationException e) {
                e.printStackTrace();
            }

            JScrollPane scrollPane = new JScrollPane(instructionsTextPane);
            scrollPane.setPreferredSize(new Dimension(300,600));
            scrollPane.setOpaque(false);
            instructionsTextPane.setCaretPosition(0);

            RoundedPanel scrollContainer = new RoundedPanel(new Color(0, 0, 0, 150), 25); // Semi-transparent black
            scrollContainer.setLayout(new BorderLayout());
            scrollContainer.add(scrollPane, BorderLayout.CENTER);
            scrollContainer.setMaximumSize(new Dimension(600, 300));
            scrollContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
            scrollContainer.setOpaque(false);

            instructions.add(scrollContainer);


        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        setContentPane(instructions);
        revalidate();
        repaint();

    }
    /**
     * Displays the parental controls window, where the user can either create a global parent account
     * by setting up a password or log in to the existing global parent account with the previously
     * set password.
     * @see createParentAccount
     * @see loginToParentalControls
     */

    public void parentalControls() {
        JFrame parentalFrame = new JFrame("Parental Controls");
        parentalFrame.setSize(700, 600);
        parentalFrame.setLocationRelativeTo(null);
        parentalFrame.setLayout(new BorderLayout());
        Font font = null;
            try {
                font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Jersey25-Regular.ttf"));
                font = font.deriveFont(Font.PLAIN, 35);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
    
        JLabel header = new JLabel("Parental Controls", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        parentalFrame.add(header, BorderLayout.NORTH);
    
        JButton createAccountBtn = new JButton("Make a Parent Account");
        JButton loginBtn = new JButton("Log In");
    
        createAccountBtn.addActionListener(e -> createParentAccount());
        loginBtn.addActionListener(e -> loginToParentalControls());
    
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(createAccountBtn);
        buttonPanel.add(loginBtn);
    
        parentalFrame.add(buttonPanel, BorderLayout.CENTER);
    
        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> parentalFrame.dispose());
        parentalFrame.add(closeBtn, BorderLayout.SOUTH);
    
        parentalFrame.setVisible(true);
    }
    /**
     * Method for when the user has not set up a parent account and would like to create one. Utilizes
     * the ParentAccountManager class to check if a parent account already exists and save a newly
     * created parent account.
     * @see ParentAccountManager
     * @see Parent
     */
    
    private void createParentAccount() {
        if (ParentAccountManager.parentAccountExists()) {
            JOptionPane.showMessageDialog(null, "Parent account already exists. Please log in.");
            return;
        }
    
        String password = JOptionPane.showInputDialog("Set Parent Password:");
        if (password != null && !password.isEmpty()) {
            Parent parent = new Parent("Parent", new Inventory(), true, null, password);
            ParentAccountManager.saveParentAccount(parent);
            JOptionPane.showMessageDialog(null, "Parent account created successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Password cannot be empty.");
        }
    }
    /**
     * Method for logging in to an existing global parent account. Creates a Parent object by loading the
     * existing account from ParentAccountManager. Authenticates log in using the Parent's authenticate()
     * method.
     * @see ParentAccountManager
     * @see Parent
     * @see Parent#authenticate(String)
     */
    
    private void loginToParentalControls() {
        Parent parent = ParentAccountManager.loadParentAccount();
        if (parent == null) {
            JOptionPane.showMessageDialog(null, "No parent account found. Please create one.");
            return;
        }
    
        String password = JOptionPane.showInputDialog("Enter Parent Password:");
        if (password != null && parent.authenticate(password)) {
            showParentalControlOptions(parent);
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect password.");
        }
    }
    /**
     * Displays the parental control options once a user has successfully logged in as a parent. Allows
     * parent to view total play time across all pets, average play time per pet, set a play time limit
     * or change their password.
     * @param parent
     */
    
    private void showParentalControlOptions(Parent parent) {
        JFrame controlFrame = new JFrame("Parental Control Options");
        controlFrame.setSize(400, 300);
        controlFrame.setLocationRelativeTo(null);
    
        JLabel infoLabel = new JLabel("Parental Controls");
        infoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel totalTime = new JLabel("Total play time: " + parent.getTotalPlayTime());
        JLabel avTime = new JLabel("Average play time: " + parent.getAveragePlayTime());
        JButton setTimeLimitBtn = new JButton("Set Play Time Limit");
        JButton changePasswordBtn = new JButton("Change Password");
    
        setTimeLimitBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Enter max allowed minutes:");
            if (input != null) {
                try {
                    float minutes = Float.parseFloat(input);
                    parent.getControls().setMaxAllowedMinutes(minutes);
                    ParentAccountManager.saveParentAccount(parent);
                    JOptionPane.showMessageDialog(null, "Play time limit set to " + minutes + " minutes.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid number format.");
                }
            }
        });
    
        changePasswordBtn.addActionListener(e -> {
            String newPassword = JOptionPane.showInputDialog("Enter New Password:");
            if (newPassword != null && !newPassword.isEmpty()) {
                parent.setPassword(newPassword);
                ParentAccountManager.saveParentAccount(parent);
                JOptionPane.showMessageDialog(null, "Password changed successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Password cannot be empty.");
            }
        });
    
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(infoLabel);
        panel.add(totalTime);
        panel.add(avTime);
        panel.add(setTimeLimitBtn);
        panel.add(changePasswordBtn);
    
        controlFrame.add(panel);
        controlFrame.setVisible(true);
        
    }
    /**
     * Method to load a saved game, invoked when "Load saved game" is clicked from main menu. Displays
     * a list of saved pets along with player name, pet name, creation date and current pet state.
     * Creates a LoadGame object when a saved pet is loaded. Displays a small image of each saved pet
     * based on default pet name (pet type) and last saved state using getPetImage.
     * @see LoadGame
     * @see LoadGame#loadGame(int)
     * @see MainMenu#getPetImage(String, String)
     */
    public void loadGame() {
        JPanel loadGamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("temp_assets/Background2.jpg");
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        JLabel title = new JLabel("Load Saved Game");
        title.setAlignmentX(CENTER_ALIGNMENT);
        loadGamePanel.setLayout(new BoxLayout(loadGamePanel, BoxLayout.Y_AXIS));
        loadGamePanel.setOpaque(false);

        JButton back = new JButton("< Main Menu");
        back.setForeground(Color.WHITE);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back to main menu button clicked");
                MainMenu newMenu = new MainMenu();
                dispose();
                newMenu.setVisible(true);
            }
        });
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setOpaque(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.setForeground(Color.decode("#FFFFFF"));
        back.setAlignmentX(LEFT_ALIGNMENT);
        back.setAlignmentY(TOP_ALIGNMENT);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));

        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Jersey25-Regular.ttf"));
            font = font.deriveFont(Font.PLAIN, 70);
            title.setFont(font);
            title.setForeground(Color.WHITE);
            font = font.deriveFont(Font.PLAIN, 25);
            back.setFont(font);
        } catch (Exception e) {
            e.printStackTrace();
        }

        topPanel.add(back, BorderLayout.WEST);
        loadGamePanel.add(topPanel, BorderLayout.NORTH);
        loadGamePanel.add(title);
        loadGamePanel.add(Box.createVerticalStrut(30));
        loadGamePanel.setOpaque(false);
        topPanel.setOpaque(false);

        JPanel savedGames = new JPanel();
        savedGames.setLayout(new BoxLayout(savedGames, BoxLayout.Y_AXIS));
        savedGames.setBorder(new EmptyBorder(0,20, 10 ,20));
        savedGames.setOpaque(false);

        LoadGame loadGame = new LoadGame();
        List<String> savedGamesList = loadGame.loadSavedGames();
        System.out.println(savedGamesList);

        if (savedGamesList.isEmpty()) {
            JPanel msg = new JPanel();
            JLabel noGames = new JLabel("You have no saved games yet.");
            font = font.deriveFont(Font.PLAIN, 40);
            noGames.setFont(font);
            noGames.setForeground(Color.decode("#6C5297"));
            noGames.setVisible(true);
            msg.add(noGames);
            msg.setVisible(true);
            savedGames.add(Box.createVerticalStrut(50));
            savedGames.add(msg);

        }

        for (String game : savedGamesList) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBackground(new Color(255, 255, 255, 50));
            card.setOpaque(true);
            card.setBorder(BorderFactory.createLineBorder(Color.decode("#6C5297"), 5));
            card.setMaximumSize(new Dimension(500, 160));
            card.setAlignmentX(Component.CENTER_ALIGNMENT);

            String[] gameData = game.split(": ");
            String petInfo = gameData[1];
            String petName = petInfo.split("'s pet ")[1].trim();
            String dateCreated = gameData[2];
            String state = gameData[3];
            String petType = gameData[4];
            LocalDateTime dateTime = LocalDateTime.parse(dateCreated.substring(0,19));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d'th', yyyy 'at' h:mm a");
            String formattedDate = dateTime.format(formatter);

            JLabel petNameLabel = new JLabel(petInfo);
            JLabel date = new JLabel("Created: " + formattedDate);
            JLabel currentState = new JLabel("Current state: " + state);

            petNameLabel.setForeground(Color.BLACK);
            font = font.deriveFont(Font.PLAIN, 25);
            petNameLabel.setFont(font);
            date.setForeground(Color.DARK_GRAY);
            font = font.deriveFont(Font.PLAIN, 15);
            date.setFont(font);
            currentState.setFont(font);

            JPanel imagePanel = new JPanel();
            imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.X_AXIS));
            ImageIcon image = getPetImage(petType, state);
            JLabel imageLabel = new JLabel(image);

            imagePanel.add(imageLabel);
            card.add(imagePanel);
            card.add(petNameLabel);
            card.add(date);
            card.add(currentState);

            JButton loadBtn = new JButton("Load");
            font = font.deriveFont(Font.PLAIN, 25);

            loadBtn.setFont(font);
            loadBtn.setForeground(Color.WHITE);
            loadBtn.setBackground(Color.decode("#6C5297"));
            loadBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            loadBtn.addActionListener(e -> {
            System.out.println("Loading: " + petInfo);
            int slotNumber = Integer.parseInt(gameData[0].replace("Slot", "").trim());
            System.out.println(slotNumber);
            LoadGame loadSavedGame = new LoadGame();
            loadSavedGame.loadGame(slotNumber);
            System.out.println("entered load game class");
            dispose();
            });

            card.add(Box.createVerticalStrut(5));
            card.add(loadBtn);
            savedGames.add(Box.createVerticalStrut(10));
            savedGames.add(card);
        }
        JScrollPane scrollPane = new JScrollPane(savedGames);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(300, 600));
        scrollPane.setBorder(new EmptyBorder(0,25,0,25));
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.getVerticalScrollBar().setBlockIncrement(50);
        Color transparentColor = new Color(0, 0, 0, 50); 
        scrollPane.setBackground(transparentColor);
        scrollPane.setOpaque(false); 

        loadGamePanel.add(scrollPane);
        setContentPane(loadGamePanel);
        revalidate();
        repaint();
    }
    /**
     * Method for retrieving a pet's image based on its default name and saved state.
     * @param petName
     * @param state
     * @return
     */
    public ImageIcon getPetImage(String petName, String state) {
        ImageIcon petIcon;
        switch (state) {
            case "NORMAL":
                petIcon = new ImageIcon("temp_assets/" + petName + ".png");
                break;
            case "HUNGRY":
                petIcon = new ImageIcon("temp_assets/" + petName + "-Hungry" +".png");
                break;
            case "ANGRY":
                petIcon = new ImageIcon("temp_assets/" + petName + "-Angry" + ".png");
                break;
            case "SLEEPING":
                petIcon = new ImageIcon("temp_assets/" + petName + "-Sleep" + ".png");
                break;
        case "DEAD":
            petIcon = new ImageIcon("temp_assets/" + petName + "-Dead" + ".png");
                break;
            default:
                petIcon = new ImageIcon("temp_assets/" + petName + ".png");
                break;
        }

        Image petImage = petIcon.getImage();
        Image resized = petImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        return new ImageIcon(resized);
    }
    /**
     * Method for exiting the game gracefully. Invoked when user clicks exit button from main menu.
     */

    public void exit() {
        System.exit(0);


    }
    /**
     * Inner class for panel styling purposes using the overrided method paintComponent.
     */
    class RoundedPanel extends JPanel {
        private final Color backgroundColor;
        private final int cornerRadius;

        public RoundedPanel(Color bgColor, int radius) {
            this.backgroundColor = bgColor;
            this.cornerRadius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}