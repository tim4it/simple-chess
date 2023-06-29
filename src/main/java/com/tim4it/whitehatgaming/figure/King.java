package com.tim4it.whitehatgaming.figure;

import com.tim4it.whitehatgaming.Board;
import com.tim4it.whitehatgaming.Color;
import com.tim4it.whitehatgaming.util.Pair;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

import java.util.Arrays;

@Value
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class King extends AbstractFigure {

    @NonNull
    Color color;

    @Override
    public Pair<Boolean, String> isValidMove(Board[][] chessboard, int[] moves) {
        int sourceRow = moves[0], sourceColumn = moves[1];
        int destinationRow = moves[2], destinationColumn = moves[3];
        if (isOutOfBoundaries(sourceRow, sourceColumn, destinationRow, destinationColumn)) {
            return new Pair<>(false, "Wrong source and destination king move: " + Arrays.toString(moves));
        }
        // Checking if the piece at the source coordinate is a king
        if (!chessboard[sourceRow][sourceColumn].toString().equals(this.toString())) {
            return new Pair<>(false, "Expected king, got " + chessboard[sourceRow][sourceColumn].toString());
        }
        // Calculates the absolute differences in the row and column positions between the source and destination coordinates.
        // If both differences are less than or equal to 1, it means the king's move is valid -
        // since the king can move at most one square in any direction: horizontally, vertically, or diagonally.
        var rowDiff = Math.abs(destinationRow - sourceRow);
        var colDiff = Math.abs(destinationColumn - sourceColumn);
        if (rowDiff <= 1 && colDiff <= 1) {
            // ensure that the destination cell is either empty or contains a piece of the opposing color
            var destinationCell = chessboard[destinationRow][destinationColumn];
            var destinationMoveCheck = destinationCell.toString().equals(EMPTY_CELL_STRING) ||
                    !destinationCell.getColor().equals(this.getColor());
            if (!destinationMoveCheck) {
                return new Pair<>(false, "Invalid king destination move " + Arrays.toString(moves));
            }
            return new Pair<>(true, null);
        }
        return new Pair<>(false, "Invalid king move " + Arrays.toString(moves));
    }

    @Override
    public String toString() {
        switch (getColor()) {
            case WHITE:
                return "♔";
            case BLACK:
                return "♚";
            default:
                throw new IllegalStateException("Only two colors available for king! " + getColor());
        }
    }
}
