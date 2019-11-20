package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import model.Color;
import model.Game;
import model.Position;
import model.Tile;

/**
 * Defines a strategy for collecting input from the turn player to make a move.
 *
 */
public interface InputStrategy {
  /**
   * The current player selects a legal position to place a piece.  There must be a legal move to make, or this method
   * will throw an Exception.
   *
   * @param game The game model.
   * @return The chosen position to place a piece.
   */
  Position selectPosition(Game game);
}
