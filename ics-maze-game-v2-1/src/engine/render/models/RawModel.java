package engine.render.models;

// RawModel class that contains information for a raw model to be rendered
public class RawModel {
	
	private int vaoID;
	private int vertexCount;
	private float[] bounds;
	
	public RawModel(int vaoID, int vertexCount, float[] bounds) {
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
		this.bounds = bounds;
	}

	// Getters and setters
	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}

	public float getBounds(int i) {
		return bounds[i];
	}

	public float[] getBoundsArr() {
		return bounds;
	}

}
