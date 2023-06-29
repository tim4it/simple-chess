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
public class Pawn implements Board {

    @NonNull
    Color color;

    @Override
    public boolean isValidMove(Board[][] chessboard, int[] moves) {
        int sourceRow = moves[0], sourceColumn = moves[1];
        int destinationRow = moves[2], destinationColumn = moves[3];
        var emptyCellString = EmptyCell.builder().build().toString();
        // Checking if the source and destination coordinates are within the board boundaries
        if (Helper.isValidBoardCoordinate(sourceRow, sourceColumn) ||
                Helper.isValidBoardCoordinate(destinationRow, destinationColumn)) {
            return false;
        }
        // Checking if the piece at the source coordinate is a queen
        if (!chessboard[sourceRow][sourceColumn].toString().equals(this.toString())) {
            return false;
        }
        // Pawn moves forward by one row and stays in the same column (normal pawn move).
        if (sourceRow == destinationRow - 1 &&
                sourceColumn == destinationColumn &&
                chessboard[destinationRow][destinationColumn].toString().equals(emptyCellString)) {
            return true;
        }
        // Pawn is in its starting position (row 1) and moves forward by two rows and stays in the same column,
        // with both intermediate and destination cells being empty.
        if (sourceRow == 1 &&
                sourceRow == destinationRow - 2 &&
                sourceColumn == destinationColumn &&
                chessboard[destinationRow][destinationColumn].toString().equals(emptyCellString) &&
                chessboard[sourceRow + 1][sourceColumn].toString().equals(emptyCellString)) {
            return true;
        }
        //Pawn moves forward by one row and either increases or decreases its column by one (diagonal capture move).
        var destinationCell = chessboard[destinationRow][destinationColumn];
        if (sourceRow == destinationRow - 1 &&
                Math.abs(sourceColumn - destinationColumn) == 1 &&
                !destinationCell.getColor().equals(this.getColor())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        switch (getColor()) {
            case WHITE:
                return "♙";
            case BLACK:
                return "♟";
            default:
                throw new IllegalStateException("Only two colors available for pawn! " + getColor());
        }
    }
}
