package game.managers;

import game.main.Handler;
import game.states.GameState;
import game.states.HelpState;
import game.states.MenuState;
import game.states.State;

public class StateManager {

    // 0 -> Menustate
    // 1 -> HelpState
    // 2 -> GameState

    private Handler handler;
    private int cur;
    private State[] states;

    public StateManager(Handler handler) {
        this.handler = handler;
        states = new State[3];
        states[0] = new MenuState(handler);
        states[1] = new HelpState(handler);
        states[2] = new GameState(handler);
        cur = 2;
    }

    public void create() {
        for(State s : states) {
            s.create();
        }
    }

    public void update() {
        states[cur].update();
    }

    public State getCurState() {
        return states[cur];
    }

    public void setState(int c) {
        cur = c;
    }

    public State getMenuState() {
        return states[0];
    }

    public State getHelpState() {
        return states[1];
    }

    public State getGameState() {
        return states[2];
    }

}
