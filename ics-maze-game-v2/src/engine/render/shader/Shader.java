package engine.render.shader;

import engine.render.game.StaticShader;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

// Base class for all Shader classes
// Shader classes uses OpenGL functions to send information to the GPU,
// increasing performance of rendering models
// Uses a vertex shader (responsible for vertices of models) and fragment shader (responsible for pixels of models)

public abstract class Shader {

    protected int programID, vertexShaderID, fragShaderID;
    protected FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

    protected abstract String vertexFile();

    protected abstract String fragFile();

    // Binds the shader program and shader glsl files into OpenGL
    public void create(){
        vertexShaderID = loadFile(vertexFile(), GL20.GL_VERTEX_SHADER);
        fragShaderID = loadFile(fragFile(), GL20.GL_FRAGMENT_SHADER);
        programID = GL20.glCreateProgram();
        GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID, fragShaderID);
        bindAll();
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);
        getAllUniformLoc();
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

    protected void bind(int attribute, String variableName){
        GL20.glBindAttribLocation(programID, attribute, variableName);
    }

    protected abstract void bindAll();

    protected int getUniformLoc(String name) {
        return GL20.glGetUniformLocation(programID, name);
    }

    protected abstract void getAllUniformLoc();

    // Loads the shader file into one string and sends it to the GPU
    protected int loadFile(String file, int type) {
        String src = new String();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(StaticShader.class.getResourceAsStream(file)));
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

    protected void loadMatrix(int location, Matrix4f matrix) {
        matrix.store(matrixBuffer);
        matrixBuffer.flip();
        GL20.glUniformMatrix4(location, false, matrixBuffer);
    }

}
