package engine.render.game;

import engine.render.shader.Shader;
import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends Shader {

	private int locTransMatrix, locProjMatrix, locViewMatrix;

	protected String vertexFile() {
		return "/shaders/vertexShader.glsl";
	}

	protected String fragFile() {
		return "/shaders/fragShader.glsl";
	}

	protected void bindAll() {
		bind(0, "pos");
		bind(1, "textCoords");
		bind(2, "normal");
	}

	@Override
	protected void getAllUniformLoc() {
		locTransMatrix = getUniformLoc("transMatrix");
		locProjMatrix = getUniformLoc("projMatrix");
		locViewMatrix = getUniformLoc("viewMatrix");
	}

	// Matrix functions
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
