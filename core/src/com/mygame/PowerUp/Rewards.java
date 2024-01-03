package com.mygame.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class Rewards extends PowerUp{

    public Rewards(GameLogic g) {
        super(g);
    }
    
    @Override
    public void clicked(){
        game.rewardClient.setNumRewards(game.rewardClient.getNumRewards()+10);
    }
}
