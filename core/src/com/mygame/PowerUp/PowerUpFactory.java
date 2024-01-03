package com.mygame.PowerUp;

import com.mygame.game.GameLogic.GameLogic;

public class PowerUpFactory {
    
    public static PowerUp createPowerUp(GameLogic game, String type){
        switch (type) {
            case "Bonus":
                return new BonusChance(game);
            case "Flame":
                return new FlameDamage(game);
            case "Speed":
                return new PlayerSpeed(game);
            case "Punishment":
                return new PunishmentChance(game);
            case "Rewards":
                return new Rewards(game);
            default:
                throw new IllegalArgumentException("Unknown reward type: " + type);
        }
    }
}
