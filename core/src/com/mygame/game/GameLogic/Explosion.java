package com.mygame.game.GameLogic;

public class Explosion {
    public float explosion_time;
    public int explosion_x[];
    public int explosion_y[];

    public Explosion(int x, int y){
        explosion_x = new int[]{x,x+1,x+0,x-1,x-1,x-1,x+0,x+1,x+1,x-2,x-2,x-2,x-2,x-2,x-1,x+0,x+1,x+2,x+2,x+2,x+2,x+2,x+1,x+0,x-1};
        explosion_y = new int[]{y,y+1,y+1,y+1,y+0,y-1,y-1,y-1,y+0,y+2,y+1,y+0,y-1,y-2,y-2,y-2,y-2,y-2,y-1,y+0,y+1,y+2,y+2,y+2,y+2};

    }
}
