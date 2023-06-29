package com.tim4it.whitehatgaming;

import com.tim4it.whitehatgaming.figure.Bishop;
import com.tim4it.whitehatgaming.figure.Knight;
import com.tim4it.whitehatgaming.figure.Pawn;
import com.tim4it.whitehatgaming.figure.Queen;
import com.tim4it.whitehatgaming.figure.Rook;
import com.tim4it.whitehatgaming.util.Helper;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class CheckDetector {

    @NonNull
    Color color;

    /**
     * Check if we have check on chess board
     *
     * @param chessboard chess board current state
     * @return true if we have check - only for one color
     */
    public boolean isCheck(@NonNull Board[][] chessboard) {
        int kingRow = -1;
        int kingColumn = -1;
        var attackedKing = getColor().equals(Color.WHITE) ? Helper.k : Helper.K;
        // Find the position of the king
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (chessboard[row][column].toString().equals(attackedKing.toString())) {
                    kingRow = row;
                    kingColumn = column;
                    break;
                }
            }
        }
        // Check for pieces attacking the king
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                var piece = chessboard[row][column];
                if (isPieceAttackKing(piece, row, column, kingRow, kingColumn)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if pieces attacks the king
     *
     * @param piece       piece {@link Board} data
     * @param pieceRow    piece row
     * @param pieceColumn piece column
     * @param kingRow     found king row
     * @param kingColumn  found king column
     * @return true if one of the pieces valid attacked the opposite king
     */
    private boolean isPieceAttackKing(@NonNull Board piece,
                                      int pieceRow, int pieceColumn, int kingRow, int kingColumn) {
        if (piece.toString().equals(Pawn.builder().color(getColor()).build().toString())) {
            return isPawnAttack(pieceRow, pieceColumn, kingRow, kingColumn);
        } else if (piece.toString().equals(Knight.builder().color(getColor()).build().toString())) {
            return isKnightAttack(pieceRow, pieceColumn, kingRow, kingColumn);
        } else if (piece.toString().equals(Bishop.builder().color(getColor()).build().toString())) {
            return isBishopAttack(pieceRow, pieceColumn, kingRow, kingColumn);
        } else if (piece.toString().equals(Rook.builder().color(getColor()).build().toString())) {
            return isRookAttack(pieceRow, pieceColumn, kingRow, kingColumn);
        } else if (piece.toString().equals(Queen.builder().color(getColor()).build().toString())) {
            return isQueenAttack(pieceRow, pieceColumn, kingRow, kingColumn);
        } else {
            return false;
        }
    }

    private boolean isPawnAttack(int pawnRow, int pawnColumn, int kingRow, int kingColumn) {
        var rowDiff = Math.abs(pawnRow - kingRow);
        var columnDiff = Math.abs(pawnColumn - kingColumn);
        return rowDiff == 1 && columnDiff == 1;
    }

    private boolean isKnightAttack(int knightRow, int knightColumn, int kingRow, int kingColumn) {
        var rowDiff = Math.abs(knightRow - kingRow);
        var columnDiff = Math.abs(knightColumn - kingColumn);
        return (rowDiff == 2 && columnDiff == 1) || (rowDiff == 1 && columnDiff == 2);
    }

    private boolean isBishopAttack(int bishopRow, int bishopColumn, int kingRow, int kingColumn) {
        var rowDiff = Math.abs(bishopRow - kingRow);
        var columnDiff = Math.abs(bishopColumn - kingColumn);
        return rowDiff == columnDiff;
    }

    private boolean isRookAttack(int rookRow, int rookColumn, int kingRow, int kingColumn) {
        return rookRow == kingRow || rookColumn == kingColumn;
    }

    private boolean isQueenAttack(int queenRow, int queenColumn, int kingRow, int kingColumn) {
        return isRookAttack(queenRow, queenColumn, kingRow, kingColumn) ||
                isBishopAttack(queenRow, queenColumn, kingRow, kingColumn);
    }
}
