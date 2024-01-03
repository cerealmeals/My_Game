package com.mygame.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class BonusChance extends PowerUp {

    public BonusChance(GameLogic g) {
        super(g);

    }

    @Override
    public void clicked(){
        game.rewardClient.setbonusRewardChance(game.rewardClient.getbonusRewardChance()+0.05f);
    }
    
}
