package com.scaler.machinecoding;


import com.scaler.machinecoding.controller.GameController;
import com.scaler.machinecoding.models.*;
import com.scaler.machinecoding.strategies.winningStrategy.OrderOneColWinningStrategy;
import com.scaler.machinecoding.strategies.winningStrategy.OrderOneDiagonalWinningStrategy;
import com.scaler.machinecoding.strategies.winningStrategy.OrderOneRowWinningStrategy;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a game
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);
        Game game = null;

        try {
            game = gameController.createGame(3,
                    List.of(new Player(new Symbol('X'), "Harish", PlayerType.HUMAN),
                            new Bot(new Symbol('O'), "Bot", BotDifficultyLevel.EASY)),
                    List.of(
                            new OrderOneRowWinningStrategy(),
                            new OrderOneColWinningStrategy(),
                            new OrderOneDiagonalWinningStrategy()
                    )
            );
        } catch (Exception e) {
            System.out.println("Something went wrong");
            return;
        }

        //While game status in inprogress
        while (gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {
            gameController.displayBoard(game); // Show board
            System.out.print("Do you want to undo? (y/n): ");
            String input = scanner.next(); // Get user decision
            if (input.equals("y")) {
                gameController.undo(game); // Undo step
            } else {
                gameController.makeMove(game); // Proceed to next move
            }
        }
        gameController.printResult(game); // Print final result (winner/draw)
        // Check status of game
        GameStatus gameStatus = gameController.getGameStatus(game);
        // if winner -> print winner  else -> print draw
        gameController.printResult(game);

    }
}