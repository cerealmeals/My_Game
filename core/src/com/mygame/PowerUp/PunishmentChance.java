package com.mygame.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class PunishmentChance extends PowerUp{

    public PunishmentChance(GameLogic g) {
        super(g);
        Name = "Less Puddles";
        description = "Decrease the chance that puddles are spawned. \n From: "+ game.rewardClient.getpunishmentChance()*100 + "%"
         + "\n To: " + game.rewardClient.getpunishmentChance()*0.8*100 + "%";
    }

    @Override
    public void clicked(){
        game.rewardClient.setpunishmentChance(game.rewardClient.getpunishmentChance()*0.8f);
    }
    
}
