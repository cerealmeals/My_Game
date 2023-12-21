package com.mygame.game.GameLogic;

import java.util.ArrayList;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.game.Screens.MainMenuScreen;
import com.mygame.game.reward.RewardClient;


public class GameLogic extends Game {

	public SpriteBatch batch;
	public BitmapFont font;
	
	public int speed = 1000;
	public int mapHeight = 10;
	public int mapWidth = 2*mapHeight; 
    public Grid grid = new Grid(mapHeight, mapWidth);
    public RewardClient rewardClient = RewardClient.getInstance(mapHeight, mapWidth);
    public Player player = new Player();

    /*int released = 1; // Used as a boolean to control key releases
    int gameStarted = 0; // Used as a boolean variable to determine whether the game has started
    int gamePaused = 0; // Used as a boolean to determine if the game is paused
    int levelOver = 0; // Used as a boolean to see if the level is completed
    int gameOver = 0; // Used as a boolean to see if the game is over
	long time;
    long time_paused; */ 
	public int level = 1;
    public int enemy_HP = 1;
    public int initial_number_of_enemies = 4;
    public int current_number_of_enemies = initial_number_of_enemies; // The default number of enemies
    public ArrayList<Enemies> enemies = new ArrayList<Enemies>(); // Stores all the enemies in one data structure
    
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
		
	}

	public void IncreaseLevel(){
		level++;
		initial_number_of_enemies += 2;
		rewardClient.setNumRewards(rewardClient.getNumRewards() + 5);
		speed -= 50;
	}

	@Override
	public void render () {
		super.render();
	}


	@Override
	public void dispose () {
		batch.dispose();
	}
}
