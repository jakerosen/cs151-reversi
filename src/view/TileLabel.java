package view;

import javax.swing.JLabel;

import model.Tile;

public class TileLabel extends JLabel {
  private TileIcon tile;
  
  public TileLabel(Tile piece, int width) {
    super();
    tile = new TileIcon(piece, width);
    setIcon(tile);
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
