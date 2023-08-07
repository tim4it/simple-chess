package com.tim4it.chess.util;

import com.tim4it.chess.Board;
import com.tim4it.chess.Color;
import com.tim4it.chess.empty.EmptyCell;
import com.tim4it.chess.figure.Bishop;
import com.tim4it.chess.figure.King;
import com.tim4it.chess.figure.Knight;
import com.tim4it.chess.figure.Pawn;
import com.tim4it.chess.figure.Queen;
import com.tim4it.chess.figure.Rook;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Helper {

    public final Board R = Rook.builder().color(Color.WHITE).build();
    public final Board r = Rook.builder().color(Color.BLACK).build();
    public final Board N = Knight.builder().color(Color.WHITE).build();
    public final Board n = Knight.builder().color(Color.BLACK).build();
    public final Board B = Bishop.builder().color(Color.WHITE).build();
    public final Board b = Bishop.builder().color(Color.BLACK).build();
    public final Board Q = Queen.builder().color(Color.WHITE).build();
    public final Board q = Queen.builder().color(Color.BLACK).build();
    public final Board K = King.builder().color(Color.WHITE).build();
    public final Board k = King.builder().color(Color.BLACK).build();
    public final Board P = Pawn.builder().color(Color.WHITE).build();
    public final Board p = Pawn.builder().color(Color.BLACK).build();
    public final Board e = EmptyCell.builder().build();

    /**
     * Checking if the source and destination coordinates are within the chessboard boundaries
     *
     * @param row    board row
     * @param column board column
     * @return true if, row and column is within boundaries
     */
    public boolean isInvalidBoardCoordinate(int row, int column) {
        return row < 0 || row > 7 || column < 0 || column > 7;
    }

    /**
     * Translate source to destination coordinates to more readable coordinates - row/column
     *
     * @param moves - moves (source/destination) from files
     * @return translated moves source/destination - row/column
     */
    public Moves translateMoves(@NonNull int[] moves) {
        return Moves.builder()
                .sourceRow(moves[1])
                .sourceColumn(moves[0])
                .destinationRow(moves[3])
                .destinationColumn(moves[2])
                .build();
    }
}
