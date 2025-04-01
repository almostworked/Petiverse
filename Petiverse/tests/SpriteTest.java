/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.petiverse;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
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
public class SpriteTest {
    
    public SpriteTest() {
        
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
     * Test of setSpriteImage method, of class Sprite.
     */
    @Test
    public void testSetSpriteImage() {
        System.out.println("setSpriteImage");
        Sprite instance = new Sprite("TestPet", 100, 100, 100, 100, true, "NORMAL");
        String spriteImage = "test_image.png";
        instance.setSpriteImage(spriteImage);
        assertEquals(spriteImage, instance.displaySpriteImage());
    }

    /**
     * Test of displaySpriteImage method, of class Sprite.
     */
    @Test
    public void testDisplaySpriteImage() {
        System.out.println("displaySpriteImage");
        Sprite instance = new Sprite("TestPet", 100, 100, 100, 100, true, "NORMAL");
        String expResult = "temp_assets/TestPet.png";
        instance.setSpriteImage(expResult); // Set the sprite image
        String result = instance.displaySpriteImage();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSpriteAnimation method, of class Sprite.
     */
    @Test
    public void testSetSpriteAnimation() {
        String spriteAnimation = "walk"; // Set a test animation
        Sprite instance = new Sprite("TestPet", 100, 100, 100, 100, true, "NORMAL");
        instance.setSpriteAnimation(spriteAnimation); // Set the sprite animation
        assertEquals(spriteAnimation, instance.displaySpriteAnimation());
    }

    /**
     * Test of displaySpriteAnimation method, of class Sprite.
     */
    @Test
    public void testDisplaySpriteAnimation() {
        System.out.println("displaySpriteAnimation");
        String expResult = "walk"; // Expected animation
        Sprite instance = new Sprite("TestPet", 100, 100, 100, 100, true, "NORMAL");
        instance.setSpriteAnimation(expResult); // Set the sprite animation
        String result = instance.displaySpriteAnimation(); // Get the current animation
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Sprite.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "TestPet"; // Expected pet name
        Sprite instance = new Sprite("TestPet", 100, 100, 100, 100, true, "NORMAL");
        String result = instance.getName(); // Get the pet name
        assertEquals(expResult, result);
    }

    /**
     * Test of setCurrentState method, of class Sprite.
     */
    @Test
    public void testSetCurrentState() {
        String state = "HUNGRY"; // Set the state to a test state
        Sprite instance = new Sprite("TestPet", 100, 100, 100, 100, true, "NORMAL");
        // Call the setCurrentState method to change the state
        instance.setCurrentState(state);
        // Verify that the state was set correctly
        assertEquals(state, instance.getCurrentState());
    }

    /**
     * Test of addPropertyChangeListener method, of class Sprite.
     */
    @Test
    public void testAddPropertyChangeListener() {
        System.out.println("addPropertyChangeListener");
        System.out.println("addPropertyChangeListener");
        // Create a PropertyChangeListener that will be notified when the state changes
        PropertyChangeListener listener = evt -> {
            // Verify the event's property name and new value
            assertEquals("currentState", evt.getPropertyName());
            assertEquals("HUNGRY", evt.getNewValue());
        };
        // Create a Sprite instance and add the listener
        Sprite instance = new Sprite("TestPet", 100, 100, 100, 100, true, "NORMAL");
        instance.addPropertyChangeListener(listener);
        // Change the state, which should trigger the listener
        instance.setCurrentState("HUNGRY");
    }

    /**
     * Test of getCurrentState method, of class Sprite.
     */
    @Test
    public void testGetCurrentState() {
        System.out.println("getCurrentState");
        Sprite instance = new Sprite("TestPet", 100, 100, 100, 100, true, "NORMAL");
        String expResult = "NORMAL";
        String result = instance.getCurrentState();
        assertEquals(expResult, result);
    }

    /**
     * Test of updateSprite method, of class Sprite.
     */
    @Test
    public void testUpdateSprite() {
        System.out.println("updateSprite");
        Sprite instance = new Sprite("TestPet", 100, 100, 100, 100, true, "NORMAL");

        // Ensure the sprite has some images loaded based on its state
        instance.updateSprite();

        // Retrieve the list of sprite images after calling updateSprite
        List<String> spriteImages = instance.getSprites();

        // Assert that the sprite images list is not empty
        assertTrue(!spriteImages.isEmpty(), "The sprite images list should not be empty after updateSprite");

        // Optionally, you can verify the first image name to check if it matches expectations
        assertEquals("temp_assets/TestPet.png", spriteImages.get(0), "The first sprite image should match the expected path");
    }

    /**
     * Test of getSprites method, of class Sprite.
     */
    @Test
    public void testGetSprites() {
        System.out.println("getSprites");
        Sprite instance = new Sprite("TestPet", 100, 100, 100, 100, true, "NORMAL");

        instance.updateSprite();
        List<String> result = instance.getSprites();
        List<String> expResult = new ArrayList<>();
        expResult.add("temp_assets/TestPet.png");
        expResult.add("temp_assets/TestPet-Sprite1.png");
        expResult.add("temp_assets/TestPet-Sprite2.png");
        assertEquals(expResult, result, "The sprite images should match the expected list");
    }

    /**
     * Test of getFrame method, of class Sprite.
     */
    @Test
    public void testGetFrame() {
        System.out.println("getFrame");
        Sprite instance = new Sprite("TestPet", 100, 100, 100, 100, true, "NORMAL");
        instance.updateSprite();
        String result = instance.getFrame();
        String expResult = "temp_assets/TestPet.png"; 
        assertEquals(expResult, result, "The frame should match the first image in the sprite images list");
    }

    /**
     * Test of nextFrame method, of class Sprite.
     */
    @Test
    public void testNextFrame() {
        System.out.println("nextFrame");
        Sprite instance = new Sprite("TestPet", 100, 100, 100, 100, true, "NORMAL");
        instance.updateSprite(); // Update sprite images based on current state
        String currentFrameBefore = instance.getFrame();
        instance.nextFrame();
        String currentFrameAfter = instance.getFrame();
        assertNotEquals("The frame should have changed after calling nextFrame", currentFrameBefore, currentFrameAfter);
    }

    /**
     * Test of resetAnimation method, of class Sprite.
     */
    @Test
    public void testResetAnimation() {
        System.out.println("resetAnimation");
        Sprite instance = new Sprite("TestPet", 100, 100, 100, 100, true, "NORMAL");
        // Ensure that the first frame is what we expect
        String initialFrame = instance.getFrame(); // Should be temp_assets/TestPet.png
        // Call nextFrame to change the frame
        instance.nextFrame();
        // Store the frame after advancing the animation
        String nextFrame = instance.getFrame();  // This should now be the second frame, temp_assets/TestPet-Sprite1.png
        // Call resetAnimation to reset to the first frame
        instance.resetAnimation();
        // Verify that after reset, the frame is the first one again
        String result = instance.getFrame();
        // Assert that the frame after reset is the first frame (temp_assets/TestPet.png)
        assertEquals(initialFrame, result, "The frame should be reset to the first frame after resetAnimation.");

    }
    
}
