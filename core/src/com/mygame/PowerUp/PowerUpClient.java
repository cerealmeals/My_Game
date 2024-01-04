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
        for(int i = 0; i < 3; i++){
            HelpergeneratePowerUps(game);
        }
    }

    private void HelpergeneratePowerUps(GameLogic game){

        String type = "";
        float pick = random.nextFloat();
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

        PowerUp power = PowerUpFactory.createPowerUp(game, type);
        if(check_If_Power_Up_is_already_Chosen(power)){
            HelpergeneratePowerUps(game);
        }
        else{
            choices.add(power);
        }
    }

    private Boolean check_If_Power_Up_is_already_Chosen(PowerUp power){
        for(int i = 0; i < choices.size(); i++){
            if(power.Name == choices.get(i).Name){
                return true;
            }
        }
        return false;
    }

    public void clear(){
        choices.clear();
    }
}
