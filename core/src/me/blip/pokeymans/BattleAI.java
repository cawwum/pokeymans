package me.blip.pokeymans;

import java.util.ArrayList;
import java.util.Random;

import static me.blip.pokeymans.Position.*;

public class BattleAI
{
    private Random random;
    private Battle battle;
    private ArrayList<Move> tempActionQueue = new ArrayList<Move>();

    public BattleAI(Battle battle)
    {
        this.battle = battle;
        random = new Random();
    }

    public void startTurn()
    {
        selectFirst();
        selectSecond();
        queueMoves();
    }

    public void queueMoves()
    {
        for (int i = 0; i < tempActionQueue.size(); i++)
        {
            battle.actionQueue.add(tempActionQueue.get(i));
        }
    }

    public boolean isSwitching(Position position)
    {
        if(tempActionQueue.size()>0)return (tempActionQueue.get(0).targets.get(0).equals(position));
        else return false;
    }

    public void selectFirst()
    {
        //Just add a random move from the list.... or switch 1/3 of the time
        Pokey pokey;
        if (battle.getPokeyByPosition(Position.ENEMY_LEFT) != null)
        {
            pokey = battle.getPokeyByPosition(Position.ENEMY_LEFT);

            int randomTo6 = random.nextInt(6);

            if (randomTo6 > 3)
            {
                if (switching(pokey))
                {
                    System.out.println("Successful AI switch!");
                }
                else
                {
                    System.out.println("Unsuccessful AI Switch");
                    int randomTo4 = random.nextInt(4);
                    tempActionQueue.add(pokey.moves.get(randomTo4));

                    if (random.nextFloat() < 0.5f)
                        tempActionQueue.get(tempActionQueue.size() - 1).singleTarget(FRIENDLY_LEFT);
                    else tempActionQueue.get(tempActionQueue.size() - 1).singleTarget(FRIENDLY_RIGHT);
                }
            }
            else
            {
                //add AI's targets LUL
                tempActionQueue.add(pokey.moves.get(randomTo6));

                if (random.nextFloat() < 0.5f)
                    tempActionQueue.get(tempActionQueue.size() - 1).singleTarget(FRIENDLY_LEFT);
                else tempActionQueue.get(tempActionQueue.size() - 1).singleTarget(FRIENDLY_RIGHT);

                System.out.println("AI queueing up move!");
            }
        }
    }

    public void selectSecond()
    {
        Pokey pokey;
        if (battle.getPokeyByPosition(Position.ENEMY_LEFT) != null)
        {
            pokey = battle.getPokeyByPosition(Position.ENEMY_LEFT);

            int randomTo6 = random.nextInt(6);

            if (randomTo6 > 3)
            {
                if (switching(pokey))
                {
                    System.out.println("Successful AI switch!");
                }
                else
                {
                    int randomTo4 = random.nextInt(4);
                    tempActionQueue.add(pokey.moves.get(randomTo4));

                    if (random.nextFloat() < 0.5f)
                        tempActionQueue.get(tempActionQueue.size() - 1).singleTarget(FRIENDLY_LEFT);
                    else tempActionQueue.get(tempActionQueue.size() - 1).singleTarget(FRIENDLY_RIGHT);
                }
            }
            else
            {
                tempActionQueue.add(pokey.moves.get(randomTo6));

                if (random.nextFloat() < 0.5f)
                    tempActionQueue.get(tempActionQueue.size() - 1).singleTarget(FRIENDLY_LEFT);
                else tempActionQueue.get(tempActionQueue.size() - 1).singleTarget(FRIENDLY_RIGHT);
            }
        }
    }

    public boolean switching(Pokey pokey)
    {
        int randomTo4 = random.nextInt(4);
        Position switchTarget;

        switch (randomTo4)
        {
            case 0:
                if (!isSwitching(ENEMY_BENCH1) && !battle.getPokeyByPosition(ENEMY_BENCH1).fainted)
                    switchTarget = ENEMY_BENCH1;
                else return false;
                break;
            case 1:
                if (!isSwitching(ENEMY_BENCH2) && !battle.getPokeyByPosition(ENEMY_BENCH2).fainted)
                    switchTarget = ENEMY_BENCH2;
                else return false;
                break;
            case 2:
                if (!isSwitching(ENEMY_BENCH3) && !battle.getPokeyByPosition(ENEMY_BENCH3).fainted)
                    switchTarget = ENEMY_BENCH3;
                else return false;
                break;
            case 3:
                if (!isSwitching(ENEMY_BENCH4) && !battle.getPokeyByPosition(ENEMY_BENCH4).fainted)
                    switchTarget = ENEMY_BENCH4;
                else return false;
                break;

            default:
                return false;

        }

        pokey.switchMove.singleTarget(switchTarget);
        tempActionQueue.add(pokey.switchMove);
        return true;
    }
}
