package Model;

import javax.swing.text.Position;
import java.awt.*;

public class Player extends Tile{

    private Color trailColor;
    private Color color;
    private int velocityX =0;
    private int velocityY =0;


    public Player(int x, int y, Color color, Color trailColor){
        super(x,y);
        this.color = color;
        this.trailColor = trailColor;
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

}
