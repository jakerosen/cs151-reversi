package model;

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
	  Board board = Board.getBoard();
	  System.out.println(board);
	  LinkedList<int[]> moves = board.getLegalMoves(Color.BLACK);
	  for (int[] pos : moves) {
	    System.out.println("(" + pos[0] + ", " + pos[1] + ")");
	  }
	}
}
