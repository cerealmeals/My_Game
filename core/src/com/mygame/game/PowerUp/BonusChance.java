package com.mygame.game.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class BonusChance extends PowerUp {

    public BonusChance(GameLogic g) {
        super(g);
        Name = "More Explosions";
        description = "Increase the chance\nthat explosions\nare spawned. \nFrom: "+ 
        String.format("%.2f", (game.rewardClient.getbonusRewardChance()*100f)) + "%"
         + "\nTo: " + String.format("%.2f", ((game.rewardClient.getbonusRewardChance()+0.2)*100f)) + "%";
    }

    @Override
    public void clicked(){
        game.rewardClient.setbonusRewardChance(game.rewardClient.getbonusRewardChance()+0.2f);
    }
    
}
