
public abstract class Ingredient extends Sprite implements CanInteractWith
{

  BackpackSlot container;
  BrewerMenuSlot container2;
  double gravity_increase = 0;
  double gravity_reduction = 0;
  int run_speed_boost = 0;
  int run_speed_nerf = 0;

  public Ingredient(String path, int _x, int _y, BackpackSlot _container)
  {
    super(path, _x, _y);
    container = _container;
  }

  public void moveToBackpack(BackpackSlot empty_slot)
  {
    //fill new container (BackpackSlot)
    this.x = empty_slot.x;
    this.y = empty_slot.y;
    this.container = empty_slot;
    empty_slot.contained = this;
    empty_slot.is_empty = false;
  }

  public void moveToBrewerMenu(BrewerMenuSlot empty_slot)
  {
    //empty old container (BackpackSlot)
    this.container.contained = null;
    this.container.is_empty = true;
    //fill new container (BrewerMenuSlot)
    this.x = empty_slot.x;
    this.y = empty_slot.y;
    this.container2 = empty_slot;
    empty_slot.contained = this;
    empty_slot.is_empty = false;
  }
}
