package models;

import textures.Texture;

public class TextureModel extends Model {
	
	public Texture texture;
	
	public TextureModel(int vaoID, int vertexCount, Texture texture) {
		super(vaoID, vertexCount);
		this.texture = texture;
	}
	
	public Texture getTexture() {
		return texture;
	}
	
}
