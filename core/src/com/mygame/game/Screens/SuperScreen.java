package com.mygame.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygame.game.GameLogic.GameLogic;
import com.badlogic.gdx.graphics.Texture;

public class SuperScreen implements Screen {

    final GameLogic game;

    OrthographicCamera camera;

    int texture_Width;
	int texture_Height;

    public SuperScreen(final GameLogic game){
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        texture_Width = Gdx.graphics.getWidth()/game.mapWidth;
		texture_Height = Gdx.graphics.getHeight()/game.mapHeight;
    }


    /**
     * Draw a button if the mouse is not hovering over it and the selected_button if the mouse is hovering over it
     * If you click the button execute the function
     * 
     * @param button Texture of the button
     * @param selected_button Texture of the selected_button
     * @param position_x where the button will be drawn to the screen in the horrizontal direction
     * @param position_y where the button will be drawn to the screen in the vertical direction
     * @param function Command interface that to execute
     */
    public void draw_Button(Texture button, Texture selected_button, int position_x, int position_y, Command function){

        if(Gdx.input.getX() > position_x && 
        Gdx.input.getX() < position_x + button.getWidth() && 
        Gdx.input.getY() < Gdx.graphics.getHeight() - position_y && 
        Gdx.input.getY() > Gdx.graphics.getHeight() - position_y - button.getHeight())
        {
            game.batch.draw(selected_button, position_x, position_y);
            function.execute();
        }
        else{
            game.batch.draw(button, position_x, position_y);
            
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public interface Command{
        public void execute();
    }

    
    public class GameScreenCommand implements Command{
        public void execute(){
            if (Gdx.input.justTouched()) {
			    game.setScreen(new Levelselectscreen(game));
			    dispose();
		    }
        }
    }

    public class QuitCommand implements Command{
        public void execute(){
            if (Gdx.input.justTouched()) {
			    Gdx.app.exit();
            }
        }
    }
    
    public class NewGameCommand implements Command{
        public void execute(){
            if (Gdx.input.justTouched()) {
                game.NewGame();
			    game.setScreen(new Levelselectscreen(game));
			    dispose();
		    }
        }
    }

    public void drawtextinfo(){
        game.layout.setText(game.font, "Level: " + game.level);
        game.font.draw(game.batch, game.layout, 10, Gdx.graphics.getHeight()-1);
        String FormatedTime = String.format("%.2f", game.time);
        game.layout.setText(game.font, "Time: " + FormatedTime);
        game.font.draw(game.batch, game.layout, (Gdx.graphics.getWidth()/2)-game.layout.width/2, Gdx.graphics.getHeight()-1);
        game.layout.setText(game.font, "Score: " + game.player.getScore());
        game.font.draw(game.batch, game.layout, (Gdx.graphics.getWidth())-game.layout.width-10, Gdx.graphics.getHeight()-1);
        
    }

    public class SettingsCommand implements Command{
        public void execute(){
            if (Gdx.input.justTouched()) {
			    game.setScreen(new SetttingsScreen(game));
			    dispose();
		    }
        }
    }

    public class ControlsCommand implements Command{
        public void execute(){
            if (Gdx.input.justTouched()) {
			    game.setScreen(new ControlScreen(game));
			    dispose();
		    }
        }
    }

    public class MainMenuCommand implements Command{
        public void execute(){
            if (Gdx.input.justTouched()) {
                game.setScreen(new MainMenuScreen(game));
			    dispose();
		    }
        }
    }
}
