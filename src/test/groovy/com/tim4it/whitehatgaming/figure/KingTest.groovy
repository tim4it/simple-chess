package com.tim4it.whitehatgaming.figure

import com.tim4it.whitehatgaming.Board
import spock.lang.Specification

import static com.tim4it.whitehatgaming.util.Helper.K
import static com.tim4it.whitehatgaming.util.Helper.N
import static com.tim4it.whitehatgaming.util.Helper.P
import static com.tim4it.whitehatgaming.util.Helper.e
import static com.tim4it.whitehatgaming.util.Helper.p

class KingTest extends Specification {

    def "King valid/invalid move testing"() {
        given:
        def board = getBoard()
        def kingSource = board[4][4]

        when:
        def isValidMove = kingSource.isValidMove(board, moves)

        then:
        kingSource instanceof King
        isValidMove.getFirst() == expextedMoveValid

        where:
        moves                 | expextedMoveValid
        new int[]{4, 4, 3, 3} | true
        new int[]{4, 4, 3, 4} | true
        new int[]{4, 4, 3, 5} | true
        new int[]{4, 4, 4, 3} | true
        new int[]{4, 4, 5, 4} | true
        new int[]{4, 4, 5, 5} | true
        new int[]{4, 4, 5, 3} | false
        new int[]{4, 4, 4, 5} | false
        new int[]{4, 4, 4, 2} | false
        new int[]{4, 4, 2, 4} | false
        new int[]{4, 4, 2, 6} | false
        new int[]{4, 4, 2, 7} | false
    }

    def getBoard() {
        new Board[][]{
                new Board[]{e, e, e, e, e, e, e, e},
                new Board[]{e, e, e, e, e, e, e, e},
                new Board[]{e, e, e, e, e, e, e, e},
                new Board[]{e, e, e, e, e, p, e, e},
                new Board[]{e, e, e, e, K, N, e, e},
                new Board[]{e, e, e, P, e, e, e, e},
                new Board[]{e, e, e, e, e, e, e, e},
                new Board[]{e, e, e, e, e, e, e, e}
        }
    }
}
