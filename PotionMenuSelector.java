
public class PotionMenuSelector extends Sprite implements CanInteract
{
  Potion overlapee = null;

  public PotionMenuSelector(String path, int _x, int _y)
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
    return null;
  }

  //going to need to have the potionmenu as input, so I can make it empty, so I can fill it
  public void drinkPotion(Potion _potion, Character _character, PotionMenu _potionmenu)
  {
    //...
    _character.player_gravity += _potion.gravity_increase;
    _character.player_gravity += _potion.gravity_reduction;
    _character.x_speed += _potion.run_speed_boost;
    _character.x_speed += _potion.run_speed_nerf;
    if (_character.player_gravity < 0.1)
    {
      _character.player_gravity = 0.1;
    }
    if (_character.x_speed < 0)
    {
      _character.x_speed = 1;
    }
    _potion.y = 1000;
    _potionmenu.is_empty = true;
  }
}
