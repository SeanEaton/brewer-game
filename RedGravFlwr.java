
public class RedGravFlwr extends Ingredient
{
  //an ingredient for decreasing pull of gravity
  public RedGravFlwr(String path, int _x, int _y, BackpackSlot _container)
  {
    super(path, _x, _y, _container);
    gravity_reduction = -0.2;
  }
}
