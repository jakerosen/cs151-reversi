package view;

import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JComponent;

/**
 * A Reversi piece, which may be empty
 */
public abstract class AbstractPiece {
  /**
   * Draws this piece.
   */
  public abstract void draw(Graphics2D g2);

  /**
   * Flip this piece with an animation (this takes about .5 seconds)
   *
   * @param comp The component that this Icon is in.
   */
  public abstract void flip(JComponent comp);

  /**
   * Flip this piece with no animation.
   *
   * @param comp The component that this Icon is in.
   */
  public abstract void fastFlip(JComponent comp);
}
