package view.dialogs;

import controller.PlaceBetActionListener;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameFrame;
import view.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class BetsDialog extends JDialog {

    private GameEngine gameEngine;

    private JPanel innerPanel;
    private JPanel buttonPanel;

    private Map<String, JComboBox<BetType>> betTypeDropdowns = new HashMap();
    private Map<String, JTextField> betFields = new LinkedHashMap();

    public BetsDialog(GameEngine gameEngine, GameFrame gameFrame) {
        super(gameFrame, "Place bets", true);

        this.gameEngine = gameEngine;

        setLayout();

        JButton okButton = new JButton(Constants.OK);
        JButton cancelButton = new JButton(Constants.CANCEL);
        this.buttonPanel.add(okButton);
        this.buttonPanel.add(cancelButton);

        PlaceBetActionListener placeBetActionListener = new PlaceBetActionListener(gameEngine, gameFrame, this);
        okButton.addMouseListener(placeBetActionListener);
        cancelButton.addMouseListener(placeBetActionListener);

        refresh();
    }

    private void setLayout() {
        JPanel parentPanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.innerPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());
        this.innerPanel.setLayout(new GridBagLayout());
        this.buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        parentPanel.setBorder(BorderFactory.createEmptyBorder(Constants.LG_PADDING, Constants.LG_PADDING, Constants.LG_PADDING, Constants.LG_PADDING));
        parentPanel.add(innerPanel, BorderLayout.CENTER);
        parentPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(parentPanel);
    }

    public void refresh() {
        Collection<Player> players = gameEngine.getAllPlayers();
        this.innerPanel.removeAll();
        this.innerPanel.repaint();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, Constants.SM_PADDING, Constants.MD_PADDING, Constants.SM_PADDING);
        gbc.gridy = 0;

        setHeadLabels(gbc);

        gbc.gridy += 1;

        for (Player player : players) {
            JLabel nameLabel = new JLabel(player.getPlayerName());
            JComboBox<BetType> betTypeDropdown = new JComboBox<>(BetType.values());
            if (player.getBetType() != null) {
                betTypeDropdown.setSelectedItem(player.getBetType());
            }
            JTextField betField = new JTextField(Constants.MD_FIELD_SIZE);
            betField.setText(String.valueOf(player.getBet()));

            gbc.gridx = Constants.FIRST_COLUMN;
            this.innerPanel.add(nameLabel, gbc);
            gbc.gridx = Constants.SECOND_COLUMN;
            this.innerPanel.add(betTypeDropdown, gbc);
            gbc.gridx = Constants.THIRD_COLUMN;
            this.innerPanel.add(betField, gbc);
            gbc.gridy += 1;

            this.betFields.put(player.getPlayerId(), betField);
            this.betTypeDropdowns.put(player.getPlayerId(), betTypeDropdown);
        }

        // finish setup
        pack();
        setMinimumSize(new Dimension(getWidth(), 0));

        // centered on screen
        setLocationRelativeTo(null);
    }


    private void setHeadLabels(GridBagConstraints gbc) {
        gbc.gridx = Constants.FIRST_COLUMN;
        JLabel name = new JLabel("Name");
        this.innerPanel.add(name, gbc);

        gbc.gridx = Constants.SECOND_COLUMN;
        JLabel betType = new JLabel("Bet type");
        this.innerPanel.add(betType, gbc);

        gbc.gridx = Constants.THIRD_COLUMN;
        JLabel bet = new JLabel("Bet (points)");
        this.innerPanel.add(bet, gbc);
    }

    public Map<String, JComboBox<BetType>> getBetTypeDropdowns() {
        return this.betTypeDropdowns;
    }

    public Map<String, JTextField> getBetFields() {
        return this.betFields;
    }
}
