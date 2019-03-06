package model;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameEngineImpl implements GameEngine {

    private List<Player> players = new ArrayList<>();

    private List<GameEngineCallback> gameEngineCallbacks = new ArrayList<>();

    public GameEngineImpl() {
        //Empty for now
    }

    /**
     * <pre>
     * spin the gaming wheel progressing from the initialDelay to the finalDelay
     * in increments of delayIncrement
     *
     * <b>NOTE:</b> All delays are in milliseconds (ms)
     *
     * 1. begin by selecting a random starting slot on the wheel
     * 2. start at initialDelay then increment the delay each time a new slot is passed on the wheel
     * 3. call GameEngineCallback.nextSlot(...) each time a slot is passed
     * 4. continue until delay {@literal >=} finalDelay
     * 5. call GameEngineCallback.result(...) to finish and apply betting results
     *
     * <b>See Also:</b>
     *  {@link GameEngineCallback#nextSlot(Slot slot, GameEngine engine)}
     *  {@link GameEngineCallback#result(Slot result, GameEngine engine)}
     *
     * @param initialDelay
     *            the starting delay in ms between updates
     *            (based on how fast the ball is rolling in the slots)
     * @param finalDelay
     *            the final delay in ms between updates when the ball stops rolling
     * @param delayIncrement
     *            how much the ball slows down (i.e. delay gets longer) after each slot
     * </pre>
     */
    @Override
    public void spin(int initialDelay, int finalDelay, int delayIncrement) {

    }

    /**
     * Iterate through players and apply win/loss point balances (via {@link BetType#applyWinLoss(Player player, Slot winSlot)})
     *
     * @param winningSlot - the winning slot as passed to GameEngineCallback.result(...)
     */
    @Override
    public void calculateResult(Slot winningSlot) {

    }

    /**
     *  <b>NOTE:</b> playerID is unique and if another player with same id is added
     *  it replaces the previous player
     *
     * @param player - to add to game
     */
    @Override
    public void addPlayer(Player player) {
        Player existingPlayer = null;
        for(Player p : players) {
            if (p.getPlayerId().equals(player.getPlayerId())) {
                existingPlayer = player;
            }
        }
        players.remove(existingPlayer);
        this.players.add(player);
    }

    /**
     * @param id - id of player to retrieve (null if not found)
     * @return - the Player or null if Player does not exist
     */
    @Override
    public Player getPlayer(String id) {
        return null;
    }

    /**
     * @param player - to remove from game
     * @return - true if the player existed and was removed
     */
    @Override
    public boolean removePlayer(Player player) {
        return false;
    }

    /**
     * @param gameEngineCallback
     * <pre> a client specific implementation of GameEngineCallback used to perform display updates etc.
     * <b>NOTE:</b> you will use a different implementation of the GameEngineCallback
     *       for the console (assignment 1) and GUI (assignment 2) versions</pre>
     * @see view.interfaces.GameEngineCallback
     */
    @Override
    public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {

    }

    /**
     * @param gameEngineCallback - instance to be removed if no longer needed
     * @return true - if the gameEngineCallback existed
     * @see view.interfaces.GameEngineCallback
     */
    @Override
    public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
        return false;
    }

    /**
     * @return - an unmodifiable collection (or a shallow copy) of all Players
     * @see model.interfaces.Player
     */
    @Override
    public Collection<Player> getAllPlayers() {
        return null;
    }

    /**
     * the implementation should make appropriate calls on the Player to place the bet and set bet type
     *
     * @param player - the Player who is placing the bet
     * @param bet - the bet in points
     * @param betType - the type of bet (red, black or either zero)
     * @return true - if bet is greater than 0 and player had sufficient points to place the bet
     */
    @Override
    public boolean placeBet(Player player, int bet, BetType betType) {
        return false;
    }

    /**
     * <pre> A debug method to return a FULL representation of the 38 slot gaming wheel
     * in the order provided by Basic_roulette_wheel.png (clockwise starting at Green '00')
     *
     * <b>NOTE:</b> this method should also be useful in your implementation
     * i.e. don't "reinvent the wheel" so to speak :)</pre>
     *
     * @return any java.util.Collection of Slot
     * @see model.interfaces.Slot
     */
    @Override
    public Collection<Slot> getWheelSlots() {
        return null;
    }
}
