package com.tim4it.whitehatgaming.figure

import com.tim4it.whitehatgaming.Board
import spock.lang.Specification

import static com.tim4it.whitehatgaming.util.Helper.*

class RookTest extends Specification {

    def "Rook valid/invalid move testing"() {
        given:
        def board = getBoard()
        def rookSource = board[4][3]

        when:
        def isValidMove = rookSource.isValidMove(board, moves)

        then:
        rookSource instanceof Rook
        isValidMove.getFirst() == expextedMoveValid

        where:
        moves                 | expextedMoveValid
        // left
        new int[]{4, 3, 4, 2} | true
        new int[]{4, 3, 4, 1} | true
        new int[]{4, 3, 4, 0} | true
        // right
        new int[]{4, 3, 4, 4} | true
        new int[]{4, 3, 4, 5} | true
        new int[]{4, 3, 4, 6} | true
        new int[]{4, 3, 4, 7} | true
        // up
        new int[]{4, 3, 3, 3} | true
        new int[]{4, 3, 2, 3} | true
        new int[]{4, 3, 1, 3} | true
        // down
        new int[]{4, 3, 5, 3} | true
        new int[]{4, 3, 6, 3} | true
        new int[]{4, 3, 1, 3} | true
        // non valid moves
        new int[]{4, 3, 7, 3} | false
        new int[]{4, 3, 0, 3} | false
        new int[]{4, 3, 3, 1} | false
        new int[]{4, 3, 3, 6} | false
        new int[]{4, 3, 5, 2} | false
        new int[]{4, 3, 5, 7} | false
        new int[]{4, 3, 1, 4} | false
    }

    def getBoard() {
        new Board[][]{
                new Board[]{r, n, b, q, k, b, n, r},
                new Board[]{e, p, p, p, p, p, p, e},
                new Board[]{e, e, e, e, e, e, e, e},
                new Board[]{e, e, e, e, e, e, e, e},
                new Board[]{p, e, e, R, e, e, e, p},
                new Board[]{e, P, e, e, e, e, e, e},
                new Board[]{P, e, e, e, e, P, P, P},
                new Board[]{e, N, B, Q, K, B, N, R}
        }
    }
}
