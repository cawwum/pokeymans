package me.blip.pokeymans.moves;

import me.blip.pokeymans.Battle;
import me.blip.pokeymans.Move;
import me.blip.pokeymans.Pokey;
import me.blip.pokeymans.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SilverWind extends Move
{
    public SilverWind(Battle battle,Pokey user)
    {
        super(battle,user,"Silver Wind",60,0, Type.BUG,
                new ArrayList<PossibleTargets>(Arrays.asList(PossibleTargets.ENEMIES,PossibleTargets.FRIEND)),true);
    }

    @Override
    public void bonusEffects()
    {
        Random random = new Random();
        if(random.nextFloat() < 0.2f)user.applyBuffAtk(1);
        if(random.nextFloat() < 0.2f)user.applyBuffDef(1);
        if(random.nextFloat() < 0.2f)user.applyBuffSpd(1);
    }
}
