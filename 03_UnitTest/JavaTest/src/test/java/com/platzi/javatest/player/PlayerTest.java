package com.platzi.javatest.player;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void looses_when_dice_number_is_too_low(){

        // Mock object - Simulate the object
        Dice dice = Mockito.mock(Dice.class);
        Mockito.when(dice.roll()).thenReturn(2);  // Forcing to get a specific result

        Player player = new Player(dice, 5);
        assertFalse(player.play());
    }

    @Test
    public void wins_when_dice_number_is_big(){

        // Mock object - Simulate the object
        Dice dice = Mockito.mock(Dice.class);
        Mockito.when(dice.roll()).thenReturn(4);  // Forcing to get a specific result

        Player player = new Player(dice, 3);
        assertTrue(player.play());
    }

}