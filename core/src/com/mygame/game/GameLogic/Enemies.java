package com.mygame.game.GameLogic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * The P_Enemies class represents enemies in the game, aiming to pursue the player.
 * They move within the game grid and interact with the player and the game grid elements.
 */
public class Enemies {
    private int health; // The health of the enemy.
    private int xpos; // The x-coordinate of the enemy's position on the grid.
    private int ypos; // The y-coordinate of the enemy's position on the grid.
    private Random rn = new Random(); // Random number generator for enemy movement.
    long time = System.currentTimeMillis(); // Time reference variables for enemy movement.
    /**
     * Default constructor for P_Enemies class.
     * Initializes the enemy's health and default position.
     */
    public Enemies(){
        health = 1;
        xpos = 2; // Default x-coordinate of the enemy.
        ypos = 2; // Default y-coordinate of the enemy.
    }

    /**
     * Constructor for P_Enemies class with specified health and initial grid position.
     *
     * @param hp     The health of the enemy.
     * @param grid2  The game grid where the enemy is spawned.
     */
    public Enemies(int hp, Grid grid2){
        health = hp; // Set the enemy's health.
        this.spawn(grid2); // Spawn the enemy on the grid.
    }

    /**
     * Spawns the enemy at a random valid position on the grid.
     *
     * @param grid2  The game grid where the enemy is spawned.
     */
    private void spawn(Grid grid2){
        xpos = rn.nextInt(grid2.getMapWidth()-3)+2; 
        ypos = rn.nextInt(grid2.getMapHeight()-3)+1;
        if (grid2.getGrid()[ypos][xpos] == 'w' || grid2.getGrid()[ypos][xpos] == 'm'||(xpos < 6 && ypos < 4))
            spawn(grid2);
    }

    /**
     * Controls the movement behavior of the enemies.
     *
     * @param player  The player object in the game.
     * @param grid2   The game grid where the enemy moves.
     */
    public int enemiesMove(Player player, Grid grid){
        int returnValue = 0;
        int i = 0;
        if(!(ypos == player.getYPos()[i] && xpos == player.getXPos()[i])){        

            int current_Position = ypos*grid.getMapWidth() + xpos;
            int player_Position = player.getYPos()[0]*grid.getMapWidth() + player.getXPos()[0]; 

            int nextposition = BFS(grid.getGraph(), grid.getNumber_of_vertices(), current_Position, player_Position); // should return the next move

            //calculate nex x and y
            int diff = nextposition - current_Position;
            // set new position
            if(diff == -1){
                xpos--;
            }
            else if(diff == 1){
                xpos++;
            }
            else if(diff == grid.getMapWidth()){
                ypos++;
            }
            else if(diff == (-grid.getMapWidth())){
                ypos--;
            }
            else if(diff == 0){
                System.out.println("do nothing enemy you are doing great");
            }
            else{
                System.out.println("how did it get here you really should look into this - Enemies/enemiesMove");
            }
        }

        // check to see if player is there.
        
        if(ypos == player.getYPos()[i] && xpos == player.getXPos()[i]){
            returnValue += 2;
        }

        // check to see if flames are there.
        for(; i< player.getTrailLength(); i++){
            
            if(ypos == player.getYPos()[i] && xpos == player.getXPos()[i]){
                // if flames are there take damage.
                health -= player.getFlameDamage();
                // maybe only get damaged once
                // i = player.getTrailLength();
            }
        }
        //check if health is zero
        if(health <= 0){
            returnValue += 1;
        }

        return returnValue;    
    }

    private int BFS(ArrayList<ArrayList<Integer>> graph, int number_of_verties, int source, int destination){

        int prev[] = new int[number_of_verties];


        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(source);
        

        boolean visited[] = new boolean[number_of_verties];
        for(int i = 0; i < number_of_verties; i++){
            visited[i] = false;
        }
        // set the first visit to true
        visited[source]= true;

        int result = 0;

        while(!queue.isEmpty()){
            // remove the top of the queue and and look at it's neighbours
            int u = queue.remove();
            // get the neighbours of the current node
            ArrayList<Integer> neighbours = graph.get(u);

            // look at all neighbours
            for(int i = 0; i <graph.get(u).size(); i++){
                // if you havn't visited it before don't look again
                if(visited[neighbours.get(i)] == false){
                    // add the neighbour to the queue
                    queue.add(neighbours.get(i));
                    // set the node to visited
                    visited[neighbours.get(i)] = true;
                    // the array prev stores at i index the number of the node it is connected to
                    prev[neighbours.get(i)] = u;
                }
            }       
        }
        
        int find = destination;
        result = destination;
        while(prev[result] != source){
            if (prev[find]==source){
                result = find;
            }
            find = prev[find];
        }
        return result;
    }

    public int getXPos() {
        return xpos;
    }

    public int getYPos() {
        return ypos;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int hp){
        this.health = hp;
    }
}
    