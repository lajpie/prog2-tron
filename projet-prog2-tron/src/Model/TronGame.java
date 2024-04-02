package Model;

import Model.Player;
import Model.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TronGame {

    //Grid and tile size
    int gridWidth;
    int gridHeight;
    int tileSize = 25;

    //Used Tiles (players, walls and trails)
    Player player1;
    Player player2;
    ArrayList<Tile> murs = new ArrayList<Tile>();
    ArrayList<Tile> player1Trail = new ArrayList<Tile>();
    ArrayList<Tile> player2Trail = new ArrayList<Tile>();



    //etat
    boolean gameOver = false;

    public TronGame(int gridWidth,int gridHeight){
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;


        player1 = new Player(5,5,Color.YELLOW,Color.ORANGE);
        player2 = new Player(26,26,Color.BLUE,Color.CYAN);
        restartGame();

        for (int i = 0; i < gridWidth/tileSize; i++){
            murs.add(new Tile(i,0));
            murs.add(new Tile(i,31));
            murs.add(new Tile(0,i));
            murs.add(new Tile(31,i));
        }


    }

    public int getGridWidth() {
        return gridWidth;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public int getTileSize() {
        return tileSize;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public ArrayList<Tile> getMurs() {
        return murs;
    }

    public ArrayList<Tile> getPlayer1Trail() {
        return player1Trail;
    }

    public ArrayList<Tile> getPlayer2Trail() {
        return player2Trail;
    }

    public void move() {
        player1Trail.add(new Tile(player1.getX(), player1.getY()));
        player1.setX(player1.getX() + player1.getVelocityX());
        player1.setY(player1.getY() + player1.getVelocityY());

        player2Trail.add(new Tile(player2.getX(), player2.getY()));
        player2.setX(player2.getX() + player2.getVelocityX());
        player2.setY(player2.getY() + player2.getVelocityY());

    }


    public void updateGame(){
        move();
    }




    public boolean isGameOver(){

        boolean uneCollision = false;

        //vérifie les murs
        for (int i = 0; i<murs.size();i++){
            Tile mur = murs.get(i);

            if(player1.collision(mur)){
                player1.setLives(player1.getLives() -1);
                uneCollision = true;
            }
            if(player2.collision(mur)){
                player2.setLives(player2.getLives() -1);
                uneCollision = true;
            }
        }

        //vérifie la player1Trail
        for (int i = 0; i<player1Trail.size();i++){
            Tile mur = player1Trail.get(i);

            if(player1.collision(mur)){
                player1.setLives(player1.getLives() -1);
                uneCollision = true;
            }
            if(player2.collision(mur)){
                player2.setLives(player2.getLives() -1);
                uneCollision = true;
            }
        }

        //vérifie la player2Trail
        for (int i = 0; i<player2Trail.size();i++){
            Tile mur = player2Trail.get(i);

            if(player1.collision(mur)){
                player1.setLives(player1.getLives() -1);
                uneCollision = true;
            }
            if(player2.collision(mur)){
                player2.setLives(player2.getLives() -1);
                uneCollision = true;
            }
        }

        //Vérifie les collision et les pv restants
        //TODO: afficher lives
        if(player1.getLives()<=0 || player2.getLives()<=0){
            gameOver =true;
        } else if ( uneCollision && (player1.getLives()>=1 || player2.getLives()>=1)) {
            restartGame();
        }
        return gameOver;
    }

    public void restartGame(){
        player1.setX(5);
        player1.setY(5);
        player1.setVelocityY(1);
        player1.setVelocityX(0);

        player2.setX(26);
        player2.setY(26);
        player2.setVelocityY(-1);
        player2.setVelocityX(0);

        player1Trail = new ArrayList<Tile>();
        player2Trail = new ArrayList<Tile>();

        gameOver = false;
    }

    public void setGameLives(int l){
        player1.setLives(l);
        player2.setLives(l);
    }


}
