package com.tim4it.whitehatgaming.empty;

import com.tim4it.whitehatgaming.Board;
import com.tim4it.whitehatgaming.Color;
import com.tim4it.whitehatgaming.util.Moves;
import com.tim4it.whitehatgaming.util.Pair;
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
    public Pair<Boolean, String> isValidMove(Board[][] chessboard, Moves moves) {
        throw new IllegalStateException("Empty cell does not have valid move information!");
    }

    @Override
    public String toString() {
        return "-";
    }
}
