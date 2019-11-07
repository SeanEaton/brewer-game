//to be implemented by things which will be moved (ingredients)
public interface CanInteractWith
{
  void moveToBackpack(BackpackSlot empty_slot);

  void moveToBrewerMenu(BrewerMenuSlot empty_slot);
}
