package Model;

import javax.swing.text.Position;
import java.awt.*;
import java.util.ArrayList;

public class Player extends Tile{

    private Color trailColor;
    private Color color;
    private int velocityX =0;
    private int velocityY =0;
    private int lives;

    private ArrayList<Tile> trail;

    public Player(int x, int y, Color color, Color trailColor){
        super(x,y);
        this.color = color;
        this.trailColor = trailColor;
        this.trail = new ArrayList<Tile>();
    }

    public Color getTrailColor() {
        return trailColor;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public Color getColor() {
        return color;
    }

    public boolean collision(Tile tile) {
        return this.getX() == tile.getX() && this.getY() == tile.getY();
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives(){
        return lives;
    }

    public ArrayList<Tile> getTrail() {
        return trail;
    }

    public void addToTrail(Tile tile){
        trail.add(tile);
    }

    public void resetTrail(){
        trail = new ArrayList<Tile>();
    }
}
