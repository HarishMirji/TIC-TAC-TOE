package com.scaler.machinecoding.strategies.winningStrategy;

import com.scaler.machinecoding.models.Board;
import com.scaler.machinecoding.models.Move;
import com.scaler.machinecoding.models.Player;
import com.scaler.machinecoding.models.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderOneColWinningStrategy implements WinningStrategy {
    private List<Map<Symbol, Integer>> colMaps;

    public OrderOneColWinningStrategy(int size, List<Player> players) {
        colMaps = new ArrayList<>();
        for(int i = 0; i < size; ++i){
            colMaps.add(new HashMap<>());
            for(Player player : players){
                colMaps.get(i).put(player.getSymbol(), 0);
            }
        }
    }
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        colMaps.get(col).put(symbol, colMaps.get(col).getOrDefault(symbol, 0) + 1);

        if(colMaps.get(col).get(symbol) == board.getSize()){
            return true;
        }
        return false;
    }
}
