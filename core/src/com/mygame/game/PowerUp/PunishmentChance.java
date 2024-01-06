package com.mygame.game.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class PunishmentChance extends PowerUp{

    public PunishmentChance(GameLogic g) {
        super(g);
        Name = "Less Puddles";
        description = "Decrease the chance\nthat puddles\nare spawned. \nFrom: "+ 
        String.format("%.2f", (game.rewardClient.getpunishmentChance()*100f)) + "%"
         + "\nTo: " + String.format("%.2f", (game.rewardClient.getpunishmentChance()*0.6f*100f)) + "%";
    }

    @Override
    public void clicked(){
        game.rewardClient.setpunishmentChance(game.rewardClient.getpunishmentChance()*0.6f);
    }
    
}
