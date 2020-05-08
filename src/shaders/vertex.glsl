#version 400 core

in vec3 position;
in vec2 uv;
out vec2 texCoords;

uniform mat4 transformMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

void main() {
    gl_Position = projectionMatrix * viewMatrix * transformMatrix * vec4(position, 1.0);
    texCoords = uv;
}