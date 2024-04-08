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
    private JLabel menuTitle;

    public MainMenu(int gridWidth, int gridHeight){
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        setPreferredSize(new Dimension(this.gridWidth,this.gridHeight));
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());

        GridBagConstraints grid = new GridBagConstraints();
        grid.gridwidth = GridBagConstraints.REMAINDER;
        grid.anchor = GridBagConstraints.NORTH;
        

        menuTitle = new JLabel("Tron");
        menuTitle.setForeground(Color.CYAN);
        menuTitle.setFont(new Font("Futura",Font.ITALIC+Font.BOLD,100));
        quickPlay = new JButton("Quick Play");
        bestOf3 = new JButton("Best of 3");
  


        //afficher titre
        this.add(menuTitle,grid);

        grid.anchor = GridBagConstraints.CENTER;
        grid.fill = GridBagConstraints.HORIZONTAL;

        this.add(quickPlay,grid);
        this.add(bestOf3,grid);
        grid.weighty = 1;

    }

    public  void setQuickPlayListener(ActionListener listener){ quickPlay.addActionListener(listener); }
    public  void setBestOf3Listener(ActionListener listener){ bestOf3.addActionListener(listener); }

}
