package com.tim4it.whitehatgaming.figure

import com.tim4it.whitehatgaming.Board
import spock.lang.Specification

import static com.tim4it.whitehatgaming.util.Helper.*

class KnightTest extends Specification {

    def "Knight valid/invalid move testing"() {
        given:
        def board = getBoard()
        def knightSource = board[4][4]

        when:
        def isValidMove = knightSource.isValidMove(board, moves)

        then:
        knightSource instanceof Knight
        isValidMove.getFirst() == expextedMoveValid

        where:
        moves                 | expextedMoveValid
        new int[]{4, 4, 2, 3} | true
        new int[]{4, 4, 3, 2} | true
        new int[]{4, 4, 2, 5} | true
        new int[]{4, 4, 3, 6} | true
        new int[]{4, 4, 5, 6} | true
        new int[]{4, 4, 6, 3} | true
        new int[]{4, 4, 6, 5} | false
        new int[]{4, 4, 5, 2} | false
        // non valid moves
        new int[]{4, 4, 4, 4} | false
        new int[]{4, 4, 4, 5} | false
        new int[]{4, 4, 4, 2} | false
        new int[]{4, 4, 6, 4} | false
        new int[]{4, 4, 2, 4} | false
        new int[]{4, 4, 5, 5} | false
        new int[]{4, 4, 3, 5} | false
        new int[]{4, 4, 1, 1} | false
        new int[]{4, 4, 1, 3} | false
        new int[]{4, 4, 1, 6} | false
        new int[]{4, 4, 6, 1} | false
        new int[]{4, 4, 7, 0} | false
    }

    def getBoard() {
        new Board[][]{
                new Board[]{r, n, b, q, k, e, n, r},
                new Board[]{p, p, p, e, e, p, p, p},
                new Board[]{e, e, e, p, p, e, e, e},
                new Board[]{e, e, b, e, e, e, e, e},
                new Board[]{e, e, e, e, N, e, e, e},
                new Board[]{e, P, Q, e, P, e, e, e},
                new Board[]{P, e, e, e, e, P, P, P},
                new Board[]{R, e, e, e, K, B, N, R}
        }
    }
}
