package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TileTest {

  @Test
  @SuppressWarnings("unlikely-arg-type")
  void tileTest() {
    Tile tile1 = new Tile(0,0);
    assertEquals(Color.EMPTY, tile1.getState());
    assertEquals(0, tile1.getStateNumeric());
    assertEquals(0, tile1.hashCode());

    tile1.setState(Color.BLACK);
    assertEquals(Color.BLACK, tile1.getState());
    assertEquals(1, tile1.getStateNumeric());
    assertEquals(64, tile1.hashCode());

    Tile tile2 = new Tile(0,0);
    tile2.setState(Color.WHITE);
    assertEquals(Color.WHITE, tile2.getState());
    assertEquals(2, tile2.getStateNumeric());
    assertEquals(128, tile2.hashCode());

    Tile tile3 = new Tile(0,0);
    assertTrue(tile3.equals(tile3));
    assertFalse(tile1.equals(tile2));
    assertFalse(tile1.equals(tile3));
    assertFalse(tile2.equals(tile1));
    assertFalse(tile2.equals(tile3));
    assertFalse(tile3.equals(tile1));
    assertFalse(tile3.equals(tile2));

    tile3.setState(Color.BLACK);
    assertTrue(tile3.equals(tile3));
    assertTrue(tile3.equals(tile1));
    assertTrue(tile1.equals(tile3));
    assertFalse(tile3.equals(tile2));
    assertFalse(tile2.equals(tile3));
    assertTrue(tile1.hashCode() == tile3.hashCode());

    tile3.setState(Color.WHITE);
    assertTrue(tile3.equals(tile3));
    assertTrue(tile3.equals(tile2));
    assertTrue(tile2.equals(tile3));
    assertFalse(tile3.equals(tile1));
    assertFalse(tile1.equals(tile3));
    assertTrue(tile2.hashCode() == tile3.hashCode());

    tile3.setState(Color.EMPTY);
    Tile tile4 = new Tile(0,3);
    assertEquals(0, tile4.getX());
    assertEquals(3, tile4.getY());
    assertEquals(3, tile4.hashCode());
    tile4.setState(Color.BLACK);
    assertEquals(67, tile4.hashCode());
    assertFalse(tile4.equals(tile1));
    assertFalse(tile1.equals(tile4));
    assertFalse(tile4.equals(tile2));
    assertFalse(tile2.equals(tile4));
    assertFalse(tile4.equals(tile3));
    assertFalse(tile3.equals(tile4));

    Tile tile5 = new Tile(3,0);
    assertEquals(3, tile5.getX());
    assertEquals(0, tile5.getY());
    assertEquals(24, tile5.hashCode());
    tile5.setState(Color.WHITE);
    assertEquals(152, tile5.hashCode());
    assertFalse(tile5.equals(tile2));
    assertFalse(tile2.equals(tile5));

    String notATile = null;
    assertFalse(tile1.equals(notATile));
    assertFalse(tile2.equals(notATile));
    assertFalse(tile3.equals(notATile));
    notATile = "Totally not a tile.";
    assertFalse(tile1.equals(notATile));
    assertFalse(tile2.equals(notATile));
    assertFalse(tile3.equals(notATile));

    Tile tile6 = new Tile(0,0);
    tile6.setState(Color.BLACK);
    Tile tile7 = new Tile(0,0);
    tile7.setState(Color.WHITE);
    assertTrue(tile6.isOppositeColor(tile7));
    assertTrue(tile7.isOppositeColor(tile6));
    assertFalse(tile7.isOppositeColor(tile7));
    assertFalse(tile6.isOppositeColor(tile6));
  }

}
