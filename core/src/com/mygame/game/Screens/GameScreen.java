package com.mygame.game.Screens;


import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygame.game.GameLogic.GameLogic;
import com.badlogic.gdx.Input.Keys;

public class GameScreen extends SuperScreen{

    int texture_Width;
	int texture_Height;
    float EnemyMovement;
    float PlayerMovement;
    
	Texture wall;
	Texture path;
	Texture reward;
	Texture punishment;
	Texture superReward;
	Texture exit;
	Texture trail_yellow;
	Texture playerYellow_1;
    Texture enemy;


    public GameScreen(final GameLogic game){
        super(game);
        
        wall = new Texture("wall.png");
		path = new Texture("path.png");
		reward = new Texture("reward.png");
		punishment = new Texture("punishment.png");
		superReward = new Texture("superReward.png");
		exit = new Texture("exit.png");
		trail_yellow = new Texture("Trail/trail_yellow.png");
		playerYellow_1 = new Texture("Yellow/playerYellow_1.png");
        enemy = new Texture("enemy.png");
		
    }
    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {
        game.time += delta;
        ScreenUtils.clear(0, 0, 0, 1);
		texture_Width = Gdx.graphics.getWidth()/game.mapWidth;
		texture_Height = Gdx.graphics.getHeight()/game.mapHeight;
        
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		drawMap();
		drawplayer();
        drawEnemies();
        drawtextinfo();
		game.batch.end();
        imputs();
        EnemieMovement();
    }

    private void drawMap(){
        for (int i = 0; i < game.mapHeight; i++) {
            for (int j = 0; j < game.mapWidth; j++) {
                char type = game.grid.getGrid()[i][j];
                switch (type) {
                    case 'w':
                        game.batch.draw(wall,  j * texture_Width, i * texture_Height, texture_Width, texture_Height);
                        break;
                    case 'p':
                        game.batch.draw(path, j * texture_Width, i * texture_Height, texture_Width, texture_Height);
                        break;
                    case 'e':
                        game.batch.draw(exit, j * texture_Width, i * texture_Height, texture_Width, texture_Height);
                        break;
                    case 'r':
                        game.batch.draw(path, j * texture_Width, i * texture_Height, texture_Width, texture_Height);
                        game.batch.draw(reward, j * texture_Width, i * texture_Height, texture_Width, texture_Height);
                        break;
                    case 'b':
                        game.batch.draw(path, j * texture_Width, i * texture_Height, texture_Width, texture_Height);
                        game.batch.draw(superReward, j * texture_Width  , i * texture_Height, texture_Width, texture_Height);
                        break;
                    case 'u':
                        game.batch.draw(path, j * texture_Width, i * texture_Height, texture_Width, texture_Height);
                        game.batch.draw(punishment, j * texture_Width, i * texture_Height, texture_Width, texture_Height);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void drawplayer(){
		for(int i = 1; i < game.player.getTrailLength(); i++){
			game.batch.draw(trail_yellow,game. player.getXPos()[i]*texture_Width, game.player.getYPos()[i]*texture_Height, texture_Width, texture_Height);
		}
		game.batch.draw(playerYellow_1, game.player.getXPos()[0]*texture_Width, game.player.getYPos()[0]*texture_Height, texture_Width, texture_Height);
	}

    private void drawEnemies(){
        for(int i = 0; i < game.current_number_of_enemies; i++){
            game.batch.draw(enemy, game.enemies.get(i).getXPos()*texture_Width, game.enemies.get(i).getYPos()*texture_Height, texture_Width, texture_Height);
        }
    }

    private void imputs(){
        playerMovement2();
        Pause();
    }

    private void playerMovement(){
        if(game.time - PlayerMovement >= game.PlayerSpeed){
                PlayerMovement = game.time;
            if(Gdx.input.isKeyPressed(Keys.LEFT)||Gdx.input.isKeyPressed(Keys.A))
            {
                game.player.movePlayer('a', game.grid);
                afterPlayerMovement();
            }
            else if(Gdx.input.isKeyPressed(Keys.RIGHT)||Gdx.input.isKeyPressed(Keys.D))
            {
                game.player.movePlayer('d', game.grid);
                afterPlayerMovement();
            }
            else if(Gdx.input.isKeyPressed(Keys.UP)||Gdx.input.isKeyPressed(Keys.W)){
                game.player.movePlayer('w', game.grid);
                afterPlayerMovement();
            }
            else if(Gdx.input.isKeyPressed(Keys.DOWN)||Gdx.input.isKeyPressed(Keys.S)){
                game.player.movePlayer('s', game.grid);
                afterPlayerMovement();
            }
        }
    }

    private void playerMovement2(){
    
        if(Gdx.input.isKeyJustPressed(Keys.LEFT)||Gdx.input.isKeyJustPressed(Keys.A))
        {
            game.player.movePlayer('a', game.grid);
            afterPlayerMovement();
        }
        else if(Gdx.input.isKeyJustPressed(Keys.RIGHT)||Gdx.input.isKeyJustPressed(Keys.D))
        {
            game.player.movePlayer('d', game.grid);
            afterPlayerMovement();
        }
        else if(Gdx.input.isKeyJustPressed(Keys.UP)||Gdx.input.isKeyJustPressed(Keys.W)){
            game.player.movePlayer('w', game.grid);
            afterPlayerMovement();
        }
        else if(Gdx.input.isKeyJustPressed(Keys.DOWN)||Gdx.input.isKeyJustPressed(Keys.S)){
            game.player.movePlayer('s', game.grid);
            afterPlayerMovement();
        }
    
    }

    private void playerMovement1(){
        Boolean flag = false;
        if(game.time - PlayerMovement >= game.PlayerSpeed){
            PlayerMovement = game.time;
            flag = true;
        }
        if(Gdx.input.isKeyJustPressed(Keys.LEFT)||Gdx.input.isKeyJustPressed(Keys.A) ||
            flag&&(Gdx.input.isKeyPressed(Keys.LEFT)||Gdx.input.isKeyPressed(Keys.A)))
        {
            game.player.movePlayer('a', game.grid);
            afterPlayerMovement();
        }
        if(Gdx.input.isKeyJustPressed(Keys.RIGHT)||Gdx.input.isKeyJustPressed(Keys.D)||
            flag&&(Gdx.input.isKeyPressed(Keys.RIGHT)||Gdx.input.isKeyPressed(Keys.D)))
        {
            game.player.movePlayer('d', game.grid);
            afterPlayerMovement();
        }
        if(Gdx.input.isKeyJustPressed(Keys.UP)||Gdx.input.isKeyJustPressed(Keys.W)||
            flag&&(Gdx.input.isKeyPressed(Keys.UP)||Gdx.input.isKeyPressed(Keys.W))){
            game.player.movePlayer('w', game.grid);
            afterPlayerMovement();
        }
        if(Gdx.input.isKeyJustPressed(Keys.DOWN)||Gdx.input.isKeyJustPressed(Keys.S)||
            flag&&(Gdx.input.isKeyPressed(Keys.DOWN)||Gdx.input.isKeyPressed(Keys.S))){
            game.player.movePlayer('s', game.grid);
            afterPlayerMovement();
        }
        flag = false;
    }

    private void Pause(){
        if(Gdx.input.isKeyJustPressed(Keys.P)||Gdx.input.isKeyJustPressed(Keys.ESCAPE)||Gdx.input.isKeyJustPressed(Keys.SPACE)){
            game.setScreen(new PauseScreen(this.game));
            dispose();
        }
    }

    private void afterPlayerMovement(){
        enemyCheck();
        PlaceExit();
        ExitCheck();
    }
    private void enemyCheck(){
        for(int i = 0; i < game.current_number_of_enemies; i++){
            if(game.enemies.get(i).getXPos() == game.player.getXPos()[0] && game.enemies.get(i).getYPos() == game.player.getYPos()[0]){
                //System.out.println("game over you touched an enemy - enemyCheck");
                GameOver();
            }
        }
    }

    private void PlaceExit(){
        if(game.grid.getNumRewards() == game.player.getRewards()){
            // place an exit on the map
            game.grid.getGrid()[game.mapHeight-2][game.mapWidth-2] = 'e'; 
        }
    }

    private void ExitCheck(){
        if(game.grid.getGrid()[game.player.getYPos()[0]][game.player.getXPos()[0]] == 'e'){
            //System.out.println("You win new level - ExitCheck");
            NextLevel();
        }
    }
    private void EnemieMovement() {
        if (game.time - EnemyMovement >= game.EnemySpeed){
            EnemyMovement = game.time;
            int i = 0;
            while(i < game.current_number_of_enemies){
                int flag = game.enemies.get(i).enemiesMove(game.player, game.grid);
                // flag can be: 0 = do nothing, 1 = enemy died, 2 = enemy moved onto player
                switch(flag){
                    case 1:
                        game.enemies.remove(game.enemies.get(i));
                        game.current_number_of_enemies--;
                        break;
                    case 2:
                        //System.out.println("game over an enemy touched you - enemyMovement");
                        GameOver();
                        break;
                    default:
                        break;
                } 
                i++; 
            }
        }
    }

    private void GameOver(){
        game.setScreen(new GameOverScreen(this.game));
        dispose();
    }

    private void NextLevel(){
        game.setScreen(new PowerUpScreen(this.game));
        dispose();
    }

    @Override
    public void dispose() {
        wall.dispose();
		path.dispose();
		reward.dispose();
		punishment.dispose();
		superReward.dispose();
		exit.dispose();
		trail_yellow.dispose();
		playerYellow_1.dispose();
        enemy.dispose();
    }
    
}
