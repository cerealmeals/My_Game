package com.mygame.game.reward;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The RewardClient class manages rewards and their distribution on the game board.
 */
public class RewardClient {
    private static RewardClient instance = null;
    private final int mapHeight;
    private final int mapWidth;
    private final List<Reward> rewards;
    private int number_of_rewards = 15;
    public int coalScore = 10;
    public int puddleScore = -5;
    public int bonusScore = 50;
    //private char[][] grid;
    Random random;

    // Used to randomly generate rewards and punishments
    float bonusRewardChance = 0.1f;
    float punishmentChance = 0.2f;

    /**
     * Constructor for the RewardClient class.
     *
     * @param mapSize The size of the game board.
     */
    private RewardClient(int height, int width, int initial) {
        this.mapHeight = height;
        this.mapWidth = width;
        this.number_of_rewards = initial;
        this.rewards = new ArrayList<>();
        random = new Random();
    }

    private RewardClient(int height, int width) {
        this.mapHeight = height;
        this.mapWidth = width;
        this.rewards = new ArrayList<>();
        random = new Random();
    }

    /**
     * Retrieves the instance of the RewardClient, creating it if it doesn't exist.
     *
     * @param mapSize The size of the game board.
     * @return The instance of RewardClient.
     */
    public static RewardClient getInstance(int height, int width, int initial) {
        if (instance == null) {
            instance = new RewardClient(height, width, initial);
        }
        return instance;
    }

    public static RewardClient getInstance(int height, int width) {
        if (instance == null) {
            instance = new RewardClient(height, width);
        }
        return instance;
    }

    /**
     * Retrieves the list of objects on the board.
     *
     * @return The list of objects.
     */
    public List<Reward> getRewards() {
        return rewards;
    }

    /**
     * Generates objects and places them on the game board.
     */
    public void generateRewards(char[][] grid) {
        // spawn 'number_of_rewards' reward random chance to be different types.

        // spawn a guaranteed Regular reward
        int x1 = random.nextInt(mapWidth-2)+1;
        int y1 = random.nextInt(mapHeight-3)+1;
        while((x1==1 && y1 == 1) || (grid[y1][x1] == 'w')){
            x1 = random.nextInt(mapWidth-2)+1;
            y1 = random.nextInt(mapHeight-3)+1;
        }
        rewards.add(RewardFactory.createReward("Regular", 10, x1, y1));

        // spawn the rest for random chance
        for (int i = 0; i < number_of_rewards-1; i++) {
            
            int x = random.nextInt(mapWidth-2)+1;
            int y = random.nextInt(mapHeight-3)+1;
            while((x==1 && y == 1) || (grid[y][x] == 'w')){
                x = random.nextInt(mapWidth-2)+1;
                y = random.nextInt(mapHeight-3)+1;
            }
            String type = "";
            int scoreValue = 0;
            float percent = random.nextFloat();
            if (percent < bonusRewardChance) {
                type =  "Bonus";
                scoreValue = bonusScore;     
            }
            else if(percent < bonusRewardChance+punishmentChance){
                type = "Punishment";
                scoreValue = puddleScore;
            }
            else{
                type = "Regular";
                scoreValue = coalScore;
            }rewards.add(RewardFactory.createReward(type, scoreValue, x, y));
            
        }
    }

    /**
     * Collects the object at a specific position.
     * Awards the player points based on the reward/punishment
     *
     * @param x The x-coordinate of the position.
     * @param y The y-coordinate of the position.
     * @return The score value of the collected reward.
     */
    public int collectReward(int x, int y) {
        if (!(x > 0 || y > 0 || x <= mapWidth || y <= mapHeight)) {

            return 0; // Invalid coordinates
        }

        Reward rewardAtPosition = getRewardAt(x, y);

        if (rewardAtPosition != null) {
            int score = rewardAtPosition.getScoreValue();
            removeRewardAt(x, y); // Remove the reward from the board
            return score;
        }
        return 0;
    }

    /**
     * Retrieves the object at a specific position.
     *
     * @param x The x-coordinate of the position.
     * @param y The y-coordinate of the position.
     * @return The reward at the given position or null if no reward is present.
     */
    public Reward getRewardAt(int x, int y) {
        
        for (Reward reward : rewards) {
            if (reward.getX() == x && reward.getY() == y) {
                return reward;
            }
        }
        return null;
    }

    /**
     * Removes the object at a specific position from the board.
     *
     * @param x The x-coordinate of the position.
     * @param y The y-coordinate of the position.
     */
    protected void removeRewardAt(int x, int y) {
        Reward rewardAt = getRewardAt(x, y);
        rewards.remove(rewardAt);
    }

    public void clear(){
        rewards.clear();
    }

    public int getNumRewards(){
        return number_of_rewards;
    }

    public void setNumRewards(int n){
        number_of_rewards = n;
    }

    public void setbonusRewardChance(float f){
        bonusRewardChance = f;
    }

    public float getbonusRewardChance(){
        return bonusRewardChance;
    }
    
    public void setpunishmentChance(float f){
        punishmentChance = f;
    }

    public float getpunishmentChance(){
        return punishmentChance;
    }
}
