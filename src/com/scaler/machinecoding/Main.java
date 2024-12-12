package com.scaler.machinecoding;


import com.scaler.machinecoding.controller.GameController;
import com.scaler.machinecoding.models.*;
import com.scaler.machinecoding.strategies.winningStrategy.OrderOneColWinningStrategy;
import com.scaler.machinecoding.strategies.winningStrategy.OrderOneDiagonalWinningStrategy;
import com.scaler.machinecoding.strategies.winningStrategy.OrderOneRowWinningStrategy;
import com.scaler.machinecoding.strategies.winningStrategy.WinningStrategy;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a game
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);
        int dimension = 3;
        Game game = null;
        List<Player> players = List.of(
                new Player(new Symbol('X'), "Harish", PlayerType.HUMAN),
                new Bot(new Symbol('O'), "Bot", BotDifficultyLevel.EASY));
        List<WinningStrategy> winningStrategies = List.of(
                new OrderOneRowWinningStrategy(dimension, players),
                new OrderOneColWinningStrategy(dimension, players),
                new OrderOneDiagonalWinningStrategy(players)
        );

        try {
            game = gameController.createGame(dimension,
                    players,
                    winningStrategies
            );
        } catch (Exception e) {
            System.out.println("Something went wrong");
            return;
        }

        System.out.println("--------------------Game is Starting--------------------");
        //While game status in inprogress
        while (gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {
            System.out.println("This is hoe board is looks like:");
            gameController.displayBoard(game); // Show board
            System.out.print("Does anyone want to undo? (y/n): ");
            String input = scanner.next(); // Get user decision
            if (input.equals("y")) {
                gameController.undo(game); // Undo step
            } else {
                gameController.makeMove(game); // Proceed to next move
            }
        }
        gameController.printResult(game); // Print final result (winner/draw)
    }
}