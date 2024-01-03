package com.mygame.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class FlameDamage extends PowerUp {

    public FlameDamage(GameLogic g) {
        super(g);
        Name = "Flame Damage";
        description = "Increase the damage your fire trail does to enemies \n From: "+ game.player.getFlameDamage()
         + "\n To: " + game.player.getFlameDamage()+1;
    }

    @Override
    public void clicked(){
        game.player.setFlameDamage(game.player.getFlameDamage()+1);
    }
    
}
