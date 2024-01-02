package com.mygame.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygame.game.GameLogic.GameLogic;

public class PauseScreen extends SuperScreen {

    Texture Resume;
    Texture Resume_c;
    Texture quit;
    Texture quit_c;

    public PauseScreen(GameLogic game) {
        super(game);
        Resume = new Texture("buttons/Large Buttons/Large Buttons/Resume Button.png");
        Resume_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Resume col_Button.png");
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
        int Resume_y = Gdx.graphics.getHeight() - Resume.getHeight();
        //Resume button
        draw_Button(Resume, Resume_c, center_x, Resume_y, new PauseScreenCommand());

        //setting button

        int quit_y = 0;
        //quit button
        draw_Button(quit, quit_c, center_x, quit_y, new QuitCommand());

        game.batch.end();

    }

    @Override
    public void dispose() {
        quit.dispose();
        quit_c.dispose();
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
