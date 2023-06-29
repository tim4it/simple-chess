package com.tim4it.whitehatgaming;

import com.tim4it.whitehatgaming.util.Pair;

public interface Board {

    /**
     * Figure color
     *
     * @return figure color - {@link Color}
     */
    Color getColor();

    /**
     * Check if we have valid move for each figure on chess board
     *
     * @param chessboard main global chess board
     * @param moves      current moves - source to destination coordinates
     * @return if move is valid (validated), return pair of true, otherwise false
     */
    Pair<Boolean, String> isValidMove(Board[][] chessboard, int[] moves);
}
