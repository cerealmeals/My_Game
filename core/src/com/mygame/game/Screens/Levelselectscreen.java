package com.mygame.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygame.game.GameLogic.GameLogic;

public class Levelselectscreen extends SuperScreen {

    Texture menu;
    Texture menu_c;

    public Levelselectscreen(GameLogic game) {
        super(game);
        menu = new Texture("buttons/Large Buttons/Large Buttons/Menu Button.png");
        menu_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Menu col_Button.png");
   
    }
    
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		
        game.font.draw(game.batch, "SELECT A Level", (Gdx.graphics.getWidth()/4), Gdx.graphics.getHeight()-1);
    
        game.font.getData().setScale(2);

        for(int i = 0; i < 3; i++){
        draw_level_option(i*Gdx.graphics.getWidth()/3);
        }

        int center_x = Gdx.graphics.getWidth() /2 - menu.getWidth()/2;
        int menu_y = 0;
        //menu button
        draw_Button(menu, menu_c, center_x, menu_y, new MainMenuCommand());

        //game.font.draw(game.batch, "5 more random stationary things spawn\nAnd number of enemies increase by 2\nEnemies also move faster",(Gdx.graphics.getWidth()/4), 100);

        game.font.getData().setScale(game.scale);
        game.batch.end();
    }

    private void draw_level_option(int x){

    }

    @Override
    public void dispose() {
        menu.dispose();
        menu_c.dispose();
    }

}
