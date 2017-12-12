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
    public Pokey friendly3 = new Pokey();
    public Pokey friendly4 = new Pokey();
    public Pokey friendly5 = new Pokey();
    public Pokey friendly6 = new Pokey();
    public Pokey enemy1 = new Pokey();
    public Pokey enemy2 = new Pokey();
    public Pokey enemy3 = new Pokey();
    public Pokey enemy4 = new Pokey();
    public Pokey enemy5 = new Pokey();
    public Pokey enemy6 = new Pokey();

    public HashMap<Position,Pokey> pokeyMap = new HashMap<Position, Pokey>();
    public ArrayList<Move> actionQueue = new ArrayList<Move>();

    public Battle(Stage stage)
    {
        battleMenu = new BattleMenu(this,stage);

        //simplified, but will 1-2-1 in final version!
        if(friendly1 != null)toPosition(friendly1,FRIENDLY_LEFT);
        if(friendly2 != null)toPosition(friendly2,FRIENDLY_RIGHT);
        if(enemy1 != null)toPosition(enemy1,ENEMY_LEFT);
        if(enemy2 != null)toPosition(enemy2,ENEMY_RIGHT);

        if(friendly3 != null)toPosition(friendly3,FRIENDLY_BENCH1);
        if(friendly4 != null)toPosition(friendly4,FRIENDLY_BENCH2);
        if(friendly5 != null)toPosition(friendly5,FRIENDLY_BENCH3);
        if(friendly6 != null)toPosition(friendly6,FRIENDLY_BENCH4);

        if(enemy3 != null)toPosition(enemy3,ENEMY_BENCH1);
        if(enemy4 != null)toPosition(enemy4,ENEMY_BENCH2);
        if(enemy5 != null)toPosition(enemy5,ENEMY_BENCH3);
        if(enemy6 != null)toPosition(enemy6,ENEMY_BENCH4);

        System.out.println(friendly1.position+","+friendly2.position+","+friendly3.position+","+
                friendly4.position+","+friendly5.position+","+friendly6.position);

        battleMenu.fightRunOut();
        battleState = FIGHT_RUN1;
    }

    public void executeActions()
    {
        for(int i=actionQueue.size()-1;i>=0;i--)
        {
            ArrayList<Position> targets = actionQueue.get(i).targets;

            for(int j=0;j<targets.size();j++)
            {
                Pokey pokey = getPokeyByPosition(targets.get(j));

                if(!pokey.fainted)
                {
                    //do stuff to pokey!
                    //pokey.hp -= actionQueue.get(i).power;
                    actionQueue.get(i).bonusEffects();
                }
            }
        }
    }

    public void toPosition(Pokey pokey,Position position)
    {
        pokey.position = position;
        pokeyMap.put(position,pokey);
    }

    //NOT SURE IF THE HASHMAP PUTS THEM IN THE RIGHT PLACE! CHECK!
    public void swapPosition(Position position1,Position position2)
    {
        getPokeyByPosition(position1).position = position2;
        getPokeyByPosition(position2).position = position1;

        pokeyMap.put(position2,getPokeyByPosition(position1));
        pokeyMap.put(position1,getPokeyByPosition(position2));

        System.out.println(friendly1.position+","+friendly2.position+","+friendly3.position+","+
                friendly4.position+","+friendly5.position+","+friendly6.position);
    }

    public Pokey getPokeyByPosition(Position position)
    {
        return pokeyMap.get(position);
    }
}
