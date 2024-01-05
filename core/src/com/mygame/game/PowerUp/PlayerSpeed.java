package com.mygame.game.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class PlayerSpeed extends PowerUp {

    public PlayerSpeed(GameLogic g) {
        super(g);
        Name = "Speed";
        description = "Increase how fast\nyou can move. \nFrom: "+ 
        String.format("%.2f",game.PlayerSpeed)
         + "\nTo: "+ String.format("%.2f",game.PlayerSpeed*0.9f);
    }
    
    @Override
    public void clicked(){
        game.PlayerSpeed *= 0.9f;
    }
}
