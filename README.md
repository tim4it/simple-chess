# Simple chess play

Write a computer program to allow two human players to play chess.

A good reference for the rules and general set up for chess can be found on Wikipedia:
http://en.wikipedia.org/wiki/Rules_of_chess

The program should simply read in moves from file and validate them, tracking and showing
the board state. It should determine if a move leaves the player is in check. It does
not need to handle checkmate.

Provided is a Java interface `com.whitehatgaming.UserInput` via which each attempted
move can be obtained. You do not need to implement this interface. A single
implementation `com.whitehatgaming.UserInputFile` is also provided which will read
the moves from a text file. 

Sample move files are provided in the `data` directory. 

Please see the Javadoc (`/javadoc`) for UserInput to see how the coordinate system for the chess
board is represented in the input data.

# Requirements

1. The board should start in the standard chess starting state with all the 16 pieces
lined up for each player.
2. Play starts with player 1 (white) and on each valid move alternates to the other
player. On an invalid move the existing player stays in control.
3. The moves must be read in using the supplied UserInputFile class until there
are no more moves
4. All moves must have a piece on the starting square and either an opponent
piece or nothing on the destination square Anything else is invalid.
5. Validate the move according to the moves allowed by the piece on the starting
square:
   ◦ The king can move only 1 square but in any direction

   ◦ The bishop can move any number of squares but only diagonally

   ◦ The rook can move any number of squares but only horizontally or vertically

   ◦ The queen can move any number of squares horizontally, vertically or diagonally.

   ◦ The knight can move in an L shape with sides of 2 and 1 squares respectively. That is 8 different possible moves. Unlike other pieces it jumps over other pieces.

   ◦ The pawn can move one or two squares forward on its first move (when not taking an opponent piece)

   ◦ The pawn can move one square forward on subsequent moves (when not taking an opponent piece)

   ◦ The pawn can move one square forward diagonally if taking an opponent piece
6. After each successful move render the board in simple ASCII form. It is suggested that player 1 is represented by upper-case characters and player 2 by lower-case characters. The conventional characters to use here are: `Rook, kNight, Bishop, King, Queen, Pawn.`
7. If the destination square contains an opponent piece then that piece is removed from the board. Unless that piece is a King where rules around check apply (see later)
8. For pieces other than the knight disallow the move if there are any other pieces in the way between the start and end square.
9. If a move ends with a player’s king under attack that is `check`
10. A player cannot end their own move in check
11. If a player starts their move in check this should be displayed as `in check`

## Build the projects

Clean build the projects with all tests

```
./gradlew clean build
```

## Run projects

Run with default sample moves file:
```
./gradlew run
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
