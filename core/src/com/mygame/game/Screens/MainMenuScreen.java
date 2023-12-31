package com.mygame.game.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygame.game.GameLogic.GameLogic;
import com.badlogic.gdx.graphics.Texture;

public class MainMenuScreen extends SuperScreen {

    Texture play;
    Texture settings;
    Texture quit;
    Texture play_c;
    Texture settings_c;
    Texture quit_c;

    public MainMenuScreen(GameLogic game) {
        super(game);
        play = new Texture("buttons/Large Buttons/Large Buttons/Play Button.png");
        settings = new Texture("buttons/Large Buttons/Large Buttons/Settings Button.png");
        quit = new Texture("buttons/Large Buttons/Large Buttons/Quit Button.png");
        play_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Play col_Button.png");
        settings_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Settings col_Button.png");
        quit_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Quit col_Button.png");
    }

    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

        int center_x = Gdx.graphics.getWidth() /2 - quit.getWidth()/2;
        int quit_y = 0;

        int play_y = Gdx.graphics.getHeight() - play.getHeight();

		game.batch.begin();
		
        

        if(Gdx.input.getX() > center_x && 
        Gdx.input.getX() < center_x + play.getWidth()&& 
        Gdx.input.getY() > 0 && 
        Gdx.input.getY() < play.getHeight())
        {
            game.batch.draw(play_c, center_x, play_y);
            if (Gdx.input.isTouched()) {
			    game.setScreen(new GameScreen(game));
			    dispose();
		    }
        }
        else{
            game.batch.draw(play, center_x, play_y);
            
        }

        if(Gdx.input.getX() > center_x && 
        Gdx.input.getX() < center_x + quit.getWidth() && 
        Gdx.input.getY() < Gdx.graphics.getHeight() && 
        Gdx.input.getY() > Gdx.graphics.getHeight() - quit.getHeight())
        {
            game.batch.draw(quit_c, center_x, quit_y);
            if (Gdx.input.isTouched()) {
			    Gdx.app.exit();
            }
        }
        else{
            game.batch.draw(quit, center_x, quit_y);  
        }

		game.batch.end();

    }

    @Override
    public void dispose() {
        play.dispose();
        play_c.dispose();
        quit.dispose();
        quit_c.dispose();
        settings.dispose();
        settings_c.dispose();
    }
    
}
