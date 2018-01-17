package engine.render.gui;

import engine.render.shader.Shader;
import org.lwjgl.util.vector.Matrix4f;

public class GuiShader extends Shader {

    private int locTransMatrix;

    protected String vertexFile() {
        return "/shaders/guiVertexShader.glsl";
    }

    protected String fragFile() {
        return "/shaders/guiFragShader.glsl";
    }

    protected void bindAll() {
        bind(0, "pos");
    }

    protected void getAllUniformLoc() {
        locTransMatrix = getUniformLoc("transMatrix");
    }

    public void loadTransMatrix(Matrix4f trans){
        loadMatrix(locTransMatrix, trans);
    }

}
