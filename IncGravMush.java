
public class IncGravMush extends Ingredient
{
  //an ingredient for increasing pull of gravity
  public IncGravMush(String path, int _x, int _y, BackpackSlot _container)
  {
    super(path, _x, _y, _container);
    gravity_increase = 0.2;
  }
}
