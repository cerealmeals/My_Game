package com.mygame.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class FlameDamage extends PowerUp {

    public FlameDamage(GameLogic g) {
        super(g);
    }

    @Override
    public void clicked(){
        game.player.setFlameDamage(game.player.getFlameDamage()+1);
    }
    
}
