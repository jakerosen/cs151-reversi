package view;

import model.Color;
import java.awt.*;
import java.awt.geom.*;

import javax.swing.JButton;
import javax.swing.JComponent;

/**
 * A Reversi piece that is not empty.
 */
public class ReversiPiece extends AbstractPiece {
  private int x;
  private int y;
  private int width;
  private Color state;
  private int theta;
  private Area bounds;
  private Area leftHalf;

  /**
   * Constructs a ReversiPiece with the given width and default black color.
   *
   * @param x The x coord to anchor this piece
   * @param y The y coord to anchor this piece
   * @param width The width of the bounding box
   */
  public ReversiPiece(int x, int y, int width) {
    this(x, y, width, Color.BLACK);
  }

  /**
   * Constructs a ReversiPiece with the given width and given color.
   *
   * @param x The x coord to anchor this piece
   * @param y The y coord to anchor this piece
   * @param width The width of the bounding box
   * @param state The color of the piece
   */
  public ReversiPiece(int x, int y, int width, Color state) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.state = state;
    theta = 180;
    bounds = new Area(new Rectangle2D.Double(x, y, width, width));
    leftHalf = new Area(new Rectangle2D.Double(x, y, width / 2., width));
  }

  /**
   * Sets the angle of this circle (used for animation).
   *
   * @param theta The angle.
   */
  private void setTheta(int theta) {
    this.theta = theta;
  }

  /**
   * Draws this piece.
   */
  public void draw(Graphics2D g2) {
    double xWidthOuter = (Math.abs(.6 * Math.cos(Math.toRadians(theta))) + .4) * width;
    double xWidthInner = Math.abs(Math.cos(Math.toRadians(theta))) * width;
    Ellipse2D.Double outer = new Ellipse2D.Double(x + (width - xWidthOuter)/2, y, xWidthOuter, width);
    Ellipse2D.Double inner = new Ellipse2D.Double(x + (width - xWidthInner)/2, y, xWidthInner, width);
    g2.setStroke(new BasicStroke(2));
    g2.setColor(java.awt.Color.BLACK);
    g2.draw(outer);
    Area black = setBlackArea(inner);
    g2.setClip(black);
    g2.fill(outer);
    black.exclusiveOr(bounds);
    g2.setColor(java.awt.Color.WHITE);
    g2.setClip(black);
    g2.fill(outer);
  }

  /**
   * Flip this piece with an animation (this takes about .5 seconds)
   *
   * @param comp The component that this Icon is in.
   */
  public void flip(JComponent comp) {
    state = (state == Color.BLACK) ? Color.WHITE : Color.BLACK;
    //if (state == Color.BLACK) state = Color.WHITE;
    //else state = Color.BLACK;

    for (int theta = 3; theta <= 180; theta += 3) {
      setTheta(theta);
      comp.repaint();
      try {
        Thread.sleep(8);
      } catch (InterruptedException e) {
        System.err.println("error: thread interrupted");
      }
      /*
      */
    }
  }

  /**
   * Flip this piece with no animation
   *
   * @param comp The component that this Icon is in.
   */
  public void fastFlip(JComponent comp) {
    state = (state == Color.BLACK) ? Color.WHITE : Color.BLACK;
    //if (state == Color.BLACK) state = Color.WHITE;
    //else state = Color.BLACK;

    for (int theta = 3; theta <= 180; theta += 3) {
      setTheta(theta);
      comp.repaint();
      /*
         try {
         Thread.sleep(16);
         } catch (InterruptedException e) {
         System.err.println("error: thread interrupted");
         }
         */
    }
  }

  /**
   * Used for animation
   */
  private Area setBlackArea(Ellipse2D.Double inner) {
    Area black = new Area(inner);
    if (theta <= 90) {
      black.exclusiveOr(bounds);
      black.intersect(leftHalf);
    }
    else {
      black.add(leftHalf);
    }
    if (state == Color.WHITE) black.exclusiveOr(bounds);
    return black;
  }
}
