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
public class Knight extends AbstractFigure {

    @NonNull
    Color color;

    @Override
    public Pair<Boolean, String> isValidMove(Board[][] chessboard, int[] moves) {
        // Extracting the source and destination coordinates from the move
        int sourceRow = moves[0], sourceColumn = moves[1];
        int destinationRow = moves[2], destinationColumn = moves[3];
        if (isOutOfBoundaries(sourceRow, sourceColumn, destinationRow, destinationColumn)) {
            return new Pair<>(false, "Wrong source and destination knight move: " + Arrays.toString(moves));
        }
        // Checking if the piece at the source coordinate is a knight
        if (!chessboard[sourceRow][sourceColumn].toString().equals(this.toString())) {
            return new Pair<>(false, "Expected knight, got " + chessboard[sourceRow][sourceColumn].toString());
        }
        int rowDiff = Math.abs(destinationRow - sourceRow);
        int columnDiff = Math.abs(destinationColumn - sourceColumn);

        if ((rowDiff == 2 && columnDiff == 1) || (rowDiff == 1 && columnDiff == 2)) {
            // ensure that the destination cell is either empty or contains a piece of the opposing color
            var destinationCell = chessboard[destinationRow][destinationColumn];
            var destinationMoveCheck = destinationCell.toString().equals(EMPTY_CELL_STRING) ||
                    !destinationCell.getColor().equals(this.getColor());
            if (!destinationMoveCheck) {
                return new Pair<>(false, "Invalid knight destination move " + Arrays.toString(moves));
            }
            return new Pair<>(true, null);
        }
        return new Pair<>(false, "Invalid knight move " + Arrays.toString(moves));
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
