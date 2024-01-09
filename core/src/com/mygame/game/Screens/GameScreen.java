package com.mygame.game.Screens;


import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygame.game.GameLogic.Enemies;
import com.mygame.game.GameLogic.GameLogic;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;

public class GameScreen extends SuperScreen{

    int texture_Width;
	int texture_Height;
    float EnemyMovement;
    float PlayerMovement;
    float explosionTracking;
    
    Sound puddle;
    Sound Enemy_Contact;
    Sound Enemy_death;
    Sound coal;
    Sound explosion;
    
	Texture wall;
	Texture path;
	Texture reward;
	Texture punishment;
	Texture superReward;
	Texture exit;
	Texture trail_yellow;
	Texture playerYellow_1;
    Texture enemy_1;
    Texture enemy_2;
    Texture enemy_3;
    Texture enemy_4;


    public GameScreen(final GameLogic game){
        super(game);
        
        wall = new Texture("path3.png");
		path = new Texture("wall3.png");
		reward = new Texture("reward.png");
		punishment = new Texture("punishment.png");
		superReward = new Texture("superReward.png");
		exit = new Texture("Door.png");
		trail_yellow = new Texture("Trail/trail_yellow.png");
		playerYellow_1 = new Texture("Yellow/playerYellow_1.png");
        enemy_1 = new Texture("Cloud-1.png");
        enemy_2 = new Texture("Cloud-2.png");
        enemy_3 = new Texture("Cloud-3.png");
        enemy_4 = new Texture("Cloud-4.png");
        puddle = Gdx.audio.newSound(Gdx.files.internal("Sounds/puddle2.wav"));
        Enemy_Contact = Gdx.audio.newSound(Gdx.files.internal("Sounds/enemy.mp3"));
        Enemy_death = Gdx.audio.newSound(Gdx.files.internal("Sounds/Cloud_death.wav"));
        coal = Gdx.audio.newSound(Gdx.files.internal("Sounds/Coal.wav"));
        explosion = Gdx.audio.newSound(Gdx.files.internal("Sounds/explosion.wav"));
		
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
        drawExposion();
		game.batch.end();

        imputs();
        EnemieMovement();
        WinCondition();
        loseCondition();
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
            Enemies enemy = game.enemies.get(i);
            float percent_hp = (float)enemy.getHealth()/(float)enemy.getmaxHealth();
            if(percent_hp > 0.75f){
                game.batch.draw(enemy_1, enemy.getXPos()*texture_Width, enemy.getYPos()*texture_Height, texture_Width, texture_Height);
            }
            else if (percent_hp > 0.5f){
                game.batch.draw(enemy_2, enemy.getXPos()*texture_Width, enemy.getYPos()*texture_Height, texture_Width, texture_Height);
            }
            else if (percent_hp > 0.25f){
                game.batch.draw(enemy_3, enemy.getXPos()*texture_Width, enemy.getYPos()*texture_Height, texture_Width, texture_Height);
            }
            else{
                game.batch.draw(enemy_4, enemy.getXPos()*texture_Width, enemy.getYPos()*texture_Height, texture_Width, texture_Height);
            }
        }
    }

    private void drawExposion(){
        if(game.player.getExplosion()){
        
            if (game.time - explosionTracking < game.player.explosiontime/2){
                for(int i = 0; i < 8; i++){
                game.batch.draw(trail_yellow, (game.player.explosion_x[i])*texture_Width,
                    (game.player.explosion_y[i])*texture_Height, texture_Width, texture_Height);
                }
            }
            else if(game.time - explosionTracking < game.player.explosiontime){
                for(int i = 0; i < 24; i++){
                    if(!(game.player.explosion_y[i] >= (game.mapHeight-1))){
                        game.batch.draw(trail_yellow, (game.player.explosion_x[i])*texture_Width,
                         (game.player.explosion_y[i])*texture_Height, texture_Width, texture_Height);
                    }
                }
            }
            else{
                game.player.setExplosion(false);
            }
            
        }
    }

    private void imputs(){
        playerMovement2();
        Pause();
    }

    private void playerMovement(){
        int sound_check = 0;
        if(game.time - PlayerMovement >= game.PlayerSpeed){
                PlayerMovement = game.time;
            if(Gdx.input.isKeyPressed(Keys.LEFT)||Gdx.input.isKeyPressed(Keys.A))
            {
                sound_check = game.player.movePlayer('a', game.grid);
                afterPlayerMovement(sound_check);
            }
            else if(Gdx.input.isKeyPressed(Keys.RIGHT)||Gdx.input.isKeyPressed(Keys.D))
            {
                sound_check = game.player.movePlayer('d', game.grid);
                afterPlayerMovement(sound_check);
            }
            else if(Gdx.input.isKeyPressed(Keys.UP)||Gdx.input.isKeyPressed(Keys.W)){
                sound_check = game.player.movePlayer('w', game.grid);
                afterPlayerMovement(sound_check);
            }
            else if(Gdx.input.isKeyPressed(Keys.DOWN)||Gdx.input.isKeyPressed(Keys.S)){
                sound_check = game.player.movePlayer('s', game.grid);
                afterPlayerMovement(sound_check);
            }
        }
    }

    private void playerMovement2(){
        int sound_check = 0;
        if(Gdx.input.isKeyJustPressed(Keys.LEFT)||Gdx.input.isKeyJustPressed(Keys.A))
        {
            sound_check = game.player.movePlayer('a', game.grid);
            afterPlayerMovement(sound_check);
        }
        else if(Gdx.input.isKeyJustPressed(Keys.RIGHT)||Gdx.input.isKeyJustPressed(Keys.D))
        {  
            sound_check = game.player.movePlayer('d', game.grid);
            afterPlayerMovement(sound_check);
        }
        else if(Gdx.input.isKeyJustPressed(Keys.UP)||Gdx.input.isKeyJustPressed(Keys.W))
        {
            sound_check = game.player.movePlayer('w', game.grid);
            afterPlayerMovement(sound_check);
        }
        else if(Gdx.input.isKeyJustPressed(Keys.DOWN)||Gdx.input.isKeyJustPressed(Keys.S))
        {
            sound_check = game.player.movePlayer('s', game.grid);
            afterPlayerMovement(sound_check);
        }
    
    }

    private void Pause(){
        if(Gdx.input.isKeyJustPressed(Keys.P)||Gdx.input.isKeyJustPressed(Keys.ESCAPE)||Gdx.input.isKeyJustPressed(Keys.SPACE)){
            game.setScreen(new PauseScreen(this.game));
            dispose();
        }
    }

    private void afterPlayerMovement(int sound_check){
        playSound(sound_check);
        enemyCheck();
        PlaceExit();
        ExitCheck();
    }

    private void playSound(int sound_check){
        switch (sound_check) {
            case 1:
                coal.play(game.volume);
                break;
            case 2:
                puddle.play(game.volume);
                break;
            case 3:
                explosion.play(game.volume);
                explosionTracking = game.time;
                break;
            default:
                break;
        }
    }

    private void enemyCheck(){
        for(int i = 0; i < game.current_number_of_enemies; i++){
            if(game.enemies.get(i).getXPos() == game.player.getXPos()[0] && game.enemies.get(i).getYPos() == game.player.getYPos()[0]){
                //System.out.println("game over you touched an enemy - enemyCheck");
                Enemy_Contact.play();
                game.player.decreasetrail();
            }
        }
    }

    private void PlaceExit(){
        if(game.grid.getNumRewards() == game.player.getRewards()){
            // place an exit on the map
            if(game.grid.getexit()){
                game.grid.exit();
            }
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
            //System.out.println();
            while(i < game.current_number_of_enemies){
                //System.out.print("Enemy number " + i + " ");
                int flag = game.enemies.get(i).enemiesMove(game.player, game.grid, game.time, explosionTracking );
                // flag can be: 0 = do nothing, 1 = enemy died, 2 = enemy moved onto player, 3 = both 1 and 2
                switch(flag){
                    case 1:
                        Enemy_death.play(game.volume);
                        game.enemies.remove(game.enemies.get(i));
                        game.current_number_of_enemies--;
                        break;
                    case 2:
                        //System.out.println("game over an enemy touched you - enemyMovement");
                        Enemy_Contact.play();
                        game.player.decreasetrail();
                        break;
                    case 3:
                        Enemy_Contact.play();
                        game.player.decreasetrail();
                        Enemy_death.play(game.volume);
                        game.enemies.remove(game.enemies.get(i));
                        game.current_number_of_enemies--;
                        break;
                    default:
                        break;
                } 
                i++; 
            }
        }
    }

    private void WinCondition(){
        if(game.player.getScore() >= 1000){
            game.setScreen(new WinScreen(this.game));
            dispose();
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

    private void loseCondition(){
        if(!game.player.alive){
            GameOver();
        }
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
        enemy_1.dispose();
        enemy_2.dispose();
        enemy_3.dispose();
        enemy_4.dispose();
        puddle.dispose();
        Enemy_Contact.dispose();
        Enemy_death.dispose();
        coal.dispose();
        explosion.dispose();
        
    }
    
}
