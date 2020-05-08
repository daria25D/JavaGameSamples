package shaders;

import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends ShaderProgram {
    private static final String VERTEX_FILE = "src/shaders/vertex.glsl";
    private static final String FRAGMENT_FILE = "src/shaders/fragment.glsl";
    private int location_transformMatrix;
    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformMatrix = super.getUniformLocation("transformMatrix");
    }

    public void loadTransformMatrix(Matrix4f matrix) {
        super.loadMatrix(location_transformMatrix, matrix);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttributes(0, "position");
        super.bindAttributes(1, "uv");
    }
}
