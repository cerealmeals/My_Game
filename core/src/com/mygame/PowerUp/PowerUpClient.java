package com.mygame.PowerUp;

import java.util.List;
import java.util.Random;

import com.mygame.game.GameLogic.GameLogic;

public class PowerUpClient {
    
    private static PowerUpClient instance = null;
    Random random;
    int number_of_power_up = 5;
    float percent = 1/number_of_power_up;
    public List<PowerUp> choices;

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
        String type = "";
        float pick = random.nextFloat();

        for(int i = 0; i < 3; i++){
            if(pick < percent){
                type = "Flame";
            }
            else if(pick < percent*2){
                type = "Speed";
            }
            else if(pick < percent*3){
                type = "Bonus";
            }
            else if(pick < percent*4){
                type = "Punishment";
            }
            else if(pick < percent*5){
                type = "Rewards";
            }
            choices.add(PowerUpFactory.createPowerUp(game, type));
        }
    }
}
