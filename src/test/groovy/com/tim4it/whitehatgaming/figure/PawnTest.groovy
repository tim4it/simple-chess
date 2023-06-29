package com.tim4it.whitehatgaming.figure

import com.tim4it.whitehatgaming.Board
import spock.lang.Specification

import static com.tim4it.whitehatgaming.util.Helper.*

class PawnTest extends Specification {

    def "Pawn valid/invalid move testing - white"() {
        given:
        def board = getBoard()
        def pawnSource = board[4][3]

        when:
        def isValidMove = pawnSource.isValidMove(board, moves)

        then:
        pawnSource instanceof Pawn
        isValidMove.getFirst() == expextedMoveValid

        where:
        moves                 | expextedMoveValid
        new int[]{4, 3, 3, 3} | true
        new int[]{4, 3, 3, 2} | true
        new int[]{4, 3, 3, 4} | false
        new int[]{4, 3, 5, 3} | false
        new int[]{4, 3, 5, 4} | false
        new int[]{4, 3, 5, 2} | false
        new int[]{4, 3, 4, 2} | false
        new int[]{4, 3, 4, 4} | false
    }

    def "Pawn valid/invalid move testing - white initial one/two step"() {
        given:
        def board = getBoard()
        def pawnSource = board[6][0]

        when:
        def isValidMove = pawnSource.isValidMove(board, moves)

        then:
        pawnSource instanceof Pawn
        isValidMove.getFirst() == expextedMoveValid

        where:
        moves                 | expextedMoveValid
        new int[]{6, 0, 4, 0} | true
        new int[]{6, 0, 5, 0} | true
        new int[]{6, 0, 3, 0} | false
        new int[]{6, 0, 2, 0} | false
        new int[]{6, 0, 1, 0} | false
        new int[]{6, 0, 0, 0} | false
        new int[]{6, 0, 5, 1} | false
    }

    def "Pawn valid/invalid move testing - black"() {
        given:
        def board = getBoard()
        def pawnSource = board[2][3]

        when:
        def isValidMove = pawnSource.isValidMove(board, moves)

        then:
        pawnSource instanceof Pawn
        isValidMove.getFirst() == expextedMoveValid

        where:
        moves                 | expextedMoveValid
        new int[]{2, 3, 3, 3} | true
        new int[]{2, 3, 3, 4} | true
        new int[]{2, 3, 3, 2} | false
        new int[]{2, 3, 4, 3} | false
        new int[]{2, 3, 5, 3} | false
        new int[]{2, 3, 6, 3} | false
        new int[]{2, 3, 4, 1} | false
        new int[]{2, 3, 4, 5} | false
    }

    def "Pawn valid/invalid move testing - black initial one/two step"() {
        given:
        def board = getBoard()
        def pawnSource = board[1][6]

        when:
        def isValidMove = pawnSource.isValidMove(board, moves)

        then:
        pawnSource instanceof Pawn
        isValidMove.getFirst() == expextedMoveValid

        where:
        moves                 | expextedMoveValid
        new int[]{1, 6, 3, 6} | true
        new int[]{1, 6, 2, 6} | true
        new int[]{1, 6, 2, 5} | false
        new int[]{1, 6, 2, 7} | false
        new int[]{1, 6, 4, 6} | false
        new int[]{1, 6, 5, 6} | false
        new int[]{1, 6, 6, 6} | false
        new int[]{1, 6, 3, 5} | false
        new int[]{1, 6, 3, 7} | false
    }

    def getBoard() {
        new Board[][]{
                new Board[]{r, n, b, q, k, e, n, r},
                new Board[]{p, p, e, e, e, p, p, p},
                new Board[]{e, e, e, p, e, e, e, e},
                new Board[]{e, e, p, e, P, e, e, e},
                new Board[]{e, e, e, P, e, e, e, e},
                new Board[]{e, e, e, e, e, e, e, e},
                new Board[]{P, P, P, e, e, P, P, P},
                new Board[]{R, N, B, Q, K, B, N, R}
        }
    }
}
