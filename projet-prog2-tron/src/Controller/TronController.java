package Controller;

import View.TronGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class TronController implements ActionListener {

    private TronGame gameView;

    //logic
    Timer gameLoop;

    public TronController(TronGame view){
        gameView = view;


        gameLoop = new Timer(200, this);
        gameLoop.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        gameView.updateGame();
    }
}
