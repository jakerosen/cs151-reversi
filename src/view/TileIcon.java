package view;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

import model.Tile;

/**
  An icon that contains a Reversi piece.
  */
public class TileIcon implements Icon {
  // private JLabel label;
  // This didn't really pan out -- needs some rethinking.

  private int width;
  private AbstractPiece piece;
  private Color backgroundColor;

  /**
   * Constructs an icon for this Tile with the given width.
   *
   * @param tile The tile
   * @param width The width
   */
  public TileIcon(Tile tile, int width) {
    model.Color color = tile.getState();
    this.width = width;
    piece = (color == model.Color.EMPTY)
      ? new EmptyPiece()
      : new ReversiPiece(width/20, width/20, width - width/10, color);
    backgroundColor = new Color(34, 139, 34);
  }

  /**
   * Constructs an icon for this Tile with the given width and given background
   * color
   *
   * @param tile The tile
   * @param width The width
   * @param backgroundColor The background color
   */
  public TileIcon(Tile tile, int width, Color backgroundColor) {
    model.Color color = tile.getState();
    this.width = width;
    piece = (color == model.Color.EMPTY)
      ? new EmptyPiece()
      : new ReversiPiece(width/20, width/20, width - width/10, color);
    this.backgroundColor = backgroundColor;
  }

  /**
   * @return The icon width
   */
  public int getIconWidth() {
    return width;
  }

  /**
   * @return The icon height (this will always be the same as width).
   */
  public int getIconHeight() {
    return width;
  }

  /**
   * Paints this tile.
   */
  public void paintIcon(Component c, Graphics g, int x, int y) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(backgroundColor);
    Rectangle2D.Double tile = new Rectangle2D.Double(0, 0, width, width);
    g2.fill(tile);

    piece.draw(g2);
  }

  /**
   * Place a piece on this tile (may be empty, which clears the tile).
   *
   * @param piece The piece to place.
   */
  public void placePiece(Tile piece) {
    model.Color color = piece.getState();
    this.piece = (color == model.Color.EMPTY)
      ? new EmptyPiece()
      : new ReversiPiece(width/20, width/20, width - width/10, piece.getState());
  }

  /**
   * Flip this piece with an animation (this takes about .5 seconds)
   *
   * @param comp The component that this Icon is in.
   */
  public void flip(JComponent comp) {
    piece.flip(comp);
  }

  /**
   * Flip this piece with no animation
   *
   * @param comp The component that this Icon is in.
   */
  public void fastFlip(JComponent comp) {
    piece.fastFlip(comp);
  }

  /**
   * Sets the background color for this tile.
   *
   * @param backgroundColor The background color.
   */
  public void setBackgroundColor(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
  }
}


