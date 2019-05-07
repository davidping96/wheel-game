package view;

import model.interfaces.GameEngine;
import view.dialogs.AddPlayerDialog;
import view.dialogs.RemovePlayerDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {

    private AddPlayerDialog addPlayerDialog;
    private RemovePlayerDialog removePlayerDialog;

    public MenuBar(GameEngine gameEngine, GameFrame gameFrame) {
        this.addPlayerDialog = new AddPlayerDialog(gameEngine, gameFrame);
        this.removePlayerDialog = new RemovePlayerDialog(gameEngine, gameFrame);

        setBackground(Color.LIGHT_GRAY);

        // create Menu
        JMenu menu = new JMenu("Players");

        menu.setMnemonic(KeyEvent.VK_P);
        add(menu);

        // create Menu Items
        JMenuItem addPlayerMenuItem = new JMenuItem("Add player", KeyEvent.VK_A);
        JMenuItem removePlayerMenuItem = new JMenuItem("Remove player", KeyEvent.VK_R);

        // add listeners to Menu Items
        addPlayerMenuItem.addActionListener(e -> this.addPlayerDialog.setVisible(true));
        removePlayerMenuItem.addActionListener(e -> this.removePlayerDialog.setVisible(true));

        // add MenuItems to the Menu
        menu.add(addPlayerMenuItem);
        menu.add(removePlayerMenuItem);

        // and the Menu to the MenuBar
        add(menu);
    }

    public RemovePlayerDialog getRemovePlayerDialog() {
        return this.removePlayerDialog;
    }
}
