package com.tim4it.whitehatgaming;

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
     * @return true if move is valid (validated), otherwise false
     */
    boolean isValidMove(Board[][] chessboard, int[] moves);
}
