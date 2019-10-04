package model;

/**
 * Represents a location on the Reversi board.
 */
public class Location {
  private int x;
  private int y;

  public Location(int x, int y) {
    if (x < 0 || x > 7 || y < 0 || y > 7) {
      throw new IllegalArgumentException("x and y must be between [0,7]");
    }

    this.x = x;
    this.y = y;
  }

  /**
   * @return x coord
   */
  public int getX() {
    return x;
  }

  /**
   * @return y coord
   */
  public int getY() {
    return y;
  }
}
