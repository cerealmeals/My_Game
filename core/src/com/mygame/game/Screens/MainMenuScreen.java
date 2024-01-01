package com.mygame.game.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygame.game.GameLogic.GameLogic;
import com.badlogic.gdx.graphics.Texture;

public class MainMenuScreen extends SuperScreen {

    Texture play;
    Texture play_c;
    Texture settings;
    Texture settings_c;
    Texture quit;
    Texture quit_c;

    public MainMenuScreen(GameLogic game) {
        super(game);
        play = new Texture("buttons/Large Buttons/Large Buttons/Play Button.png");
        play_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Play col_Button.png");
        settings = new Texture("buttons/Large Buttons/Large Buttons/Settings Button.png");
        settings_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Settings col_Button.png");
        quit = new Texture("buttons/Large Buttons/Large Buttons/Quit Button.png");
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

		game.batch.begin();
		
        int center_x = Gdx.graphics.getWidth() /2 - quit.getWidth()/2;
        int play_y = Gdx.graphics.getHeight() - play.getHeight();
        //play button
        draw_Button(play, play_c, center_x, play_y, new GameScreenCommand());

        //setting button

        int quit_y = 0;
        //quit button
        draw_Button(quit, quit_c, center_x, quit_y, new QuitCommand());

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
