package tester;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Mob;
import models.Model;
import models.TextureModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import render.Displayer;
import render.Loader;
import render.OBJLoader;
import render.Renderer;
import shader.StaticShader;
import textures.Texture;

import java.io.FileNotFoundException;

// Class that tests the code
public class Application {

	public static void main(String[] args) throws FileNotFoundException {
		// Initialize all objects for rendering
		Displayer.create();
		
		Loader loader = new Loader();
		Loader loader1 = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);

		// Actual model
		Model staticModel = OBJLoader.loadObjectModel("stall", loader);
		Texture texture = new Texture(loader.loadTexture("stallTexture"));

		TextureModel textureModel = new TextureModel(staticModel, texture);

		Entity entity = new Entity(textureModel, new Vector3f(5,-5,-25),0,0,0,1);
		Light light = new Light(new Vector3f(0,0,-20),new Vector3f(1,1,1));

		Model mob1 = OBJLoader.loadObjectModel("dragon", loader);
		Texture mobTexture = new Texture(loader1.loadTexture("white"));
		TextureModel mobTexModel = new TextureModel (mob1, mobTexture);

		// Creates a temp mob
		Mob dragon = new Mob (mobTexModel, new Vector3f(-8,-5,-25),0,0,0,1,100,100);

		// Creates the camera
		Camera camera = new Camera();

		// Main game loop
		while(!Display.isCloseRequested()) {
			entity.increaseRotation(0,1,0);
			dragon.increaseRotation(0,1,0);
			camera.move();
			renderer.start();
			shader.start();
			shader.loadLight(light);
			shader.loadViewMatrix(camera);
			renderer.render(entity, shader);
			renderer.render(dragon, shader);
			shader.stop();
			Displayer.update();
		}
		// Cleans up program on exit
		shader.close();
		loader.close();
		loader1.close();
		Displayer.close();
	}

}
