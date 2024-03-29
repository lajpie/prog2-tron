package View;

import Model.Player;
import Model.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private final int gridWidth;
    private final int gridHeight;

    private JButton quickPlay, bestOf3;

    public MainMenu(int gridWidth, int gridHeight){
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        setPreferredSize(new Dimension(this.gridWidth,this.gridHeight));
        setBackground(Color.BLACK);

        quickPlay = new JButton("Quick Play");
        bestOf3 = new JButton("Best of 3");

        this.add(quickPlay);
        this.add(bestOf3);

    }

    public  void setQuickPlayListener(ActionListener listener){ quickPlay.addActionListener(listener); }
    public  void setBestOf3Listener(ActionListener listener){ bestOf3.addActionListener(listener); }

}
