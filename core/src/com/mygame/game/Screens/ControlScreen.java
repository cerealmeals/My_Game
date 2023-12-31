package com.mygame.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygame.game.GameLogic.GameLogic;

public class ControlScreen extends SuperScreen{

    Texture play;
    Texture play_c;
    Texture menu;
    Texture menu_c;
    Texture controls;

    public ControlScreen(GameLogic game) {
        super(game);
        play = new Texture("buttons/Large Buttons/Large Buttons/Play Button.png");
        play_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Play col_Button.png");
        menu = new Texture("buttons/Large Buttons/Large Buttons/Menu Button.png");
        menu_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Menu col_Button.png");
        controls = new Texture("Controls.png");
    }
    
    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1f, 1);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		
        int left_x = Gdx.graphics.getWidth() /4 - menu.getWidth()/2;
        int right_x = Gdx.graphics.getWidth()*3/4 - menu.getWidth()/2;
        
        int bottom_y = 0;
        game.batch.draw(controls, 0, menu.getHeight(), Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - menu.getHeight());
        //play button
        draw_Button(play, play_c, left_x, bottom_y, new GameScreenCommand());


        //menu button
        draw_Button(menu, menu_c, right_x, bottom_y, new MainMenuCommand());

        game.batch.end();

    }

    @Override
    public void dispose() {
        menu.dispose();
        menu_c.dispose();
        play.dispose();
        play_c.dispose();
        controls.dispose();
    }
}
