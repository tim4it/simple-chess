package com.tim4it.whitehatgaming.figure;

import com.tim4it.whitehatgaming.Board;
import com.tim4it.whitehatgaming.empty.EmptyCell;
import com.tim4it.whitehatgaming.util.Helper;
import lombok.NonNull;

public abstract class AbstractFigure implements Board {
    String EMPTY_CELL_STRING = EmptyCell.builder().build().toString();

    /**
     * Checking if the source and destination coordinates are within the board boundaries
     *
     * @param sourceRow         source row coordinate
     * @param sourceColumn      source column coordinate
     * @param destinationRow    destination row coordinate
     * @param destinationColumn destination column coordinate
     * @return true if move is out of chess board cell, otherwise false
     */
    protected boolean isOutOfBoundaries(int sourceRow, int sourceColumn,
                                        int destinationRow, int destinationColumn) {
        return Helper.isInvalidBoardCoordinate(sourceRow, sourceColumn) ||
                Helper.isInvalidBoardCoordinate(destinationRow, destinationColumn);
    }

    /**
     * Check if path is not clear horizontally or vertically
     *
     * @param chessboard             chessboard current position
     * @param sourceRowColumn        source row/column
     * @param destinationRowColumn   destination row/column
     * @param sourceRowColumnBoard   source row/column for empty cell checking
     * @param isSourceRowColumnBoard true if clear path horizontally, otherwise vertically
     * @return true if path is not clear horizontally/vertically, otherwise false
     */
    protected boolean isPathNotClearStraight(@NonNull Board[][] chessboard,
                                             int sourceRowColumn, int destinationRowColumn,
                                             int sourceRowColumnBoard, boolean isSourceRowColumnBoard) {
        int startColumn = Math.min(sourceRowColumn, destinationRowColumn);
        int endColumn = Math.max(sourceRowColumn, destinationRowColumn);
        for (var i = startColumn + 1; i < endColumn; i++) {
            var boardCheck = isSourceRowColumnBoard ?
                    chessboard[sourceRowColumnBoard][i] : chessboard[i][sourceRowColumnBoard];
            if (!boardCheck.toString().equals(EMPTY_CELL_STRING)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if path is not clear diagonally
     *
     * @param chessboard        chessboard current position
     * @param sourceRow         source move row
     * @param sourceColumn      source move column
     * @param destinationRow    destination move row
     * @param destinationColumn destination move column
     * @return true if path is not clear diagonally, otherwise false
     */
    protected boolean isPathNotClearDiagonal(@NonNull Board[][] chessboard,
                                             int sourceRow, int sourceColumn,
                                             int destinationRow, int destinationColumn) {
        // Checking if the path is clear diagonally
        var deltaRow = destinationRow - sourceRow;
        var deltaColumn = destinationColumn - sourceColumn;
        var stepRow = deltaRow > 0 ? 1 : -1;
        var stepColumn = deltaColumn > 0 ? 1 : -1;

        var row = sourceRow + stepRow;
        var column = sourceColumn + stepColumn;
        while (row != destinationRow && column != destinationColumn) {
            if (!chessboard[row][column].toString().equals(EMPTY_CELL_STRING)) {
                return true;
            }
            row += stepRow;
            column += stepColumn;
        }
        return false;
    }
}
