package com.mygame.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class PunishmentChance extends PowerUp{

    public PunishmentChance(GameLogic g) {
        super(g);
        
    }

    @Override
    public void clicked(){
        game.rewardClient.setpunishmentChance(game.rewardClient.getpunishmentChance()*0.8f);
    }
    
}
