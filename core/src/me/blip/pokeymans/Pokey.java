package me.blip.pokeymans;

import java.util.ArrayList;

//make this abstract eventually
public class Pokey
{
    public Battle battle;
    public Position position;
    public String name;
    public boolean fainted = false;

    public int hp,atk,def,spd;
    public int baseHP,baseAtk,baseDef,baseSpd;
    public int atkBuff,defBuff,spdBuff;
    public Type type1,type2;

    public boolean burned = false;
    public boolean confused = false;
    public boolean frozen = false;
    public boolean poisoned = false;

    public ArrayList<Pair<Integer,Move>> learnableMoves = new ArrayList<Pair<Integer, Move>>();
    public ArrayList<Move> moves = new ArrayList<Move>();
    public Switch switchMove;

    public Pokey(Battle battle,int baseHP,int baseAtk,int baseDef,int baseSpd,String name,Type type1,Type type2)
    {
        this.battle = battle;
        this.baseAtk = baseAtk;
        this.baseDef = baseDef;
        this.baseSpd = baseSpd;
        this.baseHP = baseHP;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;

        switchMove = new Switch(battle,this);
    }
}
