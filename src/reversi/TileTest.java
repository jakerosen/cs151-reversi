package reversi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TileTest {

	@Test
	@SuppressWarnings("unlikely-arg-type")
	void tileTest() {
		Tile tile1 = new Tile();
		assertEquals(Color.EMPTY, tile1.getState());
		assertEquals(0, tile1.hashCode());
		
		tile1.setState(Color.BLACK);
		assertEquals(Color.BLACK, tile1.getState());
		assertEquals(1, tile1.hashCode());
		
		Tile tile2 = new Tile();
		tile2.setState(Color.WHITE);
		assertEquals(Color.WHITE, tile2.getState());
		assertEquals(2, tile2.hashCode());
		
		Tile tile3 = new Tile();
		assertTrue(tile3.equals(tile3));
		assertFalse(tile1.equals(tile2));
		assertFalse(tile1.equals(tile3));
		assertFalse(tile2.equals(tile1));
		assertFalse(tile2.equals(tile3));
		assertFalse(tile3.equals(tile1));
		assertFalse(tile3.equals(tile2));
		assertFalse(tile1.hashCode() == tile2.hashCode());
		assertFalse(tile1.hashCode() == tile3.hashCode());
		assertFalse(tile2.hashCode() == tile3.hashCode());
		
		tile3.setState(Color.BLACK);
		assertTrue(tile3.equals(tile3));
		assertTrue(tile3.equals(tile1));
		assertTrue(tile1.equals(tile3));
		assertFalse(tile3.equals(tile2));
		assertFalse(tile2.equals(tile3));
		assertTrue(tile1.hashCode() == tile3.hashCode());
		assertFalse(tile2.hashCode() == tile3.hashCode());
		
		tile3.setState(Color.WHITE);
		assertTrue(tile3.equals(tile3));
		assertTrue(tile3.equals(tile2));
		assertTrue(tile2.equals(tile3));
		assertFalse(tile3.equals(tile1));
		assertFalse(tile1.equals(tile3));
		assertTrue(tile2.hashCode() == tile3.hashCode());
		assertFalse(tile1.hashCode() == tile3.hashCode());
		
		tile3.setState(Color.EMPTY);
		String notATile = null;
		assertFalse(tile1.equals(notATile));
		assertFalse(tile2.equals(notATile));
		assertFalse(tile3.equals(notATile));
		notATile = "Totally not a tile.";
		assertFalse(tile1.equals(notATile));
		assertFalse(tile2.equals(notATile));
		assertFalse(tile3.equals(notATile));
	}

}
