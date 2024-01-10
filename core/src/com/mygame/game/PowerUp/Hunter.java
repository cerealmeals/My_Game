package com.mygame.game.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class Hunter extends PowerUp {

    public Hunter(GameLogic g) {
        super(g);
        Name = "Hunter";
        description = "Enemies are worth\npoints when defeated\nand you can defeat\nthem easier";
    }

    @Override
    public void clicked(){
        game.player.enemy_kill_value += 50;
        game.player.setFlameDamage(game.player.getFlameDamage()+1);
    }
    
}
