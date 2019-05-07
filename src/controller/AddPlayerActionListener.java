package controller;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import view.GameFrame;
import view.dialogs.AddPlayerDialog;
import view.util.Constants;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddPlayerActionListener extends MouseAdapter {

    private GameEngine gameEngine;
    private GameFrame gameFrame;
    private AddPlayerDialog source;

    public AddPlayerActionListener(GameEngine gameEngine, GameFrame gameFrame, AddPlayerDialog source) {
        this.gameEngine = gameEngine;
        this.gameFrame = gameFrame;
        this.source = source;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        switch (button.getText()) {
            case Constants.ADD:
                handleClickAddButton();
                break;
            case Constants.CANCEL:
                handleClickCancelButton();
                break;
        }
    }

    /**
     * Handles clicks on the ok button, takes inputs from the name & points field to create a new player
     */
    private void handleClickAddButton() {
        try {
            String name = source.getNameField().getText();
            String points = source.getPointsField().getText();
            this.gameEngine.addPlayer(new SimplePlayer(name, Integer.parseInt(points)));
            JOptionPane.showMessageDialog(source.getParent(), "Player added", "Success", JOptionPane.INFORMATION_MESSAGE);
            this.gameFrame.updatePlayers();
            this.source.setVisible(false);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(source.getParent(), "Invalid input, enter integer value for points field", "Failure", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void handleClickCancelButton() {
        source.setVisible(false);
    }
}
