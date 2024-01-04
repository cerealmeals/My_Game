package com.mygame.game.Screens;

import com.badlogic.gdx.Gdx;
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
		
        game.font.draw(game.batch, "SELECT A POWER UP", (Gdx.graphics.getWidth()/4), Gdx.graphics.getHeight()-1);
    
        game.font.getData().setScale(2);

        for(int i = 0; i < 3; i++){
        draw_Power_Up(i*Gdx.graphics.getWidth()/3, game.powerUpClient.choices.get(i));
        }

        game.font.draw(game.batch, "Number of Rewards increases by 5\nAnd number of enemies increase by 2\nEnemies also move faster",
         (Gdx.graphics.getWidth()/4), 100);

        game.font.getData().setScale(game.scale);
        game.batch.end();
    }

    public void draw_Power_Up(int position_x, PowerUp power){

        if(Gdx.input.getX() > position_x && 
        Gdx.input.getX() < position_x + Gdx.graphics.getWidth()/3 && 
        Gdx.input.getY() < Gdx.graphics.getHeight() -100 && 
        Gdx.input.getY() > 100)
        {
            game.font.draw(game.batch, power.description, position_x, Gdx.graphics.getHeight()*2/3);
            if (Gdx.input.isTouched()) {
                power.clicked();
                game.setScreen(new LevelOverScreen(game));
                dispose();
            }    
        }
        else{
            game.font.draw(game.batch, power.Name, position_x, Gdx.graphics.getHeight()/2);
            
        }
    }

    @Override
    public void dispose() {
    }
}
