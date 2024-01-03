package com.mygame.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygame.PowerUp.PowerUp;
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
		
        game.font.draw(game.batch, "SELECT A POWER UP", (Gdx.graphics.getWidth()/3), Gdx.graphics.getHeight()-1);
        
        for(int i = 0; i < 3; i++){
        draw_Power_Up(i*Gdx.graphics.getWidth()/3, game.powerUpClient.choices.get(i));
        }

        if (Gdx.input.isTouched()) {
            game.setScreen(new LevelOverScreen(game));
            dispose();
        }
        game.batch.end();
    }

    public void draw_Power_Up(int position_x, PowerUp power){

        if(Gdx.input.getX() > position_x && 
        Gdx.input.getX() < position_x && 
        Gdx.input.getY() < Gdx.graphics.getHeight() - position_y && 
        Gdx.input.getY() > Gdx.graphics.getHeight() - position_y)
        {
            game.font.draw(game.batch, power.description, position_x +50, Gdx.graphics.getHeight()*2/3);
            power.clicked();
        }
        else{
            game.font.draw(game.batch, power.Name, position_x, Gdx.graphics.getHeight()/2);
            
        }
    }

    @Override
    public void dispose() {
        
    }
}
