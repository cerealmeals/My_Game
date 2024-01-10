package com.mygame.game.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class PlayerSpeed extends PowerUp {

    public PlayerSpeed(GameLogic g) {
        super(g);
        Name = "Speed";
        description = "Increase how fast\nyou can move.";
    }
    
    @Override
    public void clicked(){
        game.PlayerSpeed *= 0.9f;
    }
}
