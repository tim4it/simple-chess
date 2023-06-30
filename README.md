# White hat gaming

You have been asked to write a computer program to allow two human players to play
chess.

A good reference for the rules and general set up for chess can be found on Wikipedia:
http://en.wikipedia.org/wiki/Rules_of_chess

The program should simply read in moves and validate them, tracking and showing
the board state. It should determine if a move leaves the player is in check. It does
not need to handle checkmate.

Provided is a Java interface com.whitehatgaming.UserInput via which each attempted
move can be obtained. You do not need to implement this interface. A single
implementation `com.whitehatgaming.UserInputFile` is also provided which will read
the moves from a text file. 

Three sample move files are provided in the data
directory. 

Feel free to add additional files and/or write automated tests as required.

Please see the Javadoc (`/javadoc`) for UserInput to see how the coordinate system for the chess
board is represented in the input data.

## Build the projects

Clean build the projects with all tests

```
./gradlew clean build
```

## Run projects

Run with default sample moves file:
```
./gradlew run" 
```

Run with custom sample moves - support multiple files:
```
./gradlew run --args="my-moves.txt"
./gradlew run --args="my-moves.txt sample-moves.txt"
```

Moves to check:
```
./gradlew run --args="check1.txt check2.txt check3.txt checkmate.txt"
```

Invalid moves:
```
./gradlew run --args="sample-empty-selected.txt sample-moves-invalid.txt sample-moves-invalid1.txt"
```

## Checkmate

- Check if the king is under attack
- Check if the king can move to any safe square
- Check if any friendly piece can block the attack
- Check if any piece can capture the attacking piece

## Castling

Castling consists of moving the king two squares towards a rook, then placing the rook on the other side of the king, adjacent to it. There are certain conditions and rules that must be met for castling to be valid:

- The king and the rook involved in castling must not have moved previously in the game.
- There should be no pieces between the king and the rook.
- The squares that the king moves through and the square the king ends up on should not be under attack by any of the opponent's pieces.

```java
public static boolean isValidCastling(Board[][] chessboard, int srcRow, int srcCol, int dstRow, int dstCol) {

    char kingColor = chessboard[srcRow][srcCol];
    char rookColor = chessboard[dstRow][dstCol];

    if (hasPieceMoved(srcRow, srcCol) || hasPieceMoved(dstRow, dstCol)) {
        return false;
    }

    // Check if there are no pieces between the king and the rook
    int startCol = Math.min(srcCol, dstCol) + 1;
    int endCol = Math.max(srcCol, dstCol);
    for (int col = startCol; col < endCol; col++) {
        if (chessboard[sourceRow][column].toString().equals(EMPTY_CELL_STRING)) {
            return false;
        }
    }

    int kingDestCol = srcCol + (dstCol - srcCol) / 2; // Column the king lands on after castling
    if (isUnderAttack(srcRow, srcCol) || 
        isUnderAttack(srcRow, kingDestCol) || 
        isUnderAttack(srcRow, dstCol)) {
        return false;
    }

    return true;
}
```
It verifies that both the king and the rook have not moved previously by calling the `hasPieceMoved` method.

It ensures that the squares the king moves through and lands on are not under attack by calling the `isUnderAttack` method.
