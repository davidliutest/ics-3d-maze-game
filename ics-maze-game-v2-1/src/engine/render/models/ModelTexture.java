package engine.render.models;

// Stores data that is binded in OpenGL
public class ModelTexture {
	
	private int textureID;
	
	public ModelTexture(int ID) {
		this.textureID = ID;
	}
	
	public int getID() {
		return textureID;
	}
	
}
