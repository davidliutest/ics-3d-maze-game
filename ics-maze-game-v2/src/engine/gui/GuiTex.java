package engine.gui;

import org.lwjgl.util.vector.Vector2f;

public class GuiTex {

    private int tex;
    private Vector2f position;
    private Vector2f scale;


    public GuiTex(int tex, Vector2f position, Vector2f scale) {
        this.tex = tex;
        this.position = position;
        this.scale = scale;
    }

    public int getTex() {return tex; }
    public Vector2f getPosition() { return position;}
    public Vector2f getScale() {return scale;}

}
