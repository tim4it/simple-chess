package com.tim4it.whitehatgaming.figure

import com.tim4it.whitehatgaming.Board
import spock.lang.Specification

import static com.tim4it.whitehatgaming.util.Helper.*

class QueenTest extends Specification {

    def "queen valid/invalid move testing"() {
        given:
        def board = getBoard()
        def queenSource = board[4][3]

        when:
        def isValidMove = queenSource.isValidMove(board, moves)

        then:
        isValidMove == expextedMoveValid

        where:
        moves                 | expextedMoveValid
        // straight down
        new int[]{4, 3, 5, 3} | true
        new int[]{4, 3, 6, 3} | true
        new int[]{4, 3, 7, 3} | true
        // diagonal
        new int[]{4, 3, 5, 4} | true
        new int[]{4, 3, 5, 2} | true
        new int[]{4, 3, 2, 1} | true
        new int[]{4, 3, 2, 5} | true
        new int[]{4, 3, 3, 2} | true
        new int[]{4, 3, 3, 4} | true
        new int[]{4, 3, 1, 0} | true
        new int[]{4, 3, 1, 6} | true
        // straight up
        new int[]{4, 3, 3, 3} | true
        // non valid queen moves straight up
        new int[]{4, 3, 2, 3} | false
        new int[]{4, 3, 1, 3} | false
        new int[]{4, 3, 0, 3} | false
        // non valid queen moves left
        new int[]{4, 3, 4, 2} | false
        new int[]{4, 3, 4, 1} | false
        new int[]{4, 3, 4, 0} | false
        // non valid queen moves right
        new int[]{4, 3, 4, 4} | false
        new int[]{4, 3, 4, 5} | false
        new int[]{4, 3, 4, 6} | false
        new int[]{4, 3, 4, 7} | false
        new int[]{4, 3, 4, 8} | false
        // non valid queen moves diagonal left
        new int[]{4, 3, 6, 1} | false
        new int[]{4, 3, 7, 0} | false
        // non valid queen moves diagonal right
        new int[]{4, 3, 6, 5} | false
        new int[]{4, 3, 7, 6} | false
        // non valid queen moves random on board
        new int[]{4, 3, 4, 3} | false
        new int[]{4, 3, 2, 6} | false
        new int[]{4, 3, 2, 7} | false
        new int[]{4, 3, 3, 5} | false
        new int[]{4, 3, 3, 6} | false
        new int[]{4, 3, 3, 7} | false
        new int[]{4, 3, 2, 0} | false
        new int[]{4, 3, 3, 0} | false
        new int[]{4, 3, 5, 1} | false
    }

    def getBoard() {
        new Board[][]{
                new Board[]{r, n, b, q, k, b, n, r},
                new Board[]{p, p, p, p, p, p, p, p},
                new Board[]{e, e, e, P, e, e, e, e},
                new Board[]{e, e, e, e, e, e, e, e},
                new Board[]{e, e, P, Q, P, e, e, e},
                new Board[]{e, e, e, e, e, e, e, e},
                new Board[]{P, P, e, e, e, P, P, P},
                new Board[]{R, N, B, e, K, B, N, R}
        }
    }
}
