package me.blip.pokeymans;

import java.util.ArrayList;

public abstract class Move
{
    //probably have battle messages defined in here

    //move definitions
    public String name;
    public int power,priority;
    public Type type;
    public ArrayList<Position> possibleTargets;

    //move object properties
    public Pokey user;
    public ArrayList<Position> targets;

    public Battle battle;

    public Move(Battle battle,String name,int power,int priority,Type type,Pokey user,ArrayList<Position> targets)
    {
        this.battle = battle;
        this.name = name;
        this.power = power;
        this.priority = priority;
        this.type = type;
        this.user = user;
        this.targets = targets;
    }

    public abstract void bonusEffects();
}
