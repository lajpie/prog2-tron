package Model;

import javax.swing.text.Position;
import java.awt.*;

public class Player extends Tile{

    private Color trailColor;


    public Player(int x, int y, Color color){
        super(x,y);
        trailColor = color;

    }

    public Color getTrailColor() {
        return trailColor;
    }


}
