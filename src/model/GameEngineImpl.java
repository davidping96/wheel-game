package model;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

import java.util.Collection;

public class GameEngineImpl implements GameEngine {

    public GameEngineImpl() {
        //Empty for now
    }

    @Override
    public void spin(int initialDelay, int finalDelay, int delayIncrement) {

    }

    @Override
    public void calculateResult(Slot winningSlot) {

    }

    @Override
    public void addPlayer(Player player) {

    }

    @Override
    public Player getPlayer(String id) {
        return null;
    }

    @Override
    public boolean removePlayer(Player player) {
        return false;
    }

    @Override
    public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {

    }

    @Override
    public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
        return false;
    }

    @Override
    public Collection<Player> getAllPlayers() {
        return null;
    }

    @Override
    public boolean placeBet(Player player, int bet, BetType betType) {
        return false;
    }

    @Override
    public Collection<Slot> getWheelSlots() {
        return null;
    }
}
