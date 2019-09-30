package reversi;

import junit.framework.TestCase;

import org.junit.jupiter.api.Test;

class ColorTest extends TestCase {
  public void testEqual() {
    Color empty = Color.EMPTY;
    Color black = Color.BLACK;
    Color white = Color.WHITE;

    assertTrue(Color.EMPTY == empty);
    assertTrue(Color.BLACK == black);
    assertTrue(Color.WHITE == white);
  }

  public void testNotEqual() {
    Color empty = Color.EMPTY;
    Color black = Color.BLACK;
    Color white = Color.WHITE;

    assertFalse(empty == black);
    assertFalse(empty == white);
    assertFalse(Color.EMPTY == black);
    assertFalse(Color.EMPTY == white);

    assertFalse(black == empty);
    assertFalse(black == white);
    assertFalse(Color.BLACK == empty);
    assertFalse(Color.BLACK == white);

    assertFalse(white == empty);
    assertFalse(white == black);
    assertFalse(Color.WHITE == empty);
    assertFalse(Color.WHITE == black);
  }

  @Test
  void test() {
    testEqual();
    testNotEqual();
  }
}
