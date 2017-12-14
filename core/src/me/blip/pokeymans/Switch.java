package me.blip.pokeymans;

import java.util.ArrayList;

public class Switch extends Move
{
    public Switch(Battle battle,Pokey user)
    {
        super(battle,user,"",0,4,Type.NULL,
                new ArrayList<PossibleTargets>(),false);
    }

    @Override
    public void bonusEffects()
    {
        Pokey target = battle.getPokeyByPosition(targets.get(0));

        System.out.println(user.name+", come back!");
        System.out.println("Go "+target.name+"!");

        battle.swapPosition(user.position,targets.get(0));
    }
}
