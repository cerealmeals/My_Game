package com.mygame.game.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygame.game.GameLogic.Enemies;
import com.mygame.game.GameLogic.GameLogic;
import com.badlogic.gdx.Input.Keys;

public class GameScreen implements Screen{

    final GameLogic game;

    int texture_Width;
	int texture_Height;
    OrthographicCamera camera;
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
        this.game = game;
        
        wall = new Texture("wall.png");
		path = new Texture("path.png");
		reward = new Texture("reward.png");
		punishment = new Texture("punishment.png");
		superReward = new Texture("superReward.png");
		exit = new Texture("exit.png");
		trail_yellow = new Texture("Trail/trail_yellow.png");
		playerYellow_1 = new Texture("Yellow/playerYellow_1.png");
        enemy = new Texture("enemy.png");
		
        camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
    @Override
    public void show() {
        for(int i = 0; i < game.initial_number_of_enemies; i++){
            game.enemies.add(new Enemies(game.enemy_HP, game.grid, game.speed));
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
		texture_Width = Gdx.graphics.getWidth()/game.mapWidth;
		texture_Height = Gdx.graphics.getHeight()/game.mapHeight;
        
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		drawMap();
		drawplayer();
        drawEnemies();
		game.batch.end();
        imputs();
        EnemieMovement();
    }

    private void drawMap(){
        for (int i = 0; i < game.mapHeight; i++) {
                    for (int j = 0; j < game.mapWidth; j++) {
                        if (game.grid.getGrid()[i][j] == 'w')
                            game.batch.draw(wall,  j * texture_Width, i * texture_Height, texture_Width, texture_Height);
                        if (game.grid.getGrid()[i][j] == 'p')
                            game.batch.draw(path, j * texture_Width, i * texture_Height, texture_Width, texture_Height);
                        if (game.grid.getGrid()[i][j] == 'm') {
                            //fill(163 - 100, 124 - 100, 110 - 100);
                            //rect(i * width / mapsize, j * width / mapsize, width / mapsize, width / mapsize);
                        }
                        if (game.grid.getGrid()[i][j] == 'e')
                            game.batch.draw(exit, j * texture_Width, i * texture_Height, texture_Width, texture_Height);
                        if (game.grid.getGrid()[i][j] == 'r') {
                            game.batch.draw(path, j * texture_Width, i * texture_Height, texture_Width, texture_Height);
                            game.batch.draw(reward, j * texture_Width, i * texture_Height, texture_Width, texture_Height);
                        }
                        if (game.grid.getGrid()[i][j] == 'b') {
                            game.batch.draw(path, j * texture_Width, i * texture_Height, texture_Width, texture_Height);
                            game.batch.draw(superReward, j * texture_Width  , i * texture_Height, texture_Width, texture_Height);
                        }
                        if (game.grid.getGrid()[i][j] == 'u') {
                            game.batch.draw(path, j * texture_Width, i * texture_Height, texture_Width, texture_Height);
                            game.batch.draw(punishment, j * texture_Width, i * texture_Height, texture_Width, texture_Height);
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
        playerMovement();
    }

    private void playerMovement(){
        if(Gdx.input.isKeyJustPressed(Keys.LEFT)||Gdx.input.isKeyJustPressed(Keys.A)){
            game.player.movePlayer('a', game.grid);
            afterPlayerMovement();
        }
        if(Gdx.input.isKeyJustPressed(Keys.RIGHT)||Gdx.input.isKeyJustPressed(Keys.D)){
            game.player.movePlayer('d', game.grid);
            afterPlayerMovement();
        }
        if(Gdx.input.isKeyJustPressed(Keys.UP)||Gdx.input.isKeyJustPressed(Keys.W)){
            game.player.movePlayer('w', game.grid);
            afterPlayerMovement();
        }
        if(Gdx.input.isKeyJustPressed(Keys.DOWN)||Gdx.input.isKeyJustPressed(Keys.S)){
            game.player.movePlayer('s', game.grid);
            afterPlayerMovement();
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
                System.out.println("game over you touched an enemy - enemyCheck");
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
            System.out.println("You win new level - ExitCheck");
            NextLevel();
        }
    }
    private void EnemieMovement() {
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
                    System.out.println("game over you touched an enemy - enemyMovement");
                    GameOver();
            } 
            i++; 
        }
    }

    private void GameOver(){

    }

    private void NextLevel(){

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
