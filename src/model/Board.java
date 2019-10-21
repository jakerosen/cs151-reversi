package model;

import java.util.LinkedList;

/**
 * Holds Tiles, Sets Tile state during and after turns,
 * Determines legal moves
 * Tallies score
 */
public class Board {
  private static Board board;
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
    board.reset();
  }

  /**
   * Returns a singleton Board with 64 Tiles
   * @return the Board
   */
  public Board getBoard() {
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
              if (2*t1.getStateNumeric()%3 == t.getStateNumeric()) {
                while (x > 0 && x < 7 && y > 0 && y < 7
                        && 2*t1.getStateNumeric()%3 == t.getStateNumeric()) {
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
   * @param location Which Tile to play on
   * @param player Which player to play for
   */
  public void playPiece(int location, Color player) {

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
}
