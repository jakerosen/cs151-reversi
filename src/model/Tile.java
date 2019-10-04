package model;

/**
 * Models a Tile on a Reversi Board,
 * Keeps track of its own state
 */
public class Tile {
  Color color;
  int x;
  int y;

  /**
   * Creates a new, empty Tile
   */
  public Tile(int x, int y) {
    if (x < 0 || x > 7 || y < 0 || y > 7) {
      throw new IllegalArgumentException("x and y must be between [0,7]");
    }

    color = Color.EMPTY;
    this.x = x;
    this.y = y;
  }

  /**
   * @return x coord of this tile
   */
  public int getX() {
    return x;
  }

  /**
   * @return y coord of this tile
   */
  public int getY() {
    return x;
  }

  /**
   * Sets the state of the Tile
   * @param color The color to set the Tile to
   */
  public void setState(Color color) {
    this.color = color;
  }

  /**
   * Gets the state of this Tile
   * @return The color that this Tile is currently
   */
  public Color getState() {
    return color;
  }

  @Override
  public boolean equals(Object o) {
    if(o == this)
      return true;
    if(o == null || !(o instanceof Tile))
      return false;
    Tile t = (Tile) o;
    return t.color == this.color;
  }

  @Override
  public int hashCode() {
    if(this.color == Color.BLACK)
      return 1;
    if(this.color == Color.WHITE)
      return 2;
    return 0;
  }
}
