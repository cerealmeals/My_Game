package com.mygame.game.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygame.game.GameLogic.GameLogic;


public class GameOverScreen extends SuperScreen {

    Texture New_game;
    Texture New_game_c;
    Texture menu;
    Texture menu_c;

    public GameOverScreen(final GameLogic game){
        super(game);
        New_game = new Texture("buttons/Large Buttons/Large Buttons/New game Button.png");
        New_game_c = new Texture("buttons/Large Buttons/Colored Large Buttons/New Game col_Button.png");
        menu = new Texture("buttons/Large Buttons/Large Buttons/Menu Button.png");
        menu_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Menu col_Button.png");
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		
        int center_x = Gdx.graphics.getWidth() /2 - menu.getWidth()/2;
        int New_game_y = Gdx.graphics.getHeight() - New_game.getHeight() - texture_Height;
        drawtextinfo();
        
        //New_game button
        draw_Button(New_game, New_game_c, center_x, New_game_y, new NewGameCommand());

        //setting button

        int menu_y = 0;
        //menu button
        draw_Button(menu, menu_c, center_x, menu_y, new MainMenuCommand());

        game.batch.end();

    }

    @Override
    public void dispose() {
        New_game.dispose();
        New_game_c.dispose();
        menu.dispose();
        menu_c.dispose();
    }
    
}
