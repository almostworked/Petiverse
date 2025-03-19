package src;

public class Score {
    private int score;

    public Score() {
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increaseScore(int increment) {
        this.score += increment;
    }

    public void displayScore() {
        System.out.println("Current Score: " + this.score);
    }
}
