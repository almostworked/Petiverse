// Main access point to the game, sets up GUI

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.border.Border;
//import javax.swing.event.*;
//import javax.swing.Timer;

public class MainMenu extends JFrame {

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
            setContentPane(background);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        setSize(700, 600);
        setLocationRelativeTo(null); // Centres the frame on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }
    // Fancy button animation to be potentially implemented later
    /*private JButton customButton(String text) {
        JButton button = new JButton(text);
        return button;
    }*/

    public static void main(String args[]) { // main(): Starts game/sets up GUI when program is run
        MainMenu menu = new MainMenu();
        menu.setVisible(true);

    }

    public int displayStats() {
        return 0;
    }

    public void displayInstructions() {

    }

    public void parentalControls() {

    }

    public void playNewGame() {

    }

    public void loadGame(String filename) {

    }

    public void displaySettings() {

    }

    public void exit() {

    }
}