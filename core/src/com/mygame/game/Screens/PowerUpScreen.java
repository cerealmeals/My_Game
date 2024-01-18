package com.mygame.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygame.game.GameLogic.GameLogic;
import com.mygame.game.PowerUp.PowerUp;

public class PowerUpScreen extends SuperScreen {

    Sound door_creek;

    public PowerUpScreen(GameLogic game) {
        super(game);
        door_creek = Gdx.audio.newSound(Gdx.files.internal("Sounds/door_creek.wav"));
        game.powerUpClient.generatePowerUps(game);
    }
    @Override
    public void show(){
        door_creek.play(game.volume);
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
        game.layout.setText(game.font, "Select a Power Up");
        game.font.draw(game.batch, game.layout, (Gdx.graphics.getWidth()/2)-game.layout.width/2, Gdx.graphics.getHeight()-100+game.layout.height/2);
    
        game.font.getData().setScale(2);

        for(int i = 0; i < 3; i++){
        draw_Power_Up(i*Gdx.graphics.getWidth()/3, game.powerUpClient.choices.get(i));
        }

        //game.font.draw(game.batch, "5 more random stationary things spawn\nAnd number of enemies increase by 2\nEnemies also move faster",(Gdx.graphics.getWidth()/4), 100);

        game.font.getData().setScale(game.scale);
        game.batch.end();
    }

    public void draw_Power_Up(int position_x, PowerUp power){

        int third_x = Gdx.graphics.getWidth()/3;
        int half_y = Gdx.graphics.getHeight()/2;

        if(Gdx.input.getX() > position_x && 
        Gdx.input.getX() < position_x + third_x && 
        Gdx.input.getY() < Gdx.graphics.getHeight() -200 && 
        Gdx.input.getY() > 200)
        {
            game.layout.setText(game.font, power.description);
            game.font.draw(game.batch, game.layout, position_x+(third_x/2)-(game.layout.width/2), half_y + game.layout.height/2);
            if (Gdx.input.justTouched()) {
                power.clicked();
                game.setScreen(new LevelOverScreen(game));
                dispose();
            }    
        }
        else{
            game.layout.setText(game.font, power.Name);
            game.font.draw(game.batch, game.layout, position_x+(third_x/2)-(game.layout.width/2), half_y + game.layout.height/2);
            
        }
    }

    @Override
    public void dispose() {
        door_creek.dispose();
    }
}
