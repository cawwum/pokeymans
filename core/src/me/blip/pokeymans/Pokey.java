package me.blip.pokeymans;

import java.util.ArrayList;

//make this abstract eventually
public class Pokey
{
    public Battle battle;
    public Position position;
    public String name;
    public boolean fainted = false;

    public int lv;
    public int hp,atk,def,spd;
    public int baseHP,baseAtk,baseDef,baseSpd;
    public int buffAtk = 0;
    public int buffDef = 0;
    public int buffSpd = 0;
    public int evHP = 0;
    public int evAtk = 0;
    public int evDef = 0;
    public int evSpd = 0;

    public Type type1,type2;

    public boolean burned = false;
    public boolean confused = false;
    public boolean frozen = false;
    public boolean poisoned = false;

    public ArrayList<Pair<Integer,Move>> learnableMoves = new ArrayList<Pair<Integer, Move>>();
    public ArrayList<Move> moves = new ArrayList<Move>();
    public Switch switchMove;

    public Pokey(Battle battle,int lv,int baseHP,int baseAtk,int baseDef,int baseSpd,String name,Type type1,Type type2)
    {
        this.battle = battle;
        this.baseAtk = baseAtk;
        this.baseDef = baseDef;
        this.baseSpd = baseSpd;
        this.baseHP = baseHP;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.lv = lv;

        //will add evs later!
        this.hp = Formuoli.calculateHP(baseHP,evHP,lv);
        this.atk = Formuoli.calculateStat(baseAtk,evAtk,lv);
        this.def = Formuoli.calculateStat(baseDef,evDef,lv);
        this.spd = Formuoli.calculateStat(baseSpd,evSpd,lv);

        switchMove = new Switch(battle,this);
    }

    public void applyBuffAtk(int stages)
    {
        String adjective = "";
        String verb = "";

        if(Math.abs(stages) <= 1)adjective = "";
        if(Math.abs(stages) == 2)adjective = "sharply ";
        if(Math.abs(stages) >= 3)adjective = "harshly ";

        if(stages < 0)verb = "fell";
        if(stages >= 0)verb = "rose";

        buffAtk += stages;
        System.out.println(name+"'s attack "+adjective+verb+"!");
    }

    public void applyBuffDef(int stages)
    {
        String adjective = "";
        String verb = "";

        if(Math.abs(stages) <= 1)adjective = "";
        if(Math.abs(stages) == 2)adjective = "sharply ";
        if(Math.abs(stages) >= 3)adjective = "harshly ";

        if(stages < 0)verb = "fell";
        if(stages >= 0)verb = "rose";

        buffDef += stages;
        System.out.println(name+"'s defence "+adjective+verb+"!");
    }

    public void applyBuffSpd(int stages)
    {
        String adjective = "";
        String verb = "";

        if(Math.abs(stages) <= 1)adjective = "";
        if(Math.abs(stages) == 2)adjective = "sharply ";
        if(Math.abs(stages) >= 3)adjective = "harshly ";

        if(stages < 0)verb = "fell";
        if(stages >= 0)verb = "rose";

        buffSpd += stages;
        System.out.println(name+"'s speed "+adjective+verb+"!");
    }
}
