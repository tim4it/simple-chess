package com.tim4it.whitehatgaming.figure;

import com.tim4it.whitehatgaming.Board;
import com.tim4it.whitehatgaming.Color;
import com.tim4it.whitehatgaming.util.Moves;
import com.tim4it.whitehatgaming.util.Pair;
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
    public Pair<Boolean, String> isValidMove(@NonNull Board[][] chessboard, @NonNull Moves moves) {
        // Extracting the source and destination coordinates from the move
        var sourceRow = moves.getSourceRow();
        var sourceColumn = moves.getSourceColumn();
        var destinationRow = moves.getDestinationRow();
        var destinationColumn = moves.getDestinationColumn();
        if (isOutOfBoundaries(sourceRow, sourceColumn, destinationRow, destinationColumn)) {
            return new Pair<>(false, "Wrong source and destination knight move: " + moves);
        }
        // Checking if the piece at the source coordinate is a knight
        if (!chessboard[sourceRow][sourceColumn].toString().equals(this.toString())) {
            return new Pair<>(false, "Expected knight, got " + chessboard[sourceRow][sourceColumn].toString());
        }
        var rowDiff = Math.abs(destinationRow - sourceRow);
        var columnDiff = Math.abs(destinationColumn - sourceColumn);

        if ((rowDiff == 2 && columnDiff == 1) || (rowDiff == 1 && columnDiff == 2)) {
            // ensure that the destination cell is either empty or contains a piece of the opposing color
            var destinationCell = chessboard[destinationRow][destinationColumn];
            var destinationMoveCheck = destinationCell.toString().equals(EMPTY_CELL_STRING) ||
                    !destinationCell.getColor().equals(this.getColor());
            if (!destinationMoveCheck) {
                return new Pair<>(false, "Invalid knight destination move " + moves);
            }
            return new Pair<>(true, null);
        }
        return new Pair<>(false, "Invalid knight move " + moves);
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
