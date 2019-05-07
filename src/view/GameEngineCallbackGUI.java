package view;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

import javax.swing.*;

public class GameEngineCallbackGUI implements GameEngineCallback {

    private GameFrame gameFrame;

    public GameEngineCallbackGUI(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    /**
     * called as the wheel spins<br>
     * use this to update your GUI or log to console
     *
     * @param slot   - the next slot that the rolling ball entered
     * @param engine - a convenience reference to the engine so the receiver can call methods if necessary
     * @see GameEngine
     */
    @Override
    public void nextSlot(Slot slot, GameEngine engine) {
        this.gameFrame.getWheelPanel().setCurrentSlot(slot);
        this.gameFrame.getWheelPanel().revalidate();
        this.gameFrame.getWheelPanel().repaint();
    }

    /**
     * called when the wheel has stopped spinning<br>
     * this is a convenient place to call {@link GameEngine#calculateResult(Slot winningSlot)}<br>
     * and {@link Player#resetBet()}
     *
     * @param winningSlot - the slot that the ball landed in
     * @param engine      - a convenience reference to the engine so the receiver can call methods if necessary
     * @see GameEngine
     */
    @Override
    public void result(Slot winningSlot, GameEngine engine) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gameFrame.getStatusBar().update(winningSlot);
                gameFrame.getSummaryPanel().showResults();
                gameFrame.getWheelPanel().setCurrentSlot(winningSlot);
                gameFrame.getWheelPanel().revalidate();
                gameFrame.getWheelPanel().repaint();
            }
        });
        this.gameFrame.updatePlayers();
    }
}
