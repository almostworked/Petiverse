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
public class SaveGameTest {
    
    public SaveGameTest() {
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
     * Test of setSavedName method, of class SaveGame.
     */
    @Test
    public void testSetSavedName() {
        System.out.println("setSavedName");
        String savedName = "TestName";
        SaveGame instance = new SaveGame(1, true); // Initialize instance properly
        instance.setSavedName(savedName); // Set saved name
    }

    /**
     * Test of savePet method, of class SaveGame.
     */
    @Test
    public void testSavePet() {
        System.out.println("savePet");
        Pet pet = new Pet("Buddy", 50, 80, 50, 60, true, "NORMAL"); // Provide a valid pet object
        SaveGame.savePet(pet);
    }

    /**
     * Test of save method, of class SaveGame.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        // Provide valid instances for pet, inventory, and parentalControls
        Pet pet = new Pet("Buddy", 50, 80, 50, 60, true, "NORMAL");
        Inventory inventory = new Inventory(); // Assuming a constructor exists
        ParentalControls parentalControls = new ParentalControls("password"); // Provide password to the constructor
        SaveGame instance = new SaveGame(1, true); // Assuming SaveGame has a constructor with saveSlot and isParent as parameters
        instance.save(pet, inventory, parentalControls); // Call the save method
    }
    
}
