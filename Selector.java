
public class Selector extends Sprite implements CanInteract
{
  Ingredient overlapee;

  public Selector(String path, int _x, int _y)
  {
    super(path, _x, _y);
  }

  public void scrollRight()
  {
    if (this.x >= 432)
    {
      this.x -= 432;
    }
    else
    {
      this.x += 48;
    }
  }

  public void scrollLeft()
  {
    if (this.x <= 37)
    {
      this.x += 432;
    }
    else
    {
    this.x -= 48;
    }
  }


  public void pickIngredient(Ingredient _ingredient, BackpackSlot empty_slot)
  {
    //...
  }

  //take inputted ingredient, it's destination, and execute movement
  public void selectIngredient(Ingredient _ingredient, BrewerMenuSlot empty_slot)
  {
    _ingredient.moveToBrewerMenu(empty_slot);
  }

  public Potion mixIngredients(Ingredient _ingredient, Ingredient _ingredient2, Ingredient _ingredient3, BrewerMenuSlot[] _brewermenu_slots, PotionMenu _potionmenu)
  {
    return null;
  }

  public void drinkPotion(Potion _potion, Character character, PotionMenu _potionmenu)
  {
    //...
  }
}
