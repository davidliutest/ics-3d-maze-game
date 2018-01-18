package game.states;

import game.main.Handler;

// Base abstract class for a state
// A State inits, creates, and updates all required components
public abstract class State {

    protected Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    }

    public abstract void create();

    public abstract void update();

    public void reset() {}

}
