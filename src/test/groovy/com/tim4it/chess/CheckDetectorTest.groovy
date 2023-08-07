package com.tim4it.chess

import spock.lang.Specification

import static com.tim4it.chess.util.Helper.*

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
        getBoard4()   | false              | false
        getBoard5()   | false              | false
        getBoard6()   | false              | true
        getBoard7()   | false              | false
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
                new Board[]{p, e, e, e, p, e, p, p},
                new Board[]{e, p, p, e, e, e, e, e},
                new Board[]{e, e, e, e, r, e, e, e},
                new Board[]{e, e, P, e, e, e, e, e},
                new Board[]{e, P, e, e, e, e, e, e},
                new Board[]{P, e, e, e, e, P, P, P},
                new Board[]{R, N, e, Q, K, B, N, R}
        }
    }

    def getBoard4() {
        new Board[][]{
                new Board[]{r, e, b, e, k, e, n, e},
                new Board[]{p, p, p, p, e, p, p, p},
                new Board[]{e, e, n, e, e, e, e, e},
                new Board[]{e, e, e, e, p, e, e, Q},
                new Board[]{e, e, B, e, P, e, e, e},
                new Board[]{e, e, e, e, e, e, e, e},
                new Board[]{P, P, P, P, e, P, P, P},
                new Board[]{R, N, B, e, K, e, N, R}
        }
    }

    def getBoard5() {
        new Board[][]{
                new Board[]{r, e, b, e, k, e, n, e},
                new Board[]{p, p, p, p, e, p, p, p},
                new Board[]{e, e, n, e, e, e, e, e},
                new Board[]{e, e, e, e, p, e, e, B},
                new Board[]{e, e, B, e, P, e, e, e},
                new Board[]{e, e, e, e, e, e, e, e},
                new Board[]{P, P, P, P, e, P, P, P},
                new Board[]{R, N, e, e, K, e, N, R}
        }
    }

    def getBoard6() {
        new Board[][]{
                new Board[]{r, e, b, e, k, e, n, e},
                new Board[]{p, p, p, p, q, p, p, p},
                new Board[]{e, e, n, e, e, e, e, e},
                new Board[]{e, e, e, e, e, e, e, B},
                new Board[]{e, e, B, e, e, e, e, e},
                new Board[]{e, e, e, e, e, e, e, e},
                new Board[]{P, P, P, P, K, P, P, P},
                new Board[]{R, N, e, e, e, e, N, R}
        }
    }

    def getBoard7() {
        new Board[][]{
                new Board[]{r, e, b, e, k, e, n, e},
                new Board[]{p, p, p, p, e, p, p, p},
                new Board[]{e, e, n, e, e, e, e, e},
                new Board[]{e, e, e, e, q, e, e, B},
                new Board[]{e, e, B, e, e, e, e, e},
                new Board[]{e, e, e, e, P, e, e, e},
                new Board[]{P, P, P, P, K, P, P, P},
                new Board[]{R, N, e, e, e, e, N, R}
        }
    }
}
