package models;

import textures.Texture;

public class TextureModel {

	public Model model;
	public Texture texture;
	
	public TextureModel(Model model, Texture texture) {
		this.model = model;
		this.texture = texture;
	}

	public Model getModel() { return model; }

	public Texture getTexture() {
		return texture;
	}
	
}
