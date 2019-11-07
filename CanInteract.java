//this will be implements by classes which can move/combine ingredients/drink potions (selectors, character)
public interface CanInteract
{
  void pickIngredient(Ingredient _ingredient, BackpackSlot empty_slot);

  void selectIngredient(Ingredient _ingredient, BrewerMenuSlot empty_slot);

  Potion mixIngredients(Ingredient _ingredient, Ingredient _ingredient2, Ingredient _ingredient3, BrewerMenuSlot[] _brewermenu_slots, PotionMenu _potionmenu);

  void drinkPotion(Potion _potion, Character character, PotionMenu _potionmenu);

}
