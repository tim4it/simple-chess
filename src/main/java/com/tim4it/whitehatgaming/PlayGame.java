package com.tim4it.whitehatgaming;

import com.tim4it.whitehatgaming.empty.EmptyCell;
import com.whitehatgaming.UserInputFile;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.util.Arrays;

import static com.tim4it.whitehatgaming.util.Helper.*;

public class PlayGame {

    Color playerColorState = Color.WHITE;
    Board[][] BOARD = new Board[][]{
            new Board[]{r, n, b, q, k, b, n, r},
            new Board[]{p, p, p, p, p, p, p, p},
            new Board[]{e, e, e, e, e, e, e, e},
            new Board[]{e, e, e, e, e, e, e, e},
            new Board[]{e, e, e, e, e, e, e, e},
            new Board[]{e, e, e, e, e, e, e, e},
            new Board[]{P, P, P, P, P, P, P, P},
            new Board[]{R, N, B, Q, K, B, N, R}
    };

    String fileNameMoves;

    public PlayGame(@NonNull String fileNameMoves) {
        this.fileNameMoves = fileNameMoves;
    }

    @SneakyThrows
    public void action() {
        startGameWithDefaultBoard();
        var as = new UserInputFile(this.fileNameMoves);
        int[] nextMove;
        while ((nextMove = as.nextMove()) != null) {
            var translateMove = translateSourceDestinationMove(nextMove);
            System.out.println(System.lineSeparator() + "Next move: " + Arrays.toString(translateMove));
            var cellSource = selectFigureCheck(translateMove);
            var validMove = cellSource.isValidMove(BOARD, translateMove);
            if (validMove.getFirst()) {
                BOARD[translateMove[0]][translateMove[1]] = e;
                BOARD[translateMove[2]][translateMove[3]] = cellSource;
                displayBoard();
                isValidCheck();
            } else {
                throw new IllegalStateException("Figure " + cellSource + " - " + cellSource.getColor() +
                        " →  " + validMove.getSecond());
            }
            playerColorState = playerColorState.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
        }
        System.out.println(System.lineSeparator() + "End chess moves from the file!" + System.lineSeparator());
    }

    /**
     * Translate Source coordinates to more readable coordinates - row/column
     *
     * @param movesFromFileRow - moves from file - source/destination
     * @return translated moves source/destination - row/column
     */
    private int[] translateSourceDestinationMove(int[] movesFromFileRow) {
        return new int[]{movesFromFileRow[1], movesFromFileRow[0], movesFromFileRow[3], movesFromFileRow[2]};
    }

    /**
     * Start game with default figure layout chess board
     */
    private void startGameWithDefaultBoard() {
        System.out.println(System.lineSeparator() + "PLAY GAME with file moves: " + this.fileNameMoves);
        System.out.println("Start game with initial chess board!");
        displayBoard();
    }

    /**
     * Select first figure from source coordinate - row/column
     *
     * @param moves source to destination moves - coordinates
     * @return selected board interface
     */
    private Board selectFigureCheck(int[] moves) {
        var cellSelection = BOARD[moves[0]][moves[1]];
        if (cellSelection instanceof EmptyCell) {
            throw new IllegalStateException("Chess figure is not picket. Empty pick is not allowed!");
        } else if (cellSelection.getColor().equals(playerColorState)) {
            return cellSelection;
        }
        throw new IllegalStateException("Invalid order moves - order of play is white/black/white/black/..");
    }

    /**
     * Check if chess check is present - white or black is attacking the king
     */
    private void isValidCheck() {
        if (CheckDetector.builder().color(Color.WHITE).build().isCheck(BOARD)) {
            System.out.println("→  CHECK - white figure attacked black king!");
        } else if (CheckDetector.builder().color(Color.BLACK).build().isCheck(BOARD)) {
            System.out.println("→  CHECK - black figure attacked white king!");
        }
    }

    /**
     * Display chess board
     */
    public void displayBoard() {
        var boardWidthHeight = BOARD.length;
        for (Board[] boards : BOARD) {
            System.out.print("\t");
            for (int j = 0; j < boardWidthHeight; j++) {
                System.out.print(boards[j] + " ");
            }
            System.out.println();
        }
    }
}
