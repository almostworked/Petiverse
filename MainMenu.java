// Main access point to the game, sets up GUI
// Handles entry into the game. Communicates with NewGame, LoadGame, Settings and Player

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.border.Border;
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

        // Initialize main menu buttons
        JButton startButton = new JButton("  Start new game  ");
        JButton loadButton = new JButton("  Load saved game  ");
        JButton instructionsButton = new JButton("  Tutorial & Instructions  ");
        JButton parentButton = new JButton("  Parental Controls  ");
        JButton exitButton = new JButton("  Exit  ");

        // Add action listeners for each button
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start button clicked");
                playNewGame();
            }
        });
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Load button clicked");
            }
        });
        instructionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Instructions/tutorial button clicked");
                displayInstructions();
            }
        });
        parentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Parental controls button clicked");
            }
        });
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exit button clicked");
                System.exit(0);
            }
        });

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

            exitButton.setFont(font);
            exitButton.setForeground(Color.decode("#6C5297"));
            exitButton.setBackground(Color.decode("#D9D9D9"));


            // Create a border for the buttons
            Border buttonBorder = BorderFactory.createLineBorder(Color.decode("#8B73B2"), 3);
            startButton.setBorder(buttonBorder);
            loadButton.setBorder(buttonBorder);
            instructionsButton.setBorder(buttonBorder);
            parentButton.setBorder(buttonBorder);
            exitButton.setBorder(buttonBorder);

            // Align buttons down the centre
            startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            instructionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            parentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Create background using imported background image
            JPanel background = new JPanel() { // Override the paintComponent method to add the custom background
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    ImageIcon backgroundPic = new ImageIcon("temp_assets/Background1.jpg");
                    g.drawImage(backgroundPic.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            };
            background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS)); // BoxLayout for vertical stacking

            // Add titles + buttons to the frame with space in between
            background.add(Box.createVerticalStrut(140));
            background.add(title);
            background.add(Box.createVerticalStrut(20)); 
            background.add(startButton);
            background.add(Box.createVerticalStrut(20)); 
            background.add(loadButton);
            background.add(Box.createVerticalStrut(20)); 
            background.add(instructionsButton);
            background.add(Box.createVerticalStrut(20)); 
            background.add(parentButton);
            background.add(Box.createVerticalStrut(20)); 
            background.add(exitButton);
    
            // Set the background panel as the content pane
            mainMenuPanel = background;
            setContentPane(mainMenuPanel);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        setSize(700, 600);
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
            font = font.deriveFont(Font.PLAIN, 70); // Title text size

            title.setFont(font);
            title.setForeground(Color.decode("#FFFFFF"));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);

            font = font.deriveFont(Font.PLAIN, 25);

            instructions.add(Box.createVerticalStrut(80));
            instructions.add(title);

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

            JPanel topPanel = new JPanel(new BorderLayout());
            topPanel.setOpaque(false);
            topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0)); // Padding
            topPanel.add(back, BorderLayout.WEST);
            instructions.add(topPanel, BorderLayout.NORTH);

            back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Back to main menu button clicked");
                    menu.setContentPane(mainMenuPanel);
                    menu.revalidate();
                    menu.repaint();
                }
            });

            JTextPane instructionsTextPane = new JTextPane();
            instructionsTextPane.setEditable(false);
            instructionsTextPane.setOpaque(false);
            instructionsTextPane.setContentType("text/html"); // Set HTML content to format text easily

            StyledDocument doc = instructionsTextPane.getStyledDocument();

            SimpleAttributeSet vitalsStyle = new SimpleAttributeSet();
            StyleConstants.setBold(vitalsStyle, true);
            StyleConstants.setForeground(vitalsStyle, Color.WHITE);

            SimpleAttributeSet commandsStyle = new SimpleAttributeSet();
            StyleConstants.setBold(commandsStyle, true);
            StyleConstants.setForeground(commandsStyle, Color.WHITE);

            SimpleAttributeSet healthStyle = new SimpleAttributeSet();
            StyleConstants.setBold(healthStyle, true);
            StyleConstants.setForeground(healthStyle, Color.WHITE);

            SimpleAttributeSet normalStyle = new SimpleAttributeSet();
            StyleConstants.setFontSize(normalStyle, 16);
            StyleConstants.setForeground(normalStyle, Color.WHITE);
            StyleConstants.setFontFamily(normalStyle, getName());

        try {
            doc.insertString(doc.getLength(), "===== VITALS =====\n", vitalsStyle);
            doc.insertString(doc.getLength(), "• Your pet's vitals show how full, rested, and happy they are.\n", normalStyle);
            doc.insertString(doc.getLength(), "• Vital bars go down over time. Taking care of your pet will keep them full.\n\n", normalStyle);

            doc.insertString(doc.getLength(), "===== COMMANDS =====\n", commandsStyle);
            doc.insertString(doc.getLength(), "• Feed - Give your pet food.\n", normalStyle);
            doc.insertString(doc.getLength(), "• Go to bed - Rest your pet to restore energy.\n", normalStyle);
            doc.insertString(doc.getLength(), "• Give gift - Make your pet happy with a gift.\n", normalStyle);
            doc.insertString(doc.getLength(), "• Take to vet - Heal your pet when sick.\n", normalStyle);
            doc.insertString(doc.getLength(), "• Play - Interact with your pet for joy.\n", normalStyle);
            doc.insertString(doc.getLength(), "• Exercise - Keep your pet fit and active.\n\n", normalStyle);

            doc.insertString(doc.getLength(), "===== HEALTH =====\n", healthStyle);
            doc.insertString(doc.getLength(), "• The health bar shows your pet's overall wellbeing.\n", normalStyle);
            doc.insertString(doc.getLength(), "• When a vital is at zero, health will start to go down.\n", normalStyle);
            doc.insertString(doc.getLength(), "• Replenish vitals and visit the vet to recover health.\n", normalStyle);
            doc.insertString(doc.getLength(), "• When health is at zero, you can no longer care for your pet.\n", normalStyle);

            doc.insertString(doc.getLength(), "==== LEVEL ====\n", normalStyle);
            doc.insertString(doc.getLength(), "==== PET STATE ====\n", normalStyle);
            doc.insertString(doc.getLength(), "==== INVENTORY ====\n", normalStyle);


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

    public void parentalControls() { // Initialize parental controls

    }

    public void playNewGame() { // Start a new game
        JPanel newGamePanel = new JPanel();
        newGamePanel.setLayout(new BoxLayout(newGamePanel, BoxLayout.Y_AXIS));
        newGamePanel.setOpaque(false);

        JLabel userLabel = new JLabel("Enter your name: ");
        JTextField userName = new JTextField(15);

        JLabel petLabel = new JLabel("Choose your pet:");
        String[] petOptions = {"Foxy", "Roscoe", "Sterling"};
        JComboBox<String> petDropdown = new JComboBox<>(petOptions);

        JLabel petNameLabel = new JLabel("Name your pet:");
        JTextField petNameField = new JTextField(15);

        JButton confirm = new JButton("Start Game");
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

    public void loadGame(String filename) {

    }

    public void displaySettings() {

    }

    public void exit() {

    }
    class RoundedPanel extends JPanel {
        private final Color backgroundColor;
        private final int cornerRadius;
    
        public RoundedPanel(Color bgColor, int radius) {
            this.backgroundColor = bgColor;
            this.cornerRadius = radius;
            setOpaque(false); // Important for transparency!
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