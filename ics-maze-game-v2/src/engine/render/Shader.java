package engine.render;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

public class Shader {
	
	private int programID;
	private int vertexShaderID;
	private int fragShaderID;
	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	private static final String VERTEX_FILE = "/shaders/vertexShader.glsl";
	private static final String FRAGMENT_FILE = "/shaders/fragShader.glsl";
	private int locTransMatrix, locProjMatrix, locViewMatrix;

	public void create(String F1, String F2) {
		vertexShaderID = loadFile(F1, GL20.GL_VERTEX_SHADER);
		fragShaderID = loadFile(F2, GL20.GL_FRAGMENT_SHADER);
		programID = GL20.glCreateProgram();
		GL20.glAttachShader(programID, vertexShaderID);
		GL20.glAttachShader(programID, fragShaderID);
		bindAttribute(0, "pos");
		bindAttribute(1, "textCoords");
		bindAttribute(2, "normal");
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
		locTransMatrix = getUniformLocation("transMatrix");
		locProjMatrix = getUniformLocation("projMatrix");
		locViewMatrix = getUniformLocation("viewMatrix");
	}

	public void create() {
		this.create(VERTEX_FILE,FRAGMENT_FILE);
	}
	public void bindAttribute(){};
	public void getUniformLocation(){};
	public void bindAttribute(int attribute, String variableName){
		GL20.glBindAttribLocation(programID, attribute, variableName);
	}
	public int getUniformLocation(String uniformName){
		return GL20.glGetUniformLocation(programID,uniformName);
	}

	public void start() {
		GL20.glUseProgram(programID);
	}

	public void stop() {
		GL20.glUseProgram(0);
	}

	// Cleans ups the program on exit and prevents memory leak
	public void close() {
		stop();
		GL20.glDetachShader(programID, vertexShaderID);
		GL20.glDetachShader(programID, fragShaderID);
		GL20.glDeleteShader(vertexShaderID);
		GL20.glDeleteShader(fragShaderID);
		GL20.glDeleteProgram(programID);
	}

	// Loads the shaders file into one string and sends it to the GPU
	private static int loadFile(String file, int type) {
		String src = new String();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(Shader.class.getResourceAsStream(file)));
			String ln;
			while((ln = br.readLine()) != null)
				src += ln + "\n";
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, src);
		GL20.glCompileShader(shaderID);
		if(GL20.glGetShaderi(shaderID,  GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
			System.exit(-1);
		}
		return shaderID;
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

	protected void loadMatrix(int location, Matrix4f matrix) {
		matrix.store(matrixBuffer);
		matrixBuffer.flip();
		GL20.glUniformMatrix4(location, false, matrixBuffer);
	}

}
