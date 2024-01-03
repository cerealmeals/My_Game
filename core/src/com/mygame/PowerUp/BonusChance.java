package com.mygame.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class BonusChance extends PowerUp {

    public BonusChance(GameLogic g) {
        super(g);
        Name = "More Bonus Rewards";
        description = "Increase the chance\nthat Bonus Rewards\nare spawned. \nFrom: "+ 
        String.format("%.2f", (game.rewardClient.getbonusRewardChance()*100f)) + "%"
         + "\nTo: " + String.format("%.2f", ((game.rewardClient.getbonusRewardChance()+0.05)*100f)) + "%";
    }

    @Override
    public void clicked(){
        game.rewardClient.setbonusRewardChance(game.rewardClient.getbonusRewardChance()+0.05f);
    }
    
}
