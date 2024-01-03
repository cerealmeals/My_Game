package com.mygame.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class PlayerSpeed extends PowerUp {

    public PlayerSpeed(GameLogic g) {
        super(g);
        Name = "Speed";
        description = "Increase how fast you can move. \n From: Move every "+ game.PlayerSpeed + "seconds"
         + "\n To: Move every "+ game.PlayerSpeed*0.9f + "seconds";
    }
    
    @Override
    public void clicked(){
        game.PlayerSpeed *= 0.9f;
    }
}
