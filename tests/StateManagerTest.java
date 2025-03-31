/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.petiverse;

import javax.swing.Timer;
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
public class StateManagerTest {
    
    public StateManagerTest() {
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
     * Test of setPetState method, of class StateManager.
     */
    @Test
    public void testSetPetState() {
        System.out.println("setPetState");
        Pet pet = new Pet("Buddy", 50, 80, 50, 60, true, "NORMAL"); 
        StateManager instance = new StateManager(pet, new int[]{1, 1, 1}); 
        String newState = "SLEEPING";
        instance.setPetState(newState);
        assertEquals("SLEEPING", pet.getState()); 
        newState = "ANGRY";
        instance.setPetState(newState);
        assertEquals("ANGRY", pet.getState()); 
        newState = "HUNGRY";
        instance.setPetState(newState);
        assertEquals("HUNGRY", pet.getState()); 
    }

    /**
     * Test of addStateChangeListener method, of class StateManager.
     */
    @Test
    public void testAddStateChangeListener() {
        System.out.println("addStateChangeListener");
        Pet pet = new Pet("Buddy", 50, 80, 50, 60, true, "NORMAL"); 
        StateManager instance = new StateManager(pet, new int[]{1, 1, 1});
        StateManager.StateChangeListener listener = new StateManager.StateChangeListener() {
            @Override
            public void onStateChange(String newState) {
                // Verify that state change notifications are received
                assertEquals("ANGRY", newState); // Example check, expecting "ANGRY"
            }

            @Override
            public void onStatWarning(String stat, boolean isWarning) {
                // Handle stat warnings if necessary for your test
            }
        };
        instance.addStateChangeListener(listener);
        instance.setPetState("ANGRY");
    }

    /**
     * Test of start method, of class StateManager.
     */
    @Test
    public void testStart() {
        System.out.println("start");
        Pet pet = new Pet("Buddy", 50, 80, 50, 60, true, "NORMAL");
        StateManager instance = new StateManager(pet, new int[]{1, 1, 1}); 
        Timer decayTimer = instance.getDecayTimer();
        decayTimer.stop();
        assertFalse(decayTimer.isRunning());
        instance.start();
        assertTrue(decayTimer.isRunning());
    }

    /**
     * Test of stop method, of class StateManager.
     */
    @Test
    public void testStop() {
        System.out.println("stop");
        Pet pet = new Pet("Buddy", 50, 80, 50, 60, true, "NORMAL");
        StateManager instance = new StateManager(pet, new int[]{1, 1, 1});
        Timer decayTimer = instance.getDecayTimer();
        assertTrue(decayTimer.isRunning()); 
        instance.stop();
        assertFalse(decayTimer.isRunning());
    }

    /**
     * Test of revivePet method, of class StateManager.
     */
    @Test
    public void testRevivePet() {
        System.out.println("revivePet");
        Pet pet = new Pet("Buddy", 50, 80, 50, 60, true, "NORMAL");
        StateManager instance = new StateManager(pet, new int[]{1, 1, 1});  
        instance.revivePet();
        assertEquals(100, pet.getHealth());
        assertEquals(100, pet.getSleep());
        assertEquals(100, pet.getFullness());
        assertEquals(100, pet.getHappiness());
        assertEquals("NORMAL", pet.getState());
    }
    
}
