package com.mygame.game.Screens;

import com.mygame.game.GameLogic.GameLogic;

public class PowerUpScreen extends SuperScreen {

    public PowerUpScreen(GameLogic game) {
        super(game);

        game.powerUpClient.generatePowerUps(game);
    }
    
    @Override
    public void render(float delta) {
        
    }

    @Override
    public void dispose() {
        
    }
}
