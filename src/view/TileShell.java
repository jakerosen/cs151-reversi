package view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.concurrent.BlockingQueue;

import javax.swing.JButton;
import javax.swing.JComponent;

import model.Position;
import model.Tile;

public class TileShell extends JButton {
  private TileIcon tile;
  
  public TileShell(Tile piece, int width, BlockingQueue<Message> messageQueue) {
    super();
    setPreferredSize(new Dimension(width, width));
    tile = new TileIcon(piece, width);
    setIcon(tile);
    setEnabled(false);
    Position pos = piece.getPosition();
    addActionListener(e -> {
      try {
        messageQueue.put(new PlacePieceMessage(pos));
      } catch (InterruptedException ex) {
        System.err.println("error: thread interrupted");
      }
    });
  }

  public TileShell(Tile piece, int width, Color backgroundColor, BlockingQueue<Message> messageQueue) {
    super();
    setPreferredSize(new Dimension(width, width));
    tile = new TileIcon(piece, width, backgroundColor);
    setIcon(tile);
    setEnabled(false);
    Position pos = piece.getPosition();
    addActionListener(e -> {
      try {
        messageQueue.put(new PlacePieceMessage(pos));
      } catch (InterruptedException ex) {
        System.err.println("error: thread interrupted");
      }
    });
  }
  
  public void placePiece(Tile piece) {
    tile.placePiece(piece);
    repaint();
  }
  
  public void flip() {
    tile.flip(this);
  }
  
  public void enableTile() {
    setEnabled(true);
    tile.setBackgroundColor(Color.RED);
    repaint();
  }

  public void disableTile() {
    setEnabled(false);
    tile.setBackgroundColor(new Color(34, 139, 34));
    repaint();
  }

  /**
   * Not used
   */
  private static final long serialVersionUID = 1L;
}
