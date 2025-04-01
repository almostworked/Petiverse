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
public class ParentalControls_TimeRestrictionTestTest {
    
    public ParentalControls_TimeRestrictionTestTest() {
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
     * Test of testTimeRestrictionPreventsPlay method, of class ParentalControls_TimeRestrictionTest.
     */
    @Test
    public void testTestTimeRestrictionPreventsPlay() {
        System.out.println("testTimeRestrictionPreventsPlay");
        ParentalControls_TimeRestrictionTest instance = new ParentalControls_TimeRestrictionTest();

        // Run the test method, assuming it contains assertions itself
        instance.testTimeRestrictionPreventsPlay();
    }
    
}
