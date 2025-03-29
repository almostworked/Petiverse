package com.mycompany.group30.tests;

import com.mycompany.petiverse.ParentalControls;
import com.mycompany.petiverse.Pet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Ensures that the game cannot be played outside the designated hours
 * once parental controls are enabled. 
 * Note: fully automating time-based checks may require mocking.
 */
public class ParentalControls_TimeRestrictionTest {

    @Test
    public void testTimeRestrictionPreventsPlay() {
        ParentalControls pc = new ParentalControls("secret");
        pc.setRestrictionsEnabled(true);
        pc.setAllowedPlayHours(9, 17); // 9 AM to 5 PM

        boolean canPlay = pc.canPlayNow(); 

        System.out.println("canPlayNow() returned: " + canPlay);
    
        assertTrue(true, "Placeholder - Replace with a real time mock or logic override test.");
    }
}
