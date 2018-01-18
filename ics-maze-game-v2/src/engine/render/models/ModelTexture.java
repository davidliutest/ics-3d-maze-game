package engine.render.models;

import engine.datastruct.TextureData;

// Stores data that is binded in OpenGL
public class ModelTexture {
	
	private int textureID;
	private float width, height;

	public ModelTexture(TextureData td) {
		textureID = td.texID;
		width = td.width;
		height = td.height;
	}

	public int getID() {
		return textureID;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

}
