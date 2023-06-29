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
public class Pawn extends AbstractFigure {

    @NonNull
    Color color;

    @Override
    public Pair<Boolean, String> isValidMove(Board[][] chessboard, int[] moves) {
        int sourceRow = moves[0], sourceColumn = moves[1];
        int destinationRow = moves[2], destinationColumn = moves[3];
        if (isOutOfBoundaries(sourceRow, sourceColumn, destinationRow, destinationColumn)) {
            return new Pair<>(false, "Wrong source and destination pawn move: " + Arrays.toString(moves));
        }
        // Checking if the piece at the source coordinate is a pawn
        if (!chessboard[sourceRow][sourceColumn].toString().equals(this.toString())) {
            return new Pair<>(false, "Expected pawn, got " + chessboard[sourceRow][sourceColumn].toString());
        }
        // White pawn moves up, black pawn moves down
        var pawnColorMoves = getColor().equals(Color.WHITE) ?
                ((sourceRow - 1) == destinationRow) : (sourceRow == (destinationRow - 1));
        // Pawn moves forward by one row and stays in the same column (normal pawn move)
        if (pawnColorMoves &&
                sourceColumn == destinationColumn &&
                chessboard[destinationRow][destinationColumn].toString().equals(EMPTY_CELL_STRING)) {
            return new Pair<>(true, null);
        }
        // initial position of color pawn
        var pawnInitialPosition = getColor().equals(Color.WHITE) ? sourceRow == 6 : sourceRow == 1;
        // pawn initial move can be two cells on row
        var pawnStep2Move = getColor().equals(Color.WHITE) ?
                sourceRow == destinationRow + 2 : sourceRow == destinationRow - 2;
        // pawn jumps two cells - previous cells must be empty - only for cell row position
        var pawnEmptyCellRow = getColor().equals(Color.WHITE) ? sourceRow - 1 : sourceRow + 1;
        // Pawn is in its starting position and moves forward by two rows and stays in the same column,
        // with both intermediate and destination cells being empty.
        if (pawnInitialPosition &&
                pawnStep2Move &&
                sourceColumn == destinationColumn &&
                chessboard[destinationRow][destinationColumn].toString().equals(EMPTY_CELL_STRING) &&
                chessboard[pawnEmptyCellRow][sourceColumn].toString().equals(EMPTY_CELL_STRING)) {
            return new Pair<>(true, null);
        }
        // Pawn moves forward by one row - diagonal capture move
        var destinationCell = chessboard[destinationRow][destinationColumn];
        if (pawnColorMoves &&
                (Math.abs(sourceColumn - destinationColumn) == 1) &&
                !chessboard[destinationRow][destinationColumn].toString().equals(EMPTY_CELL_STRING) &&
                (!destinationCell.getColor().equals(this.getColor()))) {
            return new Pair<>(true, null);
        }
        return new Pair<>(false, "Invalid pawn move " + Arrays.toString(moves));
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
