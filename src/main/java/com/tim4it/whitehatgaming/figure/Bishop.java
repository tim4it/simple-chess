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
public class Bishop extends AbstractFigure {

    @NonNull
    Color color;

    @Override
    public Pair<Boolean, String> isValidMove(Board[][] chessboard, int[] moves) {
        // Extracting the source and destination coordinates from the move
        int sourceRow = moves[0], sourceColumn = moves[1];
        int destinationRow = moves[2], destinationColumn = moves[3];
        if (isOutOfBoundaries(sourceRow, sourceColumn, destinationRow, destinationColumn)) {
            return new Pair<>(false, "Wrong source and destination bishop move: " + Arrays.toString(moves));
        }
        // Checking if the piece at the source coordinate is a bishop
        if (!chessboard[sourceRow][sourceColumn].toString().equals(this.toString())) {
            return new Pair<>(false, "Expected bishop, got " + chessboard[sourceRow][sourceColumn].toString());
        }
        var rowDiff = Math.abs(destinationRow - sourceRow);
        var columnDiff = Math.abs(destinationColumn - sourceColumn);
        // Checking if the move is valid horizontally, vertically, or diagonally
        if (rowDiff == columnDiff) {
            return isClearPath(chessboard, moves);
        }
        return new Pair<>(false, "Invalid bishop move " + Arrays.toString(moves));
    }

    /**
     * Checking if the path is clear between the source and destination coordinates
     *
     * @param chessboard chess board with current figures
     * @param moves      moves - source to destination
     * @return pair data first - true if move is validated, otherwise move is invalid with error message
     */
    private Pair<Boolean, String> isClearPath(Board[][] chessboard, int[] moves) {
        int sourceRow = moves[0], sourceColumn = moves[1];
        int destinationRow = moves[2], destinationColumn = moves[3];
        // Clear diagonally
        if (isPathNotClearDiagonal(chessboard, sourceRow, sourceColumn, destinationRow, destinationColumn)) {
            return new Pair<>(false, "Invalid bishop diagonal move " + Arrays.toString(moves));
        }
        // ensure that the destination cell is either empty or contains a piece of the opposing color
        var destinationCell = chessboard[destinationRow][destinationColumn];
        var destinationMoveCheck = destinationCell.toString().equals(EMPTY_CELL_STRING) ||
                !destinationCell.getColor().equals(this.getColor());
        if (!destinationMoveCheck) {
            return new Pair<>(false, "Invalid bishop destination move " + Arrays.toString(moves));
        }
        return new Pair<>(true, null);
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
