import java.util.Random;

//construct all elements, add to main executive class
public class GameRunner
{
    public static void main(String[] args)
    {
        Game game = new Game();
        Random planter = new Random();

        Background _background = new Background("background.png", 0, 0);
        game.addBackground(_background);

        Character _character = new Character("characterright.png", 60, 420, 4, 0.9, 10);
        game.addCharacter(_character);

        Brewer _brewer = new Brewer("brewer.png", 615, 434);
        game.addBrewer(_brewer);

        Backpack _backpack = new Backpack("backpack.png", 25, 630);
        game.addBackpack(_backpack);

        //add a BackpackSlot for each slot in Backpack's slots array at that slot's x, y co-ordinates
        for (int i = 0; i < _backpack.slots.length; i++)
        {
          BackpackSlot _backpackslot = new BackpackSlot(_backpack.slots[i][0], _backpack.slots[i][1], null);
          game.addBackpackSlot(_backpackslot, i);
        }

        BrewerMenu _brewermenu = new BrewerMenu("brewermenu.png", 560, 630);
        game.addBrewerMenu(_brewermenu);

        //add a BrewerMenuSlot for each slot in BrewerMenu's slots array at that slot's x, y co-ordinates
        for (int j = 0; j < _brewermenu.slots.length; j++)
        {
          BrewerMenuSlot _brewermenu_slot = new BrewerMenuSlot(_brewermenu.slots[j][0], _brewermenu.slots[j][1], null);
          game.addBrewerMenuSlot(_brewermenu_slot, j);
        }

        //add selectors
        Selector _selector = new Selector("selector.png", 37, 642);
        game.addSelector(_selector);

        BrewerMenuSelector _brewermenu_selector = new BrewerMenuSelector("brewermenuselector.png", 555, 625);
        game.addBrewerMenuSelector(_brewermenu_selector);

        PotionMenu _potionmenu = new PotionMenu("potionmenu.png", 608, 567);
        game.addPotionMenu(_potionmenu);

        PotionMenuSelector _potionmenu_selector = new PotionMenuSelector("potionmenuselector.png", 603, 562); //-5 eacj
        game.addPotionMenuSelector(_potionmenu_selector);


        //call the main run method in Game
        game.run();
        System.exit(0);
    }
}
