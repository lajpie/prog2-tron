package Model;

import javax.swing.text.Position;
import java.awt.*;

public class Player extends Tile{

    private Color trailColor;
    private int velocityX =0;
    private int velocityY =0;


    public Player(int x, int y, Color color){
        super(x,y);
        trailColor = color;
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
}
