package View;

import Model.Player;
import Model.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TronGame extends JPanel implements KeyListener {

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

    // Set of currently pressed keys
    private final Set<Integer> pressedKeys = new HashSet<>();

    //etat
    boolean gameOver = false;

    public TronGame(int gridWidth,int gridHeight){
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        setPreferredSize(new Dimension(this.gridWidth,this.gridHeight));
        setBackground(Color.BLACK);

        player1 = new Player(5,5,Color.YELLOW,Color.ORANGE);
        player2 = new Player(26,26,Color.BLUE,Color.CYAN);
        restartGame();

        for (int i = 0; i < gridWidth/tileSize; i++){
            murs.add(new Tile(i,0));
            murs.add(new Tile(i,31));
            murs.add(new Tile(0,i));
            murs.add(new Tile(31,i));
        }

        addKeyListener(this);
        setFocusable(true);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        //Grid
        for (int i = 0; i < gridWidth/tileSize; i++){
            //lignes verticales
            g.drawLine(i*tileSize,0,i*tileSize,gridHeight);

            //lignes horizontales
            g.drawLine(0, i * tileSize, gridWidth, i*tileSize);
        }

        //murs
        g.setColor(Color.GRAY);
        murs.forEach((m) -> g.fillRect(m.getX() * tileSize, m.getY() * tileSize, tileSize,tileSize));



        //player 1
        g.setColor(player1.getColor());
        player1Trail.forEach((m) -> g.fillRect(m.getX() * tileSize, m.getY() * tileSize, tileSize,tileSize));
        g.setColor(player1.getTrailColor());
        g.fillRect(player1.getX() * tileSize, player1.getY() * tileSize, tileSize,tileSize);

        //player 2
        g.setColor(player2.getColor());
        player2Trail.forEach((m) -> g.fillRect(m.getX() * tileSize, m.getY() * tileSize, tileSize,tileSize));
        g.setColor(player2.getTrailColor());
        g.fillRect(player2.getX() * tileSize, player2.getY() * tileSize, tileSize,tileSize);



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
        repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {
        //pas utilisé
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
        if (!pressedKeys.isEmpty()) {

            //player 1
            for (Iterator<Integer> it = pressedKeys.iterator(); it.hasNext();) {
                switch (it.next()) {
                    case KeyEvent.VK_W:
                        if(player1.getVelocityY()!=1){
                            player1.setVelocityY(-1);
                            player1.setVelocityX(0);
                        }
                        break;
                    case KeyEvent.VK_A:
                        if(player1.getVelocityX()!=1) {
                            player1.setVelocityY(0);
                            player1.setVelocityX(-1);
                        }
                        break;
                    case KeyEvent.VK_S:
                        if(player1.getVelocityY()!=-1){
                            player1.setVelocityY(1);
                            player1.setVelocityX(0);
                        }
                        break;
                    case KeyEvent.VK_D:
                        if(player1.getVelocityX()!=-1) {
                            player1.setVelocityY(0);
                            player1.setVelocityX(1);
                        }
                }
            }

            //player 2
            for (Iterator<Integer> it = pressedKeys.iterator(); it.hasNext();) {
                switch (it.next()) {
                    case KeyEvent.VK_UP:
                        if(player2.getVelocityY()!=1){
                            player2.setVelocityY(-1);
                            player2.setVelocityX(0);
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if(player2.getVelocityX()!=1) {
                            player2.setVelocityY(0);
                            player2.setVelocityX(-1);
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(player2.getVelocityY()!=-1){
                            player2.setVelocityY(1);
                            player2.setVelocityX(0);
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(player2.getVelocityX()!=-1) {
                            player2.setVelocityY(0);
                            player2.setVelocityX(1);
                        }
                }
            }
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
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
