// https://github.com/davidliutest/ics-maze-game
import org.lwjgl.input.Keyboard;

// State responsible for lose screen
public class LoseState extends State {

    private GuiManager guiManager;

    public LoseState(Handler handler) {
        super(handler);
        guiManager = new GuiManager(handler);
    }

    public void create() {
        guiManager.addGuiObj(handler.getAssetManager().loseGUI);
    }

    public void update() {
        guiManager.update();
        // Check if ENTER pressed to move on to menu
        if(Keyboard.next() && Keyboard.isKeyDown(Keyboard.KEY_RETURN))
            handler.getStateManager().setState(0);
    }

}
