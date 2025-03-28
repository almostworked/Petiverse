public class Score {
    private int score;
    private int highScore = 0;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        if (score > highScore) {
            highScore = score;
        }
    }

    public void displayScore() {
        System.out.println("Current Score: " + score);
        System.out.println("High Score: " + highScore);
    }

    public void increaseScore(int amount) {
        score += amount;
        if (score > highScore) {
            highScore = score;
        }
    }

    public void decreaseScore(int amount) {
        score -= amount;
    }

    public int getHighScore() {
        return highScore;
    }
}

