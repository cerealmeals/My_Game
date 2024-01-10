package com.mygame.game.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class RewardsUps extends PowerUp{

    public RewardsUps(GameLogic g) {
        super(g);
        Name = "More Stuff";
        description = "Increase the number\nof spawns on\nthe map (not enemies)";
    }
    
    @Override
    public void clicked(){
        game.rewardClient.setNumRewards(game.rewardClient.getNumRewards()+10);
    }
}
