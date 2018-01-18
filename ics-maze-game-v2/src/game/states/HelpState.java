package game.states;

import game.main.Handler;
import game.managers.GuiManager;
import org.lwjgl.input.Keyboard;

// State responsible for the instructions
public class HelpState extends State {

    private GuiManager guiManager;

    public HelpState(Handler handler) {
        super(handler);
        guiManager = new GuiManager(handler);
    }

    public void create() {
        guiManager.addGuiObj(handler.getAssetManager().helpGUI);
    }

    public void update() {
        guiManager.update();
        // Check if ENTER pressed to move on to game
        if(Keyboard.next() && Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
            handler.getStateManager().setState(2);
        }
    }

}
