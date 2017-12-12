package me.blip.pokeymans;

import java.util.ArrayList;

public class Move
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

    public void bonusEffects()
    {
        //user.spdBuff +2; etc
    }
}
