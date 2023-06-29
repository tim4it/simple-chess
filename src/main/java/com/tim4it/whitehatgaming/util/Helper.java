package com.tim4it.whitehatgaming.util;

import com.tim4it.whitehatgaming.Board;
import com.tim4it.whitehatgaming.Color;
import com.tim4it.whitehatgaming.empty.EmptyCell;
import com.tim4it.whitehatgaming.figure.Bishop;
import com.tim4it.whitehatgaming.figure.King;
import com.tim4it.whitehatgaming.figure.Knight;
import com.tim4it.whitehatgaming.figure.Pawn;
import com.tim4it.whitehatgaming.figure.Queen;
import com.tim4it.whitehatgaming.figure.Rook;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Helper {

    public static final Board R = Rook.builder().color(Color.WHITE).build();
    public static final Board r = Rook.builder().color(Color.BLACK).build();
    public static final Board N = Knight.builder().color(Color.WHITE).build();
    public static final Board n = Knight.builder().color(Color.BLACK).build();
    public static final Board B = Bishop.builder().color(Color.WHITE).build();
    public static final Board b = Bishop.builder().color(Color.BLACK).build();
    public static final Board Q = Queen.builder().color(Color.WHITE).build();
    public static final Board q = Queen.builder().color(Color.BLACK).build();
    public static final Board K = King.builder().color(Color.WHITE).build();
    public static final Board k = King.builder().color(Color.BLACK).build();
    public static final Board P = Pawn.builder().color(Color.WHITE).build();
    public static final Board p = Pawn.builder().color(Color.BLACK).build();
    public static final Board e = EmptyCell.builder().build();

    /**
     * Checking if the source and destination coordinates are within the chessboard boundaries
     *
     * @param row    board row
     * @param column board column
     * @return true if row, column is within boundaries
     */
    public boolean isValidBoardCoordinate(int row, int column) {
        return row < 0 || row > 7 || column < 0 || column > 7;
    }
}
