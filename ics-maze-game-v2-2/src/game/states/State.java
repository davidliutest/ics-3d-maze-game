package game.states;

import game.main.Handler;

public abstract class State {

    protected Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    }

    public abstract void create();

    public abstract void update();

    public void reset() {}

}
