package view;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class SummaryPanel extends JPanel {

    private static final int PANEL_WIDTH = 250;

    private GameEngine gameEngine;
    private JPanel innerPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();

    private Map<Player, Integer> previousResults = new LinkedHashMap<>();

    public SummaryPanel(GameEngine gameEngine) {
        setPreferredSize(new Dimension(PANEL_WIDTH, 0));
        setBackground(Color.LIGHT_GRAY);
        setBorder(BorderFactory.createEmptyBorder(Constants.MD_PADDING, Constants.MD_PADDING, Constants.MD_PADDING, Constants.MD_PADDING));
        setLayout(new BorderLayout());

        add(innerPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        GridBagLayout gridBagLayout = new GridBagLayout();
        this.innerPanel.setLayout(gridBagLayout);
        this.bottomPanel.setLayout(gridBagLayout);

        this.gameEngine = gameEngine;

        refresh();
    }

    public void refresh() {
        this.innerPanel.removeAll();
        Collection<Player> players = gameEngine.getAllPlayers();
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 0.25;
        gbc.insets = new Insets(0, 0, Constants.MD_PADDING, 0);
        gbc.gridy = 0;

        setSummaryHeaderLabels(gbc);

        gbc.gridy += 1;

        for (Player player : players) {
            JLabel nameLabel = new JLabel(player.getPlayerName());
            JLabel pointsLabel = new JLabel(String.valueOf(player.getPoints()));
            JLabel betLabel = new JLabel(String.valueOf(player.getBet()));
            JLabel betTypeLabel = new JLabel(player.getBetType() != null ? String.valueOf(player.getBetType()) : "");
            gbc.gridx = Constants.FIRST_COLUMN;
            this.innerPanel.add(nameLabel, gbc);
            gbc.gridx = Constants.SECOND_COLUMN;
            this.innerPanel.add(pointsLabel, gbc);
            gbc.gridx = Constants.THIRD_COLUMN;
            this.innerPanel.add(betLabel, gbc);
            gbc.gridx = Constants.FOURTH_COLUMN;
            this.innerPanel.add(betTypeLabel, gbc);
            gbc.gridy += 1;
        }
    }

    public void showResults() {
        this.bottomPanel.removeAll();
        Collection<Player> players = gameEngine.getAllPlayers();
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 0.3;
        gbc.insets = new Insets(0, 0, Constants.MD_PADDING, 0);
        gbc.gridy = 0;

        setResultsHeaderLabels(gbc);

        gbc.gridy += 1;

        for (Player player : players) {
            JLabel nameLabel = new JLabel(player.getPlayerName());
            JLabel resultLabel = new JLabel();
            JLabel winLossLabel = new JLabel();
            if (previousResults.get(player) != null) {
                int winLossResult = player.getPoints() - previousResults.get(player);
                if (winLossResult > 0) {
                    resultLabel.setText("WIN");
                } else if (winLossResult < 0) {
                    resultLabel.setText("LOSE");
                }
                winLossLabel.setText(Integer.toString(winLossResult));
                gbc.gridx = Constants.FIRST_COLUMN;
                this.bottomPanel.add(nameLabel, gbc);
                gbc.gridx = Constants.SECOND_COLUMN;
                this.bottomPanel.add(resultLabel, gbc);
                gbc.gridx = Constants.THIRD_COLUMN;
                this.bottomPanel.add(winLossLabel, gbc);
                gbc.gridy += 1;
            }
            this.previousResults.put(player, player.getPoints());
        }
    }

    private void setSummaryHeaderLabels(GridBagConstraints gbc) {
        JLabel name = new JLabel("Name");
        JLabel points = new JLabel("Points");
        JLabel bet = new JLabel("Current bet");
        JLabel betType = new JLabel("Bet type");
        gbc.gridx = Constants.FIRST_COLUMN;
        this.innerPanel.add(name, gbc);
        gbc.gridx = Constants.SECOND_COLUMN;
        this.innerPanel.add(points, gbc);
        gbc.gridx = Constants.THIRD_COLUMN;
        this.innerPanel.add(bet, gbc);
        gbc.gridx = Constants.FOURTH_COLUMN;
        this.innerPanel.add(betType, gbc);
    }

    private void setResultsHeaderLabels(GridBagConstraints gbc) {
        JLabel name = new JLabel("Name");
        JLabel result = new JLabel("Result");
        JLabel winLoss = new JLabel("Win/Loss");
        gbc.gridx = Constants.FIRST_COLUMN;
        this.bottomPanel.add(name, gbc);
        gbc.gridx = Constants.SECOND_COLUMN;
        this.bottomPanel.add(result, gbc);
        gbc.gridx = Constants.THIRD_COLUMN;
        this.bottomPanel.add(winLoss, gbc);
    }

    /**
     * Need to store the points each player has before the wheel is spun in order to calculate the points won/lost
     * and display it in the summary panel
     */
    public void storeCurrentPlayerPoints() {
        for (Player player : gameEngine.getAllPlayers()) {
            this.previousResults.put(player, player.getPoints());
        }
    }
}
