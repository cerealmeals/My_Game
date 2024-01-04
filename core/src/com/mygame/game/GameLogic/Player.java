package com.mygame.game.GameLogic;

import com.mygame.game.reward.RewardClient;

/**
 * The Player class represents the player in the game.
 * It controls player movement and interaction with the game grid elements.
 */
public class Player {
    public int keyRel = 0; // Key release state for player movement.
    public Boolean alive;
    private int trailLength = 2; // Length of the player's trail.
    private int xpos[]; // Array holding the x-coordinates of the player's positions.
    private int ypos[]; // Array holding the y-coordinates of the player's positions.
    private int score = 0; // Player's score in the game.
    private int rewards = 0; // Count of rewards collected by the player.
    private boolean explosion = false;
    private int FlameDamage = 1;
    /**
     * Default constructor for Player class.
     * Initializes player's initial position and other attributes.
     */
    public Player() {
        xpos = new int[]{1, -1}; // Default x-coordinates for player positions.
        ypos = new int[]{1, -1}; // Default y-coordinates for player positions.
        alive = true;
    }

    /**
     * Controls the movement of the player based on user input.
     *
     * @param keyPressed Indicates if a key is pressed.
     * @param key        The character representing the pressed key.
     * @param grid      The game grid where the player moves.
     */

    public int movePlayer(char key, Grid grid) {

        // Moves the player according to the key input after checking for walls
        if (key == 'd' && grid.getGrid()[ypos[0]][xpos[0] + 1] != 'w') {
            storePrev();
            xpos[0] += 1;
        } else if (key == 'a' && grid.getGrid()[ypos[0]][xpos[0] - 1] != 'w') {
            storePrev();
            xpos[0] -= 1;
        } else if (key == 's' && grid.getGrid()[ypos[0] - 1][xpos[0]] != 'w') {
            storePrev();
            ypos[0] -= 1;
        } else if (key == 'w' && grid.getGrid()[ypos[0] + 1][xpos[0]] != 'w') {
            storePrev();
            ypos[0] += 1;
        }

        /**
        * Adds rewards to the score of the player accordingly
        * Sets the reward character to a path after it is collected
        */ 
        return interact_with_reward(grid);
    }

    /**
    * Adds rewards to the score of the player accordingly
    * Sets the reward character to a path after it is collected
    */ 
    private int interact_with_reward(Grid grid){
        // 0 for path, 1 for coal/rewards, 2 for puddles/punishment, 3 for bonus reward.
        int sounds = 0;
        char cellType = grid.getGrid()[ypos[0]][xpos[0]];
        if (cellType == 'b' || cellType == 'r' || cellType == 'u') {
            if(cellType == 'r'){
                sounds = 1;
                rewards++;
                increasetrail();
            }
            if(cellType == 'u'){
                sounds = 2;
                decreasetrail();
            }
            if(cellType == 'b'){
                sounds = 3;
                explosion = true;
            }
            RewardClient rewardClient = RewardClient.getInstance(grid.getMapHeight(), grid.getMapWidth());
            score += rewardClient.collectReward( xpos[0], ypos[0]);
            // Reset the cell to a path cell.
            grid.setGrid(ypos[0], xpos[0],  'p');
        }
        return sounds;
    }


    /**
     * Stores previous positions of the player.
     */
    private void storePrev() {
        int tempx[] = new int[trailLength];
        int tempy[] = new int[trailLength];
        System.arraycopy(xpos, 0, tempx, 0, xpos.length);
        System.arraycopy(ypos, 0, tempy, 0, ypos.length);
        for (int i = 0; i < trailLength-1; i++) {
            xpos[i + 1] = tempx[i];
            ypos[i + 1] = tempy[i];
        }
    }

    private void increasetrail(){
        trailLength++;
        int tempx[] = new int[trailLength];
        int tempy[] = new int[trailLength];
        System.arraycopy(xpos, 0, tempx, 0, xpos.length);
        System.arraycopy(ypos, 0, tempy, 0, ypos.length);
        tempx[trailLength-1] = -1;
        tempy[trailLength-1] = -1;
        xpos = tempx;
        ypos = tempy;
    }

    public void decreasetrail(){
        if(trailLength > 1){
            trailLength--;
            int tempx[] = new int[trailLength];
            int tempy[] = new int[trailLength];
            System.arraycopy(xpos, 0, tempx, 0, tempx.length);
            System.arraycopy(ypos, 0, tempy, 0, tempy.length);
            xpos = tempx;
            ypos = tempy;
        }
        else{
            alive = false;
        }
        
    }



    /**
     * Retrieves the array of x-coordinates of the player's positions.
     *
     * @return The array of x-coordinates.
     */
    public int[] getXPos() {
        return xpos;
    }

    /**
     * Sets the x-coordinate at a specific index in the player's position array.
     *
     * @param value The value to set.
     * @param index The index where the value is to be set.
     */
    public void setXPos(int value, int index) {
        xpos[index] = value;
    }

    /**
     * Retrieves the array of y-coordinates of the player's positions.
     *
     * @return The array of y-coordinates.
     */
    public int[] getYPos() {
        return ypos;
    }

    /**
     * Sets the y-coordinate at a specific index in the player's position array.
     *
     * @param value The value to set.
     * @param index The index where the value is to be set.
     */
    public void setYPos(int value, int index) {
        ypos[index] = value;
    }

    /**
     * Retrieves the player's score integer.
     *
     * @return The player's score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the player's score to the value given.
     *
     * @param value The value to set.
     */
    public void setScore(int value) {
        score = value;
    }

    /**
     * Retrieves the number of rewards the player had picked up.
     *
     * @return The number of rewards.
     */
    public int getRewards() {
        return rewards;
    }

    /**
     * Retrieves the player's trail length.
     *
     * @return The trail length.
     */
    public int getTrailLength() {
        return trailLength;
    }

    public boolean getExplosion(){
        return explosion;
    }

    public int getFlameDamage(){
        return FlameDamage;    
    }

    public void setFlameDamage(int damage){
        FlameDamage = damage;
    }

    public void resetPosition(){
        xpos = new int[]{1, -1}; // Default x-coordinates for player positions.
        ypos = new int[]{1, -1}; // Default y-coordinates for player positions.  
    }
}
