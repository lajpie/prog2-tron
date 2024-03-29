import Model.GridPane;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    public Main(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.add(new GridPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

}