package engine.render.gui;

import engine.render.Loader;
import engine.render.models.RawModel;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.util.List;

// Renders models through a projection matrix using OpenGL
public class GuiRenderer {

    // Rectangle shape of GUI objs
    private RawModel quad;
    private GuiShader shader;

    public GuiRenderer(){
        shader = new GuiShader();
    }

    // Initializes shader and creates the rectangle shape of all GUI objs
    public void create(Loader loader) {
        shader.create();
        float[] positions = {-1, 1, -1, -1, 1, 1, 1, -1};
        quad = loader.loadVAO(positions,2);
    }

    // Renders a list of GUI objs
    public void render(List<GuiObj> guis){
        shader.start();
        GL30.glBindVertexArray(quad.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        for (GuiObj gui : guis) {
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, gui.getTex());
            Matrix4f matrix = createTransMatrix(gui.getPosition(), gui.getScale());
            shader.loadTransMatrix(matrix);
            GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());
        }
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        shader.stop();
    }

    // Creates the transformation matrix of GUI objs
    public static Matrix4f createTransMatrix(Vector2f translation, Vector2f scale) {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.translate(translation, matrix, matrix);
        Matrix4f.scale(new Vector3f(scale.x, scale.y, 1f), matrix, matrix);
        return matrix;
    }

    public void close(){
        shader.close();
    }

}
