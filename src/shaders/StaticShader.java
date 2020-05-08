package shaders;

import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/shaders/vertex.glsl";
    private static final String FRAGMENT_FILE = "src/shaders/fragment.glsl";
    private int location_transformMatrix;
    private int location_projectionMatrix;

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformMatrix = super.getUniformLocation("transformMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
    }

    public void loadTransformMatrix(Matrix4f matrix) {
        super.loadMatrix(location_transformMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix) {
        super.loadMatrix(location_projectionMatrix, matrix);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttributes(0, "position");
        super.bindAttributes(1, "uv");
    }
}
