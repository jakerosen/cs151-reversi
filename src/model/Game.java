package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

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
	  playGame();
	}
	
	/**
	 * Plays a game of Reversi, currently through the console.
	 */
	public static void playGame() {
	  consoleInterface();
	}
	
	/**
	 * Plays a game of Reversi through the console.
	 */
	public static void consoleInterface() {
	  Scanner input = new Scanner(System.in); // Scanner only used for console version of game

	  Color turnPlayer = Color.BLACK;
	  boolean lastPlayerPassed = false;
	  Board board = Board.getBoard();
	  
	  while (true) {
      System.out.println(board);
      HashMap<Position, LinkedList<Tile>> moves = board.getLegalMoves(turnPlayer);
      ArrayList<Position> legalPositions = new ArrayList<Position>(moves.keySet());
      
      if (legalPositions.size() > 0) {
        lastPlayerPassed = false;
        Position selectedPosition = selectPosition(turnPlayer, legalPositions, input);
        board.playPiece(selectedPosition, turnPlayer, moves);
        turnPlayer = Color.flipColor(turnPlayer);

      } else {
        if (lastPlayerPassed) {
          // both players cannot make a legal move, game over
          System.out.println("Neither player has any legal moves.  Game over.");

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

          System.out.printf("Black score: %d\nWhite score: %d\nWinner: %s\n", blackScore, whiteScore, winner);

          input.close();
          return; 
        }

        lastPlayerPassed = true;
        System.out.printf("%s has no legal moves.\n", Color.toString(turnPlayer));
        turnPlayer = Color.flipColor(turnPlayer);
      }
	  }
	}
	
	/**
	 * The current player selects a legal position to place a piece.  There must be a legal move to make, or this method
	 * will throw an Exception.
	 * 
	 * @param turnPlayer The current player
	 * @param legalPositions Array of legal positions to place a piece.  Must be non-empty.
	 * @param input The input source.
	 * @return The chosen position to place a piece.
	 */
	private static Position selectPosition(Color turnPlayer, ArrayList<Position> legalPositions, Scanner input) {
	  if (legalPositions.isEmpty()) {
	    throw new IllegalArgumentException("There must be a legal move to make");
	  }

    System.out.printf("%s's move\n", Color.toString(turnPlayer));
    System.out.println("Please select which position you would like to move to:");
    for (int i = 0; i < legalPositions.size(); i++) {
      // note that for ease of use for the user, the menu index starts count at 1, which makes it +1 shift from actual
      // array index
      System.out.printf("%d. %s\n", i + 1, legalPositions.get(i));
    }
    
    Position selectedPosition = null;
    while (selectedPosition == null) {
      System.out.print("Your Choice: ");
      int selection = 0;
      try {
        selection = input.nextInt() - 1;  // must shift menu index to array index (see above note)
        selectedPosition = legalPositions.get(selection);
      } catch (InputMismatchException e) {
        // TODO: This doesn't actually successfully guard against non-numeric input right now; that's not high priority,
        // though
        System.out.println("Please enter one of the numbers in range");
      } catch (IndexOutOfBoundsException e) {
        System.out.println("Please enter one of the numbers in range");
      }
    }

	  return selectedPosition;
	}
}
