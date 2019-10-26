package model;

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
	  Board board = Board.getBoard();
	  System.out.println(board);

	  HashMap<Position, LinkedList<Tile>> moves = board.getLegalMoves(Color.BLACK);
	  System.out.println(moves);
	  System.out.println();
	  

	  board.playPiece(3, 2, Color.BLACK, moves);
	  System.out.println(board);
	}
}
