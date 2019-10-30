package model;

import java.util.ArrayList;

/**
 * Defines a strategy for collecting input from the turn player to make a move.
 *
 */
public interface InputStrategy {
	/**
	 * The current player selects a legal position to place a piece.  There must be a legal move to make, or this method
	 * will throw an Exception.
	 * 
	 * @param turnPlayer The current player
	 * @param legalPositions Array of legal positions to place a piece.  Must be non-empty.
	 * @return The chosen position to place a piece.
	 */
  Position selectPosition(Color turnPlayer, ArrayList<Position> legalPositions);
}
