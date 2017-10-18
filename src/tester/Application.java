package tester;

import entities.Camera;
import entities.Entity;
import models.Model;
import models.TextureModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import render.Displayer;
import render.Loader;
import render.Renderer;
import shader.StaticShader;
import textures.Texture;

public class Application {

	public static void main(String[] args) {

		//this is a test

		Displayer.create();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		// Vertices of shape should be counterclockwise
		float[] vertices = {
			-0.5f,0.5f,-0.5f,
			-0.5f,-0.5f,-0.5f,
			0.5f,-0.5f,-0.5f,
			0.5f,0.5f,-0.5f,

			-0.5f,0.5f,0.5f,
			-0.5f,-0.5f,0.5f,
			0.5f,-0.5f,0.5f,
			0.5f,0.5f,0.5f,

			0.5f,0.5f,-0.5f,
			0.5f,-0.5f,-0.5f,
			0.5f,-0.5f,0.5f,
			0.5f,0.5f,0.5f,

			-0.5f,0.5f,-0.5f,
			-0.5f,-0.5f,-0.5f,
			-0.5f,-0.5f,0.5f,
			-0.5f,0.5f,0.5f,

			-0.5f,0.5f,0.5f,
			-0.5f,0.5f,-0.5f,
			0.5f,0.5f,-0.5f,
			0.5f,0.5f,0.5f,

			-0.5f,-0.5f,0.5f,
			-0.5f,-0.5f,-0.5f,
			0.5f,-0.5f,-0.5f,
			0.5f,-0.5f,0.5f

		};// rectangle coords (x, y, z)
		int[] indices = {
			0,1,3,
			3,1,2,
			4,5,7,
			7,5,6,
			8,9,11,
			11,9,10,
			12,13,15,
			15,13,14,
			16,17,19,
			19,17,18,
			20,21,23,
			23,21,22
		}; // triangles
		float[] texCoords = { // origin starts at top left
			//0,0, // v1 counterclockwise
			//0,1, // v2
			//1,1, // v3
			//1,0 // v4
			0,0,
			0,1,
			1,1,
			1,0,
			0,0,
			0,1,
			1,1,
			1,0,
			0,0,
			0,1,
			1,1,
			1,0,
			0,0,
			0,1,
			1,1,
			1,0,
			0,0,
			0,1,
			1,1,
			1,0,
			0,0,
			0,1,
			1,1,
			1,0
		};
		Model staticModel = loader.loadToVAO(vertices, texCoords, indices);
		Texture texture = new Texture(loader.loadTexture("texture"));
		TextureModel textureModel = new TextureModel(staticModel, texture);

		Entity entity = new Entity(textureModel, new Vector3f(0,0,-5),0,0,0,1);

		Camera camera = new Camera();

		while(!Display.isCloseRequested()) {
			entity.increaseRotation(1, 1, 0);
			camera.move();
			renderer.start();
			shader.start();
			shader.loadViewMatrix(camera);
			renderer.render(entity, shader);
			shader.stop();
			Displayer.update();
		}
		shader.close();
		loader.close();
		Displayer.close();
	}

}
