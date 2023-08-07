package com.tim4it.chess.figure;

import com.tim4it.chess.Board;
import com.tim4it.chess.Color;
import com.tim4it.chess.util.Moves;
import com.tim4it.chess.util.Pair;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class King extends AbstractFigure {

    @NonNull
    Color color;

    @Override
    public Pair<Boolean, String> isValidMove(@NonNull Board[][] chessboard, @NonNull Moves moves) {
        var sourceRow = moves.getSourceRow();
        var sourceColumn = moves.getSourceColumn();
        var destinationRow = moves.getDestinationRow();
        var destinationColumn = moves.getDestinationColumn();
        if (isOutOfBoundaries(sourceRow, sourceColumn, destinationRow, destinationColumn)) {
            return new Pair<>(false, "Wrong source and destination king move: " + moves);
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
                return new Pair<>(false, "Invalid king destination move " + moves);
            }
            return new Pair<>(true, null);
        }
        return new Pair<>(false, "Invalid king move " + moves);
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
