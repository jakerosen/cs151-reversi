package model;

/**
 * Includes all possible states of a board tile in Reversi:
 * A tile is either empty, contains a black piece, or contains a white piece.
 * (Also used to distinguish players, black and white)
 */
public enum Color {
  EMPTY, BLACK, WHITE;

  public static Color flipColor(Color color) {
    if (color == BLACK) {
      return WHITE;
    }

    if (color == WHITE) {
      return BLACK;
    }

    return EMPTY;
  }

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
