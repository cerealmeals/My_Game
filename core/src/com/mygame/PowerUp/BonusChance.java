package com.mygame.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class BonusChance extends PowerUp {

    public BonusChance(GameLogic g) {
        super(g);
        Name = "More Bonus Rewards";
        description = "Increase the chance that Bonus Rewards are spawned. \n From: "+ game.rewardClient.getbonusRewardChance()*100 + "%"
         + "\n To: " + (game.rewardClient.getbonusRewardChance()+0.05f)*100 + "%";
    }

    @Override
    public void clicked(){
        game.rewardClient.setbonusRewardChance(game.rewardClient.getbonusRewardChance()+0.05f);
    }
    
}
