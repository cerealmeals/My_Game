package com.mygame.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class PlayerSpeed extends PowerUp {

    public PlayerSpeed(GameLogic g) {
        super(g);
    }
    
    @Override
    public void clicked(){
        game.PlayerSpeed *= 0.9f;
    }
}
