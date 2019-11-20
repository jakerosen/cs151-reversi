package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

import model.Tile;

public class TileShell extends JButton {
  private TileIcon tile;
  
  public TileShell(Tile piece, int width) {
    super();
    setPreferredSize(new Dimension(width, width));
    tile = new TileIcon(piece, width);
    setIcon(tile);
    setEnabled(false);
  }

  public TileShell(Tile piece, int width, Color backgroundColor) {
    super();
    setPreferredSize(new Dimension(width, width));
    tile = new TileIcon(piece, width, backgroundColor);
    setIcon(tile);
    setEnabled(false);
  }
  
  public void placePiece(Tile piece) {
    tile.placePiece(piece);
    repaint();
  }
  
  public void flip() {
    tile.flip(this);
  }

  /**
   * Not used
   */
  private static final long serialVersionUID = 1L;
}
