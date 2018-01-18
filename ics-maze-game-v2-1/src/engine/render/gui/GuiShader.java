package engine.render.gui;

import engine.render.shader.Shader;
import org.lwjgl.util.vector.Matrix4f;

// Shader that loads GUI obj information into the GPU
public class GuiShader extends Shader {

    // Location of the transformation matrix
    private int locTransMatrix;

    // Directories of glsl files

    protected String vertexFile() {
        return "/shaders/guiVertexShader.glsl";
    }

    protected String fragFile() {
        return "/shaders/guiFragShader.glsl";
    }

    // See Shader class comments

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
