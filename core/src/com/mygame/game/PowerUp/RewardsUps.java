package com.mygame.game.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class RewardsUps extends PowerUp{

    public RewardsUps(GameLogic g) {
        super(g);
        Name = "More Rewards";
        description = "Increase the number\nof rewards\nFrom: "+ game.rewardClient.getNumRewards()
         + "\nTo: " + (game.rewardClient.getNumRewards()+10) + "\nPlus the usual increase";
    }
    
    @Override
    public void clicked(){
        game.rewardClient.setNumRewards(game.rewardClient.getNumRewards()+10);
    }
}
