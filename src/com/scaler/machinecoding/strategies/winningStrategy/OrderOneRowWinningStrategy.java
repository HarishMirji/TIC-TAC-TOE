package com.scaler.machinecoding.strategies.winningStrategy;

import com.scaler.machinecoding.models.Board;
import com.scaler.machinecoding.models.Move;
import com.scaler.machinecoding.models.Player;
import com.scaler.machinecoding.models.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderOneRowWinningStrategy implements WinningStrategy {

    private List<Map<Symbol, Integer>> rowMaps;

    public OrderOneRowWinningStrategy(int size, List<Player> players) {
        rowMaps = new ArrayList<>();
        for(int i = 0; i < size; ++i){
            rowMaps.add(new HashMap<>());
            for(Player player : players){
                rowMaps.get(i).put(player.getSymbol(), 0);
            }
        }
    }
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        rowMaps.get(row).put(symbol, rowMaps.get(row).getOrDefault(symbol, 0) + 1);

        if(rowMaps.get(row).get(symbol) == board.getSize()){
            return true;
        }
        return false;
    }
}
