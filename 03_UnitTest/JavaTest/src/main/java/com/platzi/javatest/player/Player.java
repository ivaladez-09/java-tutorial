package com.platzi.javatest.player;

public class Player {

    private Dice dice;
    private int minNumberToWin;

    public Player(Dice dice, int minNumberToWin) {
        this.dice = dice;
        this.minNumberToWin = minNumberToWin;
    }


    public boolean play(){
        int dice_number = dice.roll();
        return dice_number > minNumberToWin;
    }
}
