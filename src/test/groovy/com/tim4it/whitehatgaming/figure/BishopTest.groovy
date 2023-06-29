package com.tim4it.whitehatgaming.figure

import com.tim4it.whitehatgaming.Board
import spock.lang.Specification

import static com.tim4it.whitehatgaming.util.Helper.*

class BishopTest extends Specification {

    def "Bishop valid/invalid move testing"() {
        given:
        def board = getBoard()
        def bishopSource = board[4][3]

        when:
        def isValidMove = bishopSource.isValidMove(board, moves)

        then:
        bishopSource instanceof Bishop
        isValidMove.getFirst() == expextedMoveValid

        where:
        moves                 | expextedMoveValid
        // left diagonal
        new int[]{4, 3, 3, 2} | true
        new int[]{4, 3, 2, 1} | true
        new int[]{4, 3, 1, 0} | true
        // right diagonal
        new int[]{4, 3, 3, 4} | true
        new int[]{4, 3, 2, 5} | true
        new int[]{4, 3, 1, 6} | true
        // left diagonal down
        new int[]{4, 3, 5, 2} | true
        new int[]{4, 3, 6, 1} | true
        // right diagonal down
        new int[]{4, 3, 5, 4} | true
        // non valid moves
        new int[]{4, 3, 6, 5} | false
        new int[]{4, 3, 7, 0} | false
        new int[]{4, 3, 4, 2} | false
        new int[]{4, 3, 4, 6} | false
        new int[]{4, 3, 3, 1} | false
        new int[]{4, 3, 3, 5} | false
        new int[]{4, 3, 1, 7} | false
        new int[]{4, 3, 1, 5} | false
        new int[]{4, 3, 1, 1} | false
        new int[]{4, 3, 1, 3} | false
        new int[]{4, 3, 6, 5} | false
        new int[]{4, 3, 6, 7} | false
        new int[]{4, 3, 7, 0} | false
        new int[]{4, 3, 7, 1} | false
    }

    def getBoard() {
        new Board[][]{
                new Board[]{r, n, b, q, k, b, n, r},
                new Board[]{p, p, p, p, p, p, p, p},
                new Board[]{e, e, e, e, e, e, e, e},
                new Board[]{e, e, e, e, e, e, e, e},
                new Board[]{e, e, P, B, e, e, e, e},
                new Board[]{e, P, e, e, e, e, e, e},
                new Board[]{P, e, e, e, e, P, P, P},
                new Board[]{R, N, e, Q, K, B, N, R}
        }
    }
}
