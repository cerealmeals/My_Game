package com.mygame.game.Levels;

import com.mygame.game.GameLogic.GameLogic;

public class Tutorial extends Level{
    
    public Tutorial(GameLogic g){
        super(g);
        Name = "Tutorial"; 
        desciption = "A starter level\nwith only 1 starting\n enemy and a win\ncondition of 200 points";
    }

    @Override
    public void clicked(){
        game.initial_number_of_enemies = 1;
        game.winCondition = 200;
    }
}
