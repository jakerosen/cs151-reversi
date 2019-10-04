package model;

/**
 * Models a Tile on a Reversi Board,
 * Keeps track of its own state
 */
public class Tile
{
	Color color;
	
	/**
	 * Creates a new, empty Tile
	 */
	public Tile()
	{
		color = Color.EMPTY;
	}
	
	/**
	 * Sets the state of the Tile
	 * @param color The color to set the Tile to
	 */
	public void setState(Color color)
	{
		this.color = color;
	}
	
	/**
	 * Gets the state of this Tile
	 * @return The color that this Tile is currently
	 */
	public Color getState()
	{
		return color;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		if(o == null || !(o instanceof Tile))
			return false;
		Tile t = (Tile) o;
		return t.color == this.color;
	}
	
	@Override
	public int hashCode()
	{
		if(this.color == Color.BLACK)
			return 1;
		if(this.color == Color.WHITE)
			return 2;
		return 0;
	}
}
