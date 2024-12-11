package com.scaler.machinecoding.controller;

import com.scaler.machinecoding.models.Game;
import com.scaler.machinecoding.models.GameStatus;
import com.scaler.machinecoding.models.Player;
import com.scaler.machinecoding.strategies.winningStrategy.WinningStrategy;

import java.util.List;

public class GameController {

    public Game createGame(int dimension,
                           List<Player> players,
                           List<WinningStrategy> winningStrategies) {
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
    }

    public void displayBoard(Game game) {
        game.printBoard();
    }

    public void undo(Game game) {

    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public GameStatus getGameStatus(Game game) {
        return game.getGameStatus();
    }

    public void printResult(Game game) {
        game.printResult();
    }
}
