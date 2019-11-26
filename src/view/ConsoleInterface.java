package view;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import model.Board;
import model.Color;
import model.Model;
import model.Position;
import model.Tile;

/**
 * An input and output strategy using the console.
 */
public class ConsoleInterface implements InputStrategy, OutputStrategy {
  private static Scanner input = new Scanner(System.in);
  private PrintStream out;

  /**
   * An input and output strategy using the console with the default System.out as the PrintStream to send messages to.
   */
  public ConsoleInterface() {
    out = System.out;
  }

  /**
   * An input and output strategy using the console.
   *
   * @param out The PrintStream to send messages to.
   */
  public ConsoleInterface(PrintStream out) {
    this.out = out;
  }

  /**
   * The current player selects a legal position to place a piece.  There must be a legal move to make, or this method
   * will throw an Exception.
   *
   * @param game The game model.
   * @return The chosen position to place a piece.
   */
  public Position selectPosition(Model game) {
    ArrayList<Position> legalPositions = new ArrayList<Position>(game.getCurrentMoves().keySet());
    Color turnPlayer = game.getTurnPlayer();

    if (legalPositions.isEmpty()) {
      throw new IllegalArgumentException("There must be a legal move to make");
    }
    
    PrintStream out = getStream();

    Position selectedPosition = null;
    while (selectedPosition == null) {
      out.print("Your Choice: ");
      int selection = 0;
      try {
        selection = Integer.parseInt(input.nextLine()) - 1;  // must shift menu index to array index
        selectedPosition = legalPositions.get(selection);
      } catch (NumberFormatException e) {
        out.println("Please enter one of the numbers in range");
      } catch (IndexOutOfBoundsException e) {
        out.println("Please enter one of the numbers in range");
      }
    }

    return selectedPosition;
  }

  /**
   * Displays the board.
   *
   * @param board The board.
   */
  public void displayBoard(Board board) {
    getStream().println(board);
  }
  
  /**
   * Updates the board according to the piece played. This method does not change the state of the board, it merely
   * displays changes that were made.
   * 
   * No op for console interface.
   * 
   * @param board The board
   * @param placedPiece The position of the piece that was just played
   * @param flippedPieces The pieces that were flipped during this turn.
   */
  @Override
  public void updateBoard(Board board, Position placedPiece, LinkedList<Tile> flippedPieces) {
    return; // No op for console interface; the whole board is displayed each turn.
  }
  
  public void displayLegalMoves(Model game) {
    ArrayList<Position> legalPositions = new ArrayList<Position>(game.getCurrentMoves().keySet());
    Color turnPlayer = game.getTurnPlayer();

    out.printf("%s's move\n", Color.toString(turnPlayer));
    out.println("Please select which position you would like to move to:");
    for (int i = 0; i < legalPositions.size(); i++) {
      // note that for ease of use for the user, the menu index starts count at 1, which makes it +1 shift from actual
      // array index
      out.printf("%d. %s\n", i + 1, legalPositions.get(i));
    }
  }
  
  /**
   * @return The PrintStream used to send messages to.
   */
  public PrintStream getStream() {
    return out;
  }
}
