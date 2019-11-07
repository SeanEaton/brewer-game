
public class BrewerMenu extends Sprite
{
  int[][] slots;

  public BrewerMenu(String path, int _x, int _y)
  {
    super(path, _x, _y);
    slots = new int[3][2];

    //slot 1 co-ordinates (x, y) //increases by 48
    slots[0][0] = 581;
    slots[0][1] = 651;
    //slot 2
    slots[1][0] = 629;
    slots[1][1] = 651;
    //slot 3
    slots[2][0] = 677;
    slots[2][1] = 651;
  }
}
