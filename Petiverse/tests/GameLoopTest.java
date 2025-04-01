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
public class GameLoopTest {
    
    public GameLoopTest() {
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
     * Test of start method, of class GameLoop.
     */
    @Test
    public void testStart() {
        System.out.println("start");
        Inventory inventory = new Inventory();
        Pet pet = new Pet("Foxy", 100, 100, 100, 100, true, "NORMAL"); // Pet constructor
        Player player = new Player("Player1", inventory, true, pet); // Player constructor
        SaveGame saveGame = new SaveGame(1, true); // SaveGame constructor
        StateManager stateManager = new StateManager(pet, new int[]{5, 5, 5, 5}); 
        GameLoop instance = new GameLoop(pet, player, stateManager, saveGame);
        instance.start();
        assertTrue(instance.isRunning());
    }

    /**
     * Test of stop method, of class GameLoop.
     */
    @Test
    public void testStop() {
        System.out.println("stop");
        Inventory inventory = new Inventory();
        Pet pet = new Pet("Foxy", 100, 100, 100, 100, true, "NORMAL"); // Pet constructor
        Player player = new Player("Player1", inventory, true, pet); // Player constructor
        SaveGame saveGame = new SaveGame(1, true); // SaveGame constructor
        StateManager stateManager = new StateManager(pet, new int[]{5, 5, 5, 5}); 
        GameLoop instance = new GameLoop(pet, player, stateManager, saveGame);
        instance.start();
        instance.stop();
        assertFalse(instance.isRunning());
    
    }

    /**
     * Test of isRunning method, of class GameLoop.
     */
    @Test
    public void testIsRunning() {
        System.out.println("isRunning");
        Inventory inventory = new Inventory();
        Pet pet = new Pet("Foxy", 100, 100, 100, 100, true, "NORMAL"); // Pet constructor
        Player player = new Player("Player1", inventory, true, pet); // Player constructor
        SaveGame saveGame = new SaveGame(1, true); // SaveGame constructor
        StateManager stateManager = new StateManager(pet, new int[]{5, 5, 5, 5});
        GameLoop instance = new GameLoop(pet, player, stateManager, saveGame);
        boolean expResult = false;
        boolean result = instance.isRunning();
        assertEquals(expResult, result);
        instance.start();
        expResult = true;
        result = instance.isRunning();
        assertEquals(expResult, result);
    }
    
}
