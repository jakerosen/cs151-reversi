package view;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import model.Board;
import model.Color;
import model.Tile;

import javax.swing.*;

public class FlipAnimTester {
  public static void main(String[] args) {
    /*
    JFrame frame = new JFrame();
    Tile t1 = new Tile(0, 0);
    Tile t2 = new Tile(0, 0);
    t1.setState(Color.BLACK);
    t2.setState(Color.WHITE);
    //final ReversiPiece piece1 = new ReversiPiece(0, 0, 248);
    //final ReversiPiece piece2 = new ReversiPiece(25, 25, 200, Color.WHITE);
    //TileIcon icon1 = new TileIcon(piece1, 250, 250);
    //TileIcon icon2 = new TileIcon(piece2, 250, 250);
    //TileIcon icon1 = new TileIcon(t1, 250);
    //TileIcon icon2 = new TileIcon(t2, 250);
    //final JLabel label1 = new JLabel(icon1);
    //final JLabel label2 = new JLabel(icon2);
    TileShell label1 = new TileShell(t1, 250);
    TileShell label2 = new TileShell(t2, 250);
    frame.setLayout(new FlowLayout());
    frame.add(label1);
    frame.add(label2);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
    while (true) {
      ExecutorService es = Executors.newCachedThreadPool();
      es.execute(() -> label1.flip());
      es.execute(() -> label2.flip());
      es.shutdown();
      try {
        es.awaitTermination(1, TimeUnit.SECONDS);
      } catch (InterruptedException e) {
        System.err.println("error: thread interrupted");
      }
    }
    */
    
    testGUI();
  }
  
  public static void testGUI() {
    Board board = Board.getBoard();
    GUInterface gui = new GUInterface();
    gui.displayBoard(board);
  }
}
