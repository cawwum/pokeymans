package me.blip.pokeymans;

import java.util.ArrayList;

public class Switch extends Move
{
    public Switch(Battle battle, Pokey user, ArrayList<Position> targets)
    {
        super(battle,"",0,4,Type.NULL,user,targets);
    }

    @Override
    public void bonusEffects()
    {
        Pokey target = battle.getPokeyByPosition(targets.get(0));
        //whatever the bench position is
        System.out.println(user.name+", come back!");
        System.out.println("Go "+target.name+"!");

        user.switching = false;
        target.switching = false;
        battle.swapPosition(user.position,target.position);
    }
}
