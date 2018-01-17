package engine.render.models;

// Data structure to store a Model along with a texture
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
