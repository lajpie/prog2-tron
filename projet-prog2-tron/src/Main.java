import Controller.TronController;
import View.MainMenu;
import Model.TronGame;
import View.TronView;

import java.awt.GridBagLayout;

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
        TronView tronView = new TronView(tronGame);
        frame.add(tronView);
        frame.pack();

        MainMenu mainMenu = new MainMenu(gridWidth,gridHeight);
        frame.add(mainMenu);
        frame.pack();
        mainMenu.requestFocus();

        TronController tronController = new TronController(tronGame, mainMenu,tronView);
    }



}