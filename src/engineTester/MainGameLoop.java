package engineTester;

import models.TexturedModel;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
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
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();

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
        TexturedModel texturedModel = new TexturedModel(model, texture);

        while(!Display.isCloseRequested()) {
            renderer.prepare();
            shader.start();
            renderer.render(texturedModel);
            shader.stop();
            DisplayManager.updateDisplay();
        }
        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
