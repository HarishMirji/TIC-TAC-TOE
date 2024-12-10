package com.scaler.machinecoding.strategies.botPlayingStrategy;

import com.scaler.machinecoding.models.Board;
import com.scaler.machinecoding.models.Cell;

public interface BotPlayingStrategy {
    Cell makeMove(Board board);
}
