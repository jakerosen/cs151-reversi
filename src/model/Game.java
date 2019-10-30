package model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Runs the logic for Reversi,
 * Takes input,
 * Keeps track of turns and turn order,
 * Reports final score
 */
public class Game {
	/**
	 * Initializes the Game, runs the main loop
	 * @param args Unused
	 */
	public static void main(String[] args) {
	  consoleInterface();
	}
	
	/**
	 * Plays a game of Reversi
	 * 
	 * @param input The strategy to get user input for making a move
	 * @param output The strategy of displaying output
	 */
	public static void playGame(InputStrategy input, OutputStrategy output) {
	  PrintStream outStream = output.getStream();

	  Color turnPlayer = Color.BLACK;
	  boolean lastPlayerPassed = false;
	  Board board = Board.getBoard();
	  
	  while (true) {
	    output.displayBoard(board);
      HashMap<Position, LinkedList<Tile>> moves = board.getLegalMoves(turnPlayer);
      ArrayList<Position> legalPositions = new ArrayList<Position>(moves.keySet());
      
      if (legalPositions.size() > 0) {
        lastPlayerPassed = false;
        Position selectedPosition = input.selectPosition(turnPlayer, legalPositions);
        board.playPiece(selectedPosition, turnPlayer, moves);
        turnPlayer = Color.flipColor(turnPlayer);

      } else {
        if (lastPlayerPassed) {
          // both players cannot make a legal move, game over
          outStream.println("Neither player has any legal moves.  Game over.");

          // score
          int blackScore = board.getScoreOf(Color.BLACK);
          int whiteScore = board.getScoreOf(Color.WHITE);
          String winner = null;
          if (blackScore == whiteScore) {
            // tied score
            winner = "Tie game";
          } else {
            winner = (blackScore > whiteScore) ? "Black" : "White";
          } 

          outStream.printf("Black score: %d\nWhite score: %d\nWinner: %s\n", blackScore, whiteScore, winner);

          return; 
        }

        lastPlayerPassed = true;
        outStream.printf("%s has no legal moves.\n", Color.toString(turnPlayer));
        turnPlayer = Color.flipColor(turnPlayer);
      }
	  }
	}
	
	/**
	 * Plays a game of Reversi through the console.
	 */
	public static void consoleInterface() {
	  ConsoleInterface console = new ConsoleInterface(System.out);
	  playGame(console, console);
	}
	
}
