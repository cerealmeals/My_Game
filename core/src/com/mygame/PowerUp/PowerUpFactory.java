package com.mygame.PowerUp;

public class PowerUpFactory {
    
    public static PowerUp createPowerUp(){
        
        return new IncreaseFireDamage();
    }
}
