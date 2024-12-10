package com.scaler.machinecoding.models;

import com.scaler.machinecoding.strategies.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private int currentMovePlayerIndex;
    private List<Move> moves;
    private GameStatus gameStatus;
    private List<WinningStrategy> winningStrategies;
    private Player winner;

    public Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.moves = new ArrayList<>();
        this.players = players;
        this.board = new Board(dimension);
        this.currentMovePlayerIndex = 0;
        this.winningStrategies = winningStrategies;
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getCurrentMovePlayerIndex() {
        return currentMovePlayerIndex;
    }

    public void setCurrentMovePlayerIndex(int currentMovePlayerIndex) {
        this.currentMovePlayerIndex = currentMovePlayerIndex;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
// 1. Without exception, all attributes must be private
// 2. have getters and setters for each
// 3. Ensure all the attributes are initialized in constructor.