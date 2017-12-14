package me.blip.pokeymans.pokeys;

import me.blip.pokeymans.*;
import me.blip.pokeymans.moves.BulletPunch;

public class Scizor extends Pokey
{
    public Scizor(Battle battle,int lv)
    {
        super(battle,lv,70,130,100,65,"Scizor", Type.BUG,Type.STEEL);

        learnableMoves.add(new Pair<Integer, Move>(1,new BulletPunch(battle,this)));

        //yeah this gives scizor 4 bullet punches BUT THAT'S COOL RIGHT
        for(int i=0;i<4;i++)
        {
            moves.add(learnableMoves.get(0).getR());
        }
    }
}
