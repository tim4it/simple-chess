package com.tim4it.whitehatgaming;

import com.whitehatgaming.UserInputFile;
import lombok.Builder;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.Value;

import java.util.Arrays;

import static com.tim4it.whitehatgaming.util.Helper.*;

@Value
@Builder
public class PlayGame {

    static final Board[][] BOARD = new Board[][]{
            new Board[]{r, n, b, q, k, b, n, r},
            new Board[]{p, p, p, p, p, p, p, p},
            new Board[]{e, e, e, e, e, e, e, e},
            new Board[]{e, e, e, e, e, e, e, e},
            new Board[]{e, e, e, e, e, e, e, e},
            new Board[]{e, e, e, e, e, e, e, e},
            new Board[]{P, P, P, P, P, P, P, P},
            new Board[]{R, N, B, Q, K, B, N, R}
    };

    @NonNull
    String fileNameMoves;

    @SneakyThrows
    public void action() {
        System.out.println(System.lineSeparator() + "PLAY GAME with file moves: " + getFileNameMoves());
        displayBoard();
        var as = new UserInputFile(getFileNameMoves());
        int[] nextMove;
        while ((nextMove = as.nextMove()) != null) {
            System.out.println("Next move: " + Arrays.toString(nextMove));
        }
        System.out.println("END GAME");
    }

    public void displayBoard() {
        var boardWidthHeight = BOARD.length;
        for (Board[] boards : BOARD) {
            for (int j = 0; j < boardWidthHeight; j++) {
                System.out.print("  " + boards[j] + " ");
            }
            System.out.println();
        }
    }
}
