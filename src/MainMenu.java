/**
 * MainMenu.java: The main entry point to the Petiverse game. Allows the user to start a new game, load a saved game, view instructions for the
 * game, access parental controls or exit the game.
 * 
 * @author Daniella
 */

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
//import javax.swing.event.*;
//import javax.swing.Timer;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class MainMenu extends JFrame {
    public static MainMenu menu;
    private JPanel mainMenuPanel;

    public MainMenu() { // Creates main menu frame

        setTitle("Petiverse");
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS)); // Sets an organized layout for the components on screen

        JLabel title = new JLabel("Petiverse");

        // Play background music
        Sound sound = new Sound();
        String music = "temp_assets/background_music.wav";
        String buttonClick = "temp_assets/button-click.wav";

        // Initialize main menu buttons
        JButton startButton = new JButton("  Start new game  ");
        JButton loadButton = new JButton("  Load saved game  ");
        JButton instructionsButton = new JButton("  Tutorial & Instructions  ");
        JButton parentButton = new JButton("  Parental Controls  ");
        JButton soundButton = new JButton("  Sound: OFF  ");
        JButton exitButton = new JButton("  Exit  ");

        // Add action listeners for each button
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start button clicked");
                sound.playEffect(buttonClick); // Play button sound fx

                Pet foxy = new Foxy("Foxy");
                Pet roscoe = new Roscoe("Roscoe");
                Pet sterling = new Sterling("Sterling");
                

                new NewGameGUI(foxy, roscoe, sterling);
            }
        });   
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Load button clicked");
                sound.playEffect(buttonClick);
                loadGame();
            }
        });
        instructionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Instructions/tutorial button clicked");
                sound.playEffect(buttonClick);
                displayInstructions();
            }
        });
        parentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Parental controls button clicked");
                sound.playEffect(buttonClick);
                parentalControls();
            }
        });
        soundButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sound.toggleSound();
                soundButton.setText(sound.isSoundEnabled() ? "  Sound: ON  " : "  Sound: OFF  ");
            }
        });
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exit button clicked");
                sound.playEffect(buttonClick);
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
            font = font.deriveFont(Font.PLAIN, 100); // Title text size

            title.setFont(font);
            title.setForeground(Color.decode("#FFFFFF"));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);

            font = font.deriveFont(Font.PLAIN, 25); // Button text size

            // Set fonts, text colour, background colour
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

    
            // Set the background panel as the content pane
            mainMenuPanel = background;
            setContentPane(mainMenuPanel);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        setSize(700, 600); // Set the size of the frame
        setLocationRelativeTo(null); // Centres the frame on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close window when X button pressed

    }
    // Fancy button animation to be potentially implemented later
    /*private JButton customButton(String text) {
        JButton button = new JButton(text);
        return button;
    }*/

    public static void main(String args[]) { // main(): Starts game/sets up GUI when program is run
        menu = new MainMenu();
        menu.setVisible(true);

    }

    public int displayStats() {
        return 0;
    }

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
                    menu.setContentPane(mainMenuPanel); // Go back to main menu panel
                    menu.revalidate();
                    menu.repaint();
                }
            });

            // Create a text pane to hold instructions; allows colour changes + ability to add icons/images using HTML
            JTextPane instructionsTextPane = new JTextPane();
            instructionsTextPane.setEditable(false);
            instructionsTextPane.setOpaque(false);
            instructionsTextPane.setContentType("text/html");
            instructionsTextPane.setFont(font);

            StyledDocument doc = instructionsTextPane.getStyledDocument();

            // Align instructions down the centre
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);

            // Style for instructions headings
            SimpleAttributeSet instructionTitle = new SimpleAttributeSet();
            StyleConstants.setBold(instructionTitle, true);
            StyleConstants.setForeground(instructionTitle, Color.WHITE);
            StyleConstants.setFontFamily(instructionTitle, font.getFontName());

            // Style for normal instructions text
            SimpleAttributeSet normalStyle = new SimpleAttributeSet();
            StyleConstants.setFontSize(normalStyle, 20);
            StyleConstants.setForeground(normalStyle, Color.WHITE);
            StyleConstants.setFontFamily(normalStyle, font.getFontName());

            try {
                doc.insertString(doc.getLength(), "- VITALS -\n", instructionTitle);
                doc.insertString(doc.getLength(), "Your pet's vitals show how full, rested, and happy they are.\n", normalStyle);
                doc.insertString(doc.getLength(), "Vital bars go down over time. Taking care of your pet will keep them full.\n\n", normalStyle);

                doc.insertString(doc.getLength(), "- COMMANDS -\n", instructionTitle);
                doc.insertString(doc.getLength(), "• Feed - Give your pet food.\n", normalStyle);
                doc.insertString(doc.getLength(), "• Go to bed - Rest your pet to restore energy.\n", normalStyle);
                doc.insertString(doc.getLength(), "• Give gift - Make your pet happy with a gift.\n", normalStyle);
                doc.insertString(doc.getLength(), "• Take to vet - Heal your pet when sick.\n", normalStyle);
                doc.insertString(doc.getLength(), "• Play - Interact with your pet for joy.\n", normalStyle);
                doc.insertString(doc.getLength(), "• Exercise - Keep your pet fit and active.\n\n", normalStyle);

                doc.insertString(doc.getLength(), "- HEALTH -\n", instructionTitle);
                doc.insertString(doc.getLength(), "The health bar shows your pet's overall wellbeing. When a vital is at zero, health will start to go down.\nYou must replenish vitals and take your pet to the vet to fill its health up again.\nWhen health is at zero, you can no longer take care of your pet.\n\n", normalStyle);

                doc.insertString(doc.getLength(), "- LEVEL -\n", instructionTitle);
                doc.insertString(doc.getLength(), "As you take care of your pet, you will earn points.\nWith enough points you can level up. As you level up, you will earn food and gifts. At each level milestone, you will earn new pet options.\n\n", normalStyle);

                doc.insertString(doc.getLength(), "- PET STATE -\n", instructionTitle);
                doc.insertString(doc.getLength(), "Your pet can be in one of four possible states:\n> Normal <\nall commands available\n> Hungry <\nall commands available\n> Sleeping <\nno commands available\n> Dead <\nno commands available\n\n", normalStyle);

                doc.insertString(doc.getLength(), "- INVENTORY -\n", instructionTitle);
                doc.insertString(doc.getLength(), "Your inventory contains the food and gifts you currently have for your pet. Try not to overfeed your pet.\nYou can earn items by completing tasks on the inventory page.\n\n", normalStyle);

            } catch (BadLocationException e) {
                e.printStackTrace();
            }

            // Creating a scrolling window for instructions
            JScrollPane scrollPane = new JScrollPane(instructionsTextPane);
            scrollPane.setPreferredSize(new Dimension(300,600));
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);

            RoundedPanel scrollContainer = new RoundedPanel(new Color(0, 0, 0, 150), 25); // Semi-transparent black
            scrollContainer.setLayout(new BorderLayout());
            scrollContainer.add(scrollPane, BorderLayout.CENTER);
            scrollContainer.setMaximumSize(new Dimension(600, 300));
            scrollContainer.setAlignmentX(Component.CENTER_ALIGNMENT);

            instructions.add(scrollContainer);


        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        setContentPane(instructions);
        revalidate();
        repaint();

    }

    public void parentalControls() {
        JFrame parentalFrame = new JFrame("Parental Controls");
        parentalFrame.setSize(400, 300);
        parentalFrame.setLocationRelativeTo(null);
        parentalFrame.setLayout(new BorderLayout());
    
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
    
    private void showParentalControlOptions(Parent parent) {
        JFrame controlFrame = new JFrame("Parental Control Options");
        controlFrame.setSize(400, 300);
        controlFrame.setLocationRelativeTo(null);
    
        JLabel infoLabel = new JLabel("Parental Controls");
        infoLabel.setFont(new Font("Arial", Font.BOLD, 20));
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
    
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(infoLabel);
        panel.add(setTimeLimitBtn);
        panel.add(changePasswordBtn);
    
        controlFrame.add(panel);
        controlFrame.setVisible(true);
        
    }

    public void playNewGame() { // Start a new game
        JPanel newGamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("temp_assets/Background2.jpg");
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        JLabel title = new JLabel("Start New Game");
        newGamePanel.setLayout(new BoxLayout(newGamePanel, BoxLayout.Y_AXIS));
        newGamePanel.setOpaque(false);

        JLabel userLabel = new JLabel("Enter your name: ");
        JTextField userName = new JTextField(15);

        JLabel petLabel = new JLabel("Choose your pet:");
        String[] petOptions = {"Foxy", "Roscoe", "Sterling"};
        JComboBox<String> petDropdown = new JComboBox<>(petOptions);

        JLabel petNameLabel = new JLabel("Name your pet:");
        JTextField petNameField = new JTextField(15);

        JButton back = new JButton("< Main Menu");

        JButton confirm = new JButton("Start Game");

        back.setForeground(Color.WHITE);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back to main menu button clicked");
                menu.setContentPane(mainMenuPanel); // Go back to main menu panel
                menu.revalidate();
                menu.repaint();
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
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0)); // Padding
        topPanel.add(back, BorderLayout.WEST);

        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Jersey25-Regular.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            font = font.deriveFont(Font.PLAIN, 70); // Title text size

            title.setFont(font);
            title.setForeground(Color.decode("#FFFFFF"));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            title.setAlignmentY(TOP_ALIGNMENT);

            font = font.deriveFont(Font.PLAIN, 25);
            // Create a back button so the user can navigate back to the main menu
            back.setFont(font);

            userLabel.setFont(font);
            userLabel.setForeground(Color.WHITE);
            userLabel.setAlignmentX(CENTER_ALIGNMENT);

            userName.setFont(font);
            userName.setForeground(Color.WHITE);
            userName.setOpaque(false);

            petLabel.setFont(font);
            petLabel.setForeground(Color.WHITE);
            petLabel.setAlignmentX(CENTER_ALIGNMENT);

            petNameLabel.setFont(font);
            petNameLabel.setForeground(Color.WHITE);
            petNameLabel.setAlignmentX(CENTER_ALIGNMENT);

            petNameField.setFont(font);
            petNameField.setForeground(Color.WHITE);
            petNameField.setOpaque(false);

            confirm.setFont(font);
            confirm.setAlignmentX(CENTER_ALIGNMENT);
            confirm.setOpaque(false);
            confirm.setBorderPainted(true);


        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        confirm.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Confirm user wants to start a new game, send info to NewGame class
        confirm.addActionListener(e -> {
            String username = userName.getText().trim();
            String petType = (String) petDropdown.getSelectedItem();
            String petName = petNameField.getText().trim();

            if (username.isEmpty() || petName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill out all fields.");
                return;
            }

            // Call method in NewGame with username, pet type and pet name. Example usage:

            // NewGame newGame = new NewGame(username, petType, petName);
            // newGame.start();

            // Call method in SaveGame to save initial data

            this.setVisible(false); // Hide menu and launch the game
        });

        // Add buttons and labels to New Game panel
        newGamePanel.add(topPanel, BorderLayout.NORTH);
        newGamePanel.add(title);
        newGamePanel.add(Box.createVerticalStrut(30));
        newGamePanel.add(userLabel);
        newGamePanel.add(userName);
        newGamePanel.add(Box.createVerticalStrut(20));
        newGamePanel.add(petLabel);
        newGamePanel.add(petDropdown);
        newGamePanel.add(Box.createVerticalStrut(20));
        newGamePanel.add(petNameLabel);
        newGamePanel.add(petNameField);
        newGamePanel.add(Box.createVerticalStrut(20));
        newGamePanel.add(confirm);

        // Set this panel as new content pane
        setContentPane(newGamePanel);
        revalidate();
        repaint();
    }

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
                menu.setContentPane(mainMenuPanel); // Go back to main menu panel
                menu.revalidate();
                menu.repaint();
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

        JPanel savedGames = new JPanel();
        savedGames.setLayout(new BoxLayout(savedGames, BoxLayout.Y_AXIS));
        savedGames.setBorder(new EmptyBorder(0,20, 10 ,20));
        savedGames.setOpaque(false);

        // Retrieve saved games
        LoadGame loadGame = new LoadGame();
        List<String> savedGamesList = loadGame.loadSavedGames();

        for (String game : savedGamesList) {
            // Create a card for each save file
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBackground(new Color(255, 255, 255, 50)); // semi-transparent
            card.setOpaque(true);
            card.setBorder(BorderFactory.createLineBorder(Color.decode("#6C5297"), 5));
            card.setMaximumSize(new Dimension(500, 160));
            card.setAlignmentX(Component.CENTER_ALIGNMENT);


            // Extract pet name and date from saved game string
            String[] gameData = game.split(": ");
            String petInfo = gameData[1];
            String petName = petInfo.split("'s pet ")[1].trim();
            String dateCreated = gameData[2];
            String state = gameData[3];
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
            ImageIcon image = getPetImage(petName, state);
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
                LoadGame loadGame2 = new LoadGame();
                loadGame2.loadGame(slotNumber);
                System.out.println("entered load game class");
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
        scrollPane.setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        scrollPane.getVerticalScrollBar().setBlockIncrement(50);

        loadGamePanel.add(scrollPane);
        setContentPane(loadGamePanel);
        revalidate();
        repaint();
    }
    public ImageIcon getPetImage(String petName, String state) {
        System.out.println(state);
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


    public void displaySettings() {

    }

    public void exit() {
        System.exit(0); // Exit the game gracefully


    }
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