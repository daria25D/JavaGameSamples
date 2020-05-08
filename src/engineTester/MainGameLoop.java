package engineTester;

import entities.Camera;
import entities.Entity;
import models.TexturedModel;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import models.RawModel;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

import java.io.IOException;

public class MainGameLoop {
    public static void main(String[] args) throws LWJGLException, IOException {
        DisplayManager.createDisplay();

        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);

        float[] vertices = {
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f
        };
        float[] uv = {
                0f, 0f,
                0f, 1f,
                1f, 1f,
                1f, 0f
        };
        int[] indices = {
                0, 1, 3, 3, 1, 2
        };

        RawModel model = loader.loadToVAO(vertices, uv, indices);
        ModelTexture texture = new ModelTexture(loader.loadTexture("tex1"));
        TexturedModel staticModel = new TexturedModel(model, texture);
        Entity entity = new Entity(staticModel, new Vector3f(0, 0, 0), 0, 0, 0, 1);

        Camera camera = new Camera();

        while(!Display.isCloseRequested()) {
            entity.increasePosition(0, 0, -0.02f);
//            entity.increaseRotation(2, 0, 0);
            camera.move();
            renderer.prepare();
            shader.start();
            shader.loadViewMatrix(camera);
            renderer.render(entity, shader);
            shader.stop();
            DisplayManager.updateDisplay();
        }
        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
