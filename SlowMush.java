
public class SlowMush extends Ingredient
{
  //an ingredient for decreasing run speed
  public SlowMush(String path, int _x, int _y, BackpackSlot _container)
  {
    super(path, _x, _y, _container);
    run_speed_nerf = -2;
  }
}
