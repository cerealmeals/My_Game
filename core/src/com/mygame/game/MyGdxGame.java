package com.mygame.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import com.mygame.game.reward.RewardClient;
import java.util.Random;


public class MyGdxGame extends ApplicationAdapter {

	OrthographicCamera camera;
	SpriteBatch batch;
	Texture wall;
	Texture path;
	Texture reward;
	Texture punishment;
	Texture superReward;
	Texture exit;

	int screen_Width;
	int screen_Height;
	int mapHeight = 10;
	int mapWidth = 2*mapHeight;
    Grid grid = new Grid(mapHeight, mapWidth);
    RewardClient rewardClient = RewardClient.getInstance(mapHeight, mapWidth);
    Player player = new Player();

    int released = 1; // Used as a boolean to control key releases
    int gameStarted = 0; // Used as a boolean variable to determine whether the game has started
    int gamePaused = 0; // Used as a boolean to determine if the game is paused
    int levelOver = 0; // Used as a boolean to see if the level is completed
    int gameOver = 0; // Used as a boolean to see if the game is over
    Random rn = new Random();
    int enemy_HP = 4;
    int initial_number_of_enemies = 4;
    int current_number_of_enemies = initial_number_of_enemies; // The default number of enemies
    ArrayList<Enemies> enemies = new ArrayList<Enemies>(); // Stores all the enemies in one data structure
    long time;
    long time_paused;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		wall = new Texture("wall.png");
		path = new Texture("path.png");
		reward = new Texture("reward.png");
		punishment = new Texture("punishment.png");
		superReward = new Texture("superReward.png");
		exit = new Texture("exit.png");
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		drawMap(screen_Width, screen_Height);
		batch.end();
	}

	private void drawMap(int screen_Width, int screen_Height){
        for (int i = 0; i < mapHeight; i++) {
                    for (int j = 0; j < mapWidth; j++) {
                        if (grid.getGrid()[i][j] == 'w')
                            batch.draw(wall,  j * screen_Width / mapWidth, i * screen_Height / mapHeight, screen_Width / mapWidth, screen_Height / mapHeight);
                        if (grid.getGrid()[i][j] == 'p')
                            batch.draw(path, j * screen_Width / mapWidth, i * screen_Height / mapHeight, screen_Width / mapWidth, screen_Height / mapHeight);
                        if (grid.getGrid()[i][j] == 'm') {
                            //fill(163 - 100, 124 - 100, 110 - 100);
                            //rect(i * width / mapsize, j * width / mapsize, width / mapsize, width / mapsize);
                        }
                        if (grid.getGrid()[i][j] == 'e')
                            batch.draw(exit, j * screen_Width / mapWidth, i * screen_Height / mapHeight, screen_Width / mapWidth, screen_Height / mapHeight);
                        if (grid.getGrid()[i][j] == 'r') {
                            batch.draw(path, j * screen_Width / mapWidth, i * screen_Height / mapHeight, screen_Width / mapWidth, screen_Height / mapHeight);
                            batch.draw(reward, j * screen_Width / mapWidth, i * screen_Height / mapHeight, screen_Width / mapWidth, screen_Height / mapHeight);
                        }
                        if (grid.getGrid()[i][j] == 'b') {
                            batch.draw(path, j * screen_Width / mapWidth, i * screen_Height / mapHeight, screen_Width / mapWidth, screen_Height / mapHeight);
                            batch.draw(superReward, j * screen_Width / mapWidth  , i * screen_Height / mapHeight, screen_Width / mapWidth, screen_Height / mapHeight);
                        }
                        if (grid.getGrid()[i][j] == 'u') {
                            batch.draw(path, j * screen_Width / mapWidth, i * screen_Height / mapHeight, screen_Width / mapWidth, screen_Height / mapHeight);
                            batch.draw(punishment, j * screen_Width / mapWidth, i * screen_Height / mapHeight, screen_Width / mapWidth, screen_Height / mapHeight);
                        }
                    }
                }
    }

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
		screen_Width = width;
		camera.viewportHeight = height;
		screen_Height = height;
	}

	@Override
	public void dispose () {
		batch.dispose();
		wall.dispose();
	}
}
