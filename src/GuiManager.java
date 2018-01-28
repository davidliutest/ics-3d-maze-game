// https://github.com/davidliutest/ics-maze-game 


import java.util.ArrayList;

// Manages GUI objs of a State
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
