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
 * @author fin faniy
 */
public class PlayGameTest {
    
    public PlayGameTest() {
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
     * Test of displayPetState method, of class PlayGame.
     */
    @Test
    public void testDisplayPetState() {
        System.out.println("displayPetState");
        PlayGame instance = new PlayGameImpl();
        instance.displayPetState();
    }

    /**
     * Test of displayAllCommands method, of class PlayGame.
     */
    @Test
    public void testDisplayAllCommands() {
        System.out.println("displayAllCommands");
        PlayGame instance = new PlayGameImpl();
        instance.displayAllCommands();
    }

    /**
     * Test of displayGiftCommands method, of class PlayGame.
     */
    @Test
    public void testDisplayGiftCommands() {
        System.out.println("displayGiftCommands");
        PlayGame instance = new PlayGameImpl();
        instance.displayGiftCommands();
    }

    /**
     * Test of back method, of class PlayGame.
     */
    @Test
    public void testBack() {
        System.out.println("back");
        PlayGame instance = new PlayGameImpl();
        instance.back();
    }

    public class PlayGameImpl implements PlayGame {

        public void displayPetState() {
        }

        public void displayAllCommands() {
        }

        public void displayGiftCommands() {
        }

        public void back() {
        }
    }
    
}
