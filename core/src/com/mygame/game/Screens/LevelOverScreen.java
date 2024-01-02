package com.mygame.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygame.game.GameLogic.GameLogic;

public class LevelOverScreen extends SuperScreen {

    Texture Continue;
    Texture Continue_c;
    Texture quit;
    Texture quit_c;

    public LevelOverScreen(GameLogic game) {
        super(game);
        Continue = new Texture("buttons/Large Buttons/Large Buttons/Continue Button.png");
        Continue_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Continue col_Button.png");
        quit = new Texture("buttons/Large Buttons/Large Buttons/Quit Button.png");
        quit_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Quit col_Button.png");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		
        int center_x = Gdx.graphics.getWidth() /2 - quit.getWidth()/2;
        int Continue_y = Gdx.graphics.getHeight() - Continue.getHeight();
        //Continue button
        draw_Button(Continue, Continue_c, center_x, Continue_y, new NewLevelCommand());

        //setting button

        int quit_y = 0;
        //quit button
        draw_Button(quit, quit_c, center_x, quit_y, new QuitCommand());

        game.batch.end();
    }
    
    @Override
    public void dispose() {
        Continue.dispose();
        Continue_c.dispose();
        quit.dispose();
        quit_c.dispose();
    }
}
