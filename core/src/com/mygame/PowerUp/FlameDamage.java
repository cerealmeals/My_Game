package com.mygame.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class FlameDamage extends PowerUp {

    public FlameDamage(GameLogic g) {
        super(g);
        Name = "Flame Damage";
        description = "Increase the damage\nyour fire trail does\nto enemies \nFrom: "+ game.player.getFlameDamage()
         + "\nTo: " + (game.player.getFlameDamage()+1);
    }

    @Override
    public void clicked(){
        game.player.setFlameDamage(game.player.getFlameDamage()+1);
    }
    
}
