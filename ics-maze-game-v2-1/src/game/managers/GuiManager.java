package game.managers;

import engine.render.gui.GuiObj;
import game.main.Handler;

import java.util.ArrayList;

public class GuiManager {

    private Handler handler;
    private ArrayList<GuiObj> guis;

    public GuiManager(Handler handler) {
        this.handler = handler;
        guis = new ArrayList<GuiObj>();
    }

    public void update() {
        handler.getGuiRenderer().render(guis);
    }

    public void addGuiObj(GuiObj gui) {
        guis.add(gui);
    }

}
