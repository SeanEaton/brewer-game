
public class BrewerMenuSelector extends Sprite implements CanInteract
{
  //only if this is full can mixIngredients be called
  Ingredient[] overlapees = new Ingredient[3];

  public BrewerMenuSelector(String path, int _x, int _y)
  {
    super(path, _x, _y);
  }

  public void pickIngredient(Ingredient _ingredient, BackpackSlot empty_slot)
  {
    //...
  }

  public void selectIngredient(Ingredient _ingredient, BrewerMenuSlot empty_slot)
  {
    //...
  }

  public Potion mixIngredients(Ingredient _ingredient, Ingredient _ingredient2, Ingredient _ingredient3, BrewerMenuSlot[] _brewermenu_slots, PotionMenu _potionmenu)
  {
    //create new potion stats from ingredient stats
    Potion new_potion = new Potion("potion.png", _potionmenu.slot_x, _potionmenu.slot_y, _ingredient, _ingredient2, _ingredient3);
    //remove sprites from game (actually just move off-screen, as I can't figure out how to remove)
    _ingredient.y = 1000;
    _ingredient2.y = 1000;
    _ingredient3.y = 1000;
    //output potion, making it available for drawing
    _potionmenu.is_empty = false;
    //empty brewermenu_slots array
    for (BrewerMenuSlot brewermenu_slot : _brewermenu_slots)
    {
      brewermenu_slot.contained = null;
      brewermenu_slot.is_empty = true;
    }
    return new_potion;
  }

  public void drinkPotion(Potion _potion, Character character, PotionMenu _potionmenu)
  {
    //...
  }
}
