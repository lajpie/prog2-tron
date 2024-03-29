package View;

import Model.Player;
import Model.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TronGame extends JPanel {

    //Grid and tile size
    int gridWidth;
    int gridHeight;
    int tileSize = 25;

    //players
    Player player1;
    Player player2;


    ArrayList<Tile> murs = new ArrayList<Tile>();

    public TronGame(int gridWidth,int gridHeight){
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        setPreferredSize(new Dimension(this.gridWidth,this.gridHeight));
        setBackground(Color.BLACK);

        player1 = new Player(5,5,Color.YELLOW);
        player2 = new Player(26,26,Color.BLUE);

        for (int i = 0; i < gridWidth/tileSize; i++){
            murs.add(new Tile(i,0));
            murs.add(new Tile(i,31));
            murs.add(new Tile(0,i));
            murs.add(new Tile(31,i));
        }

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
        g.setColor(player1.getTrailColor());
        g.fillRect(player1.getX() * tileSize, player1.getY() * tileSize, tileSize,tileSize);

        //player 2
        g.setColor(player2.getTrailColor());
        g.fillRect(player2.getX() * tileSize, player2.getY() * tileSize, tileSize,tileSize);



    }
}
