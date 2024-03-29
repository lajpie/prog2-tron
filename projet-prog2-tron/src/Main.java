import Controller.TronController;
import View.TronGame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception{
        int gridWidth = 800;
        int gridHeight = gridWidth;

        JFrame frame = new JFrame("Tron");
        frame.setVisible(true);
        frame.setSize(gridWidth,gridHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TronGame tronGame = new TronGame(gridWidth,gridHeight);
        frame.add(tronGame);
        frame.pack();


    }



}