package com.tim4it.whitehatgaming.figure;

import com.tim4it.whitehatgaming.Board;
import com.tim4it.whitehatgaming.Color;
import com.tim4it.whitehatgaming.empty.EmptyCell;
import com.tim4it.whitehatgaming.util.Helper;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class Knight extends AbstractFigure {

    @NonNull
    Color color;

    @Override
    public boolean isValidMove(Board[][] chessboard, int[] moves) {
        // Extracting the source and destination coordinates from the move
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
        int rowDiff = Math.abs(destinationRow - sourceRow);
        int columnDiff = Math.abs(destinationColumn - sourceColumn);

        if ((rowDiff == 2 && columnDiff == 1) || (rowDiff == 1 && columnDiff == 2)) {
            // ensure that the destination cell is either empty or contains a piece of the opposing color
            var destinationCell = chessboard[destinationRow][destinationColumn];
            return destinationCell.toString().equals(emptyCellString) ||
                    !destinationCell.getColor().equals(this.getColor());
        }
        return false;
    }

    @Override
    public String toString() {
        switch (getColor()) {
            case WHITE:
                return "♘";
            case BLACK:
                return "♞";
            default:
                throw new IllegalStateException("Only two colors available for knight! " + getColor());
        }
    }
}
