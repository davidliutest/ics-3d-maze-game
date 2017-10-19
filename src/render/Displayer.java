package render;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

public class Displayer {
	
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 600;
	private static final int FPS_CAP = 60;
	
	public static void create() {
		ContextAttribs attribs = new ContextAttribs(3, 2)
				.withForwardCompatible(true)
				.withProfileCore(true);
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle("Maze Game");
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
	}
	
	public static void update() {
		Display.sync(FPS_CAP);
		Display.update();
	}
	
	public static void close() {
		Display.destroy();
	}
	
}
