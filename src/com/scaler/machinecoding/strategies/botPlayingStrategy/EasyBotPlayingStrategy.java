package com.scaler.machinecoding.strategies.botPlayingStrategy;

import com.scaler.machinecoding.models.Board;
import com.scaler.machinecoding.models.Cell;
import com.scaler.machinecoding.models.CellStatus;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {

    @Override
    public Cell makeMove(Board board) {
        for (List<Cell> row : board.getBoard()) {
            for (Cell cell : row) {
                if (cell.getCellStatus().equals(CellStatus.EMPTY))
                    return cell;
            }
        }
        return null;  // You should never come here.
    }
}
