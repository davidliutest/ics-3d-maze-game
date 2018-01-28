// https://github.com/davidliutest/ics-maze-game 

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

// Uses LWJGL lib to create a display window
// Width, height, and FPS cap is set here
public class Window {

    // Window variables
    private static final int FPS_MAX = 60;

    // LWJGL code to create display
    public static void create() {
        ContextAttribs attr = new ContextAttribs(3, 2);
        attr.withForwardCompatible(true);
        attr.withProfileCore(true);
        try {
            Display.setFullscreen(true);
            Display.setVSyncEnabled(true);
            Display.create(new PixelFormat(), attr);
            Display.setTitle("ICS Maze App");
            Mouse.setGrabbed(true);
            GL11.glViewport(0, 0, getWidth(), getHeight());
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    // Updates the window
    public static void update() {
        Display.sync(FPS_MAX);
        Display.update();
    }

    // Cleans up on close, prevents mem leaks
    public static void close() {
        Display.destroy();
    }

    // Getters and Setters

    public static int getWidth() {
        return Display.getWidth();
    }

    public static int getHeight() {
        return Display.getHeight();
    }

}
