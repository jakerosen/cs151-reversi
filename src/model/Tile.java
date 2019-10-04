package model;

/**
 * Models a Tile on a Reversi Board,
 * Keeps track of its own state
 */
public class Tile {
  Color color;
  Location location;

  /**
   * Creates a new, empty Tile
   */
  public Tile(Location location) {
    color = Color.EMPTY;
    this.location = location;
  }
  
  /**
   * Creates a new, empty Tile
   */
  public Tile(int x, int y) {
    this.location = new Location(x, y);
  }
  
  /**
   * Creates a colored Tile
   */
  public Tile(Location location, Color color) {
    this.color = color;
    this.location = location;
  }

  /**
   * Creates a colored Tile
   */
  public Tile(int x, int y, Color color) {
    this.color = color;
    this.location = new Location(x, y);
  }

  /**
   * @return x coord of this tile
   */
  public int getX() {
    return location.getX();
  }

  /**
   * @return y coord of this tile
   */
  public int getY() {
    return location.getY();
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
