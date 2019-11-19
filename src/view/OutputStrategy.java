package view;

import java.io.PrintStream;
import java.util.LinkedList;

import model.Board;
import model.Position;
import model.Tile;

/**
 * Defines a strategy for displaying output to the user.  An output strategy must be able to display the board and also
 * be able to display PrintStream messages to the user.
 */
public abstract class OutputStrategy {
  private PrintStream out;

  /**
   * An output strategy that sends PrintStream messages to the given PrintStream.
   *
   * @param out The PrintStream to send messages to.
   */
  public OutputStrategy(PrintStream out) {
    this.out = out;
  }

  /**
   * Displays the board.
   *
   * @param board The board.
   */
  public abstract void displayBoard(Board board);
  
  /**
   * Updates the board according to the piece played. This method does not change the state of the board, it merely
   * displays changes that were made.
   * 
   * @param board The board
   * @param placedPiece The position of the piece that was just played
   * @param flippedPieces The pieces that were flipped during this turn.
   */
  public abstract void updateBoard(Board board, Position placedPiece, LinkedList<Tile> flippedPieces);

  /**
   * @return The PrintStream used to send messages to.
   */
  public PrintStream getStream() {
    return out;
  }
}
