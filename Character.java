import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Character extends Sprite implements CanInteract
{

  double x_speed; //to be altered
  double y_speed;
  double jump_strength;
  double max_fall_speed = 10;
  double player_gravity; //to be altered
  int ground_level;
  boolean falling;
  boolean jumping;
  Ingredient overlapee;
  Brewer overlapee2;

  public Character(String path, int _x, int _y, double _x_speed, double _player_gravity, double _jump_strength)
  {
    super(path, _x, _y);
    x_speed = _x_speed;
    jump_strength = _jump_strength;
    player_gravity = _player_gravity;
    ground_level = _y;
  }

  //initialize upwards movement at jump_strength, before being affected by gravity
  public void jump()
  {
    this.y_speed = -jump_strength;
    this.jumping = true;
  }

  public void moveRight()
  {
    //don't go beyond right bound of window
    if (this.x >= 732)
    {
      this.x = this.x;
    }
    else
    {
      this.x += this.x_speed;
    }
  }

  public void moveLeft()
  {
    //don't go beyond left bound of window
    if (this.x <= 0)
    {
      this.x = this.x;
    }
    else
    {
      this.x -= this.x_speed;
    }
  }

  //takes input which indicates the direction character is moving, and make it's image the the properly oriented image
  public void flipSprite(boolean _direction)
  {
    if (_direction == true)
    {
      try
      {
        this.image = ImageIO.read(new File("characterright.png"));
      }
      catch(IOException e)
      {
        System.out.println(e);
        System.exit(1);
      }
    }
    else if (_direction == false)
    {
      try
      {
        this.image = ImageIO.read(new File("characterleft.png"));
      }
      catch(IOException e)
      {
        System.out.println(e);
        System.exit(1);
      }
    }
  }

  //take inputted ingredient, it's destination, and execute movement
  public void pickIngredient(Ingredient _ingredient, BackpackSlot empty_slot)
  {
    _ingredient.moveToBackpack(empty_slot);
  }

  public void selectIngredient(Ingredient _ingredient, BrewerMenuSlot empty_slot)
  {
    //...
  }

  public Potion mixIngredients(Ingredient _ingredient, Ingredient _ingredient2, Ingredient _ingredient3, BrewerMenuSlot[] _brewermenu_slots, PotionMenu _potionmenu)
  {
    return null;
  }

  public void drinkPotion(Potion _potion, Character character, PotionMenu _potionmenu)
  {
    //...
  }

  public void apply_gravity()
  {
    //if character's y value reaches below ground_level, go back to ground level
    if (this.y > this.ground_level)
    {
      this.y = this.ground_level;
      this.y_speed = 0;
      this.falling = false;
      this.jumping = false;
    }
    //if character's y value is above ground_level, enable falling...
    //honestly I don't know why this is working, but I added this
    //second conditional check and state change, and it stopped
    //rapidly adjusting y values when on the "ground"
    else if (this.y > this.ground_level)
    {
      this.falling = true;
    }

    //apply current speed of incrementation along y axis (initially altered in jump())
    this.y += this.y_speed;

    //if character is either falling or jumping, apply gravity to y axis movement
    if (this.falling || this.jumping)
    {
      y_speed += player_gravity;

      if (y_speed > max_fall_speed)
      {
        y_speed = max_fall_speed;
      }
    }
  }


}
