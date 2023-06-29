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
## Checkmate

- Check if the king is under attack
- Check if the king can move to any safe square
- Check if any friendly piece can block the attack
- Check if any piece can capture the attacking piece
