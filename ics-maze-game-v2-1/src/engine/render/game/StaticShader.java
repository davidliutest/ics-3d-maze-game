package engine.render.game;

import engine.render.shader.Shader;
import org.lwjgl.util.vector.Matrix4f;

// Shader that sends information to GPU to render entities
public class StaticShader extends Shader {

	// Location of matrix buffers in OpenGL
	private int locTransMatrix, locProjMatrix, locViewMatrix;

	// Directory of glsl files

	protected String vertexFile() {
		return "/shaders/vertexShader.glsl";
	}

	protected String fragFile() {
		return "/shaders/fragShader.glsl";
	}

	// Binds the vao attributes of the entities
	protected void bindAll() {
		bind(0, "pos");
		bind(1, "textCoords");
		bind(2, "normal");
	}

	// Set the uniform location of the matrices in OpenGL
	@Override
	protected void getAllUniformLoc() {
		locTransMatrix = getUniformLoc("transMatrix");
		locProjMatrix = getUniformLoc("projMatrix");
		locViewMatrix = getUniformLoc("viewMatrix");
	}

	// Loads matrices

	public void loadTransMatrix(Matrix4f trans){
		loadMatrix(locTransMatrix, trans);
	}

	public void loadProjMatrix(Matrix4f proj){
		loadMatrix(locProjMatrix, proj);
	}

	public void loadViewMatrix(Matrix4f view){
		loadMatrix(locViewMatrix, view);
	}

}
