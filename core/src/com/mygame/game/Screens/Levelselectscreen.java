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
        game.font.getData().setScale(7);
		game.layout.setText(game.font, "SELECT A Level");
        game.font.draw(game.batch, game.layout, (Gdx.graphics.getWidth()/2)-game.layout.width/2, Gdx.graphics.getHeight()-100+game.layout.height/2);
    
        game.font.getData().setScale(2);
        int position_x0 = 0;
        int position_x1 = Gdx.graphics.getWidth()/4;
        int position_x2 = Gdx.graphics.getWidth()*2/4;
        int position_x3 = Gdx.graphics.getWidth()*3/4;
        int half_y = (Gdx.graphics.getHeight())/2;

        if(Gdx.input.getX() > position_x0 && 
        Gdx.input.getX() < position_x0 + position_x1 && 
        Gdx.input.getY() < Gdx.graphics.getHeight() -200 && 
        Gdx.input.getY() > 200)
        {
            game.layout.setText(game.font, "A starter level\nwith one enemy to\nstart and a 200\npoint win condition");
            game.font.draw(game.batch, game.layout, position_x0+position_x1/2-game.layout.width/2, half_y+game.layout.height/2);
            if (Gdx.input.justTouched()) {
                game.initial_number_of_enemies = 1;
                game.winCondition = 2;
                game.levelSelect = 0;
                game.NewGame();
                game.setScreen(new GameScreen(game));
                dispose();
            }    
        }
        else{
            game.layout.setText(game.font, "Tutorial");
            game.font.draw(game.batch, game.layout, position_x0+position_x1/2-game.layout.width/2, half_y+game.layout.height/2);
            
        }
        
        // Quick level
        if(Gdx.input.getX() > position_x1 && 
        Gdx.input.getX() < position_x1 + position_x1 && 
        Gdx.input.getY() < Gdx.graphics.getHeight() -200 && 
        Gdx.input.getY() > 200)
        {
            game.layout.setText(game.font, "Two enemies to \nstart and a 1000\npoint win condition");
            game.font.draw(game.batch, game.layout, position_x1+position_x1/2-game.layout.width/2, half_y+game.layout.height/2);
            if (Gdx.input.justTouched()) {
                game.initial_number_of_enemies = 2;
                game.winCondition = 1000;
                game.levelSelect = 1;
                game.NewGame();
                game.setScreen(new GameScreen(game));
                dispose();
            }    
        }
        else{
            game.layout.setText(game.font, "Quick");
            game.font.draw(game.batch, game.layout, position_x1+position_x1/2-game.layout.width/2, half_y+game.layout.height/2);
            
        }

        //Medium Level
        if(Gdx.input.getX() > position_x2 && 
        Gdx.input.getX() < position_x2 + position_x1 && 
        Gdx.input.getY() < Gdx.graphics.getHeight() -200 && 
        Gdx.input.getY() > 200)
        {
            game.layout.setText(game.font, "Three enemies to\nstart and a 5000\npoint win condition");
            game.font.draw(game.batch, game.layout, position_x2+position_x1/2-game.layout.width/2, half_y+game.layout.height/2);
            if (Gdx.input.justTouched()) {
                game.initial_number_of_enemies = 3;
                game.winCondition = 5000;
                game.levelSelect = 2;
                game.NewGame();
                game.setScreen(new GameScreen(game));
                dispose();
            }    
        }
        else{
            game.layout.setText(game.font, "Medium");
            game.font.draw(game.batch, game.layout, position_x2+position_x1/2-game.layout.width/2, half_y+game.layout.height/2);
        }

        // Long level
        if(Gdx.input.getX() > position_x3 && 
        Gdx.input.getX() < position_x3 + position_x1 && 
        Gdx.input.getY() < Gdx.graphics.getHeight() -200 && 
        Gdx.input.getY() > 200)
        {
            game.layout.setText(game.font, "Three enemies to\nstart and a 10000\npoint win condition");
            game.font.draw(game.batch, game.layout, position_x3+position_x1/2-game.layout.width/2, half_y+game.layout.height/2);
            if (Gdx.input.justTouched()) {
                game.initial_number_of_enemies = 3;
                game.winCondition = 10000;
                game.levelSelect = 3;
                game.NewGame();
                game.setScreen(new GameScreen(game));
                dispose();
            }    
        }
        else{
            game.layout.setText(game.font, "Long");
            game.font.draw(game.batch, game.layout, position_x3+position_x1/2-game.layout.width/2, half_y+game.layout.height/2); 
        }

        int center_x = Gdx.graphics.getWidth() /2 - menu.getWidth()/2;
        int menu_y = 0;
        //menu button
        draw_Button(menu, menu_c, center_x, menu_y, new MainMenuCommand());

        //game.font.draw(game.batch, "5 more random stationary things spawn\nAnd number of enemies increase by 2\nEnemies also move faster",(Gdx.graphics.getWidth()/4), 100);

        game.font.getData().setScale(game.scale);
        game.batch.end();
    }

    @Override
    public void dispose() {
        menu.dispose();
        menu_c.dispose();
    }

}
