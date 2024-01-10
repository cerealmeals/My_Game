package com.mygame.game.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class FlameDamage extends PowerUp {

    public FlameDamage(GameLogic g) {
        super(g);
        Name = "Flame Damage";
        description = "Your fire trail\ninstantly kills enemies";
    }

    @Override
    public void clicked(){
        game.player.setFlameDamage(game.enemy_HP);
    }
    
}
