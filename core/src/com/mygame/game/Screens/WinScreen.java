package com.mygame.game.Screens;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygame.game.GameLogic.GameLogic;

public class WinScreen extends SuperScreen {

    Texture New_game;
    Texture New_game_c;
    Texture menu;
    Texture menu_c;
    String name;
    boolean save;
    boolean typing;
    File file;
    List<String> names;
    List<Float> scores;
    Scanner sc;

    public WinScreen(GameLogic game) throws Exception{
        super(game);
        save = true;
        typing = true;
        name = "Name";
        names = new ArrayList<>();
        scores = new ArrayList<>();
        New_game = new Texture("buttons/Large Buttons/Large Buttons/New game Button.png");
        New_game_c = new Texture("buttons/Large Buttons/Colored Large Buttons/New Game col_Button.png");
        menu = new Texture("buttons/Large Buttons/Large Buttons/Menu Button.png");
        menu_c = new Texture("buttons/Large Buttons/Colored Large Buttons/Menu col_Button.png");
        switch (game.levelSelect) {
            case 0:
                file = new File("assets/LeaderBoard/Tutorial.txt");
                break;
            case 1:
                file = new File("assets/LeaderBoard/Quick.txt");
                break;
            case 2:
                file = new File("assets/LeaderBoard/Medium.txt");
                break;
            case 3:
                file = new File("assets/LeaderBoard/Long.txt");
                break;
            default:
                break;
        }
        sc = new Scanner(file);
        while(sc.hasNextLine()){
            names.add(sc.nextLine());
            scores.add(Float.parseFloat(sc.nextLine()));
        }
        sc.close();
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

		game.layout.setText(game.font, "CONGRATULATIONS");
        game.font.draw(game.batch, game.layout, (Gdx.graphics.getWidth()/2)-game.layout.width/2, Gdx.graphics.getHeight()-10);
        int current_y = Gdx.graphics.getHeight() - (int)game.layout.height - 30;

        game.layout.setText(game.font, "YOU WIN");
        game.font.draw(game.batch, game.layout, (Gdx.graphics.getWidth()/2)-game.layout.width/2, current_y);
        current_y = current_y - (int)game.layout.height - 20;

        game.layout.setText(game.font, "with a time of " + String.format("%.2f", game.time));
        game.font.draw(game.batch, game.layout, (Gdx.graphics.getWidth()/2)-game.layout.width/2, current_y);
        current_y = current_y - (int)game.layout.height - 50;

        if(save){
            setname();
            if(name.length() == 1){
                name = name.toUpperCase();
            }
        }

        // leaderboard

        game.layout.setText(game.font, "Enter your name then hit enter to save");
        game.font.draw(game.batch, game.layout, (Gdx.graphics.getWidth()/2)-game.layout.width/2, current_y);
        current_y = current_y - (int)game.layout.height - 20;

        game.layout.setText(game.font, name);
        game.font.draw(game.batch, game.layout, (Gdx.graphics.getWidth()/2)-game.layout.width/2, current_y);

        game.font.getData().setScale(2);

        for(int i = 0; i < names.size(); i++){
            game.layout.setText(game.font, names.get(i));
            game.font.draw(game.batch, game.layout, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()-370-(i*40));
            game.layout.setText(game.font, String.format("%.2f", scores.get(i)));
            game.font.draw(game.batch, game.layout, Gdx.graphics.getWidth()*3/4, Gdx.graphics.getHeight()-370-(i*40));
        }

        game.font.getData().setScale(game.scale);

        //New_game button
        draw_Button(New_game, New_game_c, left_x, bottom_y, new NewGameCommand());

        //menu button
        draw_Button(menu, menu_c, right_x, bottom_y, new MainMenuCommand());

        game.batch.end();

    }

    private void setname(){
        if(Gdx.input.isKeyJustPressed(Keys.ENTER)&&(name!="")){
            save = false;
            int i = 0;
            for(; (i < scores.size())&&(scores.get(i) < game.time); i++){
            }
            scores.add(i, game.time);
            names.add(i, name);
            try{
                FileWriter writer = new FileWriter(file.getAbsolutePath());
                writer.write("");
                for(int j = 0; j < names.size();j++){
                    writer.append(names.get(j)+"\n");
                    writer.append(scores.get(j)+"\n");
                }
                writer.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            
        }
        else if(Gdx.input.isKeyJustPressed(Keys.Q)){
            checkName();
            name = name + "q";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.W)){
            name = name + "w";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.E)){
            checkName();
            name = name + "e";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.R)){
            checkName();
            name = name + "r";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.T)){
            checkName();
            name = name + "t";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.Y)){
            checkName();
            name = name + "y";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.U)){
            checkName();
            name = name + "u";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.I)){
            checkName();
            name = name + "i";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.O)){
            checkName();
            name = name + "o";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.P)){
            checkName();
            name = name + "p";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.A)){
            name = name + "a";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.S)){
            name = name + "s";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.D)){
            name = name + "d";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.F)){
            checkName();
            name = name + "f";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.G)){
            checkName();
            name = name + "g";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.H)){
            checkName();
            name = name + "h";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.J)){
            checkName();
            name = name + "j";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.K)){
            checkName();
            name = name + "k";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.L)){
            checkName();
            name = name + "l";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.Z)){
            checkName();
            name = name + "z";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.X)){
            checkName();
            name = name + "x";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.C)){
            checkName();
            name = name + "c";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.V)){
            checkName();
            name = name + "v";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.B)){
            checkName();
            name = name + "b";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.N)){
            checkName();
            name = name + "n";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.M)){
            checkName();
            name = name + "m";
        }
        else if(Gdx.input.isKeyJustPressed(Keys.BACKSPACE)){
            checkName();
            name = removeLastChar(name);
        }
        
    }

    private void checkName(){
        if(typing){
            name = "";
            typing = false;
        }
    }

    private String removeLastChar(String string){
        if(string ==null){
            return string;
        }
        else if(string == ""){
            return string;
        }
        else{
            return string.substring(0, string.length()-1);
        }
    }
    
    @Override
    public void dispose() {
        New_game.dispose();
        New_game_c.dispose();
        menu.dispose();
        menu_c.dispose();
    }

}
