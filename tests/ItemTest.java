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
public class ItemTest {
    
    public ItemTest() {
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
     * Test of getName method, of class Item.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Item instance = Item.APPLE;
        String expResult = "Apple";  // Corrected expected result
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class Item.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Item instance = Item.APPLE;  // Use a predefined item like APPLE
        Item.ItemType expResult = Item.ItemType.FOOD;  // Expected result is FOOD
        Item.ItemType result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEffectValue method, of class Item.
     */
    @Test
    public void testGetEffectValue() {
        System.out.println("getEffectValue");
        Item instance = Item.APPLE;
        int expResult = 20;
        int result = instance.getEffectValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of getItem method, of class Item.
     */
    @Test
    public void testGetItem() {
        System.out.println("getItem");
        String name = "Apple";
        Item.ItemType type = Item.ItemType.FOOD;
        Item expResult = Item.APPLE;
        Item result = Item.getItem(name, type);
        assertEquals(expResult, result);
    }

    /**
     * Test of setQuantity method, of class Item.
     */
    @Test
    public void testSetQuantity() {
        System.out.println("setQuantity");
        int i = 10;
        Item instance = Item.APPLE;
        //instance.setQuantity(i);
    }
    
}
