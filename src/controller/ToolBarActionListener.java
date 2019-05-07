package controller;

import model.interfaces.GameEngine;
import view.ToolBar;
import view.util.Constants;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ToolBarActionListener extends MouseAdapter {

    private GameEngine gameEngine;
    private ToolBar source;

    public ToolBarActionListener(GameEngine gameEngine, ToolBar source) {
        this.gameEngine = gameEngine;
        this.source = source;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        switch (button.getText()) {
            case Constants.SPIN:
                handleClickSpinButton();
                break;
            case Constants.PLACE_BETS:
                handleClickPlaceBetsButton();
                break;
        }
    }

    private void handleClickSpinButton() {
        new Thread() {
            @Override
            public void run() {
                //call long running GameEngine methods on separate thread
                gameEngine.spin(1, 500, 25);
            }
        }.start();
    }

    /**
     * Opens bet placement dialog
     */
    private void handleClickPlaceBetsButton() {
        this.source.getBetsDialog().setVisible(true);
    }
}
