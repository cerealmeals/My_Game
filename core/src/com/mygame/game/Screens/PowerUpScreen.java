package com.mygame.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygame.game.GameLogic.GameLogic;

public class PowerUpScreen extends SuperScreen {

    public PowerUpScreen(GameLogic game) {
        super(game);

        game.powerUpClient.generatePowerUps(game);
    }
    
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		
        game.font.draw(game.batch, "SELECT A POWER UP", (Gdx.graphics.getWidth()/2) - 20, Gdx.graphics.getHeight()-1);
        if (Gdx.input.isTouched()) {
            game.setScreen(new LevelOverScreen(game));
            dispose();
        }
        game.batch.end();
    }

    @Override
    public void dispose() {
        
    }
}
