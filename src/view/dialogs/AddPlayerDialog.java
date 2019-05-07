package view.dialogs;

import controller.AddPlayerActionListener;
import model.interfaces.GameEngine;
import view.GameFrame;
import view.util.Constants;

import javax.swing.*;
import java.awt.*;

public class AddPlayerDialog extends JDialog {

    private JPanel innerPanel;
    private JPanel buttonPanel;

    private JLabel nameLabel;
    private JLabel pointsLabel;

    private JTextField nameField;
    private JTextField pointsField;

    public AddPlayerDialog(GameEngine gameEngine, GameFrame gameFrame) {
        super(gameFrame, "Add player", true);

        setLayout();
        setComponents();
        layoutComponents();

        JButton addButton = new JButton(Constants.ADD);
        JButton cancelButton = new JButton(Constants.CANCEL);
        this.buttonPanel.add(addButton);
        this.buttonPanel.add(cancelButton);

        // Add listeners
        addButton.addMouseListener(new AddPlayerActionListener(gameEngine, gameFrame, this));
        cancelButton.addActionListener(e -> setVisible(false));

        // Finish setup
        pack();
        setMinimumSize(getSize());

        // Centered on screen
        setLocationRelativeTo(null);
    }

    private void setLayout() {
        JPanel parentPanel = new JPanel();
        this.innerPanel = new JPanel();
        this.buttonPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());
        this.buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.innerPanel.setLayout(new GridBagLayout());
        // Add padding around the dialog
        parentPanel.setBorder(BorderFactory.createEmptyBorder(Constants.LG_PADDING, Constants.LG_PADDING, Constants.LG_PADDING, Constants.LG_PADDING));
        parentPanel.add(innerPanel, BorderLayout.CENTER);
        parentPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(parentPanel);
    }

    private void setComponents() {
        this.nameLabel = new JLabel("Name: ");
        this.pointsLabel = new JLabel(("Points: "));
        this.nameField = new JTextField(Constants.LG_FIELD_SIZE);
        this.pointsField = new JTextField(Constants.LG_FIELD_SIZE);
    }

    private void layoutComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, Constants.SM_PADDING, Constants.MD_PADDING, Constants.SM_PADDING);
        gbc.gridy = 0;

        gbc.gridx = Constants.FIRST_COLUMN;
        this.innerPanel.add(nameLabel, gbc);
        gbc.gridx = Constants.SECOND_COLUMN;
        this.innerPanel.add(nameField, gbc);

        gbc.gridy += 1;

        gbc.gridx = Constants.FIRST_COLUMN;
        this.innerPanel.add(pointsLabel, gbc);
        gbc.gridx = Constants.SECOND_COLUMN;
        this.innerPanel.add(pointsField, gbc);
    }

    public JTextField getNameField() {
        return this.nameField;
    }

    public JTextField getPointsField() {
        return this.pointsField;
    }
}
