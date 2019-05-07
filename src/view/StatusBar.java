package view;

import model.interfaces.Slot;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StatusBar extends JPanel {

    private JLabel statusLabel = new JLabel("Winning Slot:", JLabel.CENTER);

    public StatusBar() {
        setLayout(new GridLayout(1, 1));
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
        this.statusLabel.setBorder(blackBorder);
        add(statusLabel);
    }

    public void update(Slot winningSlot) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Winning Slot: ");
        stringBuilder.append(winningSlot.getNumber());
        stringBuilder.append(" ");
        stringBuilder.append(winningSlot.getColor());
        this.statusLabel.setText(stringBuilder.toString());
    }
}
