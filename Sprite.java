import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Rectangle;

public abstract class Sprite
{

  int x;
  int y;
  BufferedImage image = null;
  int width;
  int height;

  public Sprite(String path, int _x, int _y)
  {
    x = _x;
    y = _y;
    try
    {
      image = ImageIO.read(new File(path));
    }
    catch(IOException e)
    {
      System.out.println(e);
      System.exit(1);
    }
    width = image.getWidth();
    height = image.getHeight();
  }

  // draw this
  void draw(Graphics g)
  {
    g.setColor(Color.BLACK);
    g.drawImage(image, x, y, null);
  }


}
