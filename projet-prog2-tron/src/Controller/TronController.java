package Controller;

import View.MainMenu;
import View.TronGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class TronController implements ActionListener {

    private TronGame gameView;
    private MainMenu menuView;

    //logic
    Timer gameLoop;

    public TronController(TronGame view, MainMenu menuView){
        gameView = view;
        this.menuView = menuView;

        gameView.setVisible(false);
        menuView.setVisible(true);
        menuView.requestFocus();

        menuView.setQuickPlayListener(new QuickPlayListener());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameView.updateGame();

        if(gameView.isGameOver()){
            gameLoop.stop();
            System.out.println("game over");

        }

    }

    private void startGame(){
        gameLoop = new Timer(200, this);
        gameLoop.start();
    }

    class QuickPlayListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            menuView.setVisible(false);
            gameView.setVisible(true);
            gameView.requestFocus();
            startGame();
        }
    }


}
