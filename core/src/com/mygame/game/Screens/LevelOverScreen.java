package com.mygame.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygame.game.GameLogic.GameLogic;

public class LevelOverScreen extends SuperScreen {

    Texture Continue;
    Texture Continue_c;
    Texture menu;
    Texture menu_c;

    public LevelOverScreen(GameLogic game) {
        super(game);
        Continue = new Texture("buttons/Large Buttons/Large Buttons/Continue Button.png");
        Continue_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Continue col_Button.png");
        menu = new Texture("buttons/Large Buttons/Large Buttons/Menu Button.png");
        menu_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Menu col_Button.png");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        texture_Width = Gdx.graphics.getWidth()/game.mapWidth;
		texture_Height = Gdx.graphics.getHeight()/game.mapHeight;

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		
        int center_x = Gdx.graphics.getWidth() /2 - menu.getWidth()/2;
        int Continue_y = Gdx.graphics.getHeight() - Continue.getHeight() - texture_Height;
        drawtextinfo();

        //Continue button
        draw_Button(Continue, Continue_c, center_x, Continue_y, new NewLevelCommand());

        //setting button

        int menu_y = 0;
        //menu button
        draw_Button(menu, menu_c, center_x, menu_y, new MainMenuCommand());

        game.batch.end();
    }

    public class NewLevelCommand implements Command{
        public void execute(){
            if (Gdx.input.isTouched()) {
                game.powerUpClient.clear();
                game.LevelUp();
			    game.setScreen(new GameScreen(game));
			    dispose();
		    }
        }
    }
    
    @Override
    public void dispose() {
        Continue.dispose();
        Continue_c.dispose();
        menu.dispose();
        menu_c.dispose();
    }
}
