package Model;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GridPane extends JPanel {
    public GridPane() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        for (int row = 0; row < 5; row++) {
            gbc.gridy = row;
            for (int col = 0; col < 7; col++) {
                gbc.gridx = col;
                add(new CellPane(), gbc);
            }
        }
    }
}

