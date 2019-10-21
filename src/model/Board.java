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
    //Initial four pieces for Othello variant.
    board[3][3].setState(Color.WHITE);
    whites.add(board[3][3]);
    board[4][3].setState(Color.BLACK);
    blacks.add(board[4][3]);
    board[3][4].setState(Color.BLACK);
    blacks.add(board[3][4]);
    board[4][4].setState(Color.WHITE);
    whites.add(board[4][4]);
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
              Tile t1 = board[x][y];
              if (t1.getState() != player && t1.getState() != Color.EMPTY) {
                while (x > 0 && x < 7 && y > 0 && y < 7
                        && t1.getState() != player
                         && t1.getState() != Color.EMPTY) {
                  t1 = board[x += i][y += j];
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
   * @return Array containing Black Tiles, White Tiles, empty Tiles respectively
   */
  public int[] getScore() {
    return null;
  }
}
