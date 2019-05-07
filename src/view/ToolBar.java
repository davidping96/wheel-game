package view;

import controller.ToolBarActionListener;
import model.interfaces.GameEngine;
import view.dialogs.BetsDialog;
import view.util.Constants;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {

    private BetsDialog betsDialog;
    private static final int BUTTON_FONT_SIZE = 20;

    public ToolBar(GameEngine gameEngine, GameFrame gameFrame) {
        setFloatable(false);

        this.betsDialog = new BetsDialog(gameEngine, gameFrame);

        JButton spinButton = new JButton(Constants.SPIN);
        JButton placeBetsButton = new JButton(Constants.PLACE_BETS);

        spinButton.setMargin(new Insets(Constants.MD_PADDING,Constants.MD_PADDING, Constants.MD_PADDING, Constants.MD_PADDING));
        placeBetsButton.setMargin(new Insets(Constants.MD_PADDING,Constants.MD_PADDING,Constants.MD_PADDING,Constants.MD_PADDING));
        spinButton.setFont(new Font("Arial", Font.PLAIN, BUTTON_FONT_SIZE));
        placeBetsButton.setFont(new Font("Arial", Font.PLAIN, BUTTON_FONT_SIZE));

        ToolBarActionListener toolBarActionListener = new ToolBarActionListener(gameEngine,this);
        spinButton.addMouseListener(toolBarActionListener);
        placeBetsButton.addMouseListener(toolBarActionListener);

        add(spinButton);
        add(placeBetsButton);
    }

    public BetsDialog getBetsDialog() {
        return this.betsDialog;
    }
}
