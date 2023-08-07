package com.tim4it.chess;

import com.tim4it.chess.empty.EmptyCell;
import com.tim4it.chess.figure.Bishop;
import com.tim4it.chess.figure.Knight;
import com.tim4it.chess.figure.Pawn;
import com.tim4it.chess.figure.Queen;
import com.tim4it.chess.figure.Rook;
import com.tim4it.chess.util.Helper;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class CheckDetector {

    static final String EMPTY_CELL_STRING = EmptyCell.builder().build().toString();

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
        for (var row = 0; row < 8; row++) {
            for (var column = 0; column < 8; column++) {
                if (chessboard[row][column].toString().equals(attackedKing.toString())) {
                    kingRow = row;
                    kingColumn = column;
                    break;
                }
            }
        }
        // Check for pieces attacking the king
        for (var row = 0; row < 8; row++) {
            for (var column = 0; column < 8; column++) {
                var piece = chessboard[row][column];
                if (isPieceAttackKing(chessboard, piece, row, column, kingRow, kingColumn)) {
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
    private boolean isPieceAttackKing(@NonNull Board[][] chessboard, @NonNull Board piece,
                                      int pieceRow, int pieceColumn, int kingRow, int kingColumn) {
        if (piece.toString().equals(Pawn.builder().color(getColor()).build().toString())) {
            return isPawnAttack(pieceRow, pieceColumn, kingRow, kingColumn);
        } else if (piece.toString().equals(Knight.builder().color(getColor()).build().toString())) {
            return isKnightAttack(pieceRow, pieceColumn, kingRow, kingColumn);
        } else if (piece.toString().equals(Bishop.builder().color(getColor()).build().toString())) {
            return isBishopAttack(chessboard, pieceRow, pieceColumn, kingRow, kingColumn);
        } else if (piece.toString().equals(Rook.builder().color(getColor()).build().toString())) {
            return isRookAttack(chessboard, pieceRow, pieceColumn, kingRow, kingColumn);
        } else if (piece.toString().equals(Queen.builder().color(getColor()).build().toString())) {
            return isQueenAttack(chessboard, pieceRow, pieceColumn, kingRow, kingColumn);
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

    private boolean isBishopAttack(@NonNull Board[][] chessboard,
                                   int bishopRow, int bishopColumn, int kingRow, int kingColumn) {
        var rowDiff = Math.abs(bishopRow - kingRow);
        var columnDiff = Math.abs(bishopColumn - kingColumn);
        return (rowDiff == columnDiff) &&
                isClearDiagonalPath(chessboard, bishopRow, bishopColumn, kingRow, kingColumn);
    }

    private boolean isRookAttack(@NonNull Board[][] chessboard,
                                 int rookRow, int rookColumn, int kingRow, int kingColumn) {
        return (rookRow == kingRow || rookColumn == kingColumn) &&
                isClearStraightPath(chessboard, rookRow, rookColumn, kingRow, kingColumn);
    }

    private boolean isQueenAttack(@NonNull Board[][] chessboard,
                                  int queenRow, int queenColumn, int kingRow, int kingColumn) {
        var queenAttack = isRookAttack(chessboard, queenRow, queenColumn, kingRow, kingColumn) ||
                isBishopAttack(chessboard, queenRow, queenColumn, kingRow, kingColumn);
        var clearPathQueenKing = isClearDiagonalPath(chessboard, queenRow, queenColumn, kingRow, kingColumn) ||
                isClearStraightPath(chessboard, queenRow, queenColumn, kingRow, kingColumn);
        return queenAttack && clearPathQueenKing;
    }

    /**
     * Check if the diagonal path between the figure (source) and destination (king) positions is clear
     *
     * @param chessboard   chess board
     * @param sourceRow    piece/figure source row position
     * @param sourceColumn piece source column position
     * @param kingRow      king row position
     * @param kingColumn   king column position
     * @return true if position from source figure to king is clear
     */
    private boolean isClearDiagonalPath(@NonNull Board[][] chessboard,
                                        int sourceRow, int sourceColumn, int kingRow, int kingColumn) {
        var deltaRow = kingRow - sourceRow;
        var deltaColumn = kingColumn - sourceColumn;
        var stepRow = deltaRow > 0 ? 1 : -1;
        var stepColumn = deltaColumn > 0 ? 1 : -1;

        var row = sourceRow + stepRow;
        var column = sourceColumn + stepColumn;
        while (row != kingRow && column != kingColumn) {
            if (!chessboard[row][column].toString().equals(EMPTY_CELL_STRING)) {
                return false;
            }
            row += stepRow;
            column += stepColumn;
        }
        return true;
    }

    /**
     * Check if the straight path between the figure (source) and destination (king) positions is clear
     *
     * @param chessboard   chess board
     * @param sourceRow    piece/figure source row position
     * @param sourceColumn piece/figure source column position
     * @param kingRow      king row position
     * @param kingColumn   king column position
     * @return true if position from source figure to king is clear - straight (horizontally, vertically)
     */
    private boolean isClearStraightPath(@NonNull Board[][] chessboard,
                                        int sourceRow, int sourceColumn, int kingRow, int kingColumn) {
        if (sourceRow == kingRow) {
            var startColumn = Math.min(sourceColumn, kingColumn);
            var endColumn = Math.max(sourceColumn, kingColumn);
            for (var column = startColumn + 1; column < endColumn; column++) {
                if (!chessboard[sourceRow][column].toString().equals(EMPTY_CELL_STRING)) {
                    return false;
                }
            }
        } else {
            var startRow = Math.min(sourceRow, kingRow);
            var endRow = Math.max(sourceRow, kingRow);
            for (var row = startRow + 1; row < endRow; row++) {
                if (!chessboard[row][sourceColumn].toString().equals(EMPTY_CELL_STRING)) {
                    return false;
                }
            }
        }
        return true;
    }
}
