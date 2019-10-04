package model;

import java.util.LinkedList;

/**
 * Holds Tiles, Sets Tile state during and after turns,
 * Determines legal moves
 * Tallies score
 */
public class Board {
  Tile[][] board;
  LinkedList<Tile> blacks;
  LinkedList<Tile> whites;

  /**
   * Initializes a Board with 64 Tiles
   */
  public Board() {
    int n = 8; // 8 by 8 board, 64 tiles total.
    board = new Tile[n][n];
    blacks = new LinkedList<Tile>();
    whites = new LinkedList<Tile>();
    for (int i = 0; i < n; i++) {
      board[i] = new Tile[n];
      for (int j = 0; i < n; i++) {
        board[i][j] = new Tile(i, j);
      }
    }
  }

  /**
   * Gets legal moves for current player
   * @param player The player to get legal moves for
   * @return Array with legal moves positions
   */
  public int[] getLegalMoves(Color player) {
    return null;
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
   * @return Array containing Black Tiles, White Tiles, empty Tiles respectively
   */
  public int[] getScore() {
    return null;
  }
}
