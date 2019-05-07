package view.dialogs;

import controller.RemovePlayerActionListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameFrame;
import view.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RemovePlayerDialog extends JDialog {

    private GameEngine gameEngine;

    private JPanel innerPanel;
    private JPanel buttonPanel;

    /**
     * Store the checkboxes inside a list to avoid getting components from the panel and using instanceof checks to
     * differ between labels and checkboxes
     */
    private List<JCheckBox> checkBoxes = new ArrayList<>();

    public RemovePlayerDialog(GameEngine gameEngine, GameFrame gameFrame) {
        super(gameFrame, "Remove player", true);

        this.gameEngine = gameEngine;

        setLayout();

        JButton removeButton = new JButton(Constants.REMOVE);
        JButton cancelButton = new JButton(Constants.CANCEL);

        RemovePlayerActionListener removePlayerActionListener = new RemovePlayerActionListener(gameEngine, gameFrame, this);
        removeButton.addMouseListener(removePlayerActionListener);
        cancelButton.addMouseListener(removePlayerActionListener);

        buttonPanel.add(removeButton);
        buttonPanel.add(cancelButton);

        refresh();
    }

    private void setLayout() {
        JPanel parentPanel = new JPanel();
        this.innerPanel = new JPanel();
        this.buttonPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());
        this.innerPanel.setLayout(new GridBagLayout());
        this.buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        // Add padding around the dialog
        parentPanel.setBorder(BorderFactory.createEmptyBorder(Constants.LG_PADDING, Constants.LG_PADDING, Constants.LG_PADDING, Constants.LG_PADDING));
        parentPanel.add(innerPanel, BorderLayout.CENTER);
        parentPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(parentPanel);
    }

    public void refresh() {
        Collection<Player> players = gameEngine.getAllPlayers();
        this.checkBoxes.clear();
        this.innerPanel.removeAll();
        this.innerPanel.repaint();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, Constants.SM_PADDING, Constants.MD_PADDING, Constants.SM_PADDING);

        gbc.gridy = 0;

        for (Player player : players) {
            JLabel nameLabel = new JLabel(player.getPlayerName());
            JCheckBox checkBox = new JCheckBox(player.getPlayerId());

            gbc.gridx = Constants.FIRST_COLUMN;
            innerPanel.add(nameLabel, gbc);
            gbc.gridx = Constants.SECOND_COLUMN;
            innerPanel.add(checkBox, gbc);
            gbc.gridy += 1;
            checkBoxes.add(checkBox);
        }

        // finish setup
        pack();
        setMinimumSize(new Dimension(getWidth(), 0));

        // centered on screen
        setLocationRelativeTo(null);
    }

    public List<JCheckBox> getCheckBoxes() {
        return this.checkBoxes;
    }
}
