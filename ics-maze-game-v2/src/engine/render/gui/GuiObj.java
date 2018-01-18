package engine.render.gui;

import org.lwjgl.util.vector.Vector2f;

// Stores the information to render a GUI obj
public class GuiObj {

    // Location of the texture of GUI obj in OpenGL
    private int tex;
    // Position of the center of the GUI obj
    private Vector2f position;
    private Vector2f scale;

    public GuiObj(int tex, Vector2f position, Vector2f scale) {
        this.tex = tex;
        this.position = position;
        this.scale = scale;
    }

    // Setters and Getters
    public int getTex() {
        return tex;
    }

    public Vector2f getPosition() {
        return position;
    }

    public Vector2f getScale() {
        return scale;
    }

    public void setScale(float nsx, float nsy) {
        scale = new Vector2f(nsx, nsy);
    }

}
