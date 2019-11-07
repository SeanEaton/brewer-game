
public class BackpackSlot
{
  int x;
  int y;
  Ingredient contained;
  boolean is_empty;
  //object for an array of objects representing slots of the backpack
  public BackpackSlot(int _x, int _y, Ingredient _contained)
  {
    x = _x;
    y = _y;
    contained = _contained;
    if (this.contained != null)
    {
      is_empty = false;
    }
    else
    {
      is_empty = true;
    }
  }
}
