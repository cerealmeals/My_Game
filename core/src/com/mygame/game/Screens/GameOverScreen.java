package com.mygame.game.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygame.game.GameLogic.GameLogic;


public class GameOverScreen extends SuperScreen {

    Texture New_game;
    Texture New_game_c;
    Texture quit;
    Texture quit_c;

    public GameOverScreen(final GameLogic game){
        super(game);
        New_game = new Texture("buttons/Large Buttons/Large Buttons/New Game Button.png");
        New_game_c = new Texture("buttons/Large Buttons/Colored Large Buttons/New Game col_Button.png");
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
        int New_game_y = Gdx.graphics.getHeight() - New_game.getHeight();
        //New_game button
        draw_Button(New_game, New_game_c, center_x, New_game_y, new GameScreenCommand());
        
        //setting button

        int quit_y = 0;
        //quit button
        draw_Button(quit, quit_c, center_x, quit_y, new QuitCommand());

        game.batch.end();

    }
    
}
