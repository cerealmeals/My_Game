package com.mygame.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class RewardsUps extends PowerUp{

    public RewardsUps(GameLogic g) {
        super(g);
        Name = "More Rewards";
        description = "Increase the number of rewards\n From: "+ game.rewardClient.getNumRewards()
         + "\n To: " + game.rewardClient.getNumRewards()+10 + "\n Plus the usual increase";
    }
    
    @Override
    public void clicked(){
        game.rewardClient.setNumRewards(game.rewardClient.getNumRewards()+10);
    }
}
