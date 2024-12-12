package com.scaler.machinecoding.models;

import com.scaler.machinecoding.strategies.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private Board board;
    private List<Player> players;
    private int currentMovePlayerIndex;
    private List<Move> moves;
    private GameStatus gameStatus;
    private List<WinningStrategy> winningStrategies;
    private Player winner;
    private static Builder builder;

    public static Builder getBuilder() {
        return new Builder();
    }

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
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

    public void printBoard() {
        this.board.print();
    }

    private boolean validateMaove(Cell cell) {
        int row = cell.getRow();
        int col = cell.getCol();

        if (row < 0 || col < 0 || row >= board.getSize() || col >= board.getSize()) {
            return false;
        }

        return board.getBoard().get(row).get(col).getCellStatus().equals(CellStatus.EMPTY);
    }
    public void makeMove(){
        Player currentPlayer = players.get(currentMovePlayerIndex);
        System.out.println("It is " + currentPlayer.getName() + "'s turn.");

        Cell proposedCell = currentPlayer.makeMove(board);

        System.out.println("Move made at row " + proposedCell.getRow() + " and column " + proposedCell.getCol() + ".");

        if(!validateMaove(proposedCell)){
            System.out.println("Invalid move. Please Try again");
            return;
        }

        Cell cellInBoard = board.getBoard().get(proposedCell.getRow()).get(proposedCell.getCol());
        cellInBoard.setCellStatus(CellStatus.FILLED);
        cellInBoard.setPlayer(currentPlayer);

        Move move = new Move(cellInBoard, currentPlayer);
        moves.add(move);

        if (checkGameHasWon(move, currentPlayer)) return;

        if (CheckDraw()) return;

        currentMovePlayerIndex = (currentMovePlayerIndex + 1) % players.size();
    }

    public void undo(){
        if(moves.size() == 0){
            System.out.println("No moves to undo");
            return;
        }
        Move lastMove = moves.get(moves.size() - 1);

        Cell cellInBoard = lastMove.getCell();
        cellInBoard.setCellStatus(CellStatus.EMPTY);
        cellInBoard.setPlayer(null);

        moves.remove(lastMove);

        currentMovePlayerIndex = (currentMovePlayerIndex - 1 + players.size()) % players.size();
    }

    private boolean CheckDraw() {
        if(moves.size() == board.getSize() * board.getSize()){
            gameStatus = GameStatus.DRAW;
            return true;
        }
        return false;
    }

    private boolean checkGameHasWon(Move move, Player currentPlayer) {
        for(WinningStrategy winningStrategy : winningStrategies){
            if(winningStrategy.checkWinner(board, move)){
                gameStatus = GameStatus.FINISHED;
                winner = currentPlayer;
                return true;
            }
        }
        return false;
    }


    public void printResult() {
        if (getGameStatus().equals(GameStatus.FINISHED)) {
            System.out.println("Game has ended");
            System.out.println("Winner is : " + winner.getName());
        } else {
            // else -> print draw
            System.out.println("Game is Drawn");
        }
    }

    public static class Builder {
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        private Builder() {
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        private boolean isValid() {
            if (this.players.size() < 2) {
                return false;
            }
            if (this.players.size() != this.dimension - 1) {
                return false;
            }
            int botCount = 0;
            for (Player player : players) {
                if (player.getPlayerType().equals(PlayerType.BOT)) {
                    botCount++;
                }
            }
            if (botCount > 1) {
                return false;
            }
            Set<Character> existingSymbols = new HashSet<>();

            for (Player player : players) {
                existingSymbols.add(player.getSymbol().getaChar());
            }
            if (existingSymbols.size() != players.size()) {
                return false;
            }
            return true;
        }

        public Game build() {
            if (!isValid()) {
                throw new RuntimeException("Invalid params for game");
            }
            return new Game(dimension, players, winningStrategies);
        }

    }
}
// 1. Without exception, all attributes must be private
// 2. have getters and setters for each
// 3. Ensure all the attributes are initialized in constructor.