package Model;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellPane extends JPanel {

    private boolean selected;

    public CellPane() {
        setBorder(new LineBorder(Color.DARK_GRAY));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toggleSelection();
            }
        });
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
            setBackground(Color.BLUE);
            setBorder(new LineBorder(Color.WHITE));
        } else {
            setBackground(null);
            setBorder(new LineBorder(Color.DARK_GRAY));
        }
        repaint();
    }

    public void toggleSelection() {
        setSelected(!isSelected());
    }

    public boolean isSelected() {
        return selected;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(30, 30);
    }
}
