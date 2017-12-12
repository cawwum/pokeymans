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

                    case SWITCHING1:
                        switching1(keycode);
                        break;

                    case SWITCHING2:
                        switching2(keycode);
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

    public void fightRunOut()
    {
        System.out.println("WHAT WILL ___ DO?");
        System.out.println("");
        System.out.println("     ^ FIGHT?");
        System.out.println("");
        System.out.println("     v POKEMON?");
        System.out.println("");
    }

    public void switchingOut()
    {
        System.out.println("SWITCH IN WHO?");
        System.out.println("");
        System.out.println("      ^ bench1");
        System.out.println("< bench3    bench2 >");
        System.out.println("      v bench4");
        System.out.println("");
    }

    public void moveOut()
    {
        System.out.println("      ^ move1");
        System.out.println("< move2       move3 >");
        System.out.println("      v move4");
        System.out.println("");
    }

    public void targetOut()
    {
        System.out.println("         ^ enemyLeft");
        System.out.println("< friendlyLeft       enemyRight >");
        System.out.println("         v friendlyRight");
        System.out.println("");
    }

    private void fightRun1(int keycode)
    {
        switch (keycode)
        {
            case Keys.UP:
                battleState = MOVE1;
                moveOut();
                break;

            case Keys.DOWN:
                battleState = SWITCHING1;
                switchingOut();
                break;
        }
    }

    private void fightRun2(int keycode)
    {
        switch (keycode)
        {
            case Keys.UP:
                battleState = MOVE2;
                moveOut();
                break;
            case Keys.DOWN:
                battleState = SWITCHING2;
                switchingOut();
                break;
            case Keys.BACKSPACE:
                battleState = FIGHT_RUN1;
                fightRunOut();
                //CLEAR THE QUEUE!!! V can probs clean this up bleugh!
                battle.actionQueue.clear();
                battle.friendly1.switching = false;
                battle.friendly2.switching = false;
                battle.friendly3.switching = false;
                battle.friendly4.switching = false;
                battle.friendly5.switching = false;
                battle.friendly6.switching = false;
                break;
        }
    }

    //queues the switch move for the 2 positions
    private void queueSwitch(Position battlePosition, Position benchPosition)
    {
        ArrayList<Position> target = new ArrayList<Position>();
        target.add(benchPosition);
        battle.actionQueue.add(new Switch(battle, battle.getPokeyByPosition(battlePosition), target));
    }

    private void switching1(int keycode)
    {
        switch (keycode)
        {
            case Keys.LEFT:
                battleState = FIGHT_RUN2;
                queueSwitch(FRIENDLY_LEFT, FRIENDLY_BENCH3);
                battle.getPokeyByPosition(FRIENDLY_BENCH3).switching = true;
                fightRunOut();
                break;
            case Keys.RIGHT:
                queueSwitch(FRIENDLY_LEFT, Position.FRIENDLY_BENCH2);
                battle.getPokeyByPosition(FRIENDLY_BENCH2).switching = true;
                battleState = FIGHT_RUN2;
                fightRunOut();
                break;
            case Keys.UP:
                queueSwitch(FRIENDLY_LEFT, Position.FRIENDLY_BENCH1);
                battle.getPokeyByPosition(FRIENDLY_BENCH1).switching = true;
                battleState = FIGHT_RUN2;
                fightRunOut();
                break;
            case Keys.DOWN:
                queueSwitch(FRIENDLY_LEFT, Position.FRIENDLY_BENCH4);
                battle.getPokeyByPosition(FRIENDLY_BENCH4).switching = true;
                battleState = FIGHT_RUN2;
                fightRunOut();
                break;
            case Keys.BACKSPACE:
                battleState = FIGHT_RUN1;
                fightRunOut();
                break;
        }
    }

    private void switching2(int keycode)
    {
        switch (keycode)
        {
            case Keys.LEFT:
                if (!battle.getPokeyByPosition(FRIENDLY_BENCH3).switching)
                {
                    queueSwitch(FRIENDLY_RIGHT, FRIENDLY_BENCH3);
                    battleState = NO_INPUT;
                    battle.executeActions();
                }
                break;

            case Keys.RIGHT:
                if (!battle.getPokeyByPosition(FRIENDLY_BENCH2).switching)
                {
                    queueSwitch(FRIENDLY_RIGHT, Position.FRIENDLY_BENCH2);
                    battleState = NO_INPUT;
                    battle.executeActions();
                }
                break;

            case Keys.UP:
                if (!battle.getPokeyByPosition(FRIENDLY_BENCH1).switching)
                {
                    queueSwitch(FRIENDLY_RIGHT, Position.FRIENDLY_BENCH1);
                    battleState = NO_INPUT;
                    battle.executeActions();
                }
                break;

            case Keys.DOWN:
                if (!battle.getPokeyByPosition(FRIENDLY_BENCH4).switching)
                {
                    queueSwitch(FRIENDLY_RIGHT, Position.FRIENDLY_BENCH4);
                    battleState = NO_INPUT;
                    battle.executeActions();
                }
                break;

            case Keys.BACKSPACE:
                battleState = FIGHT_RUN2;
                fightRunOut();
                break;
        }
    }

    private void move1(int keycode)
    {
        switch (keycode)
        {
            case Keys.LEFT:
                battleState = TARGET1;
                targetOut();
                break;
            case Keys.RIGHT:
                battleState = TARGET1;
                targetOut();
                break;
            case Keys.UP:
                battleState = TARGET1;
                targetOut();
                break;
            case Keys.DOWN:
                battleState = TARGET1;
                targetOut();
                break;
            case Keys.BACKSPACE:
                battleState = FIGHT_RUN1;
                fightRunOut();
                break;
        }
    }

    private void move2(int keycode)
    {
        switch (keycode)
        {
            case Keys.LEFT:
                battleState = TARGET2;
                targetOut();
                break;
            case Keys.RIGHT:
                battleState = TARGET2;
                targetOut();
                break;
            case Keys.UP:
                battleState = TARGET2;
                targetOut();
                break;
            case Keys.DOWN:
                battleState = TARGET2;
                targetOut();
                break;
            case Keys.BACKSPACE:
                battleState = FIGHT_RUN2;
                fightRunOut();
                break;
        }
    }

    private void target1(int keycode)
    {
        switch (keycode)
        {
            case Keys.LEFT:
                battleState = FIGHT_RUN2;
                fightRunOut();
                break;
            case Keys.RIGHT:
                battleState = FIGHT_RUN2;
                fightRunOut();
                break;
            case Keys.UP:
                battleState = FIGHT_RUN2;
                fightRunOut();
                break;
            case Keys.DOWN:
                battleState = FIGHT_RUN2;
                fightRunOut();
                break;
            case Keys.BACKSPACE:
                battleState = MOVE1;
                moveOut();
                break;
        }
    }

    private void target2(int keycode)
    {
        switch (keycode)
        {
            case Keys.LEFT:
                battleState = NO_INPUT;
                break;
            case Keys.RIGHT:
                battleState = NO_INPUT;
                break;
            case Keys.UP:
                battleState = NO_INPUT;
                break;
            case Keys.DOWN:
                battleState = NO_INPUT;
                break;
            case Keys.BACKSPACE:
                battleState = MOVE2;
                moveOut();
                break;
        }

        //battle.executeActions();
    }
}
