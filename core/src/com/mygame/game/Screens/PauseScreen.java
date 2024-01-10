package com.mygame.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygame.game.GameLogic.GameLogic;

public class PauseScreen extends SuperScreen {

    Texture Resume;
    Texture Resume_c;
    Texture menu;
    Texture menu_c;

    public PauseScreen(GameLogic game) {
        super(game);
        Resume = new Texture("buttons/Large Buttons/Large Buttons/Resume Button.png");
        Resume_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Resume col_Button.png");
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
        int Resume_y = Gdx.graphics.getHeight() - Resume.getHeight() - texture_Height;
        drawtextinfo();
        
        //Resume button
        draw_Button(Resume, Resume_c, center_x, Resume_y, new PauseScreenCommand());

        //setting button

        int menu_y = 0;
        //menu button
        draw_Button(menu, menu_c, center_x, menu_y, new MainMenuCommand());

        game.batch.end();

    }

    @Override
    public void dispose() {
        menu.dispose();
        menu_c.dispose();
        Resume.dispose();
        Resume_c.dispose();
    }

    public class PauseScreenCommand implements Command{
        public void execute(){
            if (Gdx.input.isTouched()) {
			    game.setScreen(new GameScreen(game));
			    dispose();
		    }
        }
    }
}
