package com.tim4it.whitehatgaming;

import java.io.File;

public class MainApp {

    static final String DEFAULT_MOVES_DIR = "data" + File.separator;
    static final String DEFAULT_MOVES_FILE_NAME = DEFAULT_MOVES_DIR + "sample-moves.txt";

    public static void main(String... args) {
        var playGameBuilder = PlayGame.builder();
        if (args.length < 1) {
            playGameBuilder.fileNameMoves(DEFAULT_MOVES_FILE_NAME)
                    .build()
                    .action();
        } else {
            for (String arg : args) {
                if (new File(DEFAULT_MOVES_DIR + arg).exists()) {
                    playGameBuilder
                            .fileNameMoves(DEFAULT_MOVES_DIR + arg)
                            .build()
                            .action();
                } else {
                    throw new IllegalArgumentException("Chess file (" + DEFAULT_MOVES_DIR + arg +
                            ") with moves does not exists!");
                }
            }
        }
    }
}
