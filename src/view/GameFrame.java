package view;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private SummaryPanel summaryPanel;
    private MenuBar menu;
    private ToolBar toolbar;
    private WheelPanel wheelPanel;
    private StatusBar statusBar;

    public GameFrame() {
        // title
        super("Wheel game");

        GameEngine gameEngine = new GameEngineImpl();
        gameEngine.addPlayer(new SimplePlayer("Bob", 100));
        gameEngine.addPlayer(new SimplePlayer("Jack", 100));
        gameEngine.addPlayer(new SimplePlayer("Bill", 100));

        // Add callbacks
        gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
        gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(this));

        // create sections
        this.menu = new MenuBar(gameEngine, this);
        this.toolbar = new ToolBar(gameEngine, this);
        this.wheelPanel = new WheelPanel(gameEngine);
        this.summaryPanel = new SummaryPanel(gameEngine);
        this.statusBar = new StatusBar();

        // add sections
        setJMenuBar(menu);
        add(toolbar, BorderLayout.NORTH);
        add(wheelPanel, BorderLayout.CENTER);
        add(summaryPanel, BorderLayout.EAST);
        add(statusBar, BorderLayout.SOUTH);

        // finish setup
        pack();
        setMinimumSize(getSize());

        // centered on screen
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public SummaryPanel getSummaryPanel() {
        return this.summaryPanel;
    }

    public MenuBar getMenu() {
        return this.menu;
    }

    public WheelPanel getWheelPanel() {
        return this.wheelPanel;
    }

    public StatusBar getStatusBar() {
        return this.statusBar;
    }

    /**
     * Called when when players are updated<br>
     * syncs the players displayed in the views with the players in the model.
     */
    public void updatePlayers() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                summaryPanel.refresh();
                menu.getRemovePlayerDialog().refresh();
                toolbar.getBetsDialog().refresh();
                revalidate();
                repaint();
            }
        });
    }
}
