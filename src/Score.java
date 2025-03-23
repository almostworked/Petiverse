public class Score {
    private int score;

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;

    }

    public void displayScore() {
        // Display current score
        // Display high score

    }

    public void increaseScore(int amount) {
        score += amount;
    }

    public void decreaseScore(int amount) {
        score -= amount;
    }
    
}
