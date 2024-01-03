package com.mygame.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class RewardsUps extends PowerUp{

    public RewardsUps(GameLogic g) {
        super(g);
    }
    
    @Override
    public void clicked(){
        game.rewardClient.setNumRewards(game.rewardClient.getNumRewards()+10);
    }
}
