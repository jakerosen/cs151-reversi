package model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInterface extends OutputStrategy implements InputStrategy {
  private static Scanner input = new Scanner(System.in);

  public ConsoleInterface(PrintStream out) {
    super(out);
  }
  
	/**
	 * The current player selects a legal position to place a piece.  There must be a legal move to make, or this method
	 * will throw an Exception.
	 * 
	 * @param turnPlayer The current player
	 * @param legalPositions Array of legal positions to place a piece.  Must be non-empty.
	 * @return The chosen position to place a piece.
	 */
	public Position selectPosition(Color turnPlayer, ArrayList<Position> legalPositions) {
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
  
  public void displayBoard(Board board) {
    System.out.println(board);
  }
}
