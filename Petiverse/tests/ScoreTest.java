/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.petiverse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Fin faniyi
 */
public class ScoreTest {
    
    public ScoreTest() {
        
    }
    
    @BeforeAll
    public static void setUpClass() {
        System.out.println("setUpClass()");
    }
    
    @AfterAll
    public static void tearDownClass() {
        System.out.println("tearDownClass()");
    }
    
    @BeforeEach
    public void setUp() {
        System.out.println("setUp()");
    }
    
    @AfterEach
    public void tearDown() {
        System.out.println("tearDown()");
    }

    /**
     * Test of getScore method, of class Score.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        Score instance = new Score();
        int expResult = 0; // The expected initial score is 0
        int result = instance.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of setScore method, of class Score.
     */
    @Test
    public void testSetScore() {
        System.out.println("setScore");
        Score instance = new Score();
        int score = 10;
        instance.setScore(score);
        int result = instance.getScore(); 
        assertEquals(score, result, "The score should be set correctly.");
    }

    /**
     * Test of displayScore method, of class Score.
     */
    @Test
    public void testDisplayScore() {
        System.out.println("displayScore");
        Score instance = new Score();
        instance.displayScore();
    }

    /**
     * Test of increaseScore method, of class Score.
     */
    @Test
    public void testIncreaseScore() {
        System.out.println("increaseScore");
        int amount = 0;
        Score instance = new Score();
        instance.increaseScore(amount);
    }

    /**
     * Test of decreaseScore method, of class Score.
     */
    @Test
    public void testDecreaseScore() {
        System.out.println("decreaseScore");
        int amount = 0;
        Score instance = new Score();
        instance.decreaseScore(amount);
    }

    /**
     * Test of getHighScore method, of class Score.
     */
    @Test
    public void testGetHighScore() {
        System.out.println("getHighScore");
        Score instance = new Score();
        int expResult = 0;
        int result = instance.getHighScore();
        assertEquals(expResult, result);
    }
    
}
