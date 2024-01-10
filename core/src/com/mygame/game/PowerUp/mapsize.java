package com.mygame.game.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class mapsize extends PowerUp {

    public mapsize(GameLogic g) {
        super(g);
        Name = "MapSize";
        description = "Increase the mapsize";
    }

    @Override
    public void clicked(){
        game.mapHeight += 5;
    }
    
}
