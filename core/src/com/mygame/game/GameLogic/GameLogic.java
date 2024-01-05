package com.mygame.game.GameLogic;

import java.util.ArrayList;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.game.PowerUp.PowerUpClient;
import com.mygame.game.Screens.MainMenuScreen;
import com.mygame.game.reward.RewardClient;


public class GameLogic extends Game {

	public SpriteBatch batch;
	public BitmapFont font;
	
	public float volume = 1;
	public float time = 0;
	public float EnemySpeed = 1f;
	public float PlayerSpeed = 0.1f;
	public int mapHeight = 10;
	public int mapWidth = (int)(1.5f*mapHeight); 
    public Grid grid = new Grid(mapHeight, mapWidth);
    public RewardClient rewardClient = RewardClient.getInstance(mapHeight, mapWidth);
	public PowerUpClient powerUpClient = PowerUpClient.getInstance();
    public Player player = new Player();

    /*int released = 1; // Used as a boolean to control key releases
    int gameStarted = 0; // Used as a boolean variable to determine whether the game has started
    int gamePaused = 0; // Used as a boolean to determine if the game is paused
    int levelOver = 0; // Used as a boolean to see if the level is completed
    int gameOver = 0; // Used as a boolean to see if the game is over
	*/ 
	public int level = 1;
    public int enemy_HP = 2;
    public int initial_number_of_enemies = 4;
    public int current_number_of_enemies = initial_number_of_enemies; // The default number of enemies
    public ArrayList<Enemies> enemies = new ArrayList<Enemies>(); // Stores all the enemies in one data structure
    public float scale = 4;
	
	@Override
	public void create () {
		batch = new SpriteBatch(); 
		font = new BitmapFont();
		font.getData().setScale(scale);
		for(int i = 0; i < initial_number_of_enemies; i++){
            enemies.add(new Enemies(enemy_HP, grid));
        }
		this.setScreen(new MainMenuScreen(this));
		
	}

	public void LevelUp(){
		grid = new Grid(mapHeight, mapWidth);
		level++;
		initial_number_of_enemies += 2;
		rewardClient.setNumRewards(rewardClient.getNumRewards() + 5);
		EnemySpeed -= 0.1;
		player.resetPosition();
		resetEnemies();
	}

	public void NewGame(){
		rewardClient.clear();
		rewardClient.setNumRewards(15);
		grid = new Grid(mapHeight, mapWidth);
		level = 1;
		time = 0;
		initial_number_of_enemies = 4;
		resetEnemies();
		EnemySpeed = 1;
		player = new Player();
	}

	private void resetEnemies(){
		enemies.clear();
		for(int i = 0; i < initial_number_of_enemies; i++){
            enemies.add(new Enemies(enemy_HP, grid));
        }
		current_number_of_enemies = initial_number_of_enemies;
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
