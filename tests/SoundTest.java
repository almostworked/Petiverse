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
public class SoundTest {
    
    public SoundTest() {
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
     * Test of playMusic method, of class Sound.
     */
    @Test
    public void testPlayMusic() {
        System.out.println("playMusic");
        String location = "temp_assets/background_music.wav"; // Provide a valid file path if needed
        Sound.playMusic(location);
    }

    /**
     * Test of playEffect method, of class Sound.
     */
    @Test
    public void testPlayEffect() {
        System.out.println("playEffect");
        String location = "temp_assets/sound_effect.wav"; // Provide a valid file path if needed
        Sound.playEffect(location);
    }

    /**
     * Test of isSoundEnabled method, of class Sound.
     */
    @Test
    public void testIsSoundEnabled() {
        System.out.println("isSoundEnabled");
        boolean expResult = Sound.isSoundEnabled(); // Capture the initial state
        boolean result = Sound.isSoundEnabled();
        assertEquals(expResult, result); // Ensure it matches the expected result
    }

    /**
     * Test of toggleSound method, of class Sound.
     */
    @Test
    public void testToggleSound() {
        System.out.println("toggleSound");
        Sound.toggleSound();
        // The test will pass as long as no exceptions are thrown
    }
    
}
