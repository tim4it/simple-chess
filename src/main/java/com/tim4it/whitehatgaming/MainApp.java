package com.tim4it.whitehatgaming;

import java.io.File;

public class MainApp {

    static final String DEFAULT_MOVES_DIR = "data" + File.separator;
    static final String DEFAULT_MOVES_FILE_NAME = DEFAULT_MOVES_DIR + "sample-moves.txt";

    public static void main(String... args) {
        if (args.length < 1) {
            new PlayGame(DEFAULT_MOVES_FILE_NAME).action();
        } else {
            for (String arg : args) {
                if (new File(DEFAULT_MOVES_DIR + arg).exists()) {
                    try {
                        new PlayGame(DEFAULT_MOVES_DIR + arg).action();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    throw new IllegalArgumentException("Chess file (" + DEFAULT_MOVES_DIR + arg +
                            ") with moves does not exists!");
                }
            }
        }
    }
}
