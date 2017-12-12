package me.blip.pokeymans;

import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.HashMap;

import static me.blip.pokeymans.BattleState.*;
import static me.blip.pokeymans.Position.*;

public class Battle
{
    public BattleMenu battleMenu;
    public static BattleState battleState;

    public Pokey friendly1 = new Pokey();
    public Pokey friendly2 = new Pokey();
    public Pokey enemy1 = new Pokey();
    public Pokey enemy2 = new Pokey();

    public Pokey friendly3,friendly4,friendly5,friendly6;
    public Pokey enemy3,enemy4,enemy5,enemy6;

    public HashMap<Position,Pokey> pokeyMap = new HashMap<Position, Pokey>();
    public ArrayList<Pokey> pokeyArray = new ArrayList<Pokey>();

    public Battle(Stage stage)
    {
        battleMenu = new BattleMenu(this,stage);

        //simplified, but will 1-2-1 in final version!
        changePosition(friendly1,FRIENDLY_LEFT);
        changePosition(friendly2,FRIENDLY_RIGHT);
        changePosition(enemy1,ENEMY_LEFT);
        changePosition(enemy2,ENEMY_RIGHT);

        battleMenu.fightRunOut();
        battleState = FIGHT_RUN1;
    }

    private void changePosition(Pokey pokey,Position position)
    {
        pokey.position = position;
        pokeyMap.put(position,pokey);
        pokeyArray.add(pokey);
    }
}
