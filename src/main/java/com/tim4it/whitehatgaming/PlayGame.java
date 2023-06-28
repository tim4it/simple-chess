package com.tim4it.whitehatgaming;

import com.whitehatgaming.UserInputFile;
import lombok.Builder;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.Value;

import java.util.Arrays;

@Value
@Builder
public class PlayGame {

    @NonNull
    String fileNameMoves;

    @SneakyThrows
    public void action() {
        System.out.println(System.lineSeparator() + "PLAY GAME with file moves: " + getFileNameMoves());
        var as = new UserInputFile(getFileNameMoves());
        int[] nextMove;
        while ((nextMove = as.nextMove()) != null) {
            System.out.println("Next move: " + Arrays.toString(nextMove));
        }
        System.out.println("END GAME");
    }
}
