package com.tim4it.whitehatgaming.empty;

import com.tim4it.whitehatgaming.Board;
import com.tim4it.whitehatgaming.Color;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class EmptyCell implements Board {

    @Override
    public Color getColor() {
        throw new IllegalStateException("Empty cell does not have color information!");
    }

    @Override
    public boolean isValidMove(Board[][] chessboard, int[] move) {
        throw new IllegalStateException("Empty cell does not have valid move information!");
    }

    @Override
    public String toString() {
        return "-";
    }
}
