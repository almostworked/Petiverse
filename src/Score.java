/**
 * The Score class manages the current and high score for a player.
 * It provides methods to increase, decrease, and display the score,
 * while automatically updating the high score if the current score exceeds it.
 * 
 * Author: Adrian  
 * @version 1.0
 */
public class Score {
    private int score;
    private int highScore = 0;

    /**
     * Returns the current score.
     *
     * @return the current score value
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the current score and updates the high score if necessary.
     *
     * @param score the new score value to set
     */
    public void setScore(int score) {
        this.score = score;
        if (score > highScore) {
            highScore = score;
        }
    }

    /**
     * Displays the current score and high score in the console.
     */
    public void displayScore() {
        System.out.println("Current Score: " + score);
        System.out.println("High Score: " + highScore);
    }

    /**
     * Increases the score by a specified amount and updates the high score if needed.
     *
     * @param amount the number of points to add to the score
     */
    public void increaseScore(int amount) {
        score += amount;
        if (score > highScore) {
            highScore = score;
        }
    }

    /**
     * Decreases the score by a specified amount.
     *
     * @param amount the number of points to subtract from the score
     */
    public void decreaseScore(int amount) {
        score -= amount;
    }

    /**
     * Returns the highest score achieved.
     *
     * @return the high score value
     */
    public int getHighScore() {
        return highScore;
    }
}

