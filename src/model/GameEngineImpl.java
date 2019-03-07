package model;

import model.enumeration.BetType;
import model.enumeration.Color;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.GameEngineCallbackImpl;
import view.interfaces.GameEngineCallback;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameEngineImpl implements GameEngine {

    private List<Player> players = new ArrayList<>();

    private List<GameEngineCallback> gameEngineCallbacks = new ArrayList<>();

    private static final List<Slot> wheelSlots = new ArrayList<>();

    static {
        wheelSlots.add(new SlotImpl(0, Color.GREEN00, 0));
        wheelSlots.add(new SlotImpl(1, Color.RED, 27));
        wheelSlots.add(new SlotImpl(2, Color.BLACK, 10));
        wheelSlots.add(new SlotImpl(3, Color.RED, 25));
        wheelSlots.add(new SlotImpl(4, Color.BLACK, 29));
        wheelSlots.add(new SlotImpl(5, Color.RED, 12));
        wheelSlots.add(new SlotImpl(6, Color.BLACK, 8));
        wheelSlots.add(new SlotImpl(7, Color.RED, 19));
        wheelSlots.add(new SlotImpl(8, Color.BLACK, 31));
        wheelSlots.add(new SlotImpl(9, Color.RED, 18));
        wheelSlots.add(new SlotImpl(10, Color.BLACK, 6));
        wheelSlots.add(new SlotImpl(11, Color.RED, 21));
        wheelSlots.add(new SlotImpl(12, Color.BLACK, 33));
        wheelSlots.add(new SlotImpl(13, Color.RED, 16));
        wheelSlots.add(new SlotImpl(14, Color.BLACK, 4));
        wheelSlots.add(new SlotImpl(15, Color.RED, 23));
        wheelSlots.add(new SlotImpl(16, Color.BLACK, 35));
        wheelSlots.add(new SlotImpl(17, Color.RED, 14));
        wheelSlots.add(new SlotImpl(18, Color.BLACK, 2));
        wheelSlots.add(new SlotImpl(19, Color.GREEN0, 0));
        wheelSlots.add(new SlotImpl(20, Color.BLACK, 28));
        wheelSlots.add(new SlotImpl(21, Color.RED, 9));
        wheelSlots.add(new SlotImpl(22, Color.BLACK, 26));
        wheelSlots.add(new SlotImpl(23, Color.RED, 30));
        wheelSlots.add(new SlotImpl(24, Color.BLACK, 11));
        wheelSlots.add(new SlotImpl(25, Color.RED, 7));
        wheelSlots.add(new SlotImpl(26, Color.BLACK, 20));
        wheelSlots.add(new SlotImpl(27, Color.RED, 32));
        wheelSlots.add(new SlotImpl(28, Color.BLACK, 17));
        wheelSlots.add(new SlotImpl(29, Color.RED, 5));
        wheelSlots.add(new SlotImpl(30, Color.BLACK, 22));
        wheelSlots.add(new SlotImpl(31, Color.RED, 34));
        wheelSlots.add(new SlotImpl(32, Color.BLACK, 15));
        wheelSlots.add(new SlotImpl(33, Color.RED, 3));
        wheelSlots.add(new SlotImpl(34, Color.BLACK, 24));
        wheelSlots.add(new SlotImpl(35, Color.RED, 36));
        wheelSlots.add(new SlotImpl(36, Color.BLACK, 13));
        wheelSlots.add(new SlotImpl(37, Color.RED, 1));
    }

    public GameEngineImpl() {
        //ta
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
//        int currentPosition = ThreadLocalRandom.current().nextInt(0, Slot.WHEEL_SIZE);
        int currentPosition = 0;
        int delay = initialDelay;
        GameEngineCallback gameEngineCallback = new GameEngineCallbackImpl();

        while (delay < finalDelay) {
            gameEngineCallback.nextSlot(wheelSlots.get(currentPosition), this);

            delay += delayIncrement;
            if (currentPosition == Slot.WHEEL_SIZE - 1) {
                currentPosition = 0;
            } else {
                currentPosition++;
            }
        }

        gameEngineCallback.result(wheelSlots.get(currentPosition), this);

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
     * <b>NOTE:</b> playerID is unique and if another player with same id is added
     * it replaces the previous player
     *
     * @param player - to add to game
     */
    @Override
    public void addPlayer(Player player) {
        Player existingPlayer = this.getPlayer(player.getPlayerId());
        if (existingPlayer != null) {
            players.set(players.indexOf(existingPlayer), player);
        } else {
            players.add(player);
        }
    }

    /**
     * @param id - id of player to retrieve (null if not found)
     * @return - the Player or null if Player does not exist
     */
    @Override
    public Player getPlayer(String id) {
        for (Player player : players) {
            if (player.getPlayerId().equals(id)) {
                return player;
            }
        }
        return null;
    }

    /**
     * @param player - to remove from game
     * @return - true if the player existed and was removed
     */
    @Override
    public boolean removePlayer(Player player) {
        Player existingPlayer = this.getPlayer(player.getPlayerId());
        if (existingPlayer != null) {
            players.remove(player);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param gameEngineCallback <pre> a client specific implementation of GameEngineCallback used to perform display updates etc.
     * <b>NOTE:</b> you will use a different implementation of the GameEngineCallback
     * for the console (assignment 1) and GUI (assignment 2) versions</pre>
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
        //check this
        return this.players;
    }

    /**
     * the implementation should make appropriate calls on the Player to place the bet and set bet type
     *
     * @param player  - the Player who is placing the bet
     * @param bet     - the bet in points
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
        return wheelSlots;
    }
}
