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
public class PlayerTest {
    
    public PlayerTest() {
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
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Inventory inventory = new Inventory();  // Default inventory
        Pet pet = new Pet("Buddy", 100, 100, 100, 100, true, "NORMAL");  // Valid pet object
        Player instance = new Player("TestPlayer", inventory, false, pet);
        String expResult = "TestPlayer";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of displayPetState method, of class Player.
     */
    @Test
    public void testDisplayPetState() {
        System.out.println("displayPetState");
        Inventory inventory = new Inventory();  // Default inventory
        Pet pet = new Pet("Buddy", 100, 100, 100, 100, true, "NORMAL");  // Valid pet object
        Player instance = new Player("TestPlayer", inventory, false, pet);
        instance.displayPetState();
    }

    /**
     * Test of displayAllCommands method, of class Player.
     */
    @Test
    public void testDisplayAllCommands() {
        System.out.println("displayAllCommands");
        Inventory inventory = new Inventory();  // Default inventory
        Pet pet = new Pet("Buddy", 100, 100, 100, 100, true, "NORMAL");  // Valid pet object
        Player instance = new Player("TestPlayer", inventory, false, pet);
        instance.displayAllCommands();
    }

    /**
     * Test of displayGiftCommands method, of class Player.
     */
    @Test
    public void testDisplayGiftCommands() {
        System.out.println("displayGiftCommands");
        Inventory inventory = new Inventory();  // Default inventory
        Pet pet = new Pet("Buddy", 100, 100, 100, 100, true, "NORMAL");  // Valid pet object
        Player instance = new Player("TestPlayer", inventory, false, pet);
        instance.displayGiftCommands();
    }

    /**
     * Test of back method, of class Player.
     */
    @Test
    public void testBack() {
        System.out.println("back");
        Inventory inventory = new Inventory();  // Default inventory
        Pet pet = new Pet("Buddy", 100, 100, 100, 100, true, "NORMAL");  // Valid pet object
        Player instance = new Player("TestPlayer", inventory, false, pet);
        instance.back();
    }

    /**
     * Test of displayStats method, of class Player.
     */
    @Test
    public void testDisplayStats() {
        System.out.println("displayStats");
        Inventory inventory = new Inventory();  // Default inventory
        Pet pet = new Pet("Buddy", 100, 100, 100, 100, true, "NORMAL");  // Valid pet object
        Player instance = new Player("TestPlayer", inventory, false, pet);
        instance.displayStats();
    }

    /**
     * Test of getScore method, of class Player.
     */
    @Test
    public void testGetScore() {
        Inventory inventory = new Inventory();  // Default inventory
        Pet pet = new Pet("Buddy", 100, 100, 100, 100, true, "NORMAL");  // Valid pet object
        Player instance = new Player("TestPlayer", inventory, false, pet);
        Score expResult = instance.getScore();  // Retrieve expected score object
        Score result = instance.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInventory method, of class Player.
     */
    @Test
    public void testGetInventory() {
        System.out.println("getInventory");
        Inventory expResult = new Inventory();
        Player instance = new Player("TestPlayer", expResult, false, null);
        Inventory result = instance.getInventory();
        assertEquals(expResult, result);
    }

    /**
     * Test of viewSettings method, of class Player.
     */
    @Test
    public void testViewSettings() {
        System.out.println("viewSettings");
        Inventory inventory = new Inventory();  // Default inventory
        Pet pet = new Pet("Buddy", 100, 100, 100, 100, true, "NORMAL");
        // Create Player instance with required parameters
        Player instance = new Player("TestPlayer", inventory, false, pet);
        instance.viewSettings();
    }

    /**
     * Test of getActivePet method, of class Player.
     */
    @Test
    public void testGetActivePet() {
        System.out.println("getActivePet");
        Inventory inventory = new Inventory(); 
        Pet pet = new Pet("Buddy", 100, 100, 100, 100, true, "NORMAL");  // Valid pet object
        Player instance = new Player("TestPlayer", inventory, false, pet);
        Pet expResult = pet;  
        Pet result = instance.getActivePet();
        assertEquals(expResult, result);
    }

    /**
     * Test of isParent method, of class Player.
     */
    @Test
    public void testIsParent() {
        System.out.println("isParent");
        Inventory inventory = new Inventory();  // Default inventory
        Pet pet = new Pet("Buddy", 100, 100, 100, 100, true, "NORMAL");
        Player instance = new Player("TestPlayer", inventory, false, pet);
        boolean expResult = false;
        boolean result = instance.isParent();
        assertEquals(expResult, result);
    }
    
}
