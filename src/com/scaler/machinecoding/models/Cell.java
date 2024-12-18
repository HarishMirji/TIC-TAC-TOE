package com.scaler.machinecoding.models;

public class Cell {
    private int row;
    private int col;
    private CellStatus cellStatus;
    private Player player;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellStatus = CellStatus.EMPTY;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void displayCell() {
        if(getCellStatus().equals(CellStatus.EMPTY)) {
            System.out.print(" - |");
        }else {
            System.out.print(" " + getPlayer().getSymbol().getaChar() + " |");
        }
    }
}
