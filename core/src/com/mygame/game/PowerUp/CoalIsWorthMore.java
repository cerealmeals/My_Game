package com.mygame.game.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class CoalIsWorthMore extends PowerUp {

    public CoalIsWorthMore(GameLogic g) {
        super(g);
        Name = "Better Coal";
        description = "Increase the score\nof collecting coal";
    }

    @Override
    public void clicked(){
        game.rewardClient.coalScore += 5;
    }
    
}
