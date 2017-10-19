package tester;

import entities.Camera;
import entities.Entity;
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

public class Application {

	public static void main(String[] args) throws FileNotFoundException {

		//this is a test

		Displayer.create();
		
		Loader loader = new Loader();
		Loader loader1 = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);

		Model staticModel = OBJLoader.loadObjectModel("stall", loader);
		Texture texture = new Texture(loader.loadTexture("stallTexture"));

		TextureModel textureModel = new TextureModel(staticModel, texture);

		Entity entity = new Entity(textureModel, new Vector3f(5,-5,-25),0,0,0,1);


		Model mob1 = OBJLoader.loadObjectModel("dragon", loader);
		Texture mobTexture = new Texture(loader1.loadTexture("fire"));
		TextureModel mobTexModel = new TextureModel (mob1, mobTexture);

		Mob dragon = new Mob (mobTexModel, new Vector3f(-9,-5,-25),0,0,0,1,100,100);


		Camera camera = new Camera();

		while(!Display.isCloseRequested()) {
			entity.increaseRotation(0,1,0);
			dragon.increaseRotation(0,1,0);
			camera.move();
			renderer.start();
			shader.start();
			shader.loadViewMatrix(camera);
			renderer.render(entity, shader);
			renderer.render(dragon, shader);
			shader.stop();
			Displayer.update();
		}
		shader.close();
		loader.close();
		Displayer.close();
	}

}
