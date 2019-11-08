package view;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import model.Color;
import javax.swing.*;

public class FlipAnimTester {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    final ReversiPiece piece1 = new ReversiPiece(0, 0, 248);
    final ReversiPiece piece2 = new ReversiPiece(25, 25, 200, Color.WHITE);
    TileIcon icon1 = new TileIcon(piece1, 250, 250);
    TileIcon icon2 = new TileIcon(piece2, 250, 250);
    final JLabel label1 = new JLabel(icon1);
    final JLabel label2 = new JLabel(icon2);
    frame.setLayout(new FlowLayout());
    frame.add(label1);
    frame.add(label2);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
    while (true) {
      ExecutorService es = Executors.newCachedThreadPool();
      es.execute(() -> piece1.flip(label1));
      es.execute(() -> piece2.flip(label2));
      es.shutdown();
      try {
        es.awaitTermination(1, TimeUnit.SECONDS);
      } catch (InterruptedException e) {
        System.err.println("error: thread interrupted");
      }
    }
  }
}
