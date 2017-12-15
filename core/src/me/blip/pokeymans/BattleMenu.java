package me.blip.pokeymans;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;

import static me.blip.pokeymans.Position.*;
import static me.blip.pokeymans.BattleState.*;
import static me.blip.pokeymans.Battle.battleState;

public class BattleMenu
{
    public Battle battle;
    public Stage stage;
    public ArrayList<Move> tempActionQueue = new ArrayList<Move>();

    private boolean isSwitching(Position position)
    {
        return tempActionQueue.get(0).targets.get(0).equals(position);
    }

    private void queueMoves()
    {
        for (int i = 0; i < tempActionQueue.size(); i++)
        {
            battle.actionQueue.add(tempActionQueue.get(i));
        }

        battle.executeActions();
    }

    public BattleMenu(Battle battle, Stage stage)
    {
        this.battle = battle;
        this.stage = stage;

        Actor actor = new Actor();
        actor.addListener(new InputListener()
        {
            public boolean keyDown(InputEvent event, int keycode)
            {
                switch (battleState)
                {
                    case NO_INPUT:
                        break;

                    case FIGHT_RUN1:
                        fightRun1(keycode);
                        break;

                    case FIGHT_RUN2:
                        fightRun2(keycode);
                        break;

                    case MOVE1:
                        move1(keycode);
                        break;

                    case MOVE2:
                        move2(keycode);
                        break;

                    case TARGET1:
                        target1(keycode);
                        break;

                    case TARGET2:
                        target2(keycode);
                        break;

                    case SWITCHING1:
                        switch1(keycode);
                        break;

                    case SWITCHING2:
                        switch2(keycode);
                        break;
                }
                return true;
            }

            public boolean keyUp(InputEvent event, int keycode)
            {
                return true;
            }
        });

        stage.setKeyboardFocus(actor);
    }

    private void fightRun1(int keycode)
    {
        switch (keycode)
        {
            case Keys.UP:
                battleState = MOVE1;
                moveOut(battle.getPokeyByPosition(FRIENDLY_LEFT));
                break;

            case Keys.DOWN:
                switchingOut();
                battleState = SWITCHING1;
                break;
        }
    }

    private void fightRun2(int keycode)
    {
        switch (keycode)
        {
            case Keys.UP:
                battleState = MOVE2;
                moveOut(battle.getPokeyByPosition(FRIENDLY_RIGHT));
                break;
            case Keys.DOWN:
                switchingOut();
                battleState = SWITCHING2;
                break;
            case Keys.BACKSPACE:
                battleState = FIGHT_RUN1;
                fightRunOut(battle.getPokeyByPosition(FRIENDLY_LEFT));
                tempActionQueue.clear();
                break;
        }
    }

    private void move1(int keycode)
    {
        Move move;

        switch (keycode)
        {
            case Keys.LEFT:
                move = battle.getPokeyByPosition(FRIENDLY_LEFT).moves.get(1);
                tempActionQueue.add(move);
                if (move.all)
                {
                    move.autoSelectTarget();
                    battleState = FIGHT_RUN2;
                }
                else
                {
                    targetOut();
                    battleState = TARGET1;
                }
                break;

            case Keys.RIGHT:
                move = battle.getPokeyByPosition(FRIENDLY_LEFT).moves.get(2);
                tempActionQueue.add(move);
                if (move.all)
                {
                    move.autoSelectTarget();
                    battleState = FIGHT_RUN2;
                }
                else
                {
                    targetOut();
                    battleState = TARGET1;
                }
                break;

            case Keys.UP:
                move = battle.getPokeyByPosition(FRIENDLY_LEFT).moves.get(0);
                tempActionQueue.add(move);
                if(move.all)
                {
                    move.autoSelectTarget();
                    battleState = FIGHT_RUN2;
                }
                else
                {
                    targetOut();
                    battleState = TARGET1;
                }
                break;

            case Keys.DOWN:
                move = battle.getPokeyByPosition(FRIENDLY_LEFT).moves.get(3);
                tempActionQueue.add(move);
                if(move.all)
                {
                    move.autoSelectTarget();
                    battleState = FIGHT_RUN2;
                }
                else
                {
                    targetOut();
                    battleState = TARGET1;
                }
                break;

            case Keys.BACKSPACE:
                battleState = FIGHT_RUN1;
                fightRunOut(battle.getPokeyByPosition(FRIENDLY_LEFT));
                break;
        }
    }

    private void move2(int keycode)
    {
        Move move;
        switch (keycode)
        {
            case Keys.LEFT:
                move = battle.getPokeyByPosition(FRIENDLY_RIGHT).moves.get(1);
                tempActionQueue.add(move);
                if (move.all)
                {
                    move.autoSelectTarget();
                    battleState = NO_INPUT;
                    queueMoves();
                }
                else
                {
                    targetOut();
                    battleState = TARGET2;
                }
                break;

            case Keys.RIGHT:
                move = battle.getPokeyByPosition(FRIENDLY_RIGHT).moves.get(2);
                tempActionQueue.add(move);
                if (move.all)
                {
                    move.autoSelectTarget();
                    battleState = NO_INPUT;
                    queueMoves();
                }
                else
                {
                    targetOut();
                    battleState = TARGET2;
                }
                break;

            case Keys.UP:
                move = battle.getPokeyByPosition(FRIENDLY_RIGHT).moves.get(0);
                tempActionQueue.add(move);
                if(move.all)
                {
                    move.autoSelectTarget();
                    battleState = NO_INPUT;
                    queueMoves();
                }
                else
                {
                    targetOut();
                    battleState = TARGET2;
                }
                break;

            case Keys.DOWN:
                move = battle.getPokeyByPosition(FRIENDLY_RIGHT).moves.get(3);
                tempActionQueue.add(move);
                if(move.all)
                {
                    move.autoSelectTarget();
                    battleState = NO_INPUT;
                    queueMoves();
                }
                else
                {
                    targetOut();
                    battleState = TARGET1;
                }
                break;

            case Keys.BACKSPACE:
                battleState = FIGHT_RUN2;
                fightRunOut(battle.getPokeyByPosition(FRIENDLY_RIGHT));
                break;
        }
    }

    //get targets from queued up move
    private void target1(int keycode)
    {
        switch (keycode)
        {
            case Keys.LEFT:
                battleState = FIGHT_RUN2;
                fightRunOut(battle.getPokeyByPosition(FRIENDLY_RIGHT));
                tempActionQueue.get(0).singleTarget(ENEMY_LEFT);
                break;
            case Keys.RIGHT:
                battleState = FIGHT_RUN2;
                fightRunOut(battle.getPokeyByPosition(FRIENDLY_RIGHT));
                tempActionQueue.get(0).singleTarget(ENEMY_RIGHT);
                break;
            case Keys.BACKSPACE:
                battleState = MOVE1;
                moveOut(battle.getPokeyByPosition(FRIENDLY_LEFT));
                tempActionQueue.remove(0);
                break;
        }
    }

    private void target2(int keycode)
    {
        switch (keycode)
        {
            case Keys.LEFT:
                battleState = NO_INPUT;
                tempActionQueue.get(tempActionQueue.size() - 1).singleTarget(ENEMY_LEFT);
                queueMoves();
                break;
            case Keys.RIGHT:
                battleState = NO_INPUT;
                tempActionQueue.get(tempActionQueue.size() - 1).singleTarget(ENEMY_RIGHT);
                queueMoves();
                break;
            case Keys.BACKSPACE:
                battleState = MOVE2;
                moveOut(battle.getPokeyByPosition(FRIENDLY_RIGHT));
                tempActionQueue.remove(tempActionQueue.size() - 1);
                break;
        }
    }

    private void switch1(int keycode)
    {
        switch (keycode)
        {
            case Keys.LEFT:
                battleState = FIGHT_RUN2;
                battle.getPokeyByPosition(FRIENDLY_LEFT).switchMove.singleTarget(FRIENDLY_BENCH2);
                tempActionQueue.add(battle.getPokeyByPosition(FRIENDLY_LEFT).switchMove);
                fightRunOut(battle.getPokeyByPosition(FRIENDLY_RIGHT));
                break;

            case Keys.RIGHT:
                battleState = FIGHT_RUN2;
                battle.getPokeyByPosition(FRIENDLY_LEFT).switchMove.singleTarget(FRIENDLY_BENCH3);
                tempActionQueue.add(battle.getPokeyByPosition(FRIENDLY_LEFT).switchMove);
                fightRunOut(battle.getPokeyByPosition(FRIENDLY_RIGHT));
                break;

            case Keys.UP:
                battleState = FIGHT_RUN2;
                battle.getPokeyByPosition(FRIENDLY_LEFT).switchMove.singleTarget(FRIENDLY_BENCH1);
                tempActionQueue.add(battle.getPokeyByPosition(FRIENDLY_LEFT).switchMove);
                fightRunOut(battle.getPokeyByPosition(FRIENDLY_RIGHT));
                break;

            case Keys.DOWN:
                battleState = FIGHT_RUN2;
                battle.getPokeyByPosition(FRIENDLY_LEFT).switchMove.singleTarget(FRIENDLY_BENCH4);
                tempActionQueue.add(battle.getPokeyByPosition(FRIENDLY_LEFT).switchMove);
                fightRunOut(battle.getPokeyByPosition(FRIENDLY_RIGHT));
                break;

            case Keys.BACKSPACE:
                battleState = FIGHT_RUN1;
                fightRunOut(battle.getPokeyByPosition(FRIENDLY_LEFT));
                break;
        }
    }

    private void switch2(int keycode)
    {
        switch (keycode)
        {
            case Keys.LEFT:
                if (!isSwitching(FRIENDLY_BENCH2))
                {
                    battleState = NO_INPUT;
                    battle.getPokeyByPosition(FRIENDLY_RIGHT).switchMove.singleTarget(FRIENDLY_BENCH2);
                    tempActionQueue.add(battle.getPokeyByPosition(FRIENDLY_RIGHT).switchMove);
                    queueMoves();
                }
                break;

            case Keys.RIGHT:
                if (!isSwitching(FRIENDLY_BENCH3))
                {
                    battleState = NO_INPUT;
                    battle.getPokeyByPosition(FRIENDLY_RIGHT).switchMove.singleTarget(FRIENDLY_BENCH3);
                    tempActionQueue.add(battle.getPokeyByPosition(FRIENDLY_RIGHT).switchMove);
                    queueMoves();
                }
                break;

            case Keys.UP:
                if (!isSwitching(FRIENDLY_BENCH1))
                {
                    battleState = NO_INPUT;
                    battle.getPokeyByPosition(FRIENDLY_RIGHT).switchMove.singleTarget(FRIENDLY_BENCH1);
                    tempActionQueue.add(battle.getPokeyByPosition(FRIENDLY_RIGHT).switchMove);
                    queueMoves();
                }
                break;

            case Keys.DOWN:
                if (!isSwitching(FRIENDLY_BENCH4))
                {
                    battleState = NO_INPUT;
                    battle.getPokeyByPosition(FRIENDLY_RIGHT).switchMove.singleTarget(FRIENDLY_BENCH4);
                    tempActionQueue.add(battle.getPokeyByPosition(FRIENDLY_RIGHT).switchMove);
                    queueMoves();
                }
                break;

            case Keys.BACKSPACE:
                battleState = FIGHT_RUN2;
                fightRunOut(battle.getPokeyByPosition(FRIENDLY_RIGHT));
                break;
        }
    }

    public void fightRunOut(Pokey pokey)
    {
        System.out.println("WHAT WILL " + pokey.name + " DO?");
        System.out.println("");
        System.out.println("     ^ FIGHT?");
        System.out.println("");
        System.out.println("     v TAG OUT?");
        System.out.println("");
    }

    public void moveOut(Pokey pokey)
    {
        ArrayList<Move> moves = pokey.moves;

        if (moves.size() >= 0) System.out.println("      ^ " + moves.get(0).name);
        if (moves.size() >= 1) System.out.println("< " + moves.get(1).name);
        if (moves.size() >= 2) System.out.println("         " + moves.get(2).name + " >");
        if (moves.size() >= 3) System.out.println("       v " + moves.get(3).name);
        System.out.println("");
    }

    public void switchingOut()
    {
        System.out.println("Switching blah blah blah");
    }

    public void targetOut()
    {
        System.out.println("< enemyLeft       enemyRight >");
        System.out.println("");
    }
}
