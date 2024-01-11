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
    Texture controls;
    Texture controls_c;


    public MainMenuScreen(GameLogic game) {
        super(game);
        play = new Texture("buttons/Large Buttons/Large Buttons/Play Button.png");
        play_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Play col_Button.png");
        settings = new Texture("buttons/Large Buttons/Large Buttons/Settings Button.png");
        settings_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Settings col_Button.png");
        quit = new Texture("buttons/Large Buttons/Large Buttons/Quit Button.png");
        quit_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Quit col_Button.png");
        controls = new Texture("buttons/Large Buttons/Large Buttons/Controls Button.png");
        controls_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Controls col_Button.png");
    }

    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0 , 0.2f, 1);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		
        int left_x = Gdx.graphics.getWidth() /4 - quit.getWidth()/2;
        int right_x = Gdx.graphics.getWidth()*3/4 - quit.getWidth()/2;
        int upper_y = play.getHeight()+50;
        int bottom_y = 0;

        //draw title
        game.font.getData().setScale(7);
        game.layout.setText(game.font, "The Best Title");
        game.font.draw(game.batch, game.layout, (Gdx.graphics.getWidth()/2)-game.layout.width/2, Gdx.graphics.getHeight()+(game.layout.height/2)-(Gdx.graphics.getHeight() -((quit.getHeight()*2)+50))/2);
        game.font.getData().setScale(game.scale);

        //play button
        draw_Button(play, play_c, left_x, upper_y, new GameScreenCommand());

        //controls button
        draw_Button(controls, controls_c, right_x, upper_y, new ControlsCommand());

        //setting button
        draw_Button(settings, settings_c, left_x, bottom_y, new SettingsCommand());

        //quit button
        draw_Button(quit, quit_c, right_x, bottom_y, new QuitCommand());

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
        controls.dispose();
        controls_c.dispose();
    }
    
    
}
