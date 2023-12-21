package com.mygame.game.GameLogic;

import com.mygame.game.reward.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Grid class represents the game grid and manages grid-related functionalities.
 */
public class Grid {
    private final RewardClient rewardClient; // Manages rewards in the grid.
    private char[][] grid; // The 2D grid representing the game map.
    private Random rn = new Random(); // Random number generator for grid setup.
    private int mapHeight; // The size of the map.
    private int mapWidth;
    private ArrayList<ArrayList<Integer>> graph; // Represents the graph structure of the grid.
    private int number_of_vertices; // Total number of vertices in the grid.
    private int number_of_rewards = 0; // Number of rewards in the grid.

    /**
     * Default constructor for Grid class.
     * Initializes the grid, creates rewards, and sets up the map.
     */
    Grid (int height, int width){
        if(height < 6) {
            throw new IllegalArgumentException("MapHeight is too small!");
        }
        rewardClient = RewardClient.getInstance(height, width); // Initialize the reward client.
        mapHeight = height;
        mapWidth = width;
        number_of_vertices = mapHeight*mapWidth;
        grid = makeGrid(); // Create the game grid.
    }

    /**
     * Generates the initial game grid based on specified criteria.
     *
     * @return The 2D character array representing the game map.
     */
    public char[][] makeGrid() {
        grid = new char[mapHeight][mapWidth];
        //set up boarder
        SetUpBoarder();

        //set up random walls and rewards
        SetUpWalls();
        // create a graph of the paths in the grid
        createGraph();
        //printGraph();
        
        // spawn Rewards on the paths
        spawnRewards();

        return grid;
    }

    /*private void printGraph() {
        for(int i = 0; i < number_of_vertices; i++){
            System.out.print(i + ": ");
            for(int j = 0; j < graph.get(i).size(); j++){
                System.out.print(graph.get(i).get(j) + " ");
            }
            System.out.println("");
        }
    }*/

    /**
     * Set up boarder of grid
     */
    private void SetUpBoarder(){
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                if ((j == 0)
                        || (j == mapWidth - 1)
                        || (i == 0)
                        || (i == mapHeight - 2)
                )
                    grid[i][j] = 'w';
                else
                    grid[i][j] = 'p';
                if (i == mapHeight - 1)
                    grid[i][j] = 'm';
            }
        }
    }

    private void SetUpWalls(){
        for (int i = 2; i < mapHeight-3; i++) {
            for (int j = 1; j < mapWidth-1; j++) {
                if (!(i == 1 && j == 1)) { //MAKE INITIAL POSITION CLEAR
                    if (grid[i][j-1] == 'w') { // one above is a wall
                        if (rn.nextInt(9) + 1 <= 5) {
                            grid[i][j] = 'w';
                        } // grid[i + 1][j - 1] == 'w' || 
                    } else if (!(grid[i - 1][j - 1] == 'w' || grid[i-1][j+1] == 'w')) { // walls above on both sides
                        if (rn.nextInt(9) + 1 <= 5) {
                            grid[i][j] = 'w';
                        }
                    }

                }
            }
        }
    }
    /**
     * Creates a graph structure for path navigation in the grid.
     */
    private void createGraph() {
        graph = new ArrayList<ArrayList<Integer>>(number_of_vertices);

        // add a list to track the vertices
        for (int i = 0; i < number_of_vertices; i++) {
            graph.add(new ArrayList<Integer>());
        }
        int count = 0;
        // Step through the grid and find all paths
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                //System.out.println("i = " + i + " j = " + j + grid[i][j]);
                if (grid[i][j] == 'p') {  // if it is a path check if it is adjacent
                    
                    if (grid[i + 1][j] == 'p') { // if there is a path to the right
                        graph.get(count).add(count + mapWidth);
                        //printGraph();
                    }
                    if (grid[i - 1][j] == 'p') {  // if there is a path to the left
                        graph.get(count).add(count - mapWidth);
                        //printGraph();
                    }
                    if (grid[i][j + 1] == 'p') {    // if there is a path to the below
                        graph.get(count).add(count + 1);
                        //printGraph();
                    }
                    if (grid[i][j - 1] == 'p') {    // if there is a path to the above
                        graph.get(count).add(count - 1);
                        //printGraph();
                    }
                }
                count++;
            }
        }
    }

    /**
     * Spawns rewards on the grid based on reward locations and types.
     */
    private void spawnRewards() {
        rewardClient.generateRewards(grid); // Generate rewards for the grid.
        
        for (int i = 0; i < rewardClient.getRewards().size(); i++) {
            Reward reward = rewardClient.getRewards().get(i);

            if (grid[reward.getY()][reward.getX()] == 'p') {
                
                if(reward instanceof BonusReward){
                    grid[reward.getY()][reward.getX()] = 'b';
                }
                if(reward instanceof Punishment){
                    grid[reward.getY()][reward.getX()] = 'u';
                }
                if (reward instanceof GeneralReward){
                    grid[reward.getY()][reward.getX()] = 'r';
                    number_of_rewards++;
                }                
            }            
        }
    }

    /**
     * Retrieves the number of rewards present in the grid.
     *
     * @return The number of rewards on the grid.
     */
    int getNumRewards() {
        return number_of_rewards;
    }

    /**
     * Retrieves the size of the game map.
     *
     * @return The size of the map.
     */
    int getMapHeight() {
        return mapHeight;
    }

    int getMapWidth() {
        return mapWidth;
    }

    /**
     * Retrieves the 2D grid representing the game map.
     *
     * @return The 2D grid representing the game map.
     */
    char[][] getGrid() {
        return grid;
    }

    /**
     * Sets a specific cell in the grid to the given value.
     *
     * @param xpos  The x-coordinate of the cell.
     * @param ypos  The y-coordinate of the cell.
     * @param value The value to set in the cell.
     */
    void setGrid(int ypos, int xpos, char value) {
        grid[ypos][xpos] = value;
    }

    ArrayList<ArrayList<Integer>> getGraph(){
        return graph;
    }

    int getNumber_of_vertices(){
        return number_of_vertices;
    }
}
