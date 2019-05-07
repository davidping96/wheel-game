package controller;

import model.interfaces.GameEngine;
import view.GameFrame;
import view.dialogs.RemovePlayerDialog;
import view.util.Constants;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RemovePlayerActionListener extends MouseAdapter {

    private GameEngine gameEngine;
    private GameFrame gameFrame;
    private RemovePlayerDialog source;

    public RemovePlayerActionListener(GameEngine gameEngine, GameFrame gameFrame, RemovePlayerDialog source) {
        this.gameEngine = gameEngine;
        this.gameFrame = gameFrame;
        this.source = source;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        switch (button.getText()) {
            case Constants.REMOVE:
                handleClickRemoveButton();
                break;
            case Constants.CANCEL:
                handleClickCancelButton();
                break;
        }
    }

    private void handleClickRemoveButton() {
        for (JCheckBox checkBox : source.getCheckBoxes()) {
            if (checkBox.isSelected()) {
                this.gameEngine.removePlayer(gameEngine.getPlayer(checkBox.getText()));
            }
        }
        JOptionPane.showMessageDialog(source.getParent(), "Player(s) removed", "Success", JOptionPane.INFORMATION_MESSAGE);
        this.gameFrame.updatePlayers();
    }

    private void handleClickCancelButton() {
        this.source.setVisible(false);
    }
}
