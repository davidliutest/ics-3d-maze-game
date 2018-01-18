package game.states;

import editor.MapData1;
import game.main.Handler;

public abstract class State {

    protected Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    }

    public abstract void create();

    public void create(MapData1 mapData1){}

    public abstract void update();

    public void reset() {}

}
