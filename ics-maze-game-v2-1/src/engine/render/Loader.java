package engine.render;

import engine.datastruct.TextureData;
import engine.render.models.RawModel;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

// Puts VBOs in VAOs and binds it to OpenGL
// VAO and VBO are data structures that store data of 3D models
// This data is sent to GPU
/*
HOW TO USE:
	Vbos are stored in vaos
	A vbo is an array of data
	A vao is an array of vbo with indexes
	A vao contains vbos that contains a models vertices, texture coordinates, and normals
	The vertices vbo contains vertices for EACH FACE of the model (there will be duplicate vertices)
		Three values (x, y, z)
	The texture vbo contains the coordinate of the modeltexture corresponding with the vertice in the
	vertice vbo
		Texture coordinates range from 0 to 1
		Two values (x, y)
	The normal vbo contains the normal of the corresponding vertice in the vertice vbo
		Three values (x, y, z)
	The indices vbo contains the order to make the 2 triangles of each face
		OpenGL uses triangles to update a face, so the rectangle face must be split into two triangles
		Six values for a face (a, b, d, d, b, c)
 */
public class Loader {
	
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	private List<Integer> textures = new ArrayList<Integer>();

	// Cleans up on program close
	public void close() {
		for(int vao: vaos)
			GL30.glDeleteVertexArrays(vao);
		for(int vbo : vbos)
			GL15.glDeleteBuffers(vbo);
		for(int texture: textures)
			GL11.glDeleteTextures(texture);
	}

	// Loads model info into the GPU and returns the model
	public RawModel loadVAO(float[] pos, float[] tex, float[] norm, int[] indices, float[] bounds) {
		// Creates vao
		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		// Binds vao to vbo
		bindVBOAttrib(0, 3, pos);
		bindVBOAttrib(1, 2, tex);
		bindVBOAttrib(2, 3, norm);
		bindVBOIndices(indices);
		// Unbinds vao
		GL30.glBindVertexArray(0);
		return new RawModel(vaoID, indices.length, bounds);
	}

	// Loads a vao to OpenGL
	public RawModel loadVAO(float[] pos, int dim) {
		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		bindVBOAttrib(0, dim, pos);
		GL30.glBindVertexArray(0);
		return new RawModel(vaoID, pos.length / dim, null);
	}

	// Store the vbo in the vao into OpenGL

	public void bindVBOAttrib(int num, int coordSize, float[] data) {
		// Create vbo, binds it, and stores it
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,  vboID);
		FloatBuffer buffer = storeFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(num, coordSize, GL11.GL_FLOAT, false, 0, 0);
		// Unbinds vbo
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}

	// Seperate method for binding indices of vbo
	private void bindVBOIndices(int[] indices) {
		// Create vbo and binds it
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = storeIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}

	// Loads textures (images)
	public TextureData loadTexture(String file) {
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture(
					"",
					Loader.class.getResourceAsStream("/textures/"+file+".png")
			);
			//texture = TextureLoader.getTexture("", new FileInputStream("res/textures/" + file + ".png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
		textures.add(texture.getTextureID());
		return new TextureData(texture.getTextureID(), texture.getTextureWidth(), texture.getTextureHeight());
	}

	// Initializes buffers to be sent to the GPU

	private IntBuffer storeIntBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	public FloatBuffer storeFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
}
