import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayGameGUI extends JFrame {
    private Player player;
    private JLabel healthLabel, sleepLabel, fullnessLabel, happinessLabel;

    public PlayGameGUI(Player player) {
        this.player = player;
        setTitle("Petiverse - Play Game");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel petStatusPanel = new JPanel(new GridLayout(4, 1));
        healthLabel = new JLabel();
        sleepLabel = new JLabel();
        fullnessLabel = new JLabel();
        happinessLabel = new JLabel();

        petStatusPanel.add(healthLabel);
        petStatusPanel.add(sleepLabel);
        petStatusPanel.add(fullnessLabel);
        petStatusPanel.add(happinessLabel);

        add(petStatusPanel, BorderLayout.NORTH);

        JPanel commandPanel = new JPanel();
        JButton feedButton = new JButton("Feed");
        JButton playButton = new JButton("Play");
        JButton bedButton = new JButton("Go to Bed");
        JButton giftButton = new JButton("Give Gift");
        JButton exerciseButton = new JButton("Exercise");
        JButton vetButton = new JButton("Visit Vet");

        commandPanel.add(feedButton);
        commandPanel.add(playButton);
        commandPanel.add(bedButton);
        commandPanel.add(giftButton);
        commandPanel.add(exerciseButton);
        commandPanel.add(vetButton);

        add(commandPanel, BorderLayout.SOUTH);

        // Add action listeners
        feedButton.addActionListener(e -> {
            player.feed("FoodItem"); // Replace with actual selection logic
            updateStatus();
        });

        playButton.addActionListener(e -> {
            player.play();
            updateStatus();
        });

        // Similarly for other buttons...

        updateStatus(); // Initial status display
    }

    private void updateStatus() {
        Pet pet = player.getActivePet();
        healthLabel.setText("Health: " + pet.getHealth());
        sleepLabel.setText("Sleep: " + pet.getSleep());
        fullnessLabel.setText("Fullness: " + pet.getFullness());
        happinessLabel.setText("Happiness: " + pet.getHappiness());
    }
}

