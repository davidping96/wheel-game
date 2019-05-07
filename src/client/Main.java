package client;

import view.GameFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // GUI code should run on the AWT Event dispatch/UI Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameFrame().setVisible(true);
            }
        });
    }
}
