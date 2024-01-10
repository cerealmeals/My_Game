package com.mygame.game.Levels;

import com.mygame.game.GameLogic.GameLogic;

public class Quick extends Level{

    public Quick(GameLogic g) {
        super(g);
        Name = "Quick";
        desciption = "Fast level with 3\nstarting enemies\nand 500 point\nwin condition";
    }

    @Override
    public void clicked(){
        
    }
    
}
