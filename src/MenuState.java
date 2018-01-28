// https://github.com/davidliutest/ics-maze-game 

import org.lwjgl.input.Keyboard;

// State responsible for the menu screen
public class MenuState extends State {

    private GuiManager guiManager;

    public MenuState(Handler handler) {
        super(handler);
        guiManager = new GuiManager(handler);
    }

    public void create() {
        guiManager.addGuiObj(handler.getAssetManager().coverGUI);
    }

    public void update() {
        guiManager.update();
        // Check if ENTER pressed to move on to instructions
        if(Keyboard.next() && Keyboard.isKeyDown(Keyboard.KEY_RETURN))
            handler.getStateManager().setState(1);
    }

}
