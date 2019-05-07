package view;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

/**
 * Skeleton/Partial example implementation of GameEngineCallback showing Java logging behaviour
 *
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 */
public class GameEngineCallbackImpl implements GameEngineCallback {
    private static final Logger logger = Logger.getLogger(GameEngineCallback.class.getName());

    public GameEngineCallbackImpl() {
        // FINE shows wheel spinning output, INFO only shows result
        logger.setLevel(Level.FINE);
    }

    @Override
    public void nextSlot(Slot slot, GameEngine engine) {
        // intermediate results logged at Level.FINE
        logger.log(Level.FINE, String.format("Next slot: %s", slot.toString()));
    }

    @Override
    public void result(Slot result, GameEngine engine) {
        // final results logged at Level.INFO
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : engine.getAllPlayers()) {
            stringBuilder.append("\n");
            stringBuilder.append(player.toString());
        }
        logger.log(Level.INFO, String.format("RESULT=%s", result.toString()));
        logger.log(Level.INFO, "FINAL PLAYER POINT BALANCES");
        logger.log(Level.INFO, stringBuilder.toString());
    }
}
