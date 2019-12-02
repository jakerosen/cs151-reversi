package view;

import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JComponent;

/**
 * An empty Reversi Piece.
 */
public class EmptyPiece extends AbstractPiece {
  /**
   * Draws this piece. This has no effect.
   */
  public void draw(Graphics2D g2) {
  }

  /**
   * Flip this piece with an animation (this takes about .5 seconds). This has
   * no effect.
   *
   * @param comp The component that this Icon is in.
   */
  public void flip(JComponent comp) {
  }

  /**
   * Flip this piece with no animation. This has no effect.
   *
   * @param comp The component that this Icon is in.
   */
  public void fastFlip(JComponent comp) {
  }
}
