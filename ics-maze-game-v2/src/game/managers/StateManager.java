package game.managers;

import editor.MapData1;
import game.main.Handler;
import game.states.*;

public class StateManager {

    private Handler handler;
    private int cur;
    private State[] states;

    public StateManager(Handler handler) {
        this.handler = handler;
        states = new State[5];
        states[0] = new MenuState(handler);
        states[1] = new HelpState(handler);
        states[2] = new GameState(handler);
        states[3] = new WinState(handler);
        states[4] = new LoseState(handler);
        cur = 0;
    }
    public StateManager(Handler handler, MapData1 mapdata1) {
        this.handler = handler;
        states = new State[5];
        states[0] = new MenuState(handler);
        states[1] = new HelpState(handler);
        states[2] = new GameState(handler, mapdata1);
        states[3] = new WinState(handler);
        states[4] = new LoseState(handler);
        cur = 0;
    }

    public void create() {
        for(State s : states)
            s.create();
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
