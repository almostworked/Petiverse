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
 * @author faniy
 */
public class PetTest {
    
    
    public PetTest() {
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
     * Test of setActivePlayer method, of class Pet.
     */
    @Test
    public void testSetActivePlayer() {
        System.out.println("setActivePlayer");
        Player player = null;
        Pet.setActivePlayer(player);
    }

    /**
     * Test of getActivePlayer method, of class Pet.
     */
    @Test
    public void testGetActivePlayer() {
        System.out.println("getActivePlayer");
        Pet.setActivePlayer(null);
        Player expResult = null;
        Player result = Pet.getActivePlayer();
        assertEquals(expResult, result);

    }

    /**
     * Test of feed method, of class Pet.
     */
    @Test
    public void testFeed() {
        System.out.println("feed");
        Item food = new Item("Apple", Item.ItemType.FOOD, 10);  // Assuming it increases fullness by 10

    // Create a Pet instance with initial values
        Pet instance = new Pet("Buddy", 50, 80, 50, 60, true, "NORMAL");
        instance.feed(food);
       
    }

    /**
     * Test of play method, of class Pet.
     */
    @Test
    public void testPlay() {
        System.out.println("play");
        Pet instance = new Pet("Buddy", 50, 80, 50, 60, true, "NORMAL");
        instance.play();
        
    }

    /**
     * Test of giveGift method, of class Pet.
     */
    @Test
    public void testGiveGift() {
        System.out.println("giveGift");
        Inventory inventory = new Inventory();  // Create an inventory (adjust based on your actual class)
        Player player = new Player("Alice", inventory, true, null);  // Player with no active pet initially

        // Set the active player
        Pet.setActivePlayer(player);
        Item gift = new Item("Toy", Item.ItemType.GIFT, 0);
        Pet instance = new Pet("Buddy", 50, 80, 50, 60, true, "NORMAL");
        instance.giveGift(gift);
    }

    /**
     * Test of sleep method, of class Pet.
     */
    @Test
    public void testSleep() {
        System.out.println("sleep");
        Player activePlayer = new Player("Alice", new Inventory(), true, null);
        Pet.setActivePlayer(activePlayer); 
        Pet instance = new Pet("Buddy", 50, 80, 50, 60, true, "NORMAL");;
        instance.sleep();
        
    }

    /**
     * Test of exercise method, of class Pet.
     */
    @Test
    public void testExercise() {
        System.out.println("exercise");
        Pet instance =new Pet("Buddy", 50, 80, 50, 60, true, "NORMAL");;
        instance.exercise();
    
    }

    /**
     * Test of takeToVet method, of class Pet.
     */
    @Test
    public void testTakeToVet() {
    System.out.println("takeToVet");
    Inventory inventory = new Inventory(); // Ensure Inventory class has a default constructor
    Pet pet = new Pet("Buddy", 50, 50, 50, 50, true, "SICK");
    // Create a Player instance with required arguments
    Player player = new Player("TestPlayer", inventory, false, pet);
    // Set the active player in Pet class
    Pet.setActivePlayer(player);
    // Call takeToVet
    pet.takeToVet();
    // Assertions
    assertEquals(100, pet.getHealth(), "Health should be restored to 100");
    assertEquals("NORMAL", pet.getState(), "State should be set to NORMAL");
    }

    /**
     * Test of getName method, of class Pet.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Pet instance = new Pet("Buddy", 50, 80, 50, 60, true, "NORMAL");
        String expResult = "Buddy";
        String result = instance.getName();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setName method, of class Pet.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Max";
        Pet instance = new Pet("Buddy", 50, 80, 50, 60, true, "NORMAL");
        instance.setName(name);

    }

    /**
     * Test of getHealth method, of class Pet.
     */
    @Test
    public void testGetHealth() {
        System.out.println("getHealth");
        Pet instance = new Pet("Buddy", 50, 80, 50, 60, true, "NORMAL");
        int expResult = 50;
        int result = instance.getHealth();
        assertEquals(expResult, result);

    }

    /**
     * Test of setHealth method, of class Pet.
     */
    @Test
    public void testSetHealth() {
        System.out.println("setHealth");
        int health = 50;
        Pet instance = new Pet("Buddy", 50, 80, 50, 60, true, "NORMAL");
        instance.setHealth(health);
        
    }

    /**
     * Test of getSleep method, of class Pet.
     */
    @Test
    public void testGetSleep() {
        System.out.println("getSleep");
        Pet instance = new Pet("Buddy", 50, 80, 50, 50, true, "NORMAL");
        // Expected sleep value (as set in the constructor)
        int expResult = 80;
        // Get the actual sleep value
        int result = instance.getSleep();
        // Assert that the returned sleep value matches the expected value
        assertEquals(expResult, result, "Sleep value should be correctly returned.");
    }

    /**
     * Test of setSleep method, of class Pet.
     */
    @Test
    public void testSetSleep() {
        System.out.println("setSleep");
        int sleep = 80;
        Pet instance = new Pet("Buddy", 50, 80, 50, 60, true, "NORMAL");
        instance.setSleep(sleep);
    }

    /**
     * Test of getHappiness method, of class Pet.
     */
    @Test
    public void testGetHappiness() {
        System.out.println("getHappiness");
        Pet instance = new Pet("Buddy", 50, 80, 80, 60, true, "NORMAL");
        int expResult = 80;
        int result = instance.getHappiness();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of setHappiness method, of class Pet.
     */
    @Test
    public void testSetHappiness() {
        System.out.println("setHappiness");
        int happiness = 0;
        Pet instance = new Pet("Buddy", 50, 80, 50, 60, true, "NORMAL");
        instance.setHappiness(happiness);
       
    }

    /**
     * Test of getFullness method, of class Pet.
     */
    @Test
    public void testGetFullness() {
        System.out.println("getFullness");
        Pet instance = new Pet("Buddy", 50, 80, 50, 60, true, "NORMAL");
        int expResult = 60;
        int result = instance.getFullness();
        assertEquals(expResult, result,  "Fullness should be correctly returned.");
       
    }

    /**
     * Test of setFullness method, of class Pet.
     */
    @Test
    public void testSetFullness() {
        System.out.println("setFullness");
        // Create a Pet instance
        Pet instance = new Pet("Buddy", 50, 50, 50, 50, true, "NORMAL");
        // Set fullness to a new value
        int fullness = 75;
        instance.setFullness(fullness);
        // Assertion to verify the change
        assertEquals(fullness, instance.getFullness(), "Fullness should be updated to the new value");
    }

    /**
     * Test of getState method, of class Pet.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        Pet instance = new Pet("Buddy", 50, 80, 50, 50, true, "NORMAL");
        String expResult = "NORMAL";
        String result = instance.getState();
        assertEquals(expResult, result, "State should be correctly returned.");
    }

    /**
     * Test of setState method, of class Pet.
     */
    @Test
    public void testSetState() {
        System.out.println("setState");
        String state = "NORMAL";
        Pet instance = new Pet("Buddy", 50, 80, 50, 60, true, "NORMAL");
        instance.setState(state);

    }

    /**
     * Test of isAlive method, of class Pet.
     */
    @Test
    public void testIsAlive() {
        System.out.println("isAlive");
        Pet instance = new Pet("Buddy", 50, 80, 50, 60, true, "NORMAL");
        boolean expResult = true;
        boolean result = instance.isAlive();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAlive method, of class Pet.
     */
    @Test
    public void testSetAlive() {
        System.out.println("setAlive");
        boolean alive = true;
        Pet instance = new Pet("Buddy", 50, 80, 50, 60, true, "NORMAL");
        instance.setAlive(alive);
    }
    
}
