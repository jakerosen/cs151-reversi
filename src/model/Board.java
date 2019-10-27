package model;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Holds Tiles, Sets Tile state during and after turns,
 * Determines legal moves
 * Tallies score
 */
public class Board {
  private static Board board = new Board();
  private Tile[][] tiles;
  private LinkedList<Tile> blacks;
  private LinkedList<Tile> whites;

  /**
   * Initializes the singleton Board.
   */
  private Board() {
    int n = 8; // 8 by 8 board, 64 tiles total.
    tiles = new Tile[n][n];
    for (int i = 0; i < n; i++) {
      tiles[i] = new Tile[n];
      for (int j = 0; j < n; j++) {
        tiles[i][j] = new Tile(i, j);
      }
    }
    reset();
  }

  /**
   * Returns the singleton Board with 64 Tiles
   * @return the Board
   */
  public static Board getBoard() {
    if (board == null)
      board = new Board();
    return board;
  }

  /**
   * Resets the Board, setting all Tiles except the middle four empty,
   * setting the middle four Tiles as specified by Othello variant,
   * and emptying the player lists except for the initial Tiles.
   */
  public void reset() {
    whites = new LinkedList<Tile>();
    blacks = new LinkedList<Tile>();
    for (Tile[] column : tiles) {
      for (Tile t : column) {
        if ((t.getX() == 3 && t.getY() == 3) || (t.getX() == 4 && t.getY() == 4)) {
          t.setState(Color.WHITE);
          whites.add(t);
        }
        else if ((t.getX() == 3 && t.getY() == 4) || (t.getX() == 4 && t.getY() == 3)) {
          t.setState(Color.BLACK);
          blacks.add(t);
        }
        else t.setState(Color.EMPTY);
      }
    }
  }

  /**
   * Gets legal moves for current player
   * @param player The player to get legal moves for
   * @return LinkedList with legal moves positions in format [x, y]
   */
  public HashMap<Position, LinkedList<Tile>> getLegalMoves(Color player) {
    // TODO: There is a bug with this function right now.  You should not be able to move to an adjacent square without
    // hopping over an enemy piece, but it seems that you can do that diagonally, currently.
    // Example: from beginning of game, select (3, 2) as the move for Black.  Notice that it says (5, 5) is a legal move
    // for White, but it clearly shouldn't be.
    LinkedList<Tile> currentPlayerPieces;
    if(player == Color.WHITE) {
      currentPlayerPieces = whites;
    } else {
      currentPlayerPieces = blacks;
    }

    HashMap<Position, LinkedList<Tile>> legalMoves = new HashMap<>();
    for (Tile t : currentPlayerPieces) {
   	  for (int i = -1; i <= 1; i++) {
        for (int j = -1; j <= 1; j++) {
          if (i != 0 || j != 0) { //skip if both are zero
            int x = t.getX() + i;
            int y = t.getY() + j;
            if(x >= 0 && x <= 7 && y >= 0 && y <= 7) {
              Tile t1 = tiles[x][y];
              if (t1.isOppositeColor(t)) {
                LinkedList<Tile> path = new LinkedList<>();
                while (x > 0 && x < 7 && y > 0 && y < 7 && t1.isOppositeColor(t)) {
                  path.add(t1);
                  t1 = tiles[x += i][y += j];
                }
                if (t1.getState() == Color.EMPTY) {
                  Position pos = new Position(x, y);
                  if (legalMoves.containsKey(pos)) {
                    // if there is already a path to this position, merge the lists of paths
                    LinkedList<Tile> existingPath = legalMoves.get(pos);
                    existingPath.addAll(path);
                  } else {
                    legalMoves.put(pos, path);
                  }
                }
              }
            }
          }
        }
      }
    }
    return legalMoves;
  }

  /**
   * Places a piece on the Tile in the given location of the color of the given player,
   * and flips co-linear Tiles accordingly
   * @param x x coord of Tile to play on
   * @param y y coord of Tile to play on
   * @param player Which player to play for
   * @param legalMoves The map of legal moves and path of pieces to each move (i.e. return value of getLegalMoves)
   */
  public void playPiece(int x, int y, Color player, HashMap<Position, LinkedList<Tile>> legalMoves) {
    Position pos = new Position(x, y);
    playPiece(pos, player, legalMoves);
  }

  /**
   * Places a piece on the Tile in the given location of the color of the given player,
   * and flips co-linear Tiles accordingly
   * @param pos The position of the Tile to play on
   * @param player Which player to play for
   * @param legalMoves The map of legal moves and path of pieces to each move (i.e. return value of getLegalMoves)
   */
  public void playPiece(Position pos, Color player, HashMap<Position, LinkedList<Tile>> legalMoves) {
    if (!legalMoves.containsKey(pos)) {
      throw new IllegalArgumentException("Position " + pos + " is not a legal move.");
    }

    tiles[pos.getX()][pos.getY()].setState(player);
    legalMoves.get(pos).forEach(tile -> tile.setState(player));
  }

  /**
   * Tallies the score of the Board
   * @return Array containing Empty Tiles, Black Tiles, White Tiles respectively
   */
  public int[] getScore() {
    int[] scores = new int[3];
    for (Tile[] column : tiles) {
      for (Tile t : column) {
        scores[t.getStateNumeric()]++;
      }
    }
    return scores;
  }
  
  /**
   * 
   */
  public static String displayLegalMoves(HashMap<Position, LinkedList<Tile>> legalMoves) {
    return "";
  }
  
  /**
   * @return A string representation of this board.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("  0 1 2 3 4 5 6 7\n");
    for (int i = 0; i < tiles.length; i++) {
      Tile[] row = tiles[i];
      sb.append(i);
      for (Tile tile : row) {
        sb.append(' ').append(tile);
      }
      sb.append('\n');
    }
    return sb.toString();
  }
}
