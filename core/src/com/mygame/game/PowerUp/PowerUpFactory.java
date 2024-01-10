package com.mygame.game.PowerUp;

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
                return new RewardsUps(game);
            case "Coal":
                return new CoalIsWorthMore(game);
            case "Hunter":
                return new Hunter(game);
            default:
                throw new IllegalArgumentException("Unknown reward type: " + type);
        }
    }
}
