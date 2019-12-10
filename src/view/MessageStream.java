package view;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

/**
 * A stream to send messages to display.
 */
public class MessageStream extends OutputStream {
  private JTextArea text;

  /**
   * Constructs the message stream.
   * 
   * @param text The text field
   */
  public MessageStream(JTextArea text) {
    this.text = text;
  }

  /**
   * @param b Writes this byte to the stream.
   */
  @Override
  public void write(int b) throws IOException {
    // redirects data to the text area
    text.append(String.valueOf((char)b));
    // scrolls the text area to the end of data
    text.setCaretPosition(text.getDocument().getLength());
    // keeps the textArea up to date
    text.update(text.getGraphics());
  }

}
