package me.blip.pokeymans;

import java.util.ArrayList;

public abstract class Move
{
    //probably have battle messages defined in here

    //which of these the move can hit
    public enum PossibleTargets{SELF,FRIEND,ENEMIES}
    //whether the move hits all of those listed, or select a target.

    //move definitions
    public String name;
    public int power,priority;
    public Type type;

    public ArrayList<PossibleTargets> possibleTargets;
    public boolean all = false;

    //move object properties
    public Pokey user;
    public ArrayList<Position> targets;

    public Battle battle;

    public Move(Battle battle,Pokey user,String name,int power,int priority,Type type,ArrayList<PossibleTargets> possibleTargets,boolean all)
    {
        this.user = user;
        this.battle = battle;
        this.name = name;
        this.power = power;
        this.priority = priority;
        this.type = type;
        this.possibleTargets = possibleTargets;
        this.all = all;
        targets = new ArrayList<Position>();
    }

    public void singleTarget(Position position)
    {
        targets.clear();
        targets.add(position);
    }

    public void autoSelectTarget()
    {
        targets.clear();

        if(user.position == Position.FRIENDLY_LEFT || user.position == Position.FRIENDLY_RIGHT)
        {
            for (int i = 0; i < possibleTargets.size(); i++)
            {
                switch (possibleTargets.get(i))
                {
                    case SELF:
                        targets.add(user.position);
                        break;
                    case FRIEND:
                        if (user.position.equals(Position.FRIENDLY_LEFT)) targets.add(Position.FRIENDLY_RIGHT);
                        else targets.add(Position.FRIENDLY_LEFT);
                        break;
                    case ENEMIES:
                        targets.add(Position.ENEMY_LEFT);
                        targets.add(Position.ENEMY_RIGHT);
                        break;
                }
            }
        }
        else
        {
            System.out.println("DO THIS FOR THE ENEMY duuuhhdhhhhH");
        }
    }

    public abstract void bonusEffects();
}
