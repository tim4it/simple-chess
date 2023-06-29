package com.tim4it.whitehatgaming.figure;

import com.tim4it.whitehatgaming.Board;
import com.tim4it.whitehatgaming.Color;
import com.tim4it.whitehatgaming.empty.EmptyCell;
import com.tim4it.whitehatgaming.util.Helper;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Bishop implements Board {

    @NonNull
    Color color;

    @Override
    public boolean isValidMove(Board[][] chessboard, int[] moves) {
        // Extracting the source and destination coordinates from the move
        int sourceRow = moves[0], sourceColumn = moves[1];
        int destinationRow = moves[2], destinationColumn = moves[3];
        // Checking if the source and destination coordinates are within the board boundaries
        if (Helper.isValidBoardCoordinate(sourceRow, sourceColumn) ||
                Helper.isValidBoardCoordinate(destinationRow, destinationColumn)) {
            return false;
        }
        // Checking if the piece at the source coordinate is a queen
        if (!chessboard[sourceRow][sourceColumn].toString().equals(this.toString())) {
            return false;
        }
        var rowDiff = Math.abs(destinationRow - sourceRow);
        var columnDiff = Math.abs(destinationColumn - sourceColumn);
        // Checking if the move is valid horizontally, vertically, or diagonally
        if (rowDiff == columnDiff) {
            return isClearPath(chessboard, moves);
        }
        return false;
    }

    /**
     * Checking if the path is clear between the source and destination coordinates
     *
     * @param chessboard chess board with current figures
     * @param moves      moves - source to destination
     * @return true if move is validated
     */
    private boolean isClearPath(Board[][] chessboard, int[] moves) {
        int sourceRow = moves[0], sourceColumn = moves[1];
        int destinationRow = moves[2], destinationColumn = moves[3];
        var emptyCellString = EmptyCell.builder().build().toString();

        int deltaRow = destinationRow - sourceRow;
        int deltaColumn = destinationColumn - sourceColumn;
        int stepRow = deltaRow > 0 ? 1 : -1;
        int stepColumn = deltaColumn > 0 ? 1 : -1;

        int row = sourceRow + stepRow;
        int column = sourceColumn + stepColumn;
        while (row != destinationRow && column != destinationColumn) {
            if (!chessboard[row][column].toString().equals(emptyCellString)) {
                return false;
            }
            row += stepRow;
            column += stepColumn;
        }
        // ensure that the destination cell is either empty or contains a piece of the opposing color
        var destinationCell = chessboard[destinationRow][destinationColumn];
        return destinationCell.toString().equals(emptyCellString) ||
                !destinationCell.getColor().equals(this.getColor());
    }

    @Override
    public String toString() {
        switch (getColor()) {
            case WHITE:
                return "♗";
            case BLACK:
                return "♝";
            default:
                throw new IllegalStateException("Only two colors available for bishop! " + getColor());
        }
    }
}
