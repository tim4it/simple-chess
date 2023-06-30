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
public class Queen extends AbstractFigure {

    @NonNull
    Color color;

    @Override
    public Pair<Boolean, String> isValidMove(@NonNull Board[][] chessboard, @NonNull Moves moves) {
        var sourceRow = moves.getSourceRow();
        var sourceColumn = moves.getSourceColumn();
        var destinationRow = moves.getDestinationRow();
        var destinationColumn = moves.getDestinationColumn();
        if (isOutOfBoundaries(sourceRow, sourceColumn, destinationRow, destinationColumn)) {
            return new Pair<>(false, "Wrong source and destination queen move: " + moves);
        }
        // Checking if the piece at the source coordinate is a queen
        if (!chessboard[sourceRow][sourceColumn].toString().equals(this.toString())) {
            return new Pair<>(false, "Expected queen, got " + chessboard[sourceRow][sourceColumn].toString());
        }
        // Checking if the move is valid horizontally, vertically, or diagonally
        if (sourceRow == destinationRow || sourceColumn == destinationColumn ||
                Math.abs(sourceRow - destinationRow) == Math.abs(sourceColumn - destinationColumn)) {
            return isClearPath(chessboard, moves);
        }
        return new Pair<>(false, "Invalid queen move " + moves);
    }

    /**
     * Checking if the path is clear between the source and destination coordinates
     *
     * @param chessboard chess board with current figures
     * @param moves      moves - source to destination
     * @return pair data first - true if move is validated, otherwise move is invalid with error message
     */
    private Pair<Boolean, String> isClearPath(@NonNull Board[][] chessboard, @NonNull Moves moves) {
        var sourceRow = moves.getSourceRow();
        var sourceColumn = moves.getSourceColumn();
        var destinationRow = moves.getDestinationRow();
        var destinationColumn = moves.getDestinationColumn();
        if (sourceRow == destinationRow) {
            // Clear horizontally
            if (isPathNotClearStraight(chessboard, sourceColumn, destinationColumn, sourceRow, true)) {
                return new Pair<>(false, "Invalid queen horizontal move " + moves);
            }
        } else if (sourceColumn == destinationColumn) {
            // Clear vertically
            if (isPathNotClearStraight(chessboard, sourceRow, destinationRow, sourceColumn, false)) {
                return new Pair<>(false, "Invalid queen vertical move " + moves);
            }
        } else {
            // Clear diagonally
            if (isPathNotClearDiagonal(chessboard, sourceRow, sourceColumn, destinationRow, destinationColumn)) {
                return new Pair<>(false, "Invalid queen diagonal move " + moves);
            }
        }
        // ensure that the destination cell is either empty or contains a piece of the opposing color
        var destinationCell = chessboard[destinationRow][destinationColumn];
        var destinationMoveCheck = destinationCell.toString().equals(EMPTY_CELL_STRING) ||
                !destinationCell.getColor().equals(this.getColor());
        if (!destinationMoveCheck) {
            return new Pair<>(false, "Invalid queen destination move " + moves);
        }
        return new Pair<>(true, null);
    }

    @Override
    public String toString() {
        switch (getColor()) {
            case WHITE:
                return "♕";
            case BLACK:
                return "♛";
            default:
                throw new IllegalStateException("Only two colors available for queen! " + getColor());
        }
    }
}
