/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.petiverse;

import java.time.LocalTime;
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
public class ParentalControlsTest {
    
    public ParentalControlsTest() {
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
     * Test of authenticate method, of class ParentalControls.
     */
    @Test
    public void testAuthenticate() {
        System.out.println("authenticate");
        ParentalControls instance = new ParentalControls("defaultPassword");  
        String attempt = "defaultPassword";  // Correct password
        boolean expResult = true;
        boolean result = instance.authenticate(attempt);
        assertEquals(expResult, result);
        attempt = "wrongPassword";
        expResult = false;
        result = instance.authenticate(attempt);
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class ParentalControls.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        ParentalControls instance = new ParentalControls("oldPassword");  // Initialize instance
        String newPassword = "newSecurePassword";
        instance.setPassword(newPassword); 
        assertTrue(instance.authenticate(newPassword));
        assertFalse(instance.authenticate("oldPassword"));
    }

    /**
     * Test of setRestrictionsEnabled method, of class ParentalControls.
     */
    @Test
    public void testSetRestrictionsEnabled() {
        System.out.println("setRestrictionsEnabled");
        ParentalControls instance = new ParentalControls("testPassword");  // Initialize instance
        instance.setRestrictionsEnabled(true);
        assertTrue(instance.isRestrictionsEnabled());
        instance.setRestrictionsEnabled(false);
        assertFalse(instance.isRestrictionsEnabled());
    }

    /**
     * Test of setAllowedPlayHours method, of class ParentalControls.
     */
    @Test
    public void testSetAllowedPlayHours() {
        System.out.println("setAllowedPlayHours");
        ParentalControls instance = new ParentalControls("testPassword");
        instance.setRestrictionsEnabled(true);
        instance.setAllowedPlayHours(9, 18);
        assertEquals("Allowed play hours: 9 to 18", instance.getRestrictions());
        instance.setAllowedPlayHours(25, 30);
        assertEquals("Allowed play hours: 23 to 24", instance.getRestrictions());
        instance.setAllowedPlayHours(20, 5);
        assertEquals("Allowed play hours: 20 to 5", instance.getRestrictions());
    }

    /**
     * Test of canPlayNow method, of class ParentalControls.
     */
    @Test
    public void testCanPlayNow() {
        System.out.println("canPlayNow");
        ParentalControls instance = new ParentalControls("testPassword"); 
        instance.setRestrictionsEnabled(false);
        assertTrue(instance.canPlayNow());
        instance.setRestrictionsEnabled(true);
        instance.setAllowedPlayHours(8, 20);
    }

    /**
     * Test of addPlayTime method, of class ParentalControls.
     */
    @Test
    public void testAddPlayTime() {
        System.out.println("addPlayTime");
        ParentalControls instance = new ParentalControls("testPassword"); // Initialize instance
        float initialPlayTime = instance.getTotalPlayTime();
        float minutesPlayed = 30.0F;
        instance.addPlayTime(minutesPlayed);
        assertEquals(initialPlayTime + minutesPlayed, instance.getTotalPlayTime(), 0.001);
    }

    /**
     * Test of incrementSessionCount method, of class ParentalControls.
     */
    @Test
    public void testIncrementSessionCount() {
        System.out.println("incrementSessionCount");
        ParentalControls instance = new ParentalControls("testPassword"); // Initialize instance
        int initialSessionCount = instance.getSessionCount(); 
        instance.incrementSessionCount(); 
        assertEquals(initialSessionCount + 1, instance.getSessionCount());
    }

    /**
     * Test of getTotalPlayTime method, of class ParentalControls.
     */
    @Test
    public void testGetTotalPlayTime() {
        System.out.println("getTotalPlayTime");
        ParentalControls instance = new ParentalControls("testPassword"); // Initialize instance
        float expResult = 0.0F; 
        float result = instance.getTotalPlayTime(); 
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of getAveragePlayTime method, of class ParentalControls.
     */
    @Test
    public void testGetAveragePlayTime() {
        System.out.println("getAveragePlayTime");
        ParentalControls instance = new ParentalControls("testPassword"); // Initialize instance
        float expResult = 0.0F; 
        float result = instance.getAveragePlayTime();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of resetPlayTimeStats method, of class ParentalControls.
     */
    @Test
    public void testResetPlayTimeStats() {
        System.out.println("resetPlayTimeStats");
        ParentalControls instance = new ParentalControls("testPassword");
        instance.addPlayTime(30.0F); 
        instance.incrementSessionCount(); 
        assertEquals(30.0F, instance.getTotalPlayTime(), 0); 
        assertEquals(1, instance.getSessionCount()); 
        instance.resetPlayTimeStats();
        assertEquals(0.0F, instance.getTotalPlayTime(), 0); 
        assertEquals(0, instance.getSessionCount());
    }

    /**
     * Test of revivePet method, of class ParentalControls.
     */
    @Test
    public void testRevivePet() {
        System.out.println("revivePet");
        Pet pet = new Pet("Foxy", 50, 50, 50, 50, false, "SICK");
        ParentalControls instance = new ParentalControls("testPassword");
        instance.revivePet(pet);
        assertEquals(100, pet.getHealth());
        assertEquals(100, pet.getFullness());
        assertEquals(100, pet.getHappiness());
        assertEquals(100, pet.getSleep());
        assertEquals("NORMAL", pet.getState());
    }

    /**
     * Test of isRestrictionsEnabled method, of class ParentalControls.
     */
    @Test
    public void testIsRestrictionsEnabled() {
        System.out.println("isRestrictionsEnabled");
        ParentalControls instance = new ParentalControls("testPassword");
        boolean expResult = false;
        boolean result = instance.isRestrictionsEnabled();
        assertEquals(expResult, result);
        instance.setRestrictionsEnabled(true);
        expResult = true;
        result = instance.isRestrictionsEnabled();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRestrictions method, of class ParentalControls.
     */
    @Test
    public void testGetRestrictions() {
        System.out.println("getRestrictions");
        ParentalControls instance = new ParentalControls("testPassword");
        String expResult = "No restrictions";
        String result = instance.getRestrictions();
        assertEquals(expResult, result);
        instance.setRestrictionsEnabled(true);
        instance.setAllowedPlayHours(9, 17);
        expResult = "Allowed play hours: 9 to 17";
        result = instance.getRestrictions();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSessionCount method, of class ParentalControls.
     */
    @Test
    public void testGetSessionCount() {
        System.out.println("getSessionCount");
        ParentalControls instance = new ParentalControls("testPassword");
        int expResult = 0;
        int result = instance.getSessionCount();
        assertEquals(expResult, result);
        instance.incrementSessionCount();
        expResult = 1;
        result = instance.getSessionCount();
        assertEquals(expResult, result);
    }
    
}
