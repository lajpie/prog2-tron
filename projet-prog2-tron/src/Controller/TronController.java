package Controller;

import View.MainMenu;
import Model.TronGame;
import View.TronView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.*;

public class TronController implements ActionListener, KeyListener {

    private TronView gameView;
    private MainMenu menuView;

    private TronGame tronGame;

    //logic

    private final Set<Integer> pressedKeys = new HashSet<>(); // Set of currently pressed keys
    Timer gameLoop;
    Object[] endOfGameOptions = {"yes", "no"};
    int gameMode = 1;

    public TronController(TronGame game, MainMenu menuView, TronView gameView){
        tronGame = game;
        this.menuView = menuView;
        this.gameView = gameView;
        this.gameView.addKeyListener(this);
        this.gameView.setVisible(false);
        this.menuView.setVisible(true);
        this.menuView.requestFocus();

        this.menuView.setQuickPlayListener(new QuickPlayListener());
        this.menuView.setBestOf3Listener(new BestOf3Listener());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pressedKeys.clear();
        tronGame.move();
        gameView.repaint();

        if(tronGame.isGameOver()){

            gameLoop.stop();
            System.out.println("game over");

            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Play Again?",
                    "GAME OVER "+tronGame.getWinner(),
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    endOfGameOptions,
                    endOfGameOptions[1]
            );

            if(choice==JOptionPane.YES_OPTION){
                chooseGameMode(gameMode);
            } else {
                gameView.setVisible(false);
                menuView.setVisible(true);
                menuView.requestFocus();
            }
        }

    }

    private void startGame(){
        gameLoop = new Timer(200, this);
        gameLoop.start();
    }

    private void chooseGameMode(int gameMode){
        this.gameMode = gameMode;
        tronGame.restartGame();
        tronGame.setGameLives(this.gameMode);

        startGame();
    }

    class QuickPlayListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            menuView.setVisible(false);
            gameView.setVisible(true);
            gameView.requestFocus();
            chooseGameMode(1);
        }
    }

    class BestOf3Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            menuView.setVisible(false);
            gameView.setVisible(true);
            gameView.requestFocus();
            chooseGameMode(3);
        }
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
                        if(tronGame.getPlayer1().getVelocityY()!=1){
                            tronGame.getPlayer1().setVelocityY(-1);
                            tronGame.getPlayer1().setVelocityX(0);
                        }
                        break;
                    case KeyEvent.VK_A:
                        if(tronGame.getPlayer1().getVelocityX()!=1) {
                            tronGame.getPlayer1().setVelocityY(0);
                            tronGame.getPlayer1().setVelocityX(-1);
                        }
                        break;
                    case KeyEvent.VK_S:
                        if(tronGame.getPlayer1().getVelocityY()!=-1){
                            tronGame.getPlayer1().setVelocityY(1);
                            tronGame.getPlayer1().setVelocityX(0);
                        }
                        break;
                    case KeyEvent.VK_D:
                        if(tronGame.getPlayer1().getVelocityX()!=-1) {
                            tronGame.getPlayer1().setVelocityY(0);
                            tronGame.getPlayer1().setVelocityX(1);
                        }
                }
            }

            //player 2
            for (Iterator<Integer> it = pressedKeys.iterator(); it.hasNext();) {
                switch (it.next()) {
                    case KeyEvent.VK_UP:
                        if(tronGame.getPlayer2().getVelocityY()!=1){
                            tronGame.getPlayer2().setVelocityY(-1);
                            tronGame.getPlayer2().setVelocityX(0);
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if(tronGame.getPlayer2().getVelocityX()!=1) {
                            tronGame.getPlayer2().setVelocityY(0);
                            tronGame.getPlayer2().setVelocityX(-1);
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(tronGame.getPlayer2().getVelocityY()!=-1){
                            tronGame.getPlayer2().setVelocityY(1);
                            tronGame.getPlayer2().setVelocityX(0);
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(tronGame.getPlayer2().getVelocityX()!=-1) {
                            tronGame.getPlayer2().setVelocityY(0);
                            tronGame.getPlayer2().setVelocityX(1);
                        }
                }
            }
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }

}
