package view;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

public class MessageStream extends OutputStream {
  private JTextArea text;
  
  public MessageStream(JTextArea text) {
    this.text = text;
  }

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
