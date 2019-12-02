package model;

/**
 * Includes all possible states of a board tile in Reversi:
 * A tile is either empty, contains a black piece, or contains a white piece.
 * (Also used to distinguish players, black and white)
 */
public enum Color {
  EMPTY, BLACK, WHITE;

  /**
   * @param color The color
   * @return The other color.
   */
  public static Color flipColor(Color color) {
    if (color == BLACK) {
      return WHITE;
    }

    if (color == WHITE) {
      return BLACK;
    }

    return EMPTY;
  }

  /**
   * @param color The color
   * @return a String representation of the color
   */
  public static String toString(Color color) {
    if (color == BLACK) {
      return "Black";
    }

    if (color == WHITE) {
      return "White";
    }

    return "EMPTY";
  }
}
