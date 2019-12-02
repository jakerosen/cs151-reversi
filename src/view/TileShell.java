package view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.concurrent.BlockingQueue;

import javax.swing.JButton;
import javax.swing.JComponent;

import model.Position;
import model.Tile;

/**
 * The shell of a tile, including the ability to click to place a piece there.
 */
public class TileShell extends JButton {
  private TileIcon tile;

  /**
   * Constructs the shell of this given Tile with a default forest green
   * background color (the normal color of a Reversi board).
   *
   * @param piece The piece occupying this tile (may be empty).
   * @param width The width of the tile shell.
   * @param messageQueue The queue to place the button clicked message in.
   */
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

  /**
   * Constructs the shell of this given Tile with the given background color.
   *
   * @param piece The piece occupying this tile (may be empty).
   * @param width The width of the tile shell.
   * @param messageQueue The queue to place the button clicked message in.
   */
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

  /**
   * Place a piece on this tile (may be empty, which will clear the tile).
   *
   * @param piece The piece to place.
   */
  public void placePiece(Tile piece) {
    tile.placePiece(piece);
    repaint();
  }

  /**
   * Flip this piece with an animation (this takes about .5 seconds)
   */
  public void flip() {
    tile.flip(this);
  }

  /**
   * Flip this piece with no animation.
   */
  public void fastFlip() {
    tile.fastFlip(this);
  }

  /**
   * Enable this tile to be clicked, and changes the background color to be
   * red to indicate that this is a legal position to place a piece.
   */
  public void enableTile() {
    setEnabled(true);
    tile.setBackgroundColor(Color.RED);
    repaint();
  }

  /**
   * Disable this tile so that it is no longer clickable and sets the background
   * color to a forest green color.
   */
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
