package com.tim4it.whg;

import com.whitehatgaming.UserInputFile;

import java.io.File;
import java.util.Arrays;

public class MainApp {

    public static void main(String... args) {

        try {
            var as = new UserInputFile("data" + File.separator + "my-moves.txt");
            var result = as.nextMove();
            System.out.println("Next move: " + Arrays.toString(result));
            result = as.nextMove();
            System.out.println("Next move: " + Arrays.toString(result));
            result = as.nextMove();
            System.out.println("Next move: " + Arrays.toString(result));
            result = as.nextMove();
            System.out.println("Next move: " + Arrays.toString(result));
            result = as.nextMove();
            System.out.println("Next move: " + Arrays.toString(result));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
