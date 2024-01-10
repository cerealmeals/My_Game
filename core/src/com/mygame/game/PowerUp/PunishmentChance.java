package com.mygame.game.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class PunishmentChance extends PowerUp{

    public PunishmentChance(GameLogic g) {
        super(g);
        Name = "Less Puddles";
        description = "Decrease the chance\nthat puddles\nare spawned.";
    }

    @Override
    public void clicked(){
        game.rewardClient.setpunishmentChance(game.rewardClient.getpunishmentChance()*0.6f);
    }
    
}
