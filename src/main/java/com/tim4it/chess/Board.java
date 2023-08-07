package com.tim4it.chess;

import com.tim4it.chess.util.Moves;
import com.tim4it.chess.util.Pair;

public interface Board {

    /**
     * Figure color
     *
     * @return figure color - {@link Color}
     */
    Color getColor();

    /**
     * Check if we have valid move for each figure on chess board.
     *
     * @param chessboard main global chess board
     * @param moves      current moves - source to destination coordinates
     * @return pair of data - if move is valid (validated), return true, otherwise move is invalid with error message
     */
    Pair<Boolean, String> isValidMove(Board[][] chessboard, Moves moves);
}
