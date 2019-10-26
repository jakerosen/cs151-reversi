package model;

/**
 * Models a Tile on a Reversi Board,
 * Keeps track of its own state
 */
public class Tile {
  private Color color;
  private int x;
  private int y;

  /**
   * Creates a new, empty Tile
   * @param x coord of this Tile
   * @param y coord of this Tile
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
   * @return x x coord of this Tile
   */
  public int getX() {
    return x;
  }

  /**
   * @return y y coord of this Tile
   */
  public int getY() {
    return y;
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
  
  /**
   * Gets a numeric representation of the state of this Tile
   * @return 0 for empty Tile, 1 for Black Tile, 2 for White Tile
   */
  public int getStateNumeric() {
    if (color == Color.WHITE) return 2;
    if (color == Color.BLACK) return 1;
    return 0;
  }
  
  /**
   * Checks if this tile is the opposite color of param tile.  Vacuously true if both are empty.
   * 
   * @param that The other tile
   * @return Whether this tile is the opposite of param tile.  Vacuously true if both are empty.
   */
  public boolean isOppositeColor(Tile that) {
    return (2 * this.getStateNumeric()) % 3 == that.getStateNumeric();
  }

  /**
   * @param o The other tile
   * @return Whether this tile has the same Color as o
   */
  @Override
  public boolean equals(Object o) {
    if(o == this)
      return true;
    if(o == null || !(o instanceof Tile))
      return false;
    Tile t = (Tile) o;
    return t.color == this.color && t.x == this.x && t.y == this.y;
  }

  /**
   * @return The hash code of this Tile
   */
  @Override
  public int hashCode() {
    return (getStateNumeric() * 64) + (x * 8) + y;
  }
  
  /**
   * @return A string representation of this tile's state.
   */
  @Override
  public String toString() {
    if (color == Color.WHITE) return "W";
    if (color == Color.BLACK) return "B";
    return "-";
  }
}
