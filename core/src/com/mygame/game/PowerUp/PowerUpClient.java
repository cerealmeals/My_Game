package com.mygame.game.PowerUp;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import com.mygame.game.GameLogic.GameLogic;

public class PowerUpClient {
    
    private static PowerUpClient instance = null;
    Random random;
    int number_of_power_up = 6;
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
            type = "Coal";
        }
        else if(pick < percent*4){
            type = "Punishment";
        }
        else if(pick < percent*5){
            type = "Rewards";
        }
        else if(pick < percent*6){
            type = "hunter";
        }

        PowerUp power = PowerUpFactory.createPowerUp(game, type);
        if(check_If_Power_Up_is_already_Chosen(power)){
            HelpergeneratePowerUps(game);
        }
        else if(check_If_power_up_is_legal(power, game)){
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

    private Boolean check_If_power_up_is_legal(PowerUp power, GameLogic game){
        if(power instanceof BonusChance){
            if(game.rewardClient.getbonusRewardChance() >= 0.8f){
                return true;
            }
        }
        if(power instanceof PunishmentChance){
            if(game.rewardClient.getpunishmentChance() <= 0.05f){
                return false;
            }
        }
        return false;
    }



    public void clear(){
        choices.clear();
    }
}
