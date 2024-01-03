package com.mygame.PowerUp;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import com.mygame.game.GameLogic.GameLogic;

public class PowerUpClient {
    
    private static PowerUpClient instance = null;
    Random random;
    int number_of_power_up = 5;
    float percent;
    public List<PowerUp> choices;

    private PowerUpClient(){
        percent = 1/(float)number_of_power_up;
        random = new Random();
        choices = new ArrayList<>();
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
                type = "Bonus";
            }
            else if(pick < percent*2){
                type = "Flame";
            }
            else if(pick < percent*3){
                type = "Speed";
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

    public void clear(){
        choices.clear();
    }
}
