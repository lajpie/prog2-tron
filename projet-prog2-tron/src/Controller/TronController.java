package Controller;

import View.MainMenu;
import View.TronGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TronController implements ActionListener {

    private TronGame gameView;
    private MainMenu menuView;

    //logic
    Timer gameLoop;
    Object[] endOfGameOptions = {"yes", "no"};
    int gameMode = 1;

    public TronController(TronGame view, MainMenu menuView){
        gameView = view;
        this.menuView = menuView;

        gameView.setVisible(false);
        menuView.setVisible(true);
        menuView.requestFocus();

        menuView.setQuickPlayListener(new QuickPlayListener());
        menuView.setBestOf3Listener(new BestOf3Listener());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameView.updateGame();

        if(gameView.isGameOver()){
            gameLoop.stop();
            System.out.println("game over");

            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Play Again?",
                    "GAME OVER",
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
        gameView.restartGame();
        gameView.setGameLives(this.gameMode);
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


}
