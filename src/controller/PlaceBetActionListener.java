package controller;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameFrame;
import view.dialogs.BetsDialog;
import view.util.Constants;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlaceBetActionListener extends MouseAdapter {

    private GameEngine gameEngine;
    private GameFrame gameFrame;
    private BetsDialog source;

    public PlaceBetActionListener(GameEngine gameEngine, GameFrame gameFrame, BetsDialog source) {
        this.gameEngine = gameEngine;
        this.gameFrame = gameFrame;
        this.source = source;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        switch (button.getText()) {
            case Constants.OK:
                handleClickOkButton();
                break;
            case Constants.CANCEL:
                handleClickCancelButton();
                break;
        }
    }

    /**
     * Handles clicks on the ok button for the place bets dialog, if valid bets are placed for all players then the
     * wheel will be spun.
     */
    private void handleClickOkButton() {
        boolean allBetsPlaced = true;
        boolean valid = true;
        for (Player player : gameEngine.getAllPlayers()) {
            try {
                int bet = Integer.parseInt(source.getBetFields().get(player.getPlayerId()).getText());
                BetType betType = (BetType) source.getBetTypeDropdowns().get(player.getPlayerId()).getSelectedItem();
                if (this.gameEngine.placeBet(player, bet, betType)) {
                    this.gameFrame.getSummaryPanel().storeCurrentPlayerPoints();
                    this.gameFrame.updatePlayers();
                } else {
                    allBetsPlaced = false;
                }
                if (bet < 0 || bet > player.getPoints()) {
                    valid = false;
                }
            } catch (NumberFormatException ex) {
                valid = false;
            }
        }
        if (allBetsPlaced && valid) {
            //Spin if all bets are placed
            new Thread() {
                @Override
                public void run() {
                    //call long running GameEngine methods on separate thread
                    gameEngine.spin(1, 500, 25);
                }
            }.start();
        }
        if (!valid) {
            JOptionPane.showMessageDialog(source.getParent(), "Invalid bets were made", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            source.setVisible(false);
        }
    }

    private void handleClickCancelButton() {
        source.setVisible(false);
    }
}
