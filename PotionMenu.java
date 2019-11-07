
public class PotionMenu extends Sprite
{
  int x;
  int y;
  boolean is_empty = true;
  int slot_x;
  int slot_y;

  //this doubles as the slot, rather than having separate classes for a menu and its slots
  //may have been a good idea for the rest of the menus
  public PotionMenu(String path, int _x, int _y)
  {
    super(path, _x, _y);
    slot_x = _x + 21;
    slot_y = _y + 18;
  }
}
