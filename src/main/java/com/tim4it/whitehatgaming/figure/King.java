package com.tim4it.whitehatgaming.figure;

import com.tim4it.whitehatgaming.Board;
import com.tim4it.whitehatgaming.Color;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class King extends AbstractFigure {

    @NonNull
    Color color;

    @Override
    public boolean isValidMove(Board[][] chessboard, int[] move) {
        return false;
    }

    @Override
    public String toString() {
        switch (getColor()) {
            case WHITE:
                return "♔";
            case BLACK:
                return "♚";
            default:
                throw new IllegalStateException("Only two colors available for king! " + getColor());
        }
    }
}
