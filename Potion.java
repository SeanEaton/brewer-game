
public class Potion extends Sprite
{

  //null stats before mixing ingredients
  double gravity_increase = 0;
  double gravity_reduction = 0;
  int run_speed_boost = 0;
  int run_speed_nerf = 0;

  public Potion(String path, int _x, int _y, Ingredient _ingredient, Ingredient _ingredient2, Ingredient _ingredient3)
  {
    super(path, _x, _y);
    Ingredient[] ingredients = new Ingredient[3];
    ingredients[0] = _ingredient;
    ingredients[1] = _ingredient2;
    ingredients[2] = _ingredient3;

    //increase each value of the potion's alteration stats by the proper ingredients altering stat
    for (Ingredient ingredient : ingredients)
    {
      gravity_increase += ingredient.gravity_increase;
      gravity_reduction += ingredient.gravity_reduction;
      run_speed_boost += ingredient.run_speed_boost;
      run_speed_nerf += ingredient.run_speed_nerf;
    }
  }
}
