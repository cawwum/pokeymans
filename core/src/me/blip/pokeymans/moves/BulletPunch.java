package me.blip.pokeymans.moves;

import me.blip.pokeymans.Battle;
import me.blip.pokeymans.Move;
import me.blip.pokeymans.Pokey;
import me.blip.pokeymans.Type;

import java.util.ArrayList;
import java.util.Arrays;

//move - user - targets

public class BulletPunch extends Move
{
    public BulletPunch(Battle battle,Pokey user)
    {
        super(battle,user,"Bullet Punch",40,1, Type.STEEL,
                new ArrayList<PossibleTargets>(Arrays.asList(PossibleTargets.ENEMIES)),false);
    }

    @Override
    public void bonusEffects()
    {
        System.out.println("Bullet Punching lmao");
    }
}
