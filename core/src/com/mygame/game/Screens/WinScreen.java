package com.mygame.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygame.game.GameLogic.GameLogic;

public class WinScreen extends SuperScreen {

    Texture New_game;
    Texture New_game_c;
    Texture menu;
    Texture menu_c;

    public WinScreen(GameLogic game) {
        super(game);

        New_game = new Texture("buttons/Large Buttons/Large Buttons/New Game Button.png");
        New_game_c = new Texture("buttons/Large Buttons/Colored Large Buttons/New Game col_Button.png");
        menu = new Texture("buttons/Large Buttons/Large Buttons/Menu Button.png");
        menu_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Menu col_Button.png");
    }

     @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

        int left_x = Gdx.graphics.getWidth() /4 - menu.getWidth()/2;
        int right_x = Gdx.graphics.getWidth()*3/4 - menu.getWidth()/2;
        int bottom_y = 0;

		game.batch.begin();
		game.layout.setText(game.font, "CONGRATULATION\n        YOU WIN\nWith a time of " + String.format("%.2f", game.time));
        game.font.draw(game.batch, game.layout, (Gdx.graphics.getWidth()/2)-game.layout.width/2, Gdx.graphics.getHeight()-10);
        
        //leaderboard
        
        
        //New_game button
        draw_Button(New_game, New_game_c, left_x, bottom_y, new NewGameCommand());

        //menu button
        draw_Button(menu, menu_c, right_x, bottom_y, new MainMenuCommand());

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
