package model;

/**
 * Immutable position (x, y)
 * x is col, y is row
 *
 */
public final class Position {
  private int x; // col
  private int y; // row
  
  /**
   * x and y must be between [0, 7], or an exception will be thrown.
   * 
   * @param x Col
   * @param y Row
   */
  public Position(int x, int y) {
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
  
  /**
   * @param o The other Position
   * 
   * @return whether this Position is the same as o
   */
  @Override
  public boolean equals(Object o) {
    if(o == this)
      return true;
    if(o == null || !(o instanceof Position))
      return false;
    Position that = (Position) o;
    return this.x == that.x && this.y == that.y;
  }

  /**
   * @return Very simple hash of a Position
   */
  @Override
  public int hashCode() {
    return x * 8 + y;
  }
  
  /**
   * @return A string representation of this Position
   */
  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}
