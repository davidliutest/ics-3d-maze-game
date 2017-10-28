package textures;

// Class that stores the ID of a texture for openGL to process
public class Texture {
	
	private int textureID;
	
	public Texture(int ID) {
		this.textureID = ID;
	}
	
	public int getID() {
		return textureID;
	}
	
}
