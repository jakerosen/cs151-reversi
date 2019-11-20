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
  
  public TileIcon(Tile tile, int width) {
    model.Color color = tile.getState();
    this.width = width;
    piece = (color == model.Color.EMPTY) ? new EmptyPiece() : new ReversiPiece(width/20, width/20, width - width/10, color);
    backgroundColor = new Color(34, 139, 34);
  }

  public TileIcon(Tile tile, int width, Color backgroundColor) {
    model.Color color = tile.getState();
    this.width = width;
    piece = (color == model.Color.EMPTY) ? new EmptyPiece() : new ReversiPiece(width/20, width/20, width - width/10, color);
    this.backgroundColor = backgroundColor;
  }
   
  public int getIconWidth() {
     return width;
  }

  public int getIconHeight() {
     return width;
  }

  public void paintIcon(Component c, Graphics g, int x, int y) { 
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(backgroundColor);
    Rectangle2D.Double tile = new Rectangle2D.Double(0, 0, width, width);
    g2.fill(tile);

    piece.draw(g2);
  }
  
  public void placePiece(Tile piece) {
    this.piece = new ReversiPiece(width/20, width/20, width - width/10, piece.getState());
  }
  
  public void flip(JComponent comp) {
    piece.flip(comp);
  }
  
  public void setBackgroundColor(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
  }
}


