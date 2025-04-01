/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.petiverse;

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
public class InventoryTest {
    
    public InventoryTest() {
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
     * Test of getQuantity method, of class Inventory.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        Item item = null;
        Inventory instance = new Inventory();
        int expResult = 0;
        int result = instance.getQuantity(item);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setQuantity method, of class Inventory.
     */
    @Test
    public void testSetQuantity() {
        System.out.println("setQuantity");
        Item item = null;
        int quantity = 0;
        Inventory instance = new Inventory();
        instance.setQuantity(item, quantity);
        
    }

    /**
     * Test of displayInventory method, of class Inventory.
     */
    @Test
    public void testDisplayInventory() {
        System.out.println("displayInventory");
        Inventory instance = new Inventory();
        // Expected result depends on what the method does
        Inventory result = instance.displayInventory();

        assertNotNull(result);  // Ensure it's not null
        assertEquals(instance, result); // If it returns itself
        
    }

    /**
     * Test of displayMethods method, of class Inventory.
     */
    @Test
    public void testDisplayMethods() {
        System.out.println("displayMethods");
        Inventory instance = new Inventory();
        instance.displayMethods();
        
    }

    /**
     * Test of updateInventory method, of class Inventory.
     */
    @Test
    public void testUpdateInventory() {
        System.out.println("updateInventory");
        Item item = null;
        int quantity = 0;
        Inventory instance = new Inventory();
        instance.updateInventory(item, quantity);
    }

    /**
     * Test of removeItem method, of class Inventory.
     */
    @Test
    public void testRemoveItem() {
        System.out.println("removeItem");
        Item item = null;
        int quantity = 0;
        Inventory instance = new Inventory();
        instance.removeItem(item, quantity);
        
    }

    /**
     * Test of getItems method, of class Inventory.
     */
    @Test
    public void testGetItems() {
        System.out.println("getItems");
        Inventory instance = new Inventory();
        List<Inventory.Entry> result = instance.getItems();
        assertNotNull(result);  // Ensure the list is not null
        assertFalse(result.isEmpty());  // The list should NOT be empty
        // Verify default items
        assertEquals(3, result.size());  // Expecting 3 items
}
        
   
    
}
