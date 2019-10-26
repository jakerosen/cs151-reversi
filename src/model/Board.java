package model;

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
   * Returns a singleton Board with 64 Tiles
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
  public LinkedList<int[]> getLegalMoves(Color player) {
    LinkedList<Tile> currentPlayerPieces = new LinkedList<>();
    LinkedList<int[]> legalMoves = new LinkedList<>();
    if(player == Color.WHITE) {
      currentPlayerPieces = whites;
    }
    else if(player == Color.BLACK) {
      currentPlayerPieces = blacks;
    }
    for (Tile t : currentPlayerPieces) {
   	  for (int i = -1; i <= 1; i++) {
        for (int j = -1; j <= 1; j++) {
          if (i != 0 || j != 0) { //skip if both are zero
            int x = t.getX() + i;
            int y = t.getY() + j;
            if(x >= 0 && x <= 7 && y >= 0 && y <= 7) {
              Tile t1 = tiles[x][y];
              if (t1.isOppositeColor(t)) {
                while (x > 0 && x < 7 && y > 0 && y < 7 && t1.isOppositeColor(t)) {
                  t1 = tiles[x += i][y += j];
                }
                if (t1.getState() == Color.EMPTY) {
                  int[] position = {x, y};
                  if (!legalMoves.contains(position)) {
                    legalMoves.add(position);
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
   */
  public void playPiece(int x, int y, Color player) {
    boolean player1Turn = player == Color.BLACK;
    int playerNum = (player1Turn)? 1:2; //for later hacky stuff
    LinkedList<Tile> playerPieces = (player1Turn)? blacks:whites;
    LinkedList<Tile> otherPieces = (player1Turn)? whites:blacks;
    Tile t = tiles[x][y];
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (i != 0 || j != 0) {
          int x1 = t.getX() + i;
          int y1 = t.getY() + j;
          if(x1 >= 0 && x1 <= 7 && y1 >= 0 && y1 <= 7) {
            Tile t1 = tiles[x1][y1];
            if (2*t1.getStateNumeric()%3 == playerNum) {
              while (x1 > 0 && x1 < 7 && y1 > 0 && y1 < 7
                      && 2*t1.getStateNumeric()%3 == playerNum) {
                t1 = tiles[x1 += i][y1 += j];
              }
              if (t1.getState() == player) {
                while (x1 != x && y1 != y)
                {
                  (t1 = tiles[x1 -= i][y1 -= j]).setState(player);
                  otherPieces.remove(t1);
                  if (!playerPieces.contains(t1)) playerPieces.add(t1);
                }
              }
            }
          }
        }
      }
    }
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
