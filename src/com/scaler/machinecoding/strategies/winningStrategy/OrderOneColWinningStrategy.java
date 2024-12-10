package com.scaler.machinecoding.strategies.winningStrategy;

import com.scaler.machinecoding.models.Board;
import com.scaler.machinecoding.models.Move;

public class OrderOneColWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWinner(Board board, Move move) {
        return false;
    }
}