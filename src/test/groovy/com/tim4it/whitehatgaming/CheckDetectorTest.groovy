package com.tim4it.whitehatgaming

import spock.lang.Specification

import static com.tim4it.whitehatgaming.util.Helper.*

class CheckDetectorTest extends Specification {

    def "Bishop attack"() {
        when:
        def isWhiteCheck = CheckDetector.builder().color(Color.WHITE).build().isCheck(boardPosition)
        def isBlackCheck = CheckDetector.builder().color(Color.BLACK).build().isCheck(boardPosition)

        then:
        isWhiteCheck == expectedWhiteCheck
        isBlackCheck == expectedBlack

        where:
        boardPosition | expectedWhiteCheck | expectedBlack
        getBoard()    | false              | true
        getBoard1()   | true               | false
        getBoard2()   | true               | false
        getBoard3()   | false              | true
    }

    def getBoard() {
        new Board[][]{
                new Board[]{r, n, b, q, k, e, n, r},
                new Board[]{p, p, p, p, e, p, p, p},
                new Board[]{e, e, e, e, e, e, e, e},
                new Board[]{e, e, e, e, e, e, e, e},
                new Board[]{e, b, P, B, e, e, e, e},
                new Board[]{e, P, e, e, e, e, e, e},
                new Board[]{P, e, e, e, e, P, P, P},
                new Board[]{R, N, e, Q, K, B, N, R}
        }
    }

    def getBoard1() {
        new Board[][]{
                new Board[]{r, n, b, q, k, e, n, r},
                new Board[]{p, e, e, p, e, p, p, p},
                new Board[]{e, p, p, N, e, e, e, e},
                new Board[]{e, e, e, e, e, e, e, e},
                new Board[]{e, e, P, B, e, e, e, e},
                new Board[]{e, P, e, e, e, e, e, e},
                new Board[]{P, e, e, e, e, P, P, P},
                new Board[]{R, N, e, Q, K, B, N, R}
        }
    }

    def getBoard2() {
        new Board[][]{
                new Board[]{r, n, b, e, k, e, n, r},
                new Board[]{p, e, e, P, e, p, p, p},
                new Board[]{e, p, p, e, e, e, e, e},
                new Board[]{e, e, e, e, e, e, e, e},
                new Board[]{e, e, P, e, e, e, e, e},
                new Board[]{e, P, e, e, e, e, e, e},
                new Board[]{P, e, e, e, e, P, P, P},
                new Board[]{R, N, e, Q, K, B, N, R}
        }
    }

    def getBoard3() {
        new Board[][]{
                new Board[]{r, n, b, e, k, e, n, e},
                new Board[]{p, e, e, e, r, p, p, p},
                new Board[]{e, p, p, e, e, e, e, e},
                new Board[]{e, e, e, e, e, e, e, e},
                new Board[]{e, e, P, e, e, e, e, e},
                new Board[]{e, P, e, e, e, e, e, e},
                new Board[]{P, e, e, e, e, P, P, P},
                new Board[]{R, N, e, Q, K, B, N, R}
        }
    }
}
