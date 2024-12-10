package com.scaler.machinecoding.strategies.winningStrategy;

import com.scaler.machinecoding.models.Board;
import com.scaler.machinecoding.models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
}
