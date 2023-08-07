package com.tim4it.chess;

import com.tim4it.chess.empty.EmptyCell;
import com.tim4it.chess.util.Helper;
import com.tim4it.chess.util.Moves;
import com.whitehatgaming.UserInputFile;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.util.Arrays;

import static com.tim4it.chess.util.Helper.*;

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
    final String fileNameMoves;

    public PlayGame(@NonNull String fileNameMoves) {
        this.fileNameMoves = fileNameMoves;
    }

    @SneakyThrows
    public void action() {
        startGameWithDefaultBoard();
        var userInputFile = new UserInputFile(this.fileNameMoves);
        int[] nextMove;
        while ((nextMove = userInputFile.nextMove()) != null) {
            System.out.println(System.lineSeparator() + "Next move: " + Arrays.toString(nextMove));
            var moves = Helper.translateMoves(nextMove);
            var cellSource = selectFigureCheck(moves);
            var validMove = cellSource.isValidMove(BOARD, moves);
            if (validMove.getFirst()) {
                BOARD[moves.getSourceRow()][moves.getSourceColumn()] = e;
                BOARD[moves.getDestinationRow()][moves.getDestinationColumn()] = cellSource;
                displayBoard();
                isCheckIdentified();
            } else {
                throw new IllegalStateException("Figure " + cellSource + " - " + cellSource.getColor() +
                        " →  " + validMove.getSecond());
            }
            this.playerColorState = this.playerColorState.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
        }
        System.out.println(System.lineSeparator() + "End chess moves from the file!" + System.lineSeparator());
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
     * @param moves moves object {@link Moves} - we need only source to select cell/figure
     * @return selected board interface
     */
    private Board selectFigureCheck(@NonNull Moves moves) {
        var cellSelection = BOARD[moves.getSourceRow()][moves.getSourceColumn()];
        if (cellSelection instanceof EmptyCell) {
            throw new IllegalStateException("Figure was not picked. Empty cell source selection is not allowed!");
        } else if (cellSelection.getColor().equals(playerColorState)) {
            return cellSelection;
        }
        throw new IllegalStateException("Invalid order moves - order of play is white/black/white/black/white..");
    }

    /**
     * Check if chess check is present - white or black is attacking the king
     */
    private void isCheckIdentified() {
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
            for (var j = 0; j < boardWidthHeight; j++) {
                System.out.print(boards[j] + " ");
            }
            System.out.println();
        }
    }
}
