
public class Backpack extends Sprite
{

  int[][] slots;

  public Backpack(String path, int _x, int _y)
  {
    super(path, _x, _y);
    slots = new int[10][2];

    //slot 1 co-ordinates (x, y)
    slots[0][0] = 46;
    slots[0][1] = 651;
    //slot 2
    slots[1][0] = 94;
    slots[1][1] = 651;
    //slot 3
    slots[2][0] = 142;
    slots[2][1] = 651;
    //slot 4
    slots[3][0] = 190;
    slots[3][1] = 651;
    //slot 5
    slots[4][0] = 238;
    slots[4][1] = 651;
    //slot 6
    slots[5][0] = 286;
    slots[5][1] = 651;
    //slot 7
    slots[6][0] = 334;
    slots[6][1] = 651;
    //slot 8
    slots[7][0] = 382;
    slots[7][1] = 651;
    //slot 9
    slots[8][0] = 430;
    slots[8][1] = 651;
    //slot 10
    slots[9][0] = 478;
    slots[9][1] = 651;
  }


}
