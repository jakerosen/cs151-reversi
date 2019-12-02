package view;

import model.Position;

/**
 * Sends a message to place a piece to the Controller.
 */
public class PlacePieceMessage implements Message {
  private Position pos;

  /**
   * Send a message to place a piece on this position
   *
   * @param x The x coord
   * @param y The y coord
   */
  public PlacePieceMessage(int x, int y) {
    this(new Position(x, y));
  }

  /**
   * Send a message to place a piece on this position
   *
   * @param pos The position
   */
  public PlacePieceMessage(Position pos) {
    this.pos = pos;
  }

  /**
   * @return The position of the piece to place.
   */
  public Position getPos() {
    return pos;
  }
}
