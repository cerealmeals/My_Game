package com.mygame.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygame.game.GameLogic.GameLogic;

public class SetttingsScreen extends SuperScreen {

    Sound puddle;
    Texture play;
    Texture play_c;
    Texture menu;
    Texture menu_c;
    
    Texture left;
    Texture left_c;
    Texture right;
    Texture right_c;
    

    public SetttingsScreen(GameLogic game) {
        super(game);
        play = new Texture("buttons/Large Buttons/Large Buttons/Play Button.png");
        play_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Play col_Button.png");
        menu = new Texture("buttons/Large Buttons/Large Buttons/Menu Button.png");
        menu_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Menu col_Button.png");
        left = new Texture("buttons/Square Buttons/Square Buttons/Back Square Button.png");
        left_c = new Texture("buttons/Square Buttons/Colored Square Buttons/Back col_Square Button.png");
        right = new Texture("buttons/Square Buttons/Square Buttons/Next Square Button.png");
        right_c = new Texture("buttons/Square Buttons/Colored Square Buttons/Next col_Square Button.png");
        puddle = Gdx.audio.newSound(Gdx.files.internal("Sounds/puddle2.wav"));
    }
    
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		
        int left_x = Gdx.graphics.getWidth() /4 - menu.getWidth()/2;
        int right_x = Gdx.graphics.getWidth()*3/4 - menu.getWidth()/2;
        int bottom_y = 0;
        int volume_y = Gdx.graphics.getHeight()*2/3;
        int volume_down_x = Gdx.graphics.getWidth()/4 - left.getWidth()/2;
        int volume_up_x = Gdx.graphics.getWidth()*3/4 - right.getWidth()/2;
        
        draw_Button(left, left_c, volume_down_x, volume_y, new DecreaseVolume());
        draw_Button(right, right_c, volume_up_x, volume_y, new IncreaseVolume());

        game.font.draw(game.batch, "Volume", (Gdx.graphics.getWidth()/2) - 100, (Gdx.graphics.getHeight()*4/5)+100);
        game.font.draw(game.batch, "" + String.format("%.0f",game.volume*100) + "%",
             (Gdx.graphics.getWidth()/2) - 65 , Gdx.graphics.getHeight()*4/5);

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
        left.dispose();
        left_c.dispose();
        right.dispose();
        right_c.dispose();
        puddle.dispose();
    }

    public class IncreaseVolume implements Command{

        @Override
        public void execute() {
            if (Gdx.input.justTouched()){
                if(game.volume < 1){
                    int fine = (int)(game.volume*100);
                    fine += 10;
                    game.volume = (float)fine / 100f;
                }
                puddle.play(game.volume);
            }
        }

    }

    public class DecreaseVolume implements Command{

        @Override
        public void execute() {
            if (Gdx.input.justTouched()){
                if(game.volume > 0){
                    int fine = (int)(game.volume*100);
                    fine -= 10;
                    game.volume = (float)fine / 100f;
                }
                puddle.play(game.volume);
            }
        }

    }
    
}
