// https://github.com/davidliutest/ics-maze-game
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.util.List;

// Renders models using OpenGL
public class Renderer {

	private StaticShader shader;
	private Camera cam;
	// Information for the proj matrix
	private static final float FOV = 70;
	private static final float PLANE_NEAR = 0.1f;
	private static final float PLANE_FAR = 1000;
	private Matrix4f projMatrix;

	public Renderer() {
		shader = new StaticShader();
		cam = new Camera();
	}

	// Initializes the projection matrix, which is responsible for the 2D projection
	// of the 3D environment onto the user's screen
	public void create(){
		shader.create();
		cam.create();
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float yScale = (float) ((1f / Math.tan(Math.toRadians(FOV/2f)))* aspectRatio);
		float xScale = yScale / aspectRatio;
		float frustumLen = PLANE_FAR - PLANE_NEAR;
		projMatrix = new Matrix4f();
		projMatrix.m00 = xScale;
		projMatrix.m11 = yScale;
		projMatrix.m22 = -((PLANE_FAR + PLANE_NEAR)/frustumLen);
		projMatrix.m23 = -1;
		projMatrix.m32 = -((2 * PLANE_NEAR * PLANE_FAR) / frustumLen);
		projMatrix.m33 = 0;
		// Loads the matrix into OpenGL
		shader.start();
		shader.loadProjMatrix(projMatrix);
		shader.stop();
	}

	// Clears screen for drawing
	public void start() {
		// 1 is maximum for these RGB vals
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT| GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(1, 1, 1, 1);
	}

	// Renders each entity in the entityList
	public void update(List<Entity> entityList) {
		start();
		shader.start();
		for(Entity e : entityList) {
			if(e.getVisible())
				render(e);
		}
		// Loads the view matrix of the camera in OpenGL
		shader.loadViewMatrix(createViewMatrix());
		shader.stop();
	}

	// "Closes" shader to prevent memory leak
	public void close() {
		shader.close();
	}

	// How entities are rendered:
	// Information of entities are stored in VAOs (essentially an array of values)
	// An array of VAOs are stored in a VBO (essentially a 2D array)
	// VBOs are then sent to OpenGL to be rendered into a model
	// More info in Loader class

	// Renders an entity using OpenGL
	public void render(Entity entity) {
		TextureModel model = entity.getTextureModel();
		// Binds the vaos
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		// Each entity has its transformation matrix that accounts for changes in its position in the 3D environment
		Matrix4f transMatrix = createTransMatrix(
				entity.getPos(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale()
		);
		shader.loadTransMatrix(transMatrix);
		// Entities are loaded using a collection of triangles
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID());
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}

	// Methods to apply operations for matrices
	public Matrix4f createTransMatrix(Vector3f translation, float rx, float ry, float rz, float scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(rx), new Vector3f(1,0,0), matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(ry), new Vector3f(0,1,0), matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(rz), new Vector3f(0,0,1), matrix, matrix);
		Matrix4f.scale(new Vector3f(scale, scale, scale), matrix, matrix);
		return matrix;
	}

	// Creates the view matrix using the camera
	public Matrix4f createViewMatrix() {
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.setIdentity();
		Matrix4f.rotate((float) Math.toRadians(cam.getPitch()), new Vector3f(1, 0, 0), viewMatrix, viewMatrix);
		Matrix4f.rotate((float) Math.toRadians(cam.getYaw()), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
		Vector3f camPos = cam.getPos();
		Vector3f negCamPos = new Vector3f(-camPos.x,-camPos.y,-camPos.z);
		Matrix4f.translate(negCamPos, viewMatrix, viewMatrix);
		return viewMatrix;
	}

	// Setters and Getters

	public Camera getCam() {
		return cam;
	}

}
