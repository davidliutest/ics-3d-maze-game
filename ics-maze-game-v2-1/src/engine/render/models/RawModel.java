package engine.render.models;

// Contains information for a model to be rendered
public class RawModel {

	// Location of the model stored in OpenGL
	private int vaoID;
	private int vertexCount;
	// Stores min and max x, y, z values of the model for collision
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
