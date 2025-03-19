package src;

public class Score {
    private int score;

    public Score() {
        this.score = 0; // Initialize score to 0
    }

    public int getScore() {
        return score;
    }

    public void setScore(int newScore) {
        this.score = newScore;
    }

    public void increaseScore(int points) {
        this.score += points; // Increase score by the given points
    }

    public void displayScore() {
        System.out.println("Current Score: " + score); // Display the current score
    }
}
