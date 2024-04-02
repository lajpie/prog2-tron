package View;

import Model.TronGame;

import javax.swing.*;
import java.awt.*;

public class TronView extends JPanel {

    private TronGame tronGame;
    public TronView(TronGame tronGame){
        this.tronGame = tronGame;

        setPreferredSize(new Dimension(tronGame.getGridWidth(),tronGame.getGridHeight()));
        setBackground(Color.BLACK);

        setVisible(true);
        setFocusable(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        //Grid
        for (int i = 0; i < tronGame.getGridWidth()/ tronGame.getTileSize(); i++){
            //lignes verticales
            g.drawLine(i*tronGame.getTileSize(),0,i*tronGame.getTileSize(), tronGame.getGridHeight());

            //lignes horizontales
            g.drawLine(0, i * tronGame.getTileSize(), tronGame.getGridWidth(), i*tronGame.getTileSize());
        }



        //murs
        g.setColor(Color.GRAY);
        tronGame.getMurs().forEach((m) -> g.fillRect(m.getX() * tronGame.getTileSize(), m.getY() * tronGame.getTileSize(), tronGame.getTileSize(),tronGame.getTileSize()));

        g.setFont(new Font("Arial",Font.BOLD,20));

        //player 1
        g.setColor(tronGame.getPlayer1().getColor());
        tronGame.getPlayer1Trail().forEach((m) -> g.fillRect(m.getX() * tronGame.getTileSize(), m.getY() * tronGame.getTileSize(), tronGame.getTileSize(),tronGame.getTileSize()));
        g.setColor(tronGame.getPlayer1().getTrailColor());
        g.fillRect(tronGame.getPlayer1().getX() * tronGame.getTileSize(), tronGame.getPlayer1().getY() * tronGame.getTileSize(), tronGame.getTileSize(),tronGame.getTileSize());
        g.drawString("Player 1    Lives:"+tronGame.getPlayer1().getLives(), tronGame.getTileSize(),tronGame.getTileSize() -5);

        //player 2
        g.setColor(tronGame.getPlayer2().getColor());
        tronGame.getPlayer2Trail().forEach((m) -> g.fillRect(m.getX() * tronGame.getTileSize(), m.getY() * tronGame.getTileSize(), tronGame.getTileSize(),tronGame.getTileSize()));
        g.setColor(tronGame.getPlayer2().getTrailColor());
        g.fillRect(tronGame.getPlayer2().getX() * tronGame.getTileSize(), tronGame.getPlayer2().getY() * tronGame.getTileSize(), tronGame.getTileSize(),tronGame.getTileSize());
        g.drawString("Player 2    Lives:"+tronGame.getPlayer2().getLives(),tronGame.getGridWidth()- tronGame.getTileSize()*8,tronGame.getGridHeight()-5);


    }

}
