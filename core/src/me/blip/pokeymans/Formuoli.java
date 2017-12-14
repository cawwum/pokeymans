package me.blip.pokeymans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Formuoli
{
    // POSSIBLY MAKE EV's STRONGER!! LOVE WINS LOVE WINS

    //Ice - grass
    //Ice > water
    //Electric > steel

    public HashMap<Type,Integer> intType = new HashMap<Type,Integer>();
    float[] normalVs = {1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,0.5f,0f,1f,1f,0.5f,1f,1f};
    float[] fireVs = {1f,0.5f,0.5f,1f,2f,2f,1f,1f,1f,1f,1f,2f,0.5f,1f,0.5f,1f,2f,1f,1f};
    float[] waterVs = {1f,2f,0.5f,1f,0.5f,1f,1f,1f,2f,1f,1f,1f,2f,1f,0.5f,1f,1f,1f,1f};
    float[] electricVs = {1f,1f,2f,0.5f,0.5f,1f,1f,1f,0f,2f,1f,1f,1f,1f,0.5f,1f,2f,1f,1f};
    float[] grassVs = {1f,0.5f,2f,1f,0.5f,1f,1f,0.5f,2f,0.5f,1f,0.5f,2f,1f,0.5f,1f,0.5f,1f,1f};
    float[] iceVs = {1f,0.5f,2f,1f,1f,0.5f,1f,1f,2f,2f,1f,1f,1f,1f,2f,1f,0.5f,1f,1f};
    float[] fightingVs = {2f,1f,1f,1f,1f,2f,1f,0.5f,1f,0.5f,0.5f,0.5f,2f,0f,1f,2f,2f,0.5f,1f};
    float[] poisonVs = {1f,1f,1f,1f,2f,1f,1f,0.5f,0.5f,1f,1f,1f,0.5f,0.5f,1f,1f,0f,2f,1f};
    float[] groundVs = {1f,2f,1f,2f,0.5f,1f,1f,2f,1f,0f,1f,0.5f,2f,1f,1f,1f,2f,1f,1f};
    float[] flyingVs = {1f,1f,1f,0.5f,2f,1f,2f,1f,1f,1f,1f,2f,0.5f,1f,1f,1f,0.5f,1f,1f};
    float[] psychicVs = {1f,1f,1f,1f,1f,1f,2f,2f,1f,1f,0.5f,1f,1f,1f,1f,0f,0.5f,1f,1f};
    float[] bugVs = {1f,0.5f,1f,1f,2f,1f,0.5f,0.5f,1f,0.5f,2f,1f,1f,0.5f,1f,2f,0.5f,0.5f,1f};
    float[] rockVs = {1f,2f,1f,1f,1f,2f,0.5f,1f,0.5f,2f,1f,2f,1f,1f,1f,1f,0.5f,1f,1f};
    float[] ghostVs = {0f,1f,1f,1f,1f,1f,1f,1f,1f,1f,2f,1f,1f,2f,1f,0.5f,1f,1f,1f};
    float[] dragonVs = {1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,2f,1f,0.5f,0f,1f};
    float[] darkVs = {1f,1f,1f,1f,1f,1f,0.5f,1f,1f,1f,2f,1f,1f,2f,1f,0.5f,1f,0.5f,1f};
    float[] steelVs = {1f,0.5f,0.5f,0.5f,1f,2f,1f,1f,1f,1f,1f,1f,2f,1f,1f,1f,0.5f,2f,1f};
    float[] fairyVs = {1f,0.5f,1f,1f,1f,1f,2f,0.5f,1f,1f,1f,1f,1f,1f,2f,2f,0.5f,1f,1f};
    float[] nullVs = {1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f};
    public float[][] typeChart = {normalVs,fireVs,waterVs,electricVs,grassVs,iceVs,fightingVs,poisonVs,groundVs,
            flyingVs,psychicVs,bugVs,rockVs,ghostVs,dragonVs,darkVs,steelVs,fairyVs,nullVs};

    public Formuoli()
    {
        intType.put(Type.NORMAL,0);
        intType.put(Type.FIRE,1);
        intType.put(Type.WATER,2);
        intType.put(Type.ELECTRIC,3);
        intType.put(Type.GRASS,4);
        intType.put(Type.ICE,5);
        intType.put(Type.FIGHTING,6);
        intType.put(Type.POISON,7);
        intType.put(Type.GROUND,8);
        intType.put(Type.FLYING,9);
        intType.put(Type.PSYCHIC,10);
        intType.put(Type.BUG,11);
        intType.put(Type.ROCK,12);
        intType.put(Type.GHOST,13);
        intType.put(Type.DRAGON,14);
        intType.put(Type.DARK,15);
        intType.put(Type.STEEL,16);
        intType.put(Type.FAIRY,17);
        intType.put(Type.NULL,18);
    }

    public static int calculateHP(float base,int evHP,int lv)
    {
        return (int)(((2f*base+(evHP/4))*lv)/100f)+lv+10;
    }

    public static int calculateStat(float base,int evStat,int lv)
    {
        return (int)(((2f*base+(evStat/4))*lv)/100f)+5;
    }

    public int calculateDamage(Pokey user,Pokey target,Move move)
    {
        float userBuffs = (user.buffAtk >= 0) ? 1f+0.5f*user.buffAtk : 1f/1f+0.5f*user.buffAtk;
        float targetBuffs = (target.buffDef >= 0) ? 1f + 0.5f*target.buffDef : 1f/1f+0.5f*target.buffDef;

        //make sure int/float conversions are fine ... user.atk/target.def seems dodgy to me!
        return (int)((((2f*user.lv/5f+2)*move.power*((float)user.atk*userBuffs/(target.def*targetBuffs))/50f)+2)
                *modifier(user,target,move));
    }

    //user type
    public float modifier(Pokey user,Pokey target,Move move)
    {
        Random random = new Random();
        //random * stab * matchup
        float randy = (0.85f + (random.nextFloat() * 0.15f));
        float stab = (user.type1 == move.type || user.type2 == move.type) ? 1.5f : 1f;
        float matchup = effectiveness(move.type,target.type1,target.type2);

        return randy * stab * matchup;
    }

    public float effectiveness(Type moveType,Type targetType1,Type targetType2)
    {
        float multiplier1 = typeChart[intType.get(moveType)][intType.get(targetType1)];
        float multiplier2 = typeChart[intType.get(moveType)][intType.get(targetType2)];
        float multiplier;

        //Reduce 4x to 3x??
        if(multiplier1 == 2f && multiplier2 == 2f)
        {
            multiplier = 3f;
            System.out.println("It's Critically Effective!");
        }
        else
        {
            multiplier = multiplier1 * multiplier2;
        }

        if(multiplier == 2f)System.out.println("It's Super Effective!");
        else if(multiplier == 0.5f)System.out.println("It's not very effective ...");
        else if(multiplier == 0.25f)System.out.println("It's hardly effective...");
        else if(multiplier == 0f)System.out.println("It had no effect...");

        return multiplier;
    }

    public ArrayList<Move> orderMoves(ArrayList<Move> moves)
    {
        return moves;
    }
}
