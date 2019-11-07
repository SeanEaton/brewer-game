import java.awt.Component;
import java.awt.event.*;

// makes handling input a lot simpler
public class InputHandler implements KeyListener
{

  private boolean[] keys;


  // assigns the newly created InputHandler to a Component
  // @param c Component to get input from
  public InputHandler(Component c)
  {
    keys = new boolean[256];
    c.addKeyListener(this);
  }

  // checks whether a specific key is down
  // input keyCode = the key to check
  // outputs whether the key is pressed or not
  public boolean isKeyDown(int keyCode)
  {
    if (keyCode > 0 && keyCode < 256)
    {
      return keys[keyCode];
    }

    return false;
  }




  // called when a key is pressed while the component is focused
  // input = KeyEvent sent by the component
  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() > 0 && e.getKeyCode() < 256)
    {
      keys[e.getKeyCode()] = true;
    }
  }


  // called when a key is released while the component is focused
  // input = KeyEvent sent by the component
  public void keyReleased(KeyEvent e)
  {
    if (e.getKeyCode() > 0 && e.getKeyCode() < 256)
    {
      keys[e.getKeyCode()] = false;
    }
  }

  //not used
  public void keyTyped(KeyEvent e)
  {
    //...
  }
}
