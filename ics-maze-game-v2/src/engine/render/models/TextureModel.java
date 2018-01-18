package engine.render.models;

// Stores information of a model that is textured (with an image) to be rendered
public class TextureModel extends RawModel {

	public ModelTexture texture;
	
	public TextureModel(int vaoID, int vertexCount, float[] bounds, ModelTexture texture) {
		super(vaoID, vertexCount, bounds);
		this.texture = texture;
	}

	public TextureModel(RawModel raw, ModelTexture texture) {
		super(raw.getVaoID(), raw.getVertexCount(), raw.getBoundsArr());
		this.texture = texture;
	}

	// Getters and setters
	public ModelTexture getTexture() {
		return texture;
	}
	
}
