package models;

import textures.Texture;

// Data structure to store a Model along with a texture
public class TextureModel {

	public Model model;
	public Texture texture;
	
	public TextureModel(Model model, Texture texture) {
		this.model = model;
		this.texture = texture;
	}

	// Getters and setters
	public Model getModel() { return model; }
	public Texture getTexture() {
		return texture;
	}
	
}
