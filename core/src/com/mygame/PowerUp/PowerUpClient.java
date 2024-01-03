package com.mygame.PowerUp;

import java.util.Random;

import com.mygame.game.GameLogic.GameLogic;

public class PowerUpClient {
    
    private static PowerUpClient instance = null;
    Random random;

    private PowerUpClient(){
        random = new Random();
    }

    public static PowerUpClient getInstance(){
        if(instance == null){
            return new PowerUpClient();
        }
        return instance;
    }

    public void generatePowerUps(GameLogic game){
        
    }
}
