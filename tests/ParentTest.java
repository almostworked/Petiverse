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
public class ParentTest {
    
    public ParentTest() {
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
     * Test of authenticate method, of class Parent.
     */
    @Test
    public void testAuthenticate() {
        System.out.println("authenticate");
        Inventory inventory = new Inventory();
        Pet pet = new Pet("Foxy", 100, 100, 100, 100, true, "NORMAL");
        Parent instance = new Parent("Parent1", inventory, true, pet, "defaultPassword");
        String invalidAttempt = "wrongPassword";
        boolean expResult = false;
        boolean result = instance.authenticate(invalidAttempt);
        assertEquals(expResult, result);
        String validAttempt = "defaultPassword";
        expResult = true;
        result = instance.authenticate(validAttempt);
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class Parent.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        // Set up the necessary objects for the test
        Inventory inventory = new Inventory();
        Pet pet = new Pet("Foxy", 100, 100, 100, 100, true, "NORMAL");
        Parent instance = new Parent("Parent1", inventory, true, pet, "defaultPassword");
        String newPassword = "newPassword";
        instance.setPassword(newPassword);
        boolean expResult = true;
        boolean result = instance.authenticate(newPassword);
        assertEquals(expResult, result);
        String oldPassword = "defaultPassword";
        expResult = false;
        result = instance.authenticate(oldPassword);
        assertEquals(expResult, result);
    }

    /**
     * Test of isRestrictionsEnabled method, of class Parent.
     */
    @Test
    public void testIsRestrictionsEnabled() {
        System.out.println("isRestrictionsEnabled");
        Inventory inventory = new Inventory();
        Pet pet = new Pet("Foxy", 100, 100, 100, 100, true, "NORMAL");
        Parent instance = new Parent("Parent1", inventory, true, pet, "defaultPassword");
        instance.setRestrictions(true, 8, 20); // Example: Restrictions from 8 AM to 8 PM
        boolean expResult = true;
        boolean result = instance.isRestrictionsEnabled();
        assertEquals(expResult, result);
        instance.setRestrictions(false, 0, 0); // Disable restrictions
        expResult = false;
        result = instance.isRestrictionsEnabled();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRestrictions method, of class Parent.
     */
    @Test
    public void testSetRestrictions() {
        System.out.println("setRestrictions");
        Inventory inventory = new Inventory();
        Pet pet = new Pet("Foxy", 100, 100, 100, 100, true, "NORMAL");
        Parent instance = new Parent("Parent1", inventory, true, pet, "defaultPassword");
        boolean enabled = false;
        int startHour = 0;
        int endHour = 0;
        instance.setRestrictions(enabled, startHour, endHour);
        boolean expResult = false;
        boolean result = instance.isRestrictionsEnabled();
        assertEquals(expResult, result);
        enabled = true;
        startHour = 8;  // Example start hour: 8 AM
        endHour = 20;   // Example end hour: 8 PM
        instance.setRestrictions(enabled, startHour, endHour);
        expResult = true;
        result = instance.isRestrictionsEnabled();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTotalPlayTime method, of class Parent.
     */
    @Test
    public void testGetTotalPlayTime() {
        System.out.println("getTotalPlayTime");
        System.out.println("getTotalPlayTime");
        Inventory inventory = new Inventory();
        Pet pet = new Pet("Foxy", 100, 100, 100, 100, true, "NORMAL");
        Parent instance = new Parent("Parent1", inventory, true, pet, "defaultPassword");
        instance.addPlayTime(10.0f); 
        float expResult = 10.0f;  // The expected result is the total playtime in minutes
        float result = instance.getTotalPlayTime(); 
        assertEquals(expResult, result, 0);  // The delta of 0 ensures exact matching
    }

    /**
     * Test of getAveragePlayTime method, of class Parent.
     */
    @Test
    public void testGetAveragePlayTime() {
        System.out.println("getAveragePlayTime");
        Inventory inventory = new Inventory();
        Pet pet = new Pet("Foxy", 100, 100, 100, 100, true, "NORMAL");
        Parent instance = new Parent("Parent1", inventory, true, pet, "defaultPassword");
        instance.addPlayTime(10.0f); 
        instance.incrementSessionCount(); 
        instance.addPlayTime(20.0f); 
        instance.incrementSessionCount(); 
        float expResult = 15.0f;
        float result = instance.getAveragePlayTime(); 
        assertEquals(expResult, result, 0);  // The delta of 0 ensures exact matching
    }

    /**
     * Test of addPlayTime method, of class Parent.
     */
    @Test
    public void testAddPlayTime() {
        System.out.println("addPlayTime");
        Inventory inventory = new Inventory();
        Pet pet = new Pet("Foxy", 100, 100, 100, 100, true, "NORMAL");
        Parent instance = new Parent("Parent1", inventory, true, pet, "defaultPassword");

        // Simulate adding playtime
        float minutesPlayed = 15.0f; // Add 15 minutes of playtime
        instance.addPlayTime(minutesPlayed);

        // The expected total playtime should be 15.0f
        float expResult = 15.0f;
        float result = instance.getTotalPlayTime(); // Get the actual total playtime

        // Assert the expected result with the actual result
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of incrementSessionCount method, of class Parent.
     */
    @Test
    public void testIncrementSessionCount() {
        System.out.println("incrementSessionCount");
        Inventory inventory = new Inventory();

        // Set up the pet object
        Pet pet = new Pet("Foxy", 100, 100, 100, 100, true, "NORMAL");

        // Set up the parent with pet
        Parent instance = new Parent("Parent1", inventory, true, pet, "defaultPassword");

        // Initial session count should be 0
        int initialSessionCount = instance.getControls().getSessionCount();
        assertEquals(0, initialSessionCount);

        // Increment the session count
        instance.incrementSessionCount();

        // After increment, session count should be 1
        int updatedSessionCount = instance.getControls().getSessionCount();
        assertEquals(1, updatedSessionCount);

    }

    /**
     * Test of resetPlayTimeStats method, of class Parent.
     */
    @Test
    public void testResetPlayTimeStats() {
      
    }

    /**
     * Test of revivePet method, of class Parent.
     */
    @Test
    public void testRevivePet() {
        System.out.println("revivePet");
        Inventory inventory = new Inventory(); // Create a new Inventory
        Pet pet = new Pet("Foxy", 50, 50, 50, 50, false, "DEAD"); // Create a Pet in "DEAD" state
        Parent instance = new Parent("Parent1", inventory, true, pet, "defaultPassword");
        instance.revivePet();
        assertEquals("NORMAL", pet.getState()); // Pet should be revived to "NORMAL"
        assertEquals(100, pet.getHealth()); // Pet's health should be restored to 100
        assertEquals(100, pet.getFullness()); // Pet's fullness should be restored to 100
        assertEquals(100, pet.getHappiness()); // Pet's happiness should be restored to 100
        assertEquals(100, pet.getSleep()); // Pet's sleep should be restored to 100
    }

    /**
     * Test of getControls method, of class Parent.
     */
    @Test
    public void testGetControls() {
        System.out.println("getControls");
        Inventory inventory = new Inventory(); // Set up the inventory object
        Pet pet = new Pet("Foxy", 100, 100, 100, 100, true, "NORMAL"); // Set up the pet object
        Parent instance = new Parent("Parent1", inventory, true, pet, "defaultPassword");
        ParentalControls result = instance.getControls();
        ParentalControls expResult = instance.getControls(); 
        assertEquals(expResult, result);
    }
    
}
