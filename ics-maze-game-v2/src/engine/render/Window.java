package engine.render;

// Uses LWJGL lib to create a display window
// Width, height, and FPS cap is set here

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;

public class Window {

    // Window variables
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private static final int FPS_MAX = 60;

    // LWJGL code to create display
    public static void create() {
        ContextAttribs attr = new ContextAttribs(3, 2);
        attr.withForwardCompatible(true);
        attr.withProfileCore(true);
        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create(new PixelFormat(), attr);
            Display.setTitle("ICS Maze App");
            //Display.setResizable(true);
            Mouse.setGrabbed(true);
            GL11.glViewport(0, 0, WIDTH, HEIGHT);
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

}
