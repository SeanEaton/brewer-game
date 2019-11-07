
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.util.Random;


//main class for Game, executes all functions
public class Game extends JFrame
{

  boolean isRunning = true;
  int fps = 60;
  int windowWidth = 750;
  int windowHeight = 750;

  Random planter = new Random();

  BufferedImage back_buffer;
  Insets insets;
  InputHandler input;

  public ArrayList<Ingredient> ingredients;
  public ArrayList<Sprite> uncondtional_sprites;
  public BackpackSlot[] backpackslots;
  public BrewerMenuSlot[] brewermenu_slots;

  Character character;
  Backpack backpack;
  Selector selector;
  Brewer brewer;
  BrewerMenu brewermenu;
  BrewerMenuSelector brewermenu_selector;
  PotionMenu potionmenu;
  PotionMenuSelector potionmenu_selector;
  Potion potion;

  //construct sprite lists
  public Game()
  {
    ingredients = new ArrayList<Ingredient>();
    uncondtional_sprites = new ArrayList<Sprite>();
    backpackslots = new BackpackSlot[10];
    brewermenu_slots = new BrewerMenuSlot[3];
  }

  //assemble lists and non-listed game objects
  //add non-menu sprites
  public void addBackground(Background _background)
  {
    uncondtional_sprites.add(_background);
  }

  public void addCharacter(Character _character)
  {
    character = _character;
    uncondtional_sprites.add(_character);
  }

  public void addBrewer(Brewer _brewer)
  {
    brewer = _brewer;
    uncondtional_sprites.add(_brewer);
  }

  public void addIngredient(Ingredient _ingredient)
  {
    ingredients.add(_ingredient);
    uncondtional_sprites.add(_ingredient);
  }

  //add menus and slots
  public void addBackpack(Backpack _backpack)
  {
    backpack = _backpack;
    uncondtional_sprites.add(_backpack);
  }

  public void addBackpackSlot(BackpackSlot _backpackslot, int index)
  {
    backpackslots[index] = _backpackslot;
  }

  public void addBrewerMenu(BrewerMenu _brewermenu)
  {
    brewermenu = _brewermenu;
    uncondtional_sprites.add(_brewermenu);
  }

  public void addBrewerMenuSlot(BrewerMenuSlot _brewermenu_slot, int index)
  {
    brewermenu_slots[index] = _brewermenu_slot;
  }

  public void addPotionMenu(PotionMenu _potionmenu)
  {
    potionmenu = _potionmenu;
    uncondtional_sprites.add(_potionmenu);
  }

  //add menu navigators
  public void addSelector(Selector _selector)
  {
    selector = _selector;
  }

  public void addBrewerMenuSelector(BrewerMenuSelector _brewermenu_selector)
  {
    brewermenu_selector = _brewermenu_selector;
  }

  public void addPotionMenuSelector(PotionMenuSelector _potionmenu_selector)
  {
    potionmenu_selector = _potionmenu_selector;
  }

  //run the game in a loop, calling all Game functions
  public void run()
  {
    initialize();

    while(isRunning)
    {

      long time = System.currentTimeMillis();
      update();
      draw();
      checkOverlap();

      //delay for each frame  -   time it took for one frame
      time = (1000 / fps) - (System.currentTimeMillis() - time);

      if (time > 0)
      {
        try
        {
          Thread.sleep(time);
        }
        catch(Exception e){}
      }
    }

    setVisible(false);
  }

  //initialize the canvas and input framework
  void initialize()
  {
    setTitle("Brewer");
    setSize(windowWidth, windowHeight);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
    insets = getInsets();
    setSize(insets.left + windowWidth + insets.right,
            insets.top + windowHeight + insets.bottom);

    back_buffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
    input = new InputHandler(this);
  }

  //this will check for all changes of states and permissions
  void update()
  {
    //populateField if P is pressed
    if (input.isKeyDown(KeyEvent.VK_P) == true)
    {
      populateField();
    }

    //character input
    if (input.isKeyDown(KeyEvent.VK_LEFT) == true)
    {
      character.moveLeft();
      character.flipSprite(false);
    }
    if (input.isKeyDown(KeyEvent.VK_RIGHT) == true)
    {
      character.moveRight();
      character.flipSprite(true);
    }
    if (character.jumping == false)
    {
      if (input.isKeyDown(KeyEvent.VK_UP) == true)
      {
        character.jump();
      }
    }

    //if character overlaps an ingredient, allow fillEmptyBackpackSlot to be called
    if (ingredients.contains(character.overlapee))
    {
      if (input.isKeyDown(KeyEvent.VK_SPACE) == true)
      {
        fillEmptyBackpackSlot(character.overlapee);
      }
    }

    //if character overlaps brewer sprite, allow selectors inputs
    if (character.overlapee2 == brewer)
    {
      if (input.isKeyDown(KeyEvent.VK_A) == true)
      {

        selector.scrollLeft();
      }
      if (input.isKeyDown(KeyEvent.VK_D) == true)
      {
        selector.scrollRight();
      }
      if (ingredients.contains(selector.overlapee))
      {
        if (input.isKeyDown(KeyEvent.VK_E) == true)
        {
          fillEmptyBrewerMenuSlot(selector.overlapee);
        }
      }
      //if all slots in the brewermenu are filled, and the potionmenu is empty mix each slots ingredient, and make that the current potion
      if ((brewermenu_slots[0].is_empty == false) && (brewermenu_slots[1].is_empty == false) && (brewermenu_slots[2].is_empty == false) && (potionmenu.is_empty == true))
      {
        if (input.isKeyDown(KeyEvent.VK_ENTER) == true)
        {
          Potion _potion = brewermenu_selector.mixIngredients(brewermenu_selector.overlapees[1], brewermenu_selector.overlapees[0], brewermenu_selector.overlapees[2], brewermenu_slots, potionmenu);
          potion = _potion;
        }
      }
    }

    if (potionmenu.is_empty == false)
    {
      if (input.isKeyDown(KeyEvent.VK_SHIFT) == true)
      {
        potionmenu_selector.drinkPotion(potion, character, potionmenu);
        potion = null;
      }
    }

    //exit key
    if (input.isKeyDown(KeyEvent.VK_ESCAPE) == true)
    {
      System.exit(0);
    }

    //apply gravity to character
    character.apply_gravity();
  }

  //draw all elements
  void draw()
  {
    Graphics g = getGraphics();
    Graphics bbg = back_buffer.getGraphics();

    bbg.setColor(Color.WHITE);
    bbg.fillRect(0, 0, windowWidth, windowHeight);

    //draw all sprites, and selectors/potion given each one's conditions
    for (int i = 0; i < uncondtional_sprites.size(); i++)
    {
      uncondtional_sprites.get(i).draw(bbg);
      if (character.overlapee2 == brewer)
      {
        selector.draw(bbg);
      }
      if (brewermenu_slots[0].is_empty == false && brewermenu_slots[1].is_empty == false && brewermenu_slots[2].is_empty == false && character.overlapee2 == brewer)
      {
        brewermenu_selector.draw(bbg);
      }
      if (potionmenu.is_empty == false)
      {
        potionmenu_selector.draw(bbg);
        potion.draw(bbg);
      }
    }

    g.drawImage(back_buffer, insets.left, insets.top, this);
  }

  void checkOverlap()
  {
    Rectangle char_rect = new Rectangle(character.x, character.y, character.width, character.height);
    Rectangle brew_rect = new Rectangle(brewer.x, brewer.y, brewer.width, brewer.height);
    Rectangle select_rect = new Rectangle(selector.x, selector.y, selector.width, selector.height);
    Rectangle brewerselect_rect = new Rectangle(brewermenu_selector.x, brewermenu_selector.y, brewermenu_selector.width, brewermenu_selector.height);

    //check for overlap between character sprite and ingedient sprites
    for (Ingredient herb: ingredients)
    {
      Rectangle ing_rect = new Rectangle(herb.x, herb.y, herb.width, herb.height);

      if (ing_rect.intersects(char_rect))
      {
        character.overlapee = herb;
        break;
      }
      else
      {
        character.overlapee = null;
      }
    }

    //check for overlap between selector sprite and ingredient sprites
    for (Ingredient herb: ingredients)
    {
      Rectangle ing_rect = new Rectangle(herb.x, herb.y, herb.width, herb.height);

      if (ing_rect.intersects(select_rect))
      {
        selector.overlapee = herb;
        break;
      }
      else
      {
        selector.overlapee = null;
      }
    }

    //check for overlap between brewermenu_selector sprite and ingredient sprites
    int j = 0;
    for (Ingredient herb: ingredients)
    {
      Rectangle ing_rect = new Rectangle(herb.x, herb.y, herb.width, herb.height);

      if (ing_rect.intersects(brewerselect_rect))
      {
        brewermenu_selector.overlapees[j] = herb;
        j++;
        if (j > 2)
        {
          break;
        }
      }
    }

    //check for overlap between character and brewer
    if (brew_rect.intersects(char_rect))
    {
      character.overlapee2 = brewer;
    }
    else
    {
      character.overlapee2 = null;
    }
  }

  //search for the first empty BackpackSlot, fill it with inputted Ingredient, change is_empty status
  void fillEmptyBackpackSlot(Ingredient _herb)
  {
    for (BackpackSlot slot: backpackslots)
    {
      if (slot.is_empty == true)
      {
        //call character's function to move _herb to the chosen backpack slot
        character.pickIngredient(_herb, slot);
        break;
      }
    }
  }

  //search for the first empty BackpackSlot, fill it with inputted Ingredient, change is_empty status
  void fillEmptyBrewerMenuSlot(Ingredient _herb)
  {
    for (BrewerMenuSlot slot: brewermenu_slots)
    {
      if (slot.is_empty == true)
      {
        //call selector's function to move _herb to the chosen brewermenu slot
        selector.selectIngredient(_herb, slot);
        break;
      }
    }
  }

  //randomly populate the explorable area with 10 random ingredients
  void populateField()
  {
    for (int k = 0; k < 13; k++)
    {
      int random_ingredient = planter.nextInt(4);
      int random_x = planter.nextInt(580);
      int random_y = planter.nextInt(26);
      if (random_ingredient == 0)
      {
        IncGravMush _incgravmush = new IncGravMush("incgravmush.png", random_x + 15, random_y + 463, null);
        this.addIngredient(_incgravmush);
      }
      else if (random_ingredient == 1)
      {
        RedGravFlwr _redgravflwr = new RedGravFlwr("redgravflwr.png", random_x + 15, random_y + 463, null);
        this.addIngredient(_redgravflwr);
      }
      else if (random_ingredient == 2)
      {
        SpeedFlwr _speedflwr = new SpeedFlwr("speedflower.png", random_x + 15, random_y + 463, null);
        this.addIngredient(_speedflwr);
      }
      else if (random_ingredient == 3)
      {
        SlowMush _slowmush = new SlowMush("slowmush.png", random_x + 15, random_y + 463, null);
        this.addIngredient(_slowmush);
      }
    }
  }
}
