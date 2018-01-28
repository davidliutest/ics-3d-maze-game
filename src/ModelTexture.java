// https://github.com/davidliutest/ics-maze-game 


// Stores data of a Texture (image) that is binded in OpenGL
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
